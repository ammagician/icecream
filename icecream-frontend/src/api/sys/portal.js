import request from '@/utils/request';

export function queryAll(param) {
  return request('/api/sysmgr/portal/list', {
    method: 'post',
    data: param || {},
  });
}

export function queryAllByTenant(param) {
  return request('/api/sysmgr/portal/listByTenant', {
    method: 'post',
    data: param || {},
  });
}

export function queryList(param) {
  return request('/api/sysmgr/portal/page', {
    params: param,
  });
}

export function save(data) {
  const m = data.id ? 'edit' : 'add';
  return request(`/api/sysmgr/portal/${m}`, {
    method: data.id ? 'put' : 'post',
    data,
  });
}

export function del(data) {
  return request('/api/sysmgr/portal/del', {
    method: 'delete',
    params: data,
  });
}
