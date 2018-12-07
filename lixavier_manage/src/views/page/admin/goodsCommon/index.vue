<template>
  <div class="mod-role">
    <el-form :inline="true" :model="dataForm" @keyup.enter.native="getDataList()">
      <el-form-item>
        <el-input v-model="dataForm.userId" placeholder="用户id" style="width: 240px;"></el-input>
      </el-form-item>
      <el-form-item>
        <el-input v-model="dataForm.goodsId" placeholder="商品id" style="width: 240px;"></el-input>
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
      <el-table-column type="index" header-align="center" align="center" width="80" label="编号"></el-table-column>

      <el-table-column prop="goodsId" header-align="center" align="center" label="商品id" width="150"></el-table-column>

      <el-table-column prop="isAnonymous" header-align="center" align="center" label="是否匿名" width="120">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.isAnonymous == true" type="success">是</el-tag>
          <el-tag v-else type="danger">否</el-tag>
        </template>
      </el-table-column>

      <el-table-column prop="content" header-align="center" align="center" label="评论内容"></el-table-column>

      <el-table-column align="center" label="评论图片" prop="urls">
        <template slot-scope="scope">
          <img v-for="item in scope.row.urls" :key="item" :src="item" width="40" style="margin-right: 5px;">
        </template>
      </el-table-column>

      <el-table-column prop="isReply" header-align="center" align="center" label="是否回复" width="120">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.isReply == true" type="success">是</el-tag>
          <el-tag v-else type="danger">否</el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="replyContent" header-align="center" align="center" label="回复内容"
                       width="150"></el-table-column>


      <el-table-column
        prop="createTime"
        header-align="center"
        align="center"
        :formatter="dateFormat"
        width="180"
        label="创建时间">
      </el-table-column>

      <el-table-column fixed="right" header-align="center" align="center" width="120" label="操作">
        <template slot-scope="scope">
          <el-button v-if="scope.row.isReply == false" type="text" size="small" @click="replyHandle(scope.row.id)">
            回复
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

    <reply-goods v-if="replyVisible" ref="replyGoods" @refreshDataList="getDataList"></reply-goods>


  </div>
</template>

<script>
  import ReplyGoods from './reply';
  import {dateFormat} from '@/utils';
  export default {
    components: {
      ReplyGoods
    },
    data () {
      return {
        replyVisible: false,
        transactionTypeList: [],
        channelList: [],
        dataForm: {
          goodsId: '',
          userId: '',
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
      replyHandle(id){
        this.replyVisible = true;
        this.$nextTick(() => {
          this.$refs.replyGoods.init(id);
        })
      },
      // 获取数据列表
      getDataList () {
        let t = this;
        t.dataListLoading = true;

        t.$http({
          url: t.$http.adornUrl('goodsComment/queryGoodsComment'),
          method: 'post',
          data: t.$http.adornData({
            'pageNo': t.pageIndex,
            'pageSize': t.pageSize,
            'userId': t.dataForm.userId,
            'goodsId': t.dataForm.goodsId,
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
