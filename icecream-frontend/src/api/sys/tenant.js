import request from '@/utils/request';

export function queryAll(param) {
  return request('/api/sysmgr/tenant/list', {
    method: 'post',
    data: param || {},
  });
}

export function queryList(param) {
  return request('/api/sysmgr/tenant/page', {
    params: param,
  });
}

export function save(data) {
  const m = data.id ? 'edit' : 'add';
  return request(`/api/sysmgr/tenant/${m}`, {
    method: data.id ? 'put' : 'post',
    data,
  });
}

export function del(data) {
  return request('/api/sysmgr/tenant/del', {
    method: 'delete',
    params: data,
  });
}

export function resetPwd(data) {
  return request(`/api/sysmgr/tenant/resetPwd`, {
    method: 'post',
    data,
  });
}
