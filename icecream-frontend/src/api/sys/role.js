import request from '@/utils/request';

export function queryList(param) {
  return request('/api/sysmgr/role/page', {
    params: param,
  });
}

export function queryAll() {
  return request('/api/sysmgr/role/list');
}

export function save(data) {
  const m = data.id ? 'edit' : 'add';
  return request(`/api/sysmgr/role/${m}`, {
    method: data.id ? 'put' : 'post',
    data,
  });
}

export function del(data) {
  return request('/api/sysmgr/role/del', {
    method: 'delete',
    params: data,
  });
}

export function queryRolePermList(roleId, portalId) {
  return request(`/api/sysmgr/role/permision`, {
    params: {
      roleId,
      portalId
    }
  });
}

export function updatePermision(data) {
  return request('/api/sysmgr/role/permision/edit', {
    method: 'put',
    data,
  });
}
