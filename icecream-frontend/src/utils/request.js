import axios from 'axios'
import { MessageBox, Message } from 'element-ui'
import store from '@/store'
import { getToken } from '@/utils/auth'

const service = axios.create({
  baseURL: process.env.VUE_APP_BASE_API, // url = base url + request url
  // withCredentials: true, // send cookies when cross-domain requests
  timeout: 30000 // request timeout
})

// request interceptor
service.interceptors.request.use(
  config => {
    if (store.getters.token) {
      config.headers['X-Token'] = getToken()
    }
    return config
  },
  error => {
    console.log(error) // for debug
    return Promise.reject(error)
  }
)

service.interceptors.response.use(
  response => {
    const res = response.data
    if (res.code !== 200) {
      Message({
        message: res.message || 'Error',
        type: 'error',
        duration: 5 * 1000
      })
      return Promise.reject(new Error(res.message || 'Error'))
    } else {
      return res
    }
  },
  error => {
    const res = error.response
    if (res.status === 401 || res.status === 403) {
      relogin()
    } else if (res.status === 500) {
      Message({
        message: '服务器内部异常',
        type: 'error',
        duration: 5 * 1000
      })
    }

    return Promise.reject(error)
  }
)

const relogin = function() {
  // MessageBox.confirm(
  //   '你已经登出，你可以取消留在本页面或者重新登录',
  //   '确认登出',
  //   {
  //     confirmButtonText: '重新登录',
  //     cancelButtonText: '取消',
  //     type: 'warning'
  //   }
  // ).then(() => {
  //   store.dispatch('user/resetToken').then(() => {
  //     location.reload()
  //   })
  // })
  store.dispatch('user/resetToken').then(() => {
    location.reload()
  })
}

export default service
