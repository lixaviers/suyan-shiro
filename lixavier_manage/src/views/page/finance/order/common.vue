<template>
  <el-dialog
    title="评论商品"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()"
             label-width="80px">
      <el-form-item label="内容" prop="content">
        <el-input type="textarea" v-model="dataForm.content" rows="4"></el-input>
      </el-form-item>

      <el-form-item label="图片">
        <el-upload
          :action="uploadPath"
          :limit="5"
          :on-exceed="uploadOverrun"
          :on-success="uploadPicUrl"
          :on-remove="handleRemove"
          class="avatar-uploader"
          list-type="picture-card"
          accept=".jpg,.jpeg,.png,.gif">
          <i class="el-icon-plus"/>
        </el-upload>
      </el-form-item>

      <el-form-item label="是否匿名">
        <el-switch v-model="dataForm.isAnonymous"></el-switch>
      </el-form-item>

    </el-form>

    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button :loading="loading" type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
  </el-dialog>
</template>

<script>
  import {treeDataTranslate} from '@/utils'
  export default {
    data () {
      return {
        uploadPath: this.$http.adornUrl(`storage/create`),
        visible: false,
        loading: false,
        dataForm: {
          orderGoodsId: null,
          content: '',
          urls: [],
          isAnonymous: true,
        },
        dataRule: {
          content: [
            {required: true, message: '内容不能为空', trigger: 'blur'}
          ],
        },
      }
    },
    methods: {
      init (id) {
        this.dataForm.orderGoodsId = id;
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
        })
      },
      uploadPicUrl: function (response) {
        if (response && response.code === 200) {
          this.dataForm.urls.push(response.dataMap)
        }
      },
      uploadOverrun: function () {
        this.$message({
          type: 'error',
          message: '上传文件个数超出限制!最多上传5张图片!'
        })
      },
      handleRemove: function (file, fileList) {
        for (var i = 0; i < this.dataForm.urls.length; i++) {
          var url
          if (file.response === undefined) {
            url = file.url
          } else {
            url = file.response.dataMap
          }
          if (this.dataForm.urls[i] === url) {
            this.dataForm.urls.splice(i, 1)
          }
        }
      },
      // 表单提交
      dataFormSubmit () {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            this.loading = true;
            this.$http({
              url: this.$http.adornUrl(`goodsComment/createGoodsComment`),
              method: 'post',
              data: this.dataForm
            }).then(({data}) => {
              if (data && data.code === 200) {
                this.$message({
                  message: '操作成功',
                  type: 'success',
                  duration: 1500,
                  onClose: () => {
                    this.visible = false
                    this.$emit('refreshDataList')
                    this.loading = false;
                  }
                })
              } else {
                this.loading = false;
              }
            })
          }
        })
      }
    }
  }
</script>
