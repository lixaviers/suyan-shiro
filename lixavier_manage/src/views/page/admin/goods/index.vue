<template>
  <div>

    <div v-show="visible">
      <!-- 查询和其他操作 -->
      <el-form :inline="true" :model="listQuery" @keyup.enter.native="getDataList()">
        <el-form-item>
          <el-input v-model="listQuery.name" clearable style="width: 200px;" placeholder="请输入商品名称"/>
        </el-form-item>
        <el-form-item>
          <el-button @click="getDataList()">查询</el-button>
          <el-button v-if="isAuth('goods:createGoods')" type="primary" icon="el-icon-edit" @click="addOrUpdate()">添加
          </el-button>
          <el-button :loading="downloadLoading" class="filter-item" type="primary" icon="el-icon-download"
                     @click="handleDownload()">导出
          </el-button>
        </el-form-item>
      </el-form>
      <!-- 查询结果 -->
      <el-table v-loading="dataListLoading" :data="dataList" size="small" border fit
                highlight-current-row>

        <el-table-column type="expand">
          <template slot-scope="props">
            <el-form label-position="left" class="table-expand">
              <el-form-item label="类目ID">
                <span>{{ props.row.categoryId }}</span>
              </el-form-item>
              <el-form-item label="品牌商ID">
                <span>{{ props.row.brandId }}</span>
              </el-form-item>
            </el-form>
          </template>
        </el-table-column>

        <el-table-column align="center" min-width="100" label="名称" prop="name"/>

        <el-table-column align="center" property="pictureUrl" label="图片">
          <template slot-scope="scope">
            <img :src="scope.row.pictureUrl" width="40">
          </template>
        </el-table-column>

        <el-table-column align="center" label="详情" prop="description">
          <template slot-scope="scope">
            <el-dialog :visible.sync="detailDialogVisible" title="商品详情">
              <div v-html="goodsDetail"/>
            </el-dialog>
            <el-button size="mini" @click="showDetail(scope.row.description)">查看</el-button>
          </template>
        </el-table-column>

        <el-table-column align="center" label="当前价格" prop="price"/>
        <el-table-column align="center" label="库存" prop="inventory"/>
        <el-table-column align="center" label="剩余库存" prop="remainingInventory"/>

        <el-table-column
          prop="createTime"
          header-align="center"
          align="center"
          width="180"
          :formatter="dateFormat"
          label="创建时间">
        </el-table-column>

        <!--<el-table-column align="center" label="是否新品" prop="isNew">
          <template slot-scope="scope">
            <el-tag :type="scope.row.isNew ? 'success' : 'error' ">{{ scope.row.isNew ? '新品' : '非新品' }}</el-tag>
          </template>
        </el-table-column>-->

        <el-table-column align="center" label="状态" prop="statusDesc">
          <template slot-scope="scope">
            <el-tag :type="scope.row.commonStatus == 0 ? 'success' : 'error' ">{{ scope.row.statusDesc }}</el-tag>
          </template>
        </el-table-column>

        <el-table-column align="center" label="操作" width="200" class-name="small-padding fixed-width">
          <template slot-scope="scope">
            <el-button v-if="isAuth('goods:updateGoods')" type="primary" size="mini" @click="addOrUpdate(scope.row.id)">编辑</el-button>
            <el-button v-if="isAuth('goods:deleteGoods')" type="danger" size="mini" @click="deleteHandle(scope.row.id)">删除</el-button>
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

    <create v-show="!visible" ref="addOrUpdate" @refreshDataList="getDataList"></create>

  </div>
</template>

<style>
  .table-expand {
    font-size: 0;
  }

  .table-expand label {
    width: 100px;
    color: #99a9bf;
  }

  .table-expand .el-form-item {
    margin-right: 0;
    margin-bottom: 0;
  }

  .gallery {
    width: 80px;
    margin-right: 10px;
  }
</style>

<script>
  import Create from './create.vue';
  import {dateFormat} from '@/utils';

  export default {
    components: {Create},
    data() {
      return {
        visible: true,
        downloadLoading: false,
        dataListLoading: false,
        detailDialogVisible: false,
        dataList: [],
        pageIndex: 1,
        pageSize: 10,
        totalPage: 0,
        listQuery: {
          name: null,
        },
        goodsDetail: '',
      }
    },
    created() {
      this.getDataList()
    },
    methods: {
      addOrUpdate(id) {
        this.visible = false;
        this.$nextTick(() => {
          this.$refs.addOrUpdate.init(id);
        })
//        this.$router.push({path: '/admin/goods/create'});
      },
      getDataList() {
        this.visible = true;
        this.dataListLoading = true;
        this.$http({
          url: this.$http.adornUrl('goods/queryGoods'),
          method: 'post',
          data: this.$http.adornData({
            'pageNo': this.pageIndex,
            'pageSize': this.pageSize,
            'isDeleted': false,
            'orderBy': 'id',
            'nameLike': this.listQuery.name
          })
        }).then(({data}) => {
          if (data && data.code === 200) {
            this.dataList = data.dataMap.records;
            this.totalPage = data.dataMap.totalRecords;
          } else {
            this.dataList = []
            this.totalPage = 0
          }
          this.dataListLoading = false;
        })
      },
      showDetail(detail) {
        this.goodsDetail = detail
        this.detailDialogVisible = true
      },
      handleDownload() {
        this.downloadLoading = true;
        import('@/utils/exportExcel').then(excel => {
          const tHeader = ['商品ID', '名称', '当前价格', '商品图片', '类目ID', '品牌商ID']
          const filterVal = ['id', 'name', 'price', 'pictureUrl', 'categoryId', 'brandId']
          excel.export_json_to_excel2(tHeader, this.dataList, filterVal, '商品信息')
          this.downloadLoading = false
        })
      },
      // 删除
      deleteHandle(id) {
        this.$confirm(`确定要进行删除操作?`, '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$http({
            url: this.$http.adornUrl('goods/deleteGoods/' + id),
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
      // 每页数
      sizeChangeHandle(val) {
        this.pageSize = val;
        this.pageIndex = 1;
        this.getDataList();
      },
      // 当前页
      currentChangeHandle(val) {
        this.pageIndex = val;
        this.getDataList();
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
