import axios from 'axios'

const service = axios.create({
  baseURL: process.env.VUE_APP_BASE_API,
  timeout: 10000
})

service.interceptors.request.use(
  config => {
    config.headers['X-Token'] = ''
    return config
  }
)

service.interceptors.response.use(

  response => {
    if (response.status === 401) {
      return Promise.reject(new Error('权限错误'))
    }

    if (response.status >= 500) {
      return Promise.reject(new Error('系统错误'))
    }

    const res = response.data

    if (res.code !== 200) {
      return Promise.reject(new Error(res.message))
    }

    return res.data.datas
  }

)

export default service
