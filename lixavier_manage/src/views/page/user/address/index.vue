<template>
  <div class="mod-role">
    <el-form :inline="true" :model="dataForm" @keyup.enter.native="getDataList()">
      <el-form-item>
        <el-input v-model="dataForm.consignee" placeholder="收货人姓名" clearable></el-input>
      </el-form-item>
      <el-form-item>
        <el-button @click="getDataList()">查询</el-button>
        <el-button v-if="isAuth('userAddress:createUserAddress')" type="primary" @click="addOrUpdateHandle()">新增</el-button>
      </el-form-item>
    </el-form>
    <el-table
      :data="dataList"
      border
      v-loading="dataListLoading"
      @selection-change="selectionChangeHandle"
      style="width: 100%;">
      <el-table-column
        type="index"
        header-align="center"
        align="center"
        width="80"
        label="编号">
      </el-table-column>
      <el-table-column
        header-align="center"
        align="center"
        label="地址信息">
        <template slot-scope="scope">
          <el-tag type="primary" plain>{{scope.row.provinceName}}</el-tag>
          <el-tag type="primary" plain>{{scope.row.cityName}}</el-tag>
          <el-tag type="primary" plain>{{scope.row.areaName}}</el-tag>
        </template>
      </el-table-column>
      <el-table-column
        prop="address"
        header-align="center"
        align="center"
        label="详细地址">
      </el-table-column>
      <el-table-column
        prop="consignee"
        header-align="center"
        align="center"
        label="收货人姓名">
      </el-table-column>
      <el-table-column
        prop="mobile"
        header-align="center"
        align="center"
        width="180"
        label="手机号码">
      </el-table-column>
      <el-table-column
        prop="isDefault"
        header-align="center"
        align="center"
        width="120"
        label="是否默认">
        <template slot-scope="scope">
          <el-tag :type="scope.row.isDefault ? 'success' : 'error' ">{{ scope.row.isDefault ? '是' : '否' }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column
        prop="createTime"
        header-align="center"
        align="center"
        width="180"
        :formatter="dateFormat"
        label="创建时间">
      </el-table-column>
      <el-table-column
        fixed="right"
        header-align="center"
        align="center"
        width="150"
        label="操作">
        <template slot-scope="scope">
          <el-button v-if="isAuth('userAddress:updateUserAddress')" type="text" size="small" @click="addOrUpdateHandle(scope.row.id)">修改</el-button>
          <el-button v-if="isAuth('userAddress:deleteUserAddress')" type="text" size="small" @click="deleteHandle(scope.row.id)">删除</el-button>
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
    <!-- 弹窗, 新增 / 修改 -->
    <add-or-update v-if="addOrUpdateVisible" ref="addOrUpdate" @refreshDataList="getDataList"></add-or-update>
  </div>
</template>

<script>
  import AddOrUpdate from './address-add-or-update'
  import {dateFormat} from '@/utils';
  export default {
    data () {
      return {
        dataForm: {
          consignee: ''
        },
        dataList: [],
        pageIndex: 1,
        pageSize: 10,
        totalPage: 0,
        dataListLoading: false,
        dataListSelections: [],
        addOrUpdateVisible: false
      }
    },
    components: {
      AddOrUpdate
    },
    activated () {
      this.getDataList()
    },
    methods: {
      // 获取数据列表
      getDataList () {
        this.dataListLoading = true
        this.$http({
          url: this.$http.adornUrl('userAddress/queryUserAddress'),
          method: 'post',
          data: this.$http.adornData({
            'pageNo': this.pageIndex,
            'pageSize': this.pageSize,
            'isDeleted': false,
            'orderBy': 'is_default desc,id',
            'consigneeLike': this.dataForm.consignee
          })
        }).then(({data}) => {
          if (data && data.code === 200) {
            this.dataList = data.dataMap.records;
            this.totalPage = data.dataMap.totalRecords;
          } else {
            this.dataList = []
            this.totalPage = 0
          }
          this.dataListLoading = false
        })
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
      // 多选
      selectionChangeHandle (val) {
        this.dataListSelections = val
      },
      // 新增 / 修改
      addOrUpdateHandle (id) {
        this.addOrUpdateVisible = true;
        this.$nextTick(() => {
          this.$refs.addOrUpdate.init(id);
        })
      },
      // 删除
      deleteHandle (id) {
        this.$confirm(`确定要进行删除操作?`, '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$http({
            url: this.$http.adornUrl('userAddress/deleteUserAddress/' + id),
            method: 'post'
          }).then(({data}) => {
            if (data && data.code === 200) {
              this.$message({
                message: '操作成功',
                type: 'success',
                duration: 1000,
                onClose: () => {
                  this.getDataList()
                }
              })
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
