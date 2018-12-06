<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()"
             label-width="120px">
      <el-form-item label="价格" prop="price">
        <el-input-number v-model="dataForm.price" :precision="2" :min="0.01" :step="1" :max="100000000"></el-input-number>
      </el-form-item>
      <el-form-item label="失效时间" prop="endTime">
        <el-date-picker v-model="dataForm.endTime" type="datetime" placeholder="选择日期时间"></el-date-picker>
      </el-form-item>
      <el-form-item label="张数" prop="count">
        <el-input-number v-model="dataForm.count" controls-position="right" :min="1" :max="10000" label="张数"></el-input-number>
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
          price: 1,
          endTime: null,
          count: 1,
        },
        dataRule: {
          price: [
            {required: true, message: '价格不能为空', trigger: 'blur'}
          ],
          endTime: [
            {required: true, message: '失效时间不能为空', trigger: 'blur'}
          ],
          count: [
            {required: true, message: '张数不能为空', trigger: 'blur'}
          ],
        },
      }
    },
    methods: {
      init (id) {
        this.dataForm.id = id || null;

        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
        });
        if (this.dataForm.id) {
          this.$http({
            url: this.$http.adornUrl(`giftCard/getGiftcard/${this.dataForm.id}`),
            method: 'get',
            params: this.$http.adornParams()
          }).then(({data}) => {
            if (data && data.code === 200) {
              this.dataForm.roleName = data.dataMap.roleName;
              this.dataForm.remark = data.dataMap.remark;
            }
          })
        }
      },
      // 表单提交
      dataFormSubmit() {
        this.$refs['dataForm'].validate((valid) => {

          if (valid) {
            this.loading = true;
            this.$http({
              url: this.$http.adornUrl(`giftCard/${!this.dataForm.id ? 'createGiftCard' : 'updateGiftCard'}`),
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
                    this.$emit('refreshGiftCardDataList');
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
