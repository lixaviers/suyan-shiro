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
        <el-tab-pane :label="'累计评价 ' + totalPage">
          <table class="goods-common" width="100%">
            <tr v-for="(item, index) in goodsCommonList" :key="item.id">
              <td class="tm-col-master">
                <div class="tm-rate-content">
                  <div class="tm-rate-fulltxt">{{item.content}}</div>
                  <div v-if="item.urls && item.urls.length > 0" class="tm-m-photos">
                    <ul class="tm-m-photos-thumb">
                      <li v-for="item in item.urls" :key="item">
                        <img :src="item"/><b class="tm-photos-arrow"></b>
                      </li>
                    </ul>
                  </div>
                </div>
                <div class="tm-rate-date mt10">{{formatDate(item.createTime)}}</div>
                <div v-if="item.isReply" class="tm-rate-reply">
                  <div class="tm-rate-fulltxt">后台回复：{{item.replyContent}}</div>
                </div>
              </td>
              <td class="col-author">
                <div class="rate-user-info">{{item.nickName}}<span v-if="item.isAnonymous">（匿名）</span></div>
              </td>
            </tr>
          </table>
          <el-pagination
            layout="prev, pager, next"
            :total="totalPage"
            style="margin-top: 20px;"
            @current-change="currentChangeHandle"
            :current-page="pageIndex"
            :page-size="pageSize">
          </el-pagination>
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

  .goods-common {
    font-size: 12px;
    font-family: tahoma, arial, \5FAE\8F6F\96C5\9ED1, sans-serif;
    .tm-col-master {
      width: 600px;
      padding-right: 30px;
    }

    .tm-rate-content, .tm-rate-reply {
      word-wrap: break-word;
      word-break: break-all;
      line-height: 19px;
      overflow: hidden;
    }

    .tm-rate-date {
      clear: both;
      color: #ccc;
    }
    td {
      padding: 16px 7px;
      border-bottom: 1px solid #e3e3e3;
    }

    .rate-user-info {
      text-overflow: ellipsis;
      white-space: nowrap;
      overflow: hidden;
      width: 95px;
    }

    .tm-rate-reply {
      color: #AF874D;
      margin-top: 8px;
    }

    .tm-m-photos-thumb {
      margin: 10px 0;
      list-style-type: none;
      height: 40px;
      zoom: 1;
      li {
        float: left;
        border: 2px solid #f2f2f2;
        zoom: 1;
        padding: 2px;
        margin-right: 8px;
        position: relative;
        transition: border-color .2s ease-out;
      }
      img {
        display: block;
        width: 40px;
        height: 40px;
      }
    }
  }


</style>

<script>
  import Decimal from 'decimal';
  import {dateFormat} from '@/utils';
  export default {
    data() {
      return {
        goods: {},
        goodsCommonList: [],
        pageIndex: 1,
        pageSize: 20,
        totalPage: 0,
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
          }).then(function () {
            t.getCommonList();
          });
        }
      },
      getCommonList() {
        let t = this;
        t.$http({
          url: t.$http.adornUrl('goodsComment/queryGoodsComment'),
          method: 'post',
          data: t.$http.adornData({
            'pageNo': t.pageIndex,
            'pageSize': t.pageSize,
            'orderBy': 'create_time desc,id desc',
          })
        }).then(({data}) => {
          if (data && data.code === 200) {
            t.goodsCommonList = data.dataMap.records;
            t.totalPage = data.dataMap.totalRecords;
          } else {
            t.goodsCommonList = []
            t.totalPage = 0
          }
          t.dataListLoading = false
        })
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
      formatDate(time) {
        return dateFormat(time, 'YYYY-MM-DD');
      },
      // 当前页
      currentChangeHandle (val) {
        this.pageIndex = val;
        this.getCommonList();
      },
      removeTabHandle(tabName, name) {
        this.mainTabs = this.mainTabs.filter(item => item.name !== tabName);
        this.menuActiveName = name;
      }
    }
  }
</script>
