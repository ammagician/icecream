import request from '@/utils/request'

export function login(data) {
  return request({
    url: '/api/auth/allow/login',
    method: 'post',
    data
  })
}

export function getInfo(token) {
  return request({
    url: '/api/auth/resource/userUI',
    method: 'get',
    params: { token }
  })
}

export function getSetting(id) {
  return request({
    url: '/api/sysmgr/user/setting',
    method: 'get'
  })
}

export function saveSetting(data) {
  return request({
    url: '/api/sysmgr/user/setting',
    method: 'put',
    data
  })
}

export function logout() {
  return request({
    url: '/api/auth/allow/logout',
    method: 'post'
  })
}
