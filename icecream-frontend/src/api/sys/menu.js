import request from '@/utils/request'

export function queryList(param) {
  return request('/api/sysmgr/menu/tree', {
    params: param
  })
}

export function queryPermissionTree(portalId) {
  return request('/api/sysmgr/menu/permissionTree', {
    params: {
      portalId
    }
  })
}

export function save(data) {
  const m = data.id ? 'edit' : 'add'
  return request(`/api/sysmgr/menu/${m}`, {
    method: data.id ? 'put' : 'post',
    data
  })
}

export function del(data) {
  return request('/api/sysmgr/menu/del', {
    method: 'delete',
    params: data
  })
}
