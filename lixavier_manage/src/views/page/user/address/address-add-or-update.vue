<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()"
             label-width="100px">
      <el-form-item label="地址信息" prop="addressInfo">
        <el-cascader
          style="width: 300px;"
          v-model="dataForm.addressInfo"
          :options="options"
          :props="props"
          @change="handleChange">
        </el-cascader>
      </el-form-item>
      <el-form-item label="详细地址" prop="address">
        <el-input v-model="dataForm.address" placeholder="详细地址"></el-input>
      </el-form-item>
      <el-form-item label="收货人姓名" prop="consignee">
        <el-input v-model="dataForm.consignee" placeholder="收货人姓名"></el-input>
      </el-form-item>
      <el-form-item label="手机号码" prop="mobile">
        <el-input v-model="dataForm.mobile" placeholder="手机号码"></el-input>
      </el-form-item>
      <el-form-item label="默认收货地址" prop="isDefault">
        <el-switch
          v-model="dataForm.isDefault">
        </el-switch>
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
    data() {
      return {
        visible: false,
        loading: false,
        options: [],
        props: {
          value: 'id',
          label: 'name',
          children: 'children',
        },
        dataForm: {
          id: null,
          provinceId: null,
          cityId: null,
          areaId: null,
          address: '',
          consignee: '',
          mobile: '',
          addressInfo: null,
          isDefault: false,
        },
        dataRule: {
          addressInfo: [
            {required: true, message: '地址信息不能为空', trigger: 'blur'}
          ],
          address: [
            {required: true, message: '详细地址不能为空', trigger: 'blur'}
          ],
          consignee: [
            {required: true, message: '收货人姓名不能为空', trigger: 'blur'}
          ],
          mobile: [
            {required: true, message: '手机号码不能为空', trigger: 'blur'}
          ],
        },
      }
    },
    methods: {
      init(id) {
        this.dataForm.id = id || null;
        this.loading = false;
        this.$http({
          url: this.$http.adornUrl('address/getAddressList'),
          method: 'get',
        }).then(({data}) => {
          if (data && data.code === 200) {
            this.options = data.dataMap;
          } else {
            this.options = [];
          }
        }).then(() => {
          this.visible = true
          this.$nextTick(() => {
            this.$refs['dataForm'].resetFields();
          })
        }).then(() => {
          if (this.dataForm.id) {
            this.$http({
              url: this.$http.adornUrl(`userAddress/getUserAddress/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 200) {
                this.dataForm = data.dataMap;
                this.dataForm.addressInfo = [data.dataMap.provinceId, data.dataMap.cityId, data.dataMap.areaId];
              }
            })
          }
        })
      },
      handleChange(value) {
        this.dataForm.provinceId = value[0];
        this.dataForm.cityId = value[1];
        this.dataForm.areaId = value[2];
      },
      // 表单提交
      dataFormSubmit() {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            this.loading = true;
            this.$http({
              url: this.$http.adornUrl(`userAddress/${!this.dataForm.id ? 'createUserAddress' : 'updateUserAddress'}`),
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
