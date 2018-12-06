<template>
  <div class="mod-user">
    <el-table
      :data="dataList"
      border
      v-loading="dataListLoading"
      @selection-change="selectionChangeHandle"
      style="width: 100%;">
      <el-table-column
        type="selection"
        header-align="center"
        align="center"
        width="50">
      </el-table-column>
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
        <template slot-scope="scope">
          <el-input-number style="width: 150px;" v-model="scope.row.number" @change="handleChange"
                           :max="scope.row.inventory"
                           label="数量"></el-input-number>
        </template>
      </el-table-column>
      <el-table-column
        prop="sum"
        header-align="center"
        align="center"
        width="180"
        label="金额">
        <!--<template slot-scope="scope">`Decimal.mul(￥{{scope.row.price}}, ￥{{scope.row.number}}).toNumber()`</template>-->
        <template slot-scope="scope">{{ aDouble[scope.$index].sum }}</template>
      </el-table-column>

      <el-table-column
        fixed="right"
        header-align="center"
        align="center"
        width="150"
        label="操作">
        <template slot-scope="scope">
          <el-button v-if="isAuth('user:delete:button')" type="text" size="small" @click="deleteHandle(scope.row.id)">
            删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-row class="mt20 footer">
      <el-button v-if="isAuth('user:delete:button')" type="danger" @click="deleteHandle()"
                 :disabled="dataListSelections.length <= 0">删除
      </el-button>
      <div class="float-right">
        <span>已选商品<span class="total">{{dataListSelections.length}}</span>种</span>
        <span>合计不含运费：<span class="total">￥{{total}}</span></span>
        <el-button v-if="isAuth('user:delete:button')" type="warning" @click="orderConfirmHandle()"
                   :disabled="dataListSelections.length <= 0">结算
        </el-button>
      </div>

    </el-row>
  </div>
</template>

<style lang="scss">
  .footer {
    padding: 10px;
    background: #e5e5e5;
    color: #3c3c3c;
    font: 12px/1.5 tahoma, arial, 'Hiragino Sans GB', '\5b8b\4f53', sans-serif;
    .total {
      padding: 0 4px;
      font-weight: 500;
      font-size: 21px;
      color: #f43043;
    }
  }
</style>

<script>
  import {dateFormat} from '@/utils';
  import Decimal from 'decimal';
  export default {
    data () {
      return {
        total: 0,
        dataList: [],
        dataListLoading: false,
        dataListSelections: [],
        addOrUpdateVisible: false
      }
    },
    activated () {
      this.getDataList()
    },
    computed: {
      aDouble() {
        this.dataList.map((v, i) => {
          v.sum = Decimal.mul(v.price, v.number).toNumber();
        });
        return this.dataList;
      },
      totalPrice() {
        let total = 0;
        this.dataListSelections.map((v, i) => {
          let sum = Decimal.mul(v.price, v.number).toNumber();
          total = Decimal.add(total, sum).toNumber();
        });
        return total;
      },
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
      // 多选
      selectionChangeHandle (val) {
        this.dataListSelections = val;
        this.setTotal();
      },
      setTotal(){
          this.total = this.totalPrice;
      },
      handleChange(val) {
        this.setTotal();
      },
      // 获取数据列表
      getDataList () {
        this.dataListLoading = true
        this.$http({
          url: this.$http.adornUrl('userCart/queryUserCart'),
          method: 'post',
          data: {}
        }).then(({data}) => {
          if (data && data.code === 200) {
            this.dataList = data.dataMap.records;
          } else {
            this.dataList = [];
          }
          this.dataListLoading = false;
        })
      },
      // 结算
      orderConfirmHandle () {
        sessionStorage.setItem('order_confirm_goods', JSON.stringify(this.dataListSelections));
        sessionStorage.setItem('order_jump', true);
        this.removeTabHandle();
        this.$router.push({path: '/mall/order/confirm'});
      },
      removeTabHandle() {
        var tabName = 'mall/cart/index';
        this.mainTabs = this.mainTabs.filter(item => item.name !== tabName);
        this.menuActiveName = 'mall/order/confirm';
      },
      deleteHandle (id) {
        var ids = id ? [id] : this.dataListSelections.map(item => {
          return item.id
        })
        this.$confirm(`确定要进行删除操作?`, '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$http({
            url: this.$http.adornUrl('userCart/deleteUserCart'),
            method: 'post',
            data: this.$http.adornData(ids, false)
          }).then(({data}) => {
            if (data && data.code === 200) {
              this.$message({
                message: '操作成功',
                type: 'success',
                duration: 1500,
                onClose: () => {
                  this.getDataList()
                }
              })
            } else {
              this.$message.error(data.msg)
            }
          })
        }).catch(() => {
        })
      },
      //时间格式化
      dateFormat: function (row, column) {
        var date = row[column.property];
        if (date == undefined) {
          return "";
        }
        return dateFormat(date);
      }
    }
  }
</script>
