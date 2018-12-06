<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()"
             label-width="100px">
      <el-form-item label="金额" prop="balance">
        <el-input-number v-model="dataForm.balance" :precision="2" :min="0.01" :step="1" :max="100000000">
        </el-input-number>
      </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button :loading="loading" type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
  </el-dialog>
</template>

<script>
  import {isEmail, isMobile} from '@/utils/validate'
  export default {
    data () {
      return {
        visible: false,
        loading: false,
        dataForm: {
          id: 0,
          balance: 1,
        },
        dataRule: {
          balance: [
            {required: true, message: '金额不能为空', trigger: 'blur'}
          ],
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.id = id;
        this.visible = true;
        this.dataForm.balance = 1;
      },
      // 表单提交
      dataFormSubmit () {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            this.loading = true;
            this.$http({
              url: this.$http.adornUrl(`user/addBalance`),
              method: 'post',
              data: this.dataForm
            }).then(({data}) => {
              if (data && data.code === 200) {
                this.$message({
                  message: '操作成功',
                  type: 'success',
                  duration: 1500,
                  onClose: () => {
                    this.visible = false;
                    this.$emit('refreshDataList');
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
