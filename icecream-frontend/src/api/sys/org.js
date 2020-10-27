import request from '@/utils/request'

export function queryList() {
  return request('/api/sysmgr/org/list');
}

export function save(data) {
  const m = data.id ? 'edit' : 'add';
  return request(`/api/sysmgr/org/${m}`, {
    method: data.id ? 'put' : 'post',
    data,
  });
}

export function del(ids) {
  return request('/api/sysmgr/org/del', {
    method: 'delete',
    params: { ids: ids },
  });
}

export function updateOrder(ids) {
  return request('/api/sysmgr/org/order', {
    method: 'put',
    data: ids,
  });
}
