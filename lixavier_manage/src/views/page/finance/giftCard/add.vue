<template>
  <el-dialog
    title="激活礼品卡"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()"
             label-width="120px">
      <el-form-item label="礼品券码" prop="cardCode">
        <el-input v-model="dataForm.id" style="width: 320px;" :maxlength="30" placeholder="请输入礼品券号码"></el-input>
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
        },
        dataRule: {
          id: [
            {required: true, message: '礼品券码不能为空', trigger: 'blur'}
          ],
        },
      }
    },
    methods: {
      init () {
        this.visible = true
        this.$nextTick(() => {
          this.dataForm.id = null
        });
      },
      // 表单提交
      dataFormSubmit() {
        this.$refs['dataForm'].validate((valid) => {

          if (valid) {
            this.loading = true;
            this.$http({
              url: this.$http.adornUrl(`giftCard/createUserGiftCard`),
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
                    this.$emit('refreshUserGiftCardDataList')
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
