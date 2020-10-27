<template>
  <div v-if="!item.hidden">
    <template v-if="isMenu(item)">
      <app-link :to="item.url" :index="item.id">
        <el-menu-item :index="item.id" :class="{'submenu-title-noDropdown':!isNest}">
          <item :icon="item.icon" :title="item.name" />
        </el-menu-item>
      </app-link>
    </template>

    <el-submenu v-else ref="subMenu" :index="item.id" popper-append-to-body>
      <template slot="title">
        <item :icon="item.icon" :title="item.name" />
      </template>
      <sidebar-item
        v-for="child in item.children"
        :key="child.id"
        :is-nest="true"
        :item="child"
        class="nest-menu"
      />
    </el-submenu>
  </div>
</template>

<script>
import Item from './Item'
import AppLink from './Link'
import FixiOSBug from './FixiOSBug'

export default {
  name: 'SidebarItem',
  components: { Item, AppLink },
  mixins: [FixiOSBug],
  props: {
    // route object
    item: {
      type: Object,
      required: true
    },
    isNest: {
      type: Boolean,
      default: false
    }
  },
  methods: {
    isMenu(item) {
      return !item.children
    }
  }
}
</script>
