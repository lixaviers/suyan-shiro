<template>
  <div>

    <el-card class="box-card">
      <h3>商品介绍</h3>
      <el-form ref="dataForm" :rules="rules" :model="dataForm" label-width="150px"
               @keyup.enter.native="dataFormSubmit()">
        <el-form-item label="商品名称" prop="name">
          <el-input v-model="dataForm.name"/>
        </el-form-item>
        <el-form-item label="当前价格" prop="price">
          <el-input-number v-model="dataForm.price" :precision="2" :min="0.01" :step="1" :max="100000000">
          </el-input-number>
          元
        </el-form-item>
        <el-form-item label="库存" prop="inventory">
          <el-input-number v-model="dataForm.inventory" :precision="2" :min="0.01" :step="1" :max="100000000">
          </el-input-number>
        </el-form-item>

        <el-form-item label="商品图片">
          <el-upload
            :action="uploadPath"
            :show-file-list="false"
            :on-success="uploadPicUrl"
            class="avatar-uploader"
            list-type="picture-card"
            accept=".jpg,.jpeg,.png,.gif">
            <img v-if="dataForm.pictureUrl" :src="dataForm.pictureUrl" class="avatar">
            <i v-else class="el-icon-plus avatar-uploader-icon"/>
          </el-upload>
        </el-form-item>

        <el-form-item label="所属分类">
          <el-cascader :options="categoryList" :props="listProps" expand-trigger="hover" v-model="categoryIds"
                       @change="handleCategoryChange"/>
        </el-form-item>

        <el-form-item label="所属品牌商">
          <el-select v-model="dataForm.brandId">
            <el-option v-for="item in brandList" :key="item.id" :label="item.name" :value="item.id"/>
          </el-select>
        </el-form-item>

        <el-form-item label="商品详细介绍">
          <editor :init="editorInit" v-model="dataForm.description"/>
        </el-form-item>
      </el-form>
    </el-card>


    <div class="mt20">
      <el-button type="primary" :loading="loading" @click="dataFormSubmit">发布</el-button>
      <el-button @click="$emit('refreshDataList')">取消</el-button>
    </div>

  </div>
</template>

<style>

  .el-tag + .el-tag {
    margin-left: 10px;
  }

  .input-new-keyword {
    width: 90px;
    margin-left: 10px;
    vertical-align: bottom;
  }

  .avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }

  .avatar-uploader .el-upload:hover {
    border-color: #20a0ff;
  }

  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 120px;
    height: 120px;
    line-height: 120px;
    text-align: center;
  }

  .avatar {
    width: 120px;
    height: 120px;
    display: block;
  }
</style>

<script>
  import Editor from '@tinymce/tinymce-vue';
  import {createStorage} from '@/api/storage'

  export default {
    name: 'AdminGoodsCreate',
    components: {Editor},
    data() {
      return {
        categoryIds: [],
        loading: false,
        visible: false,
        listProps: {
          value: 'id',
          label: 'name',
        },
        dataForm: {
          id: null,
          name: '',
          price: '1',
          inventory: '1',
          pictureUrl: '',
          categoryId: null,
          brandId: null,
          description: null,
        },
        uploadPath: this.$http.adornUrl(`storage/create`),
        categoryList: [],
        brandList: [],
        rules: {
          name: [{required: true, message: '名称不能为空', trigger: 'blur'}],
          price: [{required: true, message: '价格不能为空', trigger: 'blur'}],
          inventory: [{required: true, message: '库存不能为空', trigger: 'blur'}],
        },
        editorInit: {
          language: 'zh_CN',
          convert_urls: false,
          height: '300px',
          plugins: ['advlist anchor autolink autosave code codesample colorpicker colorpicker contextmenu directionality emoticons fullscreen hr image imagetools importcss insertdatetime link lists media nonbreaking noneditable pagebreak paste preview print save searchreplace spellchecker tabfocus table template textcolor textpattern visualblocks visualchars wordcount'],
          toolbar: ['searchreplace bold italic underline strikethrough alignleft aligncenter alignright outdent indent  blockquote undo redo removeformat subscript superscript code codesample', 'hr bullist numlist link image charmap preview anchor pagebreak insertdatetime media table emoticons forecolor backcolor fullscreen'],
          images_upload_handler: function (blobInfo, success, failure) {
            const formData = new FormData()
            formData.append('file', blobInfo.blob());

            createStorage(formData).then(({data}) => {
              if (data && data.code === 200) {
                success(data.dataMap);
              } else {
                if (data.code != 403) {
                  failure('上传失败，请重新上传');
                }
              }
            });
          }
        }
      }
    },

    methods: {
      init: function (id) {
        let t = this;
        t.dataForm.id = id || null;
        t.$nextTick(() => {
          t.$refs['dataForm'].resetFields();
          t.dataForm.pictureUrl = '';
          t.dataForm.description = '';
        })

        t.$http({
          url: t.$http.adornUrl('category/getCategoryList'),
          method: 'get',
        }).then(({data}) => {
          if (data && data.code === 200) {
            t.categoryList = data.dataMap;
          } else {
            t.categoryList = [];
          }
        }).then(function () {
          t.$http({
            url: t.$http.adornUrl('brand/getBrandList'),
            method: 'get',
          }).then(({data}) => {
            if (data && data.code === 200) {
              t.brandList = data.dataMap;
            } else {
              t.brandList = [];
            }
          });
        }).then(() => {
          if (t.dataForm.id) {
            t.$http({
              url: t.$http.adornUrl(`goods/getGoods/${this.dataForm.id}`),
              method: 'get',
              params: t.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 200) {
                this.dataForm = data.dataMap;
                this.categoryIds = data.dataMap.categoryIds;
              }
            })
          }
        });
      },
      handleCategoryChange(value) {
        this.dataForm.categoryId = value[value.length - 1]
      },
      // 表单提交
      dataFormSubmit() {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            this.loading = true;
            this.$http({
              url: this.$http.adornUrl(`goods/${!this.dataForm.id ? 'createGoods' : 'updateGoods'}`),
              method: 'post',
              data: this.dataForm
            }).then(({data}) => {
              if (data && data.code === 200) {
                this.$message({
                  message: '操作成功',
                  type: 'success',
                  duration: 1500,
                  onClose: () => {
//                    this.$router.push({path: '/admin/goods/index'})
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
      },
      uploadPicUrl: function (response) {
        this.dataForm.pictureUrl = response.dataMap;
      },
    }
  }
</script>
