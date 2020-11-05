<template>
  <div class="navbar">
    <hamburger
      id="hamburger-container"
      :is-active="sidebar.opened"
      class="hamburger-container"
      @toggleClick="toggleSideBar"
    />

    <breadcrumb id="breadcrumb-container" class="breadcrumb-container" />

    <div class="right-menu">
      <template v-if="false && device !== 'mobile'">
        <search id="header-search" class="right-menu-item" />

        <error-log class="errLog-container right-menu-item hover-effect" />

        <screenfull id="screenfull" class="right-menu-item hover-effect" />

        <el-tooltip content="Global Size" effect="dark" placement="bottom">
          <size-select id="size-select" class="right-menu-item hover-effect" />
        </el-tooltip>
      </template>
      <div class="handle-button" :style="{color: theme}" @click="openSetting" >
        <i class="el-icon-setting" />
      </div>
      <span class="username">{{userName}}</span>
      <el-dropdown
        class="avatar-container right-menu-item hover-effect"
        trigger="click"
      >

        <div class="avatar-wrapper">
          <img :src="avatarImg" class="user-avatar" />
          <i class="el-icon-caret-bottom" />
        </div>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item @click.native="logout">
            <span style="display:block;">Log Out</span>
          </el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
      <el-dropdown
        class="avatar-container right-menu-item hover-effect"
        trigger="click"
      >
        <div class="avatar-wrapper">
          {{currentPortal}}
          <i class="el-icon-caret-bottom" />
        </div>
        <el-dropdown-menu slot="dropdown">
          <div v-for="p in portals" @click="changePortal(p.id)" :key="p.id">
            <el-dropdown-item>{{p.name}}</el-dropdown-item>
          </div>
        </el-dropdown-menu>
      </el-dropdown>
    </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import Breadcrumb from '@/components/Breadcrumb'
import Hamburger from '@/components/Hamburger'
import ErrorLog from '@/components/ErrorLog'
import Screenfull from '@/components/Screenfull'
import SizeSelect from '@/components/SizeSelect'
import Search from '@/components/HeaderSearch'
import avatar from '@/assets/avatar.jpeg'

export default {
  components: {
    Breadcrumb,
    Hamburger,
    ErrorLog,
    Screenfull,
    SizeSelect,
    Search
  },

  data(){
    return {
      avatarImg: avatar
    }
  },
  computed: {
    ...mapGetters(['sidebar', 'avatar', 'device']),
    portals: function(){
      return this.$store.state.user.portals.map(p=>{
        return {
          id: p.id,
          name: p.name
        }
      })
    },

    currentPortal: function(){
      const user = this.$store.state.user;
      const portal = user.portals.find(p=>{
        return p.id === user.setting.currentPortal
      })

      return portal? portal.name : ''
    },

    userName: function(){
      const user = this.$store.state.user;
      return user.name
    },

    theme() {
      return this.$store.state.settings.theme
    }
  },
  methods: {
    toggleSideBar() {
      this.$store.dispatch('app/toggleSideBar')
    },
    async logout() {
      await this.$store.dispatch('user/logout')
      this.$router.push(`/login`)
    },
    changePortal(id){
      const setting = {...this.$store.state.user.setting}
      setting.currentPortal = id;
      this.$store.dispatch('user/changePortal', setting)
    },

    openSetting(){
      this.$emit("openSetting")
    }
  }
}
</script>

<style lang="scss" scoped>
.navbar {
  height: 50px;
  overflow: hidden;
  position: relative;
  background: #fff;
  box-shadow: 0 1px 4px rgba(0, 21, 41, 0.08);

  .hamburger-container {
    line-height: 46px;
    height: 100%;
    float: left;
    cursor: pointer;
    transition: background 0.3s;
    -webkit-tap-highlight-color: transparent;

    &:hover {
      background: rgba(0, 0, 0, 0.025);
    }
  }

  .breadcrumb-container {
    float: left;
  }

  .errLog-container {
    display: inline-block;
    vertical-align: top;
  }

  .right-menu {
    float: right;
    height: 100%;
    line-height: 50px;

    .handle-button{
      vertical-align: top;
      padding-top: 4px;
      padding-right: 10px;
      display:inline-block;
      cursor: pointer;
      font-size: 20px
    }

    .username{
      display: inline-block;
      vertical-align: top;
      padding-top: 5px;
    }

    &:focus {
      outline: none;
    }

    .right-menu-item {
      display: inline-block;
      padding: 0 8px;
      height: 100%;
      font-size: 18px;
      color: #5a5e66;
      vertical-align: text-bottom;

      &.hover-effect {
        cursor: pointer;
        transition: background 0.3s;

        &:hover {
          background: rgba(0, 0, 0, 0.025);
        }
      }
    }

    .avatar-container {
      margin-right: 30px;

      .avatar-wrapper {
        margin-top: 5px;
        position: relative;
        font-size: 16px;

        .user-avatar {
          cursor: pointer;
          width: 30px;
          height: 30px;
          border-radius: 10px;
          margin-top: 8px;
        }

        .el-icon-caret-bottom {
          cursor: pointer;
          position: absolute;
          right: -20px;
          top: 18px;
          font-size: 16px;
        }
      }
    }
  }
}
</style>
