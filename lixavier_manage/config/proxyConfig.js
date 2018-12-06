
module.exports = {
  '/api': {
    // 测试环境
    target: 'http://localhost:40002',  // 接口域名
    changeOrigin: true,  //是否跨域
    pathRewrite: {
      '^/api': ''   //需要rewrite重写的,
    }
  }
}
