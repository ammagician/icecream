import request from '@/utils/request';

export function group() {
  return request(`/api/sysmgr/page/group`);
}

export function queryList(param, pageId) {
  const m1 = pageId ? 'btn' : 'page';
  const m2 = pageId ? 'list' : 'page';

  return request(`/api/sysmgr/${m1}/${m2}`, {
    params: pageId? {page: pageId} : param,
  });
}

export function save(data, isPage) {
  const m1 = isPage ? 'page' : 'btn';
  const m2 = data.id ? 'edit' : 'add';
  return request(`/api/sysmgr/${m1}/${m2}`, {
    method: data.id ? 'put' : 'post',
    data,
  });
}

export function del(data, isPage) {
  const m = isPage ? 'page' : 'btn';
  return request(`/api/sysmgr/${m}/del`, {
    method: 'delete',
    params: data,
  });
}
