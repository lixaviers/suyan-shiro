<template>
  <div>
    <el-row>
      <div class="float-left" style="width: 350px;height: 350px;">
        <img :src="goods.pictureUrl" width="350" height="350">
      </div>
      <div class="float-left" style="width: calc(100% - 370px);margin-left: 20px;">
        <p class="sku-name">{{goods.name}}{{goods.name}}</p>
        <div class="summary">
          <p class="summary-price">商 品 价<span class="sku-price">￥{{goods.price}}</span></p>
        </div>
        <p class="summary-price mt20">库存:<span class="pl5">{{goods.remainingInventory}}</span></p>
        <p class="summary-price mt20">数量:
          <el-input-number style="width: 150px;" v-model="goods.number" :min="1" :max="goods.remainingInventory"
                           label="数量"></el-input-number>
        </p>
        <p class="summary-price mt20">运费:<span class="pl5">10元，满88包邮</span></p>
        <el-row class="mt20">
          <el-button @click="addCart()">加入购物车</el-button>
          <el-button :disabled="goods.remainingInventory < 1" @click="orderConfirm()" type="danger">立即购买</el-button>
        </el-row>
      </div>
    </el-row>
    <el-row class="mt20">
      <el-tabs type="border-card">
        <el-tab-pane label="商品详情">
          <el-row v-html="goods.description"></el-row>
        </el-tab-pane>
        <el-tab-pane label="累计评价 0">
          <el-row v-for="i in 30" :key="i" class="mt20">这里是评价，待开发</el-row>
        </el-tab-pane>
      </el-tabs>
    </el-row>
  </div>
</template>

<style lang="scss">
  .sku-name {
    font: 700 16px Arial, "microsoft yahei";
    color: #666;
    padding-top: 10px;
    line-height: 28px;
    margin-bottom: 10px;
  }

  .summary {
    padding: 10px 0 10px 10px;
    background: #f3f3f3;
  }

  .summary-price {
    font-family: simsun;
    color: #999;
    font-size: 14px;
    .sku-price {
      padding-left: 5px;
      font-size: 22px;
      color: #e4393c;
    }
  }


</style>

<script>
  import Decimal from 'decimal';
  export default {
    data() {
      return {
        goods: {},
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
          t.removeTabHandle('home');
          t.$router.push({path: '/mall/goods/index'})
        } else {
          t.$http({
            url: t.$http.adornUrl(`goods/getGoods/${id}`),
            method: 'get',
            params: t.$http.adornParams()
          }).then(({data}) => {
            if (data && data.code === 200) {
              this.goods = data.dataMap;
              this.goods.number = 1;
            }
          })
        }
      },
      orderConfirm() {
        this.goods.description = '';
        var goods = {
          id: this.goods.id,
          name: this.goods.name,
          price: this.goods.price,
          number: this.goods.number,
          sum: Decimal.mul(this.goods.price, this.goods.number).toNumber(),
        };
        sessionStorage.setItem('order_confirm_goods', JSON.stringify([goods]));
        sessionStorage.setItem('order_jump', false);
        this.removeTabHandle('mallGoodsDetail', 'mall/order/confirm');
        this.$router.push({path: '/mall/order/confirm'});
      },
      addCart() {
        let t = this;
        t.$http({
          url: t.$http.adornUrl(`userCart/createUserCart`),
          method: 'POST',
          data: this.$http.adornData({
            'goodsId': this.goods.id,
            'number': this.goods.number,
          })
        }).then(({data}) => {
          if (data && data.code === 200) {
            t.removeTabHandle('mallGoodsDetail', 'mall/cart/index');
            t.$router.push({path: '/mall/cart/index'});
          }
        })
      },
      removeTabHandle(tabName, name) {
        this.mainTabs = this.mainTabs.filter(item => item.name !== tabName);
        this.menuActiveName = name;
      }
    }
  }
</script>
