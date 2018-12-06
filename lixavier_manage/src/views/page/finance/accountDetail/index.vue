<template>
  <div class="mod-role">
    <el-form :inline="true" :model="dataForm" @keyup.enter.native="getDataList()">
      <el-form-item>
        <el-select v-model="dataForm.transactionType" placeholder="交易类型">
          <el-option value="" label="交易类型:全部"></el-option>
          <el-option v-for="item in transactionTypeList" :key="item.value" :value="item.value" :label="item.desc"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-select v-model="dataForm.channel" placeholder="渠道">
          <el-option value="" label="渠道:全部"></el-option>
          <el-option v-for="item in channelList" :key="item.value" :value="item.value" :label="item.desc"></el-option>
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
      <el-table-column
        prop="id"
        header-align="center"
        align="center"
        width="80"
        label="编号">
      </el-table-column>
      <el-table-column
        prop="product"
        header-align="center"
        align="center"
        label="产品">
      </el-table-column>

      <el-table-column
        prop="transactionTypeDesc"
        header-align="center"
        align="center"
        label="交易类型">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.transactionType == 1" type="warning">{{ scope.row.transactionTypeDesc }}</el-tag>
          <el-tag v-if="scope.row.transactionType == 2" type="success">{{ scope.row.transactionTypeDesc }}</el-tag>
          <el-tag v-if="scope.row.transactionType == 3" type="danger">{{ scope.row.transactionTypeDesc }}</el-tag>
        </template>
      </el-table-column>

      <el-table-column
        prop="amount"
        header-align="center"
        align="center"
        label="金额">
        <template slot-scope="scope">
          <span v-if="scope.row.transactionType == 1" style="color: #e6a23c;">￥{{ scope.row.amount }}</span>
          <span v-if="scope.row.transactionType == 2" style="color: #67c23a;">￥{{ scope.row.amount }}</span>
          <span v-if="scope.row.transactionType == 3" style="color: #f56c6c;">￥{{ scope.row.amount }}</span>
        </template>
      </el-table-column>

      <el-table-column
        prop="balance"
        header-align="center"
        align="center"
        label="余额">
        <template slot-scope="scope"><span style="color: #e4393c;">￥{{scope.row.balance}}</span></template>
      </el-table-column>

      <el-table-column
        prop="channelDesc"
        header-align="center"
        align="center"
        label="渠道">
      </el-table-column>

      <el-table-column
        prop="createTime"
        header-align="center"
        align="center"
        :formatter="dateFormat"
        width="180"
        label="创建时间">
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
          transactionType: '',
          channel: '',
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
              url: t.$http.adornUrl('userAccountDetail/queryUserAccountDetail'),
              method: 'post',
              data: t.$http.adornData({
                'pageNo': t.pageIndex,
                'pageSize': t.pageSize,
                'transactionType': t.dataForm.transactionType,
                'channel': t.dataForm.channel,
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
