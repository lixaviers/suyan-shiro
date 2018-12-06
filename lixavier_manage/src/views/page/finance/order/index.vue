<template>
  <div class="mod-role">
    <el-form :inline="true" :model="dataForm" @keyup.enter.native="getDataList()">
      <el-form-item>
        <el-input v-model="dataForm.orderNo" placeholder="订单号" style="width: 240px;"></el-input>
      </el-form-item>
      <el-form-item>
        <el-select v-model="dataForm.orderStatus" placeholder="状态">
          <el-option value="" label="状态:全部"></el-option>
          <el-option value="0" label="待支付"></el-option>
          <el-option value="1" label="已支付"></el-option>
          <el-option value="2" label="已关闭"></el-option>
          <el-option value="3" label="待评价"></el-option>
          <el-option value="4" label="已完成"></el-option>
          <el-option value="5" label="退款中"></el-option>
          <el-option value="6" label="已退款"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button @click="getDataList()">查询</el-button>
      </el-form-item>
    </el-form>

    <el-table
      :data="dataList"
      border
      v-loading="dataListLoading"
      style="width: 100%;">
      <el-table-column type="index" header-align="center" align="center" width="80" label="编号">
      </el-table-column>

      <el-table-column prop="orderNo" header-align="center" align="center" label="订单号"></el-table-column>

      <el-table-column prop="goodsName" header-align="center" align="center" label="商品名称"></el-table-column>

      <el-table-column prop="goodsNumber" header-align="center" align="center" label="商品数量"
                       width="150"></el-table-column>

      <el-table-column prop="goodsPrice" header-align="center" align="center" label="商品价格" width="180">
        <template slot-scope="scope">
          <span>￥{{ scope.row.goodsPrice }}</span>
        </template>
      </el-table-column>

      <el-table-column prop="totalPrice" header-align="center" align="center" label="总价" width="180">
        <template slot-scope="scope">
          <span style="color: #f43043;">￥{{ scope.row.totalPrice }}</span>
        </template>
      </el-table-column>

      <el-table-column prop="commonStatus" header-align="center" align="center" label="状态" width="150">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.commonStatus == 0" type="warning">待支付</el-tag>
          <el-tag v-if="scope.row.commonStatus == 1" type="success">已支付</el-tag>
          <el-tag v-if="scope.row.commonStatus == 2" type="info">已关闭</el-tag>
          <el-tag v-if="scope.row.commonStatus == 3">待评价</el-tag>
          <el-tag v-if="scope.row.commonStatus == 4">已完成</el-tag>
          <el-tag v-if="scope.row.commonStatus == 5" type="warning">退款中</el-tag>
          <el-tag v-if="scope.row.commonStatus == 6">已退款</el-tag>
        </template>
      </el-table-column>

      <el-table-column fixed="right" header-align="center" align="center" width="180" label="操作">
        <template slot-scope="scope">
          <el-button type="text" size="small" @click="detailHandle(scope.row.id)">详情</el-button>
          <el-button v-if="scope.row.commonStatus == 0" type="text" size="small" @click="payHandle(scope.row.orderNo)">
            去支付
          </el-button>
          <el-button v-if="scope.row.commonStatus == 1" type="text" size="small" @click="confirmHandle(scope.row.id)">
            确认收货
          </el-button>
          <el-button v-if="scope.row.commonStatus == 1" type="text" size="small" @click="applyRefundHandle(scope.row.id)">
            申请退款
          </el-button>
          <el-button v-if="scope.row.commonStatus == 2 || scope.row.commonStatus == 4 || scope.row.commonStatus == 6"
                     type="text" size="small" @click="deleteOrderGoodsHandle(scope.row.id)">删除
          </el-button>
          <el-button v-if="scope.row.commonStatus == 3" type="text" size="small" @click="confirmHandle(scope.row.id)">
            评价
          </el-button>
        </template>
      </el-table-column>

    </el-table>
    <el-pagination
      style="margin-top: 20px;"
      @size-change="sizeChangeHandle"
      @current-change="currentChangeHandle"
      :current-page="pageIndex"
      :page-sizes="[10, 20, 50, 100]"
      :page-size="pageSize"
      :total="totalPage"
      layout="total, sizes, prev, pager, next, jumper">
    </el-pagination>
  </div>
</template>

<script>
  import {dateFormat} from '@/utils';
  export default {
    data () {
      return {
        transactionTypeList: [],
        channelList: [],
        dataForm: {
          orderStatus: '',
          orderNo: '',
        },
        dataList: [],
        pageIndex: 1,
        pageSize: 10,
        totalPage: 0,
        dataListLoading: false,
      }
    },
    activated () {
      this.getDataList()
    },
    methods: {
      detailHandle(id){
        this.$router.push({path: '/finance/order/detail', query: {id: id}});
      },
      deleteOrderGoodsHandle(id){
        this.$confirm(`确定要删除订单吗?`, '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$http({
            url: this.$http.adornUrl('orderGoods/deleteOrderGoods/' + id),
            method: 'post',
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
      applyRefundHandle(id){
        this.$confirm(`确定要申请退款吗?`, '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$http({
            url: this.$http.adornUrl('orderGoods/applyRefund/' + id),
            method: 'post',
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
      confirmHandle(id){
        this.$confirm(`确定要确认收货吗?`, '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$http({
            url: this.$http.adornUrl('orderGoods/confirmReceipt/' + id),
            method: 'post',
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
      payHandle(orderNo){
        sessionStorage.setItem('pay_order_no', orderNo);
        this.$router.push({path: '/mall/order/pay'});
      },
      // 获取数据列表
      getDataList () {
        let t = this;
        t.dataListLoading = true;

        t.$http({
          url: t.$http.adornUrl('userAccountDetail/getTransactionTypeList'),
          method: 'get',
        }).then(({data}) => {
          if (data && data.code === 200) {
            t.transactionTypeList = data.dataMap;
          }
        }).then(function () {
          t.$http({
            url: t.$http.adornUrl('userAccountDetail/getChannelList'),
            method: 'get',
          }).then(({data}) => {
            if (data && data.code === 200) {
              t.channelList = data.dataMap;
            }
          }).then(function () {
            t.$http({
              url: t.$http.adornUrl('orderGoods/queryOrderGoods'),
              method: 'post',
              data: t.$http.adornData({
                'pageNo': t.pageIndex,
                'pageSize': t.pageSize,
                'commonStatus': t.dataForm.orderStatus,
                'orderNo': t.dataForm.orderNo,
                'type': 1,
                'orderBy': 'create_time desc,id desc',
              })
            }).then(({data}) => {
              if (data && data.code === 200) {
                t.dataList = data.dataMap.records;
                t.totalPage = data.dataMap.totalRecords;
              } else {
                t.dataList = []
                t.totalPage = 0
              }
              t.dataListLoading = false
            })
          });
        });


      },
      // 每页数
      sizeChangeHandle (val) {
        this.pageSize = val
        this.pageIndex = 1
        this.getDataList()
      },
      // 当前页
      currentChangeHandle (val) {
        this.pageIndex = val
        this.getDataList()
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
