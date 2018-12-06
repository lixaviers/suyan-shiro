<template>
  <div>
    <el-row v-if="!paySuccess">
      <p>订单号：{{orderNo}}</p>
      <p class="mt10">支付金额：<span class="price">￥{{payPrice}}</span></p>
      <p class="mt10 mb10">账户余额：<span class="price">￥{{balance}}</span></p>
      <el-row :gutter="20">
        <el-col :span="6" v-for="(item,index) in dataList" :key="item.id">
          <el-card class="box-card mt10">
            <div slot="header" class="clearfix">
              <el-checkbox-group v-model="selections" @change="handleCheckedCitiesChange" class="float-left ">
                <el-checkbox :label="index">礼品卡</el-checkbox>
              </el-checkbox-group>
              <span class="float-right">{{item.endTime}}到期</span>
            </div>
            <div class="text item">金额：￥{{item.price}}</div>
          </el-card>
        </el-col>
      </el-row>

      <el-row class="mt20">
        <el-button :disabled="disabled" :loading="loading" type="danger" @click="payOrderHandle()">支付</el-button>
        <span class="price">{{message}}</span>
      </el-row>
    </el-row>
    <pay-success v-else ref="paySuccess"></pay-success>
  </div>
</template>

<style lang="scss">

  .price {
    color: #f43043;
  }
</style>

<script>
  import {dateFormat} from '@/utils';
  import PaySuccess from './paySuccess';
  export default {
    data() {
      return {
        message: '',
        loading: false,
        paySuccess: false,
        disabled: false,
        orderNo: '',
        payPrice: 0,
        balance: 0,
        dataList: [],
        dataListLoading: false,
        selections: [],
      }
    },
    components: {
      PaySuccess,
    },
    activated(){
      this.init();
    },
    computed: {
      mainTabsActiveName: {
        get () {
          return this.$store.state.common.mainTabsActiveName
        },
        set (val) {
          this.$store.commit('common/updateMainTabsActiveName', val)
        }
      },
      mainTabs: {
        get () {
          return this.$store.state.common.mainTabs
        },
        set (val) {
          this.$store.commit('common/updateMainTabs', val)
        }
      },
    },
    methods: {
      init(){
//        let orderNo = this.$route.query.orderNo, t = this;
        let orderNo = sessionStorage.getItem('pay_order_no'), t = this;
        if (!orderNo) {
          t.removeTabHandle('mallOrderPay');
        } else {
          t.orderNo = orderNo;
          t.loading = false;
          t.paySuccess = false;
          t.$http({
            url: t.$http.adornUrl(`user/getUserInfo`),
            method: 'get',
            params: t.$http.adornParams()
          }).then(({data}) => {
            if (data && data.code === 200) {
              t.balance = data.dataMap.balance;
            }
          }).then(function () {
            t.$http({
              url: t.$http.adornUrl(`order/getOrderPayPrice/${orderNo}`),
              method: 'get',
              params: t.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 200) {
                t.payPrice = data.dataMap;
                if (t.balance < t.payPrice) {
                  t.message = '金额不足';
                  t.disabled = true;
                }
              } else {
                sessionStorage.setItem('pay_order_no', '');
              }
            }).then(function () {
              t.getDataList();
            });
          });
        }
      },
      getDataList () {
        this.dataListLoading = true;
        this.$http({
          url: this.$http.adornUrl('giftCard/queryUserGiftCards'),
          method: 'post',
          data: this.$http.adornData({
            'pageNo': 1,
            'pageSize': 100000,
            'status': 0,
          })
        }).then(({data}) => {
          if (data && data.code === 200) {
            this.dataList = data.dataMap.records;
            if (this.dataList && this.dataList.length > 0) {
              for (var i = 0; i < this.dataList.length; i++) {
                this.dataList[i].endTime = dateFormat(this.dataList[i].endTime, 'YYYY-MM-DD');
              }
            }
          } else {
            this.dataList = []
          }
          this.dataListLoading = false
        })
      },
      payOrderHandle() {
        this.loading = true;
        let giftCardList = [];
        if (this.selections && this.selections.length > 0) {
          for (var i = 0; i < this.selections.length; i++) {
            giftCardList.push(this.dataList[this.selections[i]].id);
          }
        }
        let payData = {
          orderNo: this.orderNo,
          giftCardList: giftCardList,
        };
        this.$http({
          url: this.$http.adornUrl('order/payOrder'),
          method: 'post',
          data: payData
        }).then(({data}) => {
          if (data && data.code === 200) {
            sessionStorage.setItem('pay_order_no', '');
            this.paySuccess = true;
            this.$nextTick(() => {
              this.$refs.paySuccess.init(this.orderNo);
            })
          } else {
            this.loading = false;
          }
        });
      },
      removeTabHandle(tabName) {
        this.mainTabs = this.mainTabs.filter(item => item.name !== tabName)
        if (this.mainTabs.length >= 1) {
          // 当前选中tab被删除
          if (tabName === this.mainTabsActiveName) {
            this.$router.push({name: this.mainTabs[this.mainTabs.length - 1].name}, () => {
              this.mainTabsActiveName = this.$route.name
            })
          }
        } else {
          this.menuActiveName = ''
          this.$router.push({name: 'home'})
        }
      },
      handleCheckedCitiesChange(value) {
        if (value && value.length > 0) {
          let price = 0;
          for (var i = 0; i < value.length; i++) {
            price += this.dataList[value[i]].price;
          }
          if (price > this.payPrice) {
            this.message = '礼品卡总金额不能超过支付金额';
            this.disabled = true;
            return;
          }
          if (price + this.balance >= this.payPrice) {
            this.message = '';
            this.disabled = false;
            return;
          } else {
            t.message = '金额不足';
            t.disabled = true;
            return;
          }
        }

      },
    }
  }
</script>
