<template>
  <div class="mod-role">
    <el-row style="margin-bottom: 20px;">
      <el-button v-model="typeId" :autofocus="true" @click="changeType(null)">全部类型</el-button>
      <el-button @click="changeType(item.value)" :value="item.value" v-for="item in typeList" :key="item.value">
        {{item.desc}}<label v-show="item.count > 0">({{item.count }})</label>
      </el-button>
    </el-row>
    <el-row style="margin-bottom: 20px;">
      <el-button v-if="isAuth('userLetter:read:api')" @click="readHandle()" :disabled="dataListSelections.length <= 0">
        标记已读
      </el-button>
      <el-button v-if="isAuth('userLetter:deleteUserLetter:api')" @click="deleteHandle()" :disabled="dataListSelections.length <= 0">
        删除
      </el-button>
    </el-row>

    <el-table
      @expand-change="change"
      :data="dataList"
      border
      v-loading="dataListLoading"
      :row-style="rowStyle"
      @selection-change="selectionChangeHandle"
      style="width: 100%;">
      <el-table-column
        type="selection"
        header-align="center"
        align="center"
        width="50">
      </el-table-column>
      <el-table-column
        prop="title"
        label="标题">
      </el-table-column>
      <el-table-column
        prop="typeDesc"
        header-align="center"
        align="center"
        width="180"
        label="类型">
      </el-table-column>
      <el-table-column
        prop="createTime"
        header-align="center"
        align="center"
        :formatter="dateFormat"
        width="180"
        label="接收时间">
      </el-table-column>
      <el-table-column type="expand">
        <template slot-scope="props">
          {{props.row.content}}
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
        typeId: null,
        typeList: [],
        dataList: [],
        pageIndex: 1,
        pageSize: 10,
        totalPage: 0,
        dataListLoading: false,
        dataListSelections: [],
      }
    },
    activated () {
      this.getTypeList();
    },
    methods: {
      changeType(type){
        this.typeId = type;
        this.getDataList();
      },
      rowStyle(obj){
        if (obj.row.isRead) {
          return {"color": "#ccc"};
        }
      },
      // 删除
      deleteHandle () {
        var ids = this.dataListSelections.map(item => {
          return item.id
        })
        this.$confirm(`确定要对选中站内信进行删除操作吗?`, '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$http({
            url: this.$http.adornUrl('userLetter/deleteUserLetter'),
            method: 'post',
            data: this.$http.adornData(ids, false)
          }).then(({data}) => {
            if (data && data.code === 200) {
              this.getDataList()
            }
          })
        })
      },
      readHandle() {
        this.$confirm(`确定要对选中站内信设置已读吗？`, '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          var ids = this.dataListSelections.map(item => {
            return item.id
          });
          this.$http({
            url: this.$http.adornUrl('userLetter/read'),
            method: 'post',
            data: ids
          }).then(({data}) => {
            if (data && data.code === 200) {
              this.getDataList();
            }
          });
        });
      },
      change(row, expandedRows) {
        if (!row.isRead) {
          this.$http({
            url: this.$http.adornUrl('userLetter/read'),
            method: 'post',
            data: [row.id]
          }).then(({data}) => {
            if (data && data.code === 200) {
              row.isRead = true;
            }
          });
        }
      },
      getTypeList(){
        this.dataListLoading = true;
        this.$http({
          url: this.$http.adornUrl('userLetter/getLetterTypeCount'),
          method: 'get',
        }).then(({data}) => {
          if (data && data.code === 200) {
            this.typeList = data.dataMap;
            this.getDataList();
          } else {
            this.typeList = []
          }
        });
      },
      // 获取数据列表
      getDataList () {

        this.$http({
          url: this.$http.adornUrl('userLetter/queryUserLetter'),
          method: 'post',
          data: this.$http.adornData({
            'pageNo': this.pageIndex,
            'pageSize': this.pageSize,
            'type': this.typeId,
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
      // 多选
      selectionChangeHandle (val) {
        this.dataListSelections = val
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
