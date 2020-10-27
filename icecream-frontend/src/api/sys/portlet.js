import request from '@/utils/request';

export function group() {
  return request('/api/sysmgr/portlet/group');
}

export function queryList(param) {
  return request('/api/sysmgr/portlet/page', {
    params: param,
  });
}

export function save(data) {
  const m = data.id ? 'edit' : 'add';
  return request(`/api/sysmgr/portlet/${m}`, {
    method: data.id ? 'put' : 'post',
    data,
  });
}

export function del(data) {
  return request('/api/sysmgr/portlet/del', {
    method: 'delete',
    params: data,
  });
}
