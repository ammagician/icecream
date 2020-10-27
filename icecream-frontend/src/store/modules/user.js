import { login, logout, getInfo, getSetting, saveSetting } from '@/api/user'
import { getToken, setToken, removeToken } from '@/utils/auth'
import { resetRouter } from '@/router'
import router from '@/router'

const getDefaultState = () => {
  return {
    token: getToken(),
    name: '',
    avatar: '',
    portals: [],
    setting: {}
  }
}

const state = getDefaultState()

const mutations = {
  RESET_STATE: state => {
    Object.assign(state, getDefaultState())
  },
  SET_TOKEN: (state, token) => {
    state.token = token
  },
  SET_NAME: (state, name) => {
    state.name = name
  },
  SET_AVATAR: (state, avatar) => {
    state.avatar = avatar
  },

  SET_PROTALS: (state, portals) => {
    state.portals = portals
  },

  SET_SETTING: (state, setting) => {
    state.setting = setting
  }
}

const actions = {
  // user login
  login({ commit }, userInfo) {
    const { username, password, tenant } = userInfo
    return new Promise((resolve, reject) => {
      login({ userName: username.trim(), password: password, tenant })
        .then(response => {
          if (response.success) {
            const { data } = response
            commit('SET_TOKEN', data.token)
            setToken(data.token)
            resolve()
          } else {
            reject(response.msg)
          }
        })
        .catch(error => {
          reject(error)
        })
    })
  },

  // get user info
  getInfo({ commit, state }) {
    return new Promise((resolve, reject) => {
      getInfo(state.token)
        .then(response => {
          const { data, success, msg } = response

          if (!success) {
            reject(msg)
          }

          let { user4AuthVO, portalList } = data
          portalList = portalList.filter(p => {
            return p.menuList.length > 0
          })

          const setting = state.setting
          if(!setting || portalList.length === 0){
            reject(new Error("你没有任何门户的访问权限，请联系管理员"))
          }

          let portal = portalList.find(p => {
            return p.id === setting.currentPortal
          })

          portal = portal || portalList[0]
          setting.currentPortal = portal.id

          commit('SET_NAME', user4AuthVO.userName)
          commit('SET_AVATAR', user4AuthVO.avatar)
          commit('SET_PROTALS', portalList)
          resolve(portal)
        })
        .catch(error => {
          reject(error)
        })
    })
  },

  // get user setting
  getSetting({ commit, state }) {
    return new Promise((resolve, reject) => {
      getSetting()
        .then(response => {
          const { data } = response
          commit('SET_SETTING', data)
          resolve(data)
        })
        .catch(error => {
          reject(error)
        })
    })
  },

  // changePortal
  changePortal({ commit, state, dispatch}, setting) {
    return new Promise((resolve, reject) => {
      saveSetting(setting)
        .then(async response => {
          commit('SET_SETTING', setting)

          //切换菜单，路由
          const portal = state.portals.find(p=>{
            return p.id === setting.currentPortal
          })

          const menuList = portal? portal.menuList : []
          const accessRoutes = await dispatch(
            'permission/generateAsyncRoutes',
            menuList,
            { root: true }
          )
          resetRouter()
          router.addRoutes(accessRoutes)
          const path = menuList[0]? menuList[0].children[0].url : '/'
          router.replace({ path: path })

          resolve()
        })
        .catch(error => {
          reject(error)
        })
    })
  },

  // user logout
  logout({ commit, state }) {
    return new Promise((resolve, reject) => {
      logout(state.token)
        .then(() => {
          removeToken() // must remove  token  first
          resetRouter()
          commit('RESET_STATE')
          resolve()
        })
        .catch(error => {
          reject(error)
        })
    })
  },

  // remove token
  resetToken({ commit }) {
    return new Promise(resolve => {
      removeToken() // must remove  token  first
      commit('RESET_STATE')
      resolve()
    })
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}
