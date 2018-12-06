<template>
  <div>
    <el-row>
      <div class="account_info">
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span>账户信息</span>
          </div>
          <el-row>
            <el-col :span="14">
              <div class="account">
                <p class="title">账户余额（元）</p>
                <p class="value">{{balance}}</p>
                <router-link class="pay" to="/about">去充值</router-link>
              </div>
            </el-col>
            <el-col :span="10">
              <el-row>
                <router-link class="gift_card" to="/about">礼品卡<span class="value">{{giftCardCount}}</span>张</router-link>
              </el-row>
              <el-row>
                <router-link class="gift_card" to="/about">优惠券<span class="value">0</span>张</router-link>
              </el-row>
              <el-row>
                <router-link class="gift_card" to="/about">代金券<span class="value">0</span>张</router-link>
              </el-row>
            </el-col>
          </el-row>
        </el-card>
      </div>

    </el-row>
  </div>
</template>


<style lang="scss">
  .el-card__header {
    padding: 10px 20px;
  }

  .account_info {
    float: left;
    width: 420px;
    .account {
      background-color: #f5f7fa;
      margin: 5px 10px 0;
      text-align: center;
      .title {
        font-size: 14px;
        padding-top: 11px;
      }
      .value {
        font-size: 26px;
        color: #c0c4cc;
        margin-top: 12px;
      }
      .pay {
        display: inline-block;
        width: 100%;
        background: #17b3a3;
        margin-top: 10px;
        padding: 15px 10px;
        font-size: 13px;
      }
    }
  }

  .gift_card {
    display: block;
    color: #303133;
    font-size: 13px;
    background-color: #f5f7fa;
    padding: 10px;
    margin: 5px 0;
    .value {
      padding: 0 5px;
      color: #a9acb3;
    }

  }
</style>

<script>
  export default {
    data () {
      return {
        balance: '0.00',
        giftCardCount: 0,
      }
    },
    created () {
      this.getUserInfo()
    },
    methods: {
      // 获取当前用户信息
      getUserInfo () {
        let t = this;
        t.$http({
          url: t.$http.adornUrl(`user/getUserInfo`),
          method: 'get',
          params: t.$http.adornParams()
        }).then(({data}) => {
          if (data && data.code === 200) {
            t.balance = data.dataMap.balance;
          }
        }).then(function () {
          t.$http({
            url: t.$http.adornUrl('giftCard/queryUserGiftCards'),
            method: 'post',
            data: t.$http.adornData({
              'pageNo': 1,
              'pageSize': 10,
              'status': 0,
            })
          }).then(({data}) => {
            if (data && data.code === 200) {
              t.giftCardCount = data.dataMap.totalRecords;
            } else {
              t.giftCardCount = 0;
            }
          })
        })

      }
    }
  }
</script>


