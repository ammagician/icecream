import request from '@/utils/request'

export function queryList(params) {
  return request('/api/sysmgr/user/page', {
    params
  })
}

export function save(data) {
  const m = data.id ? 'edit' : 'add'
  return request(`/api/sysmgr/user/${m}`, {
    method: data.id ? 'put' : 'post',
    data
  })
}

export function del(data) {
  return request('/api/sysmgr/user/del', {
    method: 'delete',
    params: data
  })
}

export function queryDetail(id) {
  return request(`/api/sysmgr/user/detail`, {
    params: {
      id
    }
  })
}

export function toggle(data) {
  return request(`/api/sysmgr/user/toggle`, {
    method: 'put',
    data
  })
}

export function resetPwd(data) {
  return request(`/api/sysmgr/user/updatePwd`, {
    method: 'put',
    data
  })
}
