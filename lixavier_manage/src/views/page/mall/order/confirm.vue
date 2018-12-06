<template>
  <div>
    <el-form label-width="60px">
      <p style="border-bottom: 2px solid #f1f1f1;">确认收货地址</p>
      <el-row class="mt10" v-for="(item,index) in userAddressList" :key="item.id">
        <el-radio @change="change" v-model="userAddressIndex" :label="index">
          {{item.provinceName}} {{item.cityName}} {{item.areaName}} {{item.address}} ({{item.consignee}} 收) {{item.mobile}}
          <span v-if="item.isDefault">默认地址</span>
        </el-radio>
      </el-row>
      <p class="mt20 mb10">确认订单信息</p>
      <el-table
        :data="goodsList"
        border
        v-loading="goodsListLoading"
        style="width: 100%;">
        <el-table-column
          prop="name"
          header-align="center"
          align="center"
          label="商品信息">
        </el-table-column>
        <el-table-column
          prop="price"
          header-align="center"
          align="center"
          width="180"
          label="单价">
        </el-table-column>
        <el-table-column
          prop="number"
          header-align="center"
          align="center"
          width="180"
          label="数量">
        </el-table-column>
        <el-table-column prop="sum" header-align="center" align="center" width="180" label="小计"></el-table-column>
      </el-table>
      <el-row>
        <el-row class="mt20" style="float:right;">
          <el-form-item>
            <el-input v-model="message" style="width: 320px;" placeholder="给卖家留言"></el-input>
          </el-form-item>
          <p style="text-align: right;">快递费：{{freightPrice}}</p>
        </el-row>
      </el-row>
      <el-row class="float-right payInfo mt20">
        <div class="payInfo-shadow">
          <p></p>
          <p>实付款：<span class="total">￥{{realPayPrice}}</span></p>
          <p class="mt10">寄送至：{{addressInfo.address}}</p>
          <p class="mt10">收货人：{{addressInfo.consignee}}</p>
          <el-row class="mt10">
            <el-button type="text" style="font-weight: normal;" @click="userCartHandle()">返回购物车</el-button>
            <el-button :loading="loading" type="danger" @click="submitOrderHandle()">提交订单</el-button>
          </el-row>
        </div>
      </el-row>
    </el-form>
  </div>
</template>

<style lang="scss">
  .payInfo {
    text-align: right;
    border: 1px solid #f40;
    .payInfo-shadow {
      border: 3px solid #fff0e8;
      min-width: 230px;
      padding: 20px;
      .total {
        font-size: 22px;
        color: #f43043;
      }
    }
  }
</style>

<script>
  import Decimal from 'decimal';
  export default {
    data() {
      return {
        loading: false,
        realPayPrice: 0,
        userAddressIndex: 0,
        userAddressList: [],
        goodsListLoading: false,
        goodsList: [],
        message: null,
        freightPrice: '0.00',
        addressInfo: {
          address: '',
          consignee: '',
        },
      }
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
    activated() {
      this.getDataList();
    },
    methods: {
      change(val) {
        let obj = this.userAddressList[val];
        this.addressInfo.address = obj.provinceName + ' ' + obj.cityName + ' ' + obj.areaName + ' ' + obj.address;
        this.addressInfo.consignee = obj.consignee + obj.mobile;
      },
      setTotal(){
        var data = this.goodsList;
        var total = 0;
        for (var i = 0; i < data.length; i++) {
          total = Decimal.add(total, data[i].sum).toNumber();
        }
        if (total < 88) {
          this.freightPrice = '10.00';
          total = Decimal.add(total, 10).toNumber();
        }
        this.realPayPrice = total + '';
      },
      getDataList() {
        this.goodsListLoading = true;
        let goodsList = sessionStorage.getItem('order_confirm_goods'), t = this;
        if (!goodsList || goodsList.length < 1) {
          t.$router.push({path: '/mall/cart/index'});
        }
        t.goodsList = JSON.parse(goodsList);
        t.goodsListLoading = false;
        t.$http({
          url: t.$http.adornUrl('userAddress/queryUserAddress'),
          method: 'post',
          data: t.$http.adornData({
            'pageNo': 0,
            'pageSize': 5,
            'isDeleted': false,
            'orderBy': 'is_default desc,id',
          })
        }).then(({data}) => {
          if (data && data.code === 200) {
            t.userAddressList = data.dataMap.records;
            t.change(0);
            t.setTotal();
          }
        }).then(function () {
        });
      },
      submitOrderHandle(){
        this.loading = true;
        let goodsList = [];
        for (var i = 0; i < this.goodsList.length; i++) {
          goodsList.push({goodsId: this.goodsList[i].id, goodsNumber: this.goodsList[i].number})
        }
        let orderJump = sessionStorage.getItem('order_jump') === 'true' ? true : false;
        let orderData = {
          userAddressId: this.userAddressList[this.userAddressIndex].id,
          goodsList: goodsList,
          message: this.message,
          flag: orderJump,
        };
        this.$http({
          url: this.$http.adornUrl('order/createOrder'),
          method: 'post',
          data: orderData
        }).then(({data}) => {
          if (data && data.code === 200) {
            this.removeTabHandle('mallOrderConfirm', 'mall/order/pay');
            sessionStorage.setItem('order_confirm_goods', []);
            sessionStorage.setItem('pay_order_no', data.dataMap);
            this.$router.push({path: '/mall/order/pay'});
          } else {
            this.loading = false;
          }
        });
      },
      userCartHandle(){
        this.removeTabHandle('mallOrderConfirm', 'mall/cart/index');
        sessionStorage.setItem('order_confirm_goods', []);
        this.$router.push({path: '/mall/cart/index'});
      },
      removeTabHandle(tagName, name) {
        this.mainTabs = this.mainTabs.filter(item => item.name !== tagName);
        this.menuActiveName = name;
      },
    }
  }
</script>
