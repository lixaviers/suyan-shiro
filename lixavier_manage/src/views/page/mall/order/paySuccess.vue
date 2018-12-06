<template>
  <div>
    <el-card>
      <el-row>
        <div class="float-left"><i class="el-icon-circle-check" style="font-size: 60px;color: #67c23a;"></i></div>
        <div class="float-left ml10" style="line-height: 60px;font-size: 28px;">支付成功</div>
      </el-row>
      <p class="mt20"><span class="text">订单号：</span>{{info.orderNo}}</p>
      <p class="mt10"><span class="text">总金额：</span><span class="red">￥{{info.totalPrice}}</span></p>
      <p class="mt10"><span class="text">运费金额：</span><span class="red">￥{{info.freightPrice}}</span></p>
      <p class="mt10"><span class="text">促销金额：</span><span class="red">￥{{info.promotionPrice}}</span></p>
      <p class="mt10"><span class="text">礼品卡：</span><span class="red">￥{{info.giftCardPrice}}</span></p>
      <p class="mt10"><span class="text">支付金额：</span><span class="red">￥{{info.realPayPrice}}</span></p>
      <p class="mt10 mb10"><span class="text">支付时间：</span>{{info.payTime}}</p>
    </el-card>
  </div>
</template>

<style lang="scss">
  .text {
    display: inline-block;
    width: 100px;
    text-align: right;
  }

  .red {
    font-size: 20px;
    color: #e4393c;
  }
</style>

<script>
  import {dateFormat} from '@/utils';
  export default {
    data() {
      return {
        info: {
          orderNo: '',
          totalPrice: '',
          freightPrice: '',
          promotionPrice: '',
          giftCardPrice: '',
          payTime: '',
        }
      }
    },
    methods: {
      init(orderNo) {
        this.orderNo = orderNo;
        this.$http({
          url: this.$http.adornUrl(`order/getPayOrder/${orderNo}`),
          method: 'get',
        }).then(({data}) => {
          if (data && data.code === 200) {
            this.info = data.dataMap;
            this.info.payTime = dateFormat(data.dataMap.payTime);
          }
        });
      }
    }
  }
</script>
