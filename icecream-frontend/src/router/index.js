import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

import Layout from '@/layout'

/**
 * Note: sub-menu only appear when route children.length >= 1
 * Detail see: https://panjiachen.github.io/vue-element-admin-site/guide/essentials/router-and-nav.html
 *
 * hidden: true                   if set true, item will not show in the sidebar(default is false)
 * alwaysShow: true               if set true, will always show the root menu
 *                                if not set alwaysShow, when item has more than one children route,
 *                                it will becomes nested mode, otherwise not show the root menu
 * redirect: noRedirect           if set noRedirect will no redirect in the breadcrumb
 * name:'router-name'             the name is used by <keep-alive> (must set!!!)
 * meta : {
    roles: ['admin','editor']    control the page roles (you can set multiple roles)
    title: 'title'               the name show in sidebar and breadcrumb (recommend set)
    icon: 'svg-name'             the icon show in the sidebar
    breadcrumb: false            if set false, the item will hidden in breadcrumb(default is true)
    activeMenu: '/example/list'  if set path, the sidebar will highlight the path you set
  }
 */

export const constantRoutes = [
  {
    path: '/login',
    component: () => import('@/views/login/index'),
    hidden: true
  },

  {
    path: '/404',
    component: () => import('@/views/404'),
    hidden: true
  },

  {
    path: '/401',
    component: () => import('@/views/401'),
    hidden: true
  },

  {
    path: '/',
    component: Layout,
    redirect: '/sys/tenant' 
  },

  // 404 page must be placed at the end !!!
  { path: '*', redirect: '/404', hidden: true }
]

export const asyncRoutes = [
  {
    path: '/sys',
    component: Layout,
    redirect: '/sys/org',
    name: 'System',
    meta: { title: 'System', icon: 'form' },
    children: [
      {
        path: 'org',
        name: 'Org',
        component: () => import('@/views/system/org/index'),
        meta: { title: 'Org', icon: 'tree' }
      },
      {
        path: 'role',
        name: 'Role',
        component: () => import('@/views/system/role/index'),
        meta: { title: 'Role', icon: 'tree' }
      },
      {
        path: 'portal',
        name: 'Portal',
        component: () => import('@/views/system/portal/index'),
        meta: { title: 'Portal', icon: 'tree' }
      },
      {
        path: 'portlet',
        name: 'Portlet',
        component: () => import('@/views/system/portlet/index'),
        meta: { title: 'Portel', icon: 'tree' }
      },
      {
        path: 'user',
        name: 'User',
        component: () => import('@/views/system/user/index'),
        meta: { title: 'User', icon: 'tree' }
      },
      {
        path: 'page',
        name: 'Page',
        component: () => import('@/views/system/page/index'),
        meta: { title: 'Page', icon: 'tree' }
      },
      {
        path: 'menu',
        name: 'Menu',
        component: () => import('@/views/system/menu/index'),
        meta: { title: 'Menu', icon: 'tree' } 
      },
      {
        path: 'tenant',
        name: 'Tenant',
        component: () => import('@/views/system/tenant/index'),
        meta: { title: 'Tenant', icon: 'tree' } 
      }
    ]
  },

  {
    path: 'external-link',
    component: Layout,
    children: [
      {
        path: 'https://panjiachen.github.io/vue-element-admin-site/#/',
        meta: { title: 'External Link', icon: 'link' }
      }
    ]
  },

  {
    path: '/test',
    component: Layout,
    children: [
      {
        path: '',
        name: 'test',
        component: () => import('@/views/test'),
        meta: { title: 'Test', icon: 'tree' } 
      }
    ]
  }
]

const createRouter = () => new Router({
  // mode: 'history', // require service support
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRoutes
})

const router = createRouter()

export function resetRouter() {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher // reset router
}

export default router
