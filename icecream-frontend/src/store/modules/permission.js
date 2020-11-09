import { asyncRoutes, constantRoutes } from '@/router'

/**
 * Filter menus by recursion
 * @param routes menus
 */
function filterMenus(routes) {
  let res = []
  routes.forEach(route => {
    if (!route.children) {
      res.push(route.url)
    } else {
      res = res.concat(filterMenus(route.children))
    }
  })

  return res
}

/**
 * Filter asynchronous routing tables by recursion
 * @param routes asyncRoutes
 * @param roles
 */
export function filterAsyncRoutes(routes, parentPath, paths) {
  const res = []

  routes.forEach(route => {
    const tmp = { ...route }
    let p = `/${parentPath}/${tmp.path}/`
    p = p.replace(/\/{1,}/g, '/')
    const match = paths.find(item => {
      return `/${item}/`.replace(/\/{1,}/g, '/').indexOf(p) === 0
    })

    if (match) {
      if (tmp.children) {
        tmp.children = filterAsyncRoutes(tmp.children, p, paths)
      }
      res.push(tmp)
    }
  })

  return res
}

const state = {
  routes: []
}

const mutations = {
  SET_ROUTES: (state, routes) => {
    state.routes = routes
  }
}

const actions = {
  generateAsyncRoutes({ commit }, routes) {
    return new Promise(resolve => {
      const dashboard = routes[0].children.find((c)=>{
        return c.id === 'dashboard'
      })
      if(!dashboard){
        routes[0].children.unshift({id:'dashboard', url: '/dashboard', name: 'Dashboard', icon: 'dashboard'})
      }

      commit('SET_ROUTES', routes)
      const menus = filterMenus(routes)
      const accessedRoutes = filterAsyncRoutes(asyncRoutes, '', menus)
      resolve(constantRoutes.concat(accessedRoutes))
    })
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}
