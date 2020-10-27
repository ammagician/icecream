
const tokens = {
  admin: {
    token: 'admin-token'
  },
  editor: {
    token: 'editor-token'
  }
}

const users = {
  'admin-token': {
    roles: ['admin'],
    routes: [
      {
        icon: 'form',
        title: '系统管理',
        id: 'sys',
        children: [
          {
            icon: 'tree',
            title: '组织管理',
            id: 'org',
            path: '/sys/org'
          },
          {
            icon: 'tree',
            title: '角色管理',
            id: 'role',
            path: '/sys/role'
          },
          {
            icon: 'tree',
            title: '门户管理',
            id: 'protal',
            path: '/sys/protal'
          },
          {
            icon: 'tree',
            title: 'Portel管理',
            id: 'portel',
            path: '/sys/portel'
          },
          {
            icon: 'tree',
            title: '用户管理',
            id: 'user',
            path: '/sys/user'
          },
          {
            icon: 'tree',
            title: '页面管理',
            id: 'page',
            path: '/sys/page'
          },
          {
            icon: 'tree',
            title: '菜单管理',
            id: 'menu',
            path: '/sys/menu'
          },
        ]
      },
      {
        icon: 'form',
        title: 'example2',
        id: 'example2',
        children: [
          {
            icon: 'table',
            title: 'table2',
            id: 'table2',
            path: '/example2/table'
          },
          {
            icon: 'tree',
            title: 'tree2',
            id: 'tree2',
            path: '/example2/tree'
          },
          {
            icon: 'form',
            title: 'tree22',
            id: 'tree22',
            children: [

            ]
          }
        ]
      },
      {
        icon: 'tree',
        title: 'tree',
        id: 'tree',
        path: '/example/tree',
        actions: ['add','edit','del']
      },
      {
        icon: 'table',
        title: 'table',
        id: 'table',
        path: 'https://www.baidu.com'
      },
      {
        icon: 'form',
        title: 'example3',
        id: 'example3',
        children: []
      }
    ],
    introduction: 'I am a super administrator',
    avatar: 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif',
    name: 'Super Admin'
  },
  'editor-token': {
    roles: ['editor'],
    routes: [
      {
        icon: 'form',
        title: 'example2',
        id: 'example2',
        children: [
          {
            icon: 'table',
            title: 'table2',
            id: 'table2',
            path: '/example2/table'
          },
          {
            icon: 'tree',
            title: 'tree2',
            id: 'tree2',
            path: '/example2/tree'
          },
          {
            icon: 'form',
            title: 'tree22',
            id: 'tree22',
            children: [

            ]
          }
        ]
      }
    ],
    introduction: 'I am an editor',
    avatar: 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif',
    name: 'Normal Editor'
  }
}

export default [
  // user login
  {
    url: '/vue-admin-template/user/login',
    type: 'post',
    response: config => {
      const { username } = config.body
      const token = tokens[username]

      // mock error
      if (!token) {
        return {
          code: 60204,
          message: 'Account and password are incorrect.'
        }
      }

      return {
        code: 20000,
        data: token
      }
    }
  },

  // get user info
  {
    url: '/vue-admin-template/user/info\.*',
    type: 'get',
    response: config => {
      const { token } = config.query
      const info = users[token]

      // mock error
      if (!info) {
        return {
          code: 50008,
          message: 'Login failed, unable to get user details.'
        }
      }

      return {
        code: 20000,
        data: info
      }
    }
  },

  // user logout
  {
    url: '/vue-admin-template/user/logout',
    type: 'post',
    response: _ => {
      return {
        code: 20000,
        data: 'success'
      }
    }
  }
]
