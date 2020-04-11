import request from '@/utils/request'
import qs from 'qs'

export function getPinpointDatas (app, params) {
  return request({
    url: '/' + app + '/requests',
    method: 'post',
    data: qs.stringify(params)
  })
}

export function getApps () {
  return request({
    url: '/apps',
    method: 'get'
  })
}

export function getAgentIds (app) {
  return request({
    url: '/' + app + '/agents',
    method: 'get'
  })
}
