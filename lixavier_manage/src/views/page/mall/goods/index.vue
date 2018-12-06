<template>
  <div>
    <el-row style="margin-bottom: 20px;">
      <el-button @click="changeType(item.id)" :value="item.id" v-for="item in categoryList" :key="item.id">
        {{item.name}}
      </el-button>
    </el-row>
    <el-row :gutter="20">
      <el-col v-for="item in goodsList" :key="item.id" :span="6">
        <a href="javascript:;" @click="detail(item)" class="goods">
          <el-card shadow="hover">
            <div style="text-align: center;">
              <img :src="item.pictureUrl" width="350" height="350">
            </div>
            <div class="title">
              <span>{{item.name}}</span>
            </div>
            <div>
              <span class="price">¥ {{item.price}}</span>
            </div>
          </el-card>
        </a>
      </el-col>
    </el-row>
  </div>
</template>

<style lang="scss">
  .goods {
    .title {
      margin: 10px 0;
      width: 100%;
      height: 37px;
      overflow: hidden;
    }

    .price {
      text-align: left;
      padding-top: 15px;
      color: #f43043;
      font-size: 22px;
      white-space: nowrap;
    }
  }
</style>

<script>
  export default {
    data() {
      return {
        categoryList: [],
        goodsList: [],
        categoryId: null,
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
      this.removeTabHandle('mallGoodsDetail', 'home');
      this.getCategoryList();
    },
    methods: {
      detail(item){
        this.removeTabHandle('mall/goods/index', 'mallGoodsDetail');
        this.$router.push({path: '/mall/goods/detail', query: {id: item.id}})
      },
      removeTabHandle(tabName, name) {
        this.mainTabs = this.mainTabs.filter(item => item.name !== tabName);
        this.menuActiveName = name;
      },
      getCategoryList() {
        let t = this;
        t.$http({
          url: t.$http.adornUrl('category/getCategoryList'),
          method: 'get',
        }).then(({data}) => {
          if (data && data.code === 200) {
            t.categoryList = data.dataMap;
          } else {
            t.categoryList = [];
          }
        }).then(function () {
          t.getDataList();
        })
      },
      getDataList() {
        this.visible = true;
        this.$http({
          url: this.$http.adornUrl('goods/queryGoodsList'),
          method: 'post',
          data: this.$http.adornData({
            'pageNo': this.pageIndex,
            'pageSize': this.pageSize,
            'categoryId': this.categoryId,
          })
        }).then(({data}) => {
          if (data && data.code === 200) {
            this.goodsList = data.dataMap.records;
          } else {
            this.goodsList = []
          }
        })
      },
      changeType(id) {
        this.categoryId = id;
        this.getDataList();
      }
    }
  }
</script>
