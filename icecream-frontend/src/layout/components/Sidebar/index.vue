<template>
  <div :class="{'has-logo':showLogo}">
    <logo v-if="showLogo" :collapse="isCollapse" />
    <el-scrollbar wrap-class="scrollbar-wrapper">
      <el-menu
        :default-active="activeMenu"
        :collapse="isCollapse"
        :background-color="variables.menuBg"
        :text-color="variables.menuText"
        :unique-opened="false"
        :active-text-color="variables.menuActiveText"
        :collapse-transition="false"
        mode="vertical"
      >
        <sidebar-item v-for="route in permission_routes[0].children" :key="route.id" :item="route" />
      </el-menu>
    </el-scrollbar>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import Logo from './Logo'
import SidebarItem from './SidebarItem'
import variables from '@/styles/variables.scss'

export default {
  components: { SidebarItem, Logo },
  data: () => {
    return {
      permission_routes2: [
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
          path: '/example/tree'
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
      ]
    }
  },
  computed: {
    ...mapGetters([
      'sidebar',
      'permission_routes'
    ]),
    activeMenu() {
      const route = this.$route
      const { query } = route
      return query.index || 'dashboard'
    },
    showLogo() {
      return this.$store.state.settings.sidebarLogo
    },
    variables() {
      return variables
    },
    isCollapse() {
      return !this.sidebar.opened
    }
  }
}
</script>
