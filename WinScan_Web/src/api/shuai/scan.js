import request from '@/utils/request'

// 查询应用扫描列表
export function listScan(query) {
  return request({
    url: '/shuai/scan/list',
    method: 'get',
    params: query
  })
}

// 查询应用扫描详细
export function getScan(id) {
  return request({
    url: '/shuai/scan/' + id,
    method: 'get'
  })
}

// 新增应用扫描
export function addScan(data) {
  return request({
    url: '/shuai/scan',
    method: 'post',
    data: data
  })
}

// 修改应用扫描
export function updateScan(data) {
  return request({
    url: '/shuai/scan',
    method: 'put',
    data: data
  })
}

// 删除应用扫描
export function delScan(id) {
  return request({
    url: '/shuai/scan/' + id,
    method: 'delete'
  })
}

// 导出应用扫描
export function exportScan(query) {
  return request({
    url: '/shuai/scan/export',
    method: 'get',
    params: query
  })
}

export function doScan() {
  return request({
    url: '/shuai/scan/doScan',
    method: 'get',
  })
}
