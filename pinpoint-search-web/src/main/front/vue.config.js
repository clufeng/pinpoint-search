
const path = require('path')

function resolve (dir) {
  return path.join(__dirname, dir)
}

module.exports = {

  outputDir: 'dist',
  assetsDir: 'static',
  publicPath: './',
  devServer: {
    proxy: {
      '/api': {
        target: 'http://localhost:9090/pinpoint-search',
        ws: true,
        changeOrigin: true,
        pathRewrite: {
          '^/api': 'api'
        }
      }
    }
  },
  configureWebpack: {
    name: 'pinpoint-search',
    resolve: {
      alias: {
        '@': resolve('src')
      }
    }
  }
}
