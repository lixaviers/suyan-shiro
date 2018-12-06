<template>
  <div>
    <el-row>订单状态：
      <el-tag v-if="orderInfo.commonStatus == 0" type="warning">待支付</el-tag>
      <el-tag v-else-if="orderInfo.commonStatus == 1" type="success">已支付</el-tag>
      <el-tag v-else-if="orderInfo.commonStatus == 2" type="info">已关闭</el-tag>
      <el-tag v-else-if="orderInfo.commonStatus == 3">待评价</el-tag>
      <el-tag v-else-if="orderInfo.commonStatus == 4">已完成</el-tag>
      <el-tag v-else-if="orderInfo.commonStatus == 5" type="warning">退款中</el-tag>
      <el-tag v-else-if="orderInfo.commonStatus == 6">已退款</el-tag>
      <el-button class="ml10" @click="backList()">返回列表</el-button>
    </el-row>
    <el-card class="mt20">
      <div slot="header" class="clearfix">
        <span>订单信息</span>
      </div>
      <p class="mt10"><span class="text">订单编号：</span><span>{{orderInfo.order.orderNo}}</span></p>
      <p class="mt10"><span class="text">创建时间：</span><span>{{orderInfo.order.createTime}}</span></p>
      <p class="mt10" v-if="orderInfo.payTime"><span class="text">付款时间：</span><span>{{orderInfo.payTime}}</span></p>
      <p class="mt10" v-if="orderInfo.deliveryTime"><span class="text">发货时间：</span><span>{{orderInfo.deliveryTime}}</span></p>
      <p class="mt10" v-if="orderInfo.commonStatus == 3"><span class="text">成交时间：</span><span>{{orderInfo.dealTime}}</span></p>
      <p class="mt10" v-if="orderInfo.commonStatus == 6"><span class="text">退款时间：</span><span>{{orderInfo.refundTime}}</span></p>
    </el-card>
    <el-card class="mt20">
      <div slot="header" class="clearfix">
        <span>付款信息</span>
      </div>
      <p class="mt10"><span class="text">商品总价：</span><span>￥{{orderInfo.totalPrice}}</span></p>
      <p class="mt10" v-if="orderInfo.giftCardPrice"><span class="text">礼品卡：</span><span>￥{{orderInfo.giftCardPrice}}</span></p>
      <p class="mt10"><span class="text">促销金额：</span><span>￥{{orderInfo.promotionPrice}}</span></p>
      <p class="mt10"><span class="text">实付金额：</span><span class="price">￥{{orderInfo.payPrice}}</span></p>
    </el-card>
  </div>
</template>

<style lang="scss">
  .text {
    width: 80px;
    display: inline-block;
  }

  .price {
    font-size: 20px;
    color: #e4393c;
  }
</style>

<script>
  import {dateFormat} from '@/utils';
  export default {
    data() {
      return {
        orderInfo: {
          commonStatus: '',
          totalPrice: '',
          giftCardPrice: '',
          promotionPrice: '',
          payPrice: '',
          payTime: '',
          deliveryTime: '',
          dealTime: '',
          refundTime: '',
          order: {
            orderNo: '',
            createTime: '',
          }
        },
      }
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
        let id = this.$route.query.id, t = this;
        if (!id) {
          t.removeTabHandle();
          t.$router.push({path: '/finance/order/index'})
        } else {
          t.$http({
            url: t.$http.adornUrl(`orderGoods/getOrderInfo/${id}`),
            method: 'get',
            params: t.$http.adornParams()
          }).then(({data}) => {
            if (data && data.code === 200) {
              this.orderInfo = data.dataMap;
              this.orderInfo.payTime = dateFormat(data.dataMap.payTime);
              this.orderInfo.deliveryTime = dateFormat(data.dataMap.deliveryTime);
              this.orderInfo.dealTime = dateFormat(data.dataMap.dealTime);
              this.orderInfo.refundTime = dateFormat(data.dataMap.refundTime);
              this.orderInfo.order.orderNo = data.dataMap.order.orderNo;
              this.orderInfo.order.createTime = dateFormat(data.dataMap.order.createTime);
            }
          })
        }
      },
      backList() {
        this.removeTabHandle();
        this.$router.push({path: '/finance/order/index'})
      },
      removeTabHandle() {
        this.mainTabs = this.mainTabs.filter(item => item.name !== 'financeOrderDetail');
      }
    }
  }
</script>
