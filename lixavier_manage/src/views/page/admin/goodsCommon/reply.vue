<template>
  <el-dialog
    title="评论商品"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()"
             label-width="80px">
      <el-form-item label="内容" prop="replyContent">
        <el-input type="textarea" v-model="dataForm.replyContent" rows="4"></el-input>
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
        visible: false,
        loading: false,
        dataForm: {
          id: null,
          replyContent: '',
        },
        dataRule: {
          replyContent: [
            {required: true, message: '内容不能为空', trigger: 'blur'}
          ],
        },
      }
    },
    methods: {
      init (id) {
        this.dataForm.id = id;
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
        })
      },
      // 表单提交
      dataFormSubmit () {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            this.loading = true;
            this.$http({
              url: this.$http.adornUrl(`goodsComment/replyGoodsComment`),
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
