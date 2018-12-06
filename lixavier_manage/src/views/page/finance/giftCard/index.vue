<template>
  <div class="mod-role">
    <el-form :inline="true" :model="dataForm" @keyup.enter.native="getDataList()">
      <el-form-item>
        <el-button type="primary" @click="addHandle()">激活礼品卡</el-button>
      </el-form-item>
      <el-form-item>
        <el-select v-model="dataForm.status" placeholder="请选择">
          <el-option value="0" label="有效"></el-option>
          <el-option value="1" label="无效"></el-option>
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
        prop="id"
        header-align="center"
        align="center"
        label="编码">
      </el-table-column>
      <el-table-column
        prop="price"
        header-align="center"
        align="center"
        label="价格">
      </el-table-column>
      <el-table-column
        prop="endTime"
        header-align="center"
        align="center"
        :formatter="dateFormat"
        label="失效时间">
      </el-table-column>
      <el-table-column
        prop="createTime"
        header-align="center"
        align="center"
        :formatter="dateFormat"
        label="领取时间">
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
    <add v-if="addVisible" ref="add" @refreshUserGiftCardDataList="getDataList"></add>
  </div>
</template>

<script>
  import Add from './add.vue';
  import {dateFormat} from '@/utils';
  export default {
    data () {
      return {
        dataForm: {
          status: '0'
        },
        dataList: [],
        pageIndex: 1,
        pageSize: 10,
        totalPage: 0,
        dataListLoading: false,
        dataListSelections: [],
        addVisible: false
      }
    },
    components: {
      Add
    },
    activated () {
      this.getDataList()
    },
    methods: {
      // 获取数据列表
      getDataList () {
        this.dataListLoading = true;
        this.$http({
          url: this.$http.adornUrl('giftCard/queryUserGiftCards'),
          method: 'post',
          data: this.$http.adornData({
            'pageNo': this.pageIndex,
            'pageSize': this.pageSize,
            'status': this.dataForm.status,
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
      // 新增
      addHandle (id) {
        this.addVisible = true;
        this.$nextTick(() => {
          this.$refs.add.init(id);
        })
      },
      // 删除
      deleteHandle (id) {
        var giftCardIds = id ? [id] : this.dataListSelections.map(item => {
          return item.id
        })
        this.$confirm(`确定对[id=${giftCardIds.join(',')}]进行[${id ? '删除' : '批量删除'}]操作?`, '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$http({
            url: this.$http.adornUrl('giftCard/deleteGiftCard'),
            method: 'post',
            data: this.$http.adornData(giftCardIds, false)
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
