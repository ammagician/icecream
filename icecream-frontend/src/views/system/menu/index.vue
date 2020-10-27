<template>
  <div class="app-container">
    <el-form
      :model="searchObj"
      :inline="true"
      label-width="40px"
      label-position="left"
      size="small"
    >
      <el-form-item label="门户">
        <el-select v-model="searchObj.portalId" clearable placeholder="请选择">
          <el-option
            v-for="item in portals"
            :key="item.id"
            :label="item.name"
            :value="item.id"
          >
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button
          type="primary"
          icon="el-icon-search"
          size="small"
          @click="queryList"
          >查询</el-button
        >
      </el-form-item>
      <el-form-item>
        <el-button
          type="primary"
          icon="el-icon-plus"
          size="small"
          @click="handleAddRoot()"
          >新增根目录</el-button
        >
      </el-form-item>
    </el-form>
    <el-table
      :data="menus"
      style="width: 100%;"
      row-key="id"
      stripe
      border
      default-expand-all
      :tree-props="{ children: 'children', hasChildren: 'hasChildren' }"
      :row-class-name="tableRowClassName"
      size="small"
    >
      <el-table-column
        label="名称"
        width="220"
        align="left"
        headerAlign="center"
      >
        <template slot-scope="scope">
          {{ scope.row.name }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="编码" width="220">
        <template slot-scope="scope">
          {{ scope.row.code }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="类型" width="220">
        <template slot-scope="scope">
          {{ scope.row.type === 4 ? '菜单' : '目录' }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="页面路径" width="220">
        <template slot-scope="scope">
          {{ scope.row.url }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="所属门户" width="220">
        <template slot-scope="scope">
          {{ scope.row.parentId ? '' : findPortalName(scope.row.portalId) }}
        </template>
      </el-table-column>
      <el-table-column align="header-center" label="备注">
        <template slot-scope="scope">
          {{ scope.row.remarks }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="操作">
        <template slot-scope="scope">
          <el-button
            v-bind:disabled="scope.row.type == '4'"
            type="primary"
            size="mini"
            icon="el-icon-plus"
            @click="handleAdd(scope.row)"
            >添加</el-button
          >
          <el-button
            type="primary"
            size="mini"
            icon="el-icon-edit"
            @click="handleEdit(scope.row)"
            >编辑</el-button
          >
          <el-button
            type="danger"
            size="mini"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            >删除</el-button
          >
        </template>
      </el-table-column>
    </el-table>

    <el-dialog
      :visible.sync="dialogVisible"
      :title="menu.id ? '编辑' : '新增'"
      width="600px"
    >
      <el-form :model="menu" :rules="rules" label-width="80px" size="small">
        <el-form-item label="类型">
          <el-radio
            :disabled="!!menu.id || menu.isRoot"
            v-model="menu.type"
            label="3"
            >目录</el-radio
          >
          <el-radio
            :disabled="!!menu.id || menu.isRoot"
            v-model="menu.type"
            label="4"
            >菜单</el-radio
          >
        </el-form-item>
        <el-form-item label="名称" prop="name">
          <el-input v-model="menu.name" placeholder="名称" />
        </el-form-item>
        <el-form-item label="编码" prop="code">
          <el-input v-model="menu.code" placeholder="示例：menu.xx" />
        </el-form-item>
        <el-form-item label="上级菜单">
          <el-select
            v-model="menu.parentId"
            :disabled="menu.isRoot || !menu.parentId"
            placeholder="请选择上级菜单"
            style="width: 480px"
            ref="selectReport"
          >
            <el-option
              :value="menu.parentId"
              :label="menu.parentName"
              style="width: 480px;height:200px;overflow: auto;background-color:#fff"
            >
              <el-tree
                :data="filterMenus"
                :props="{ children: 'children', label: 'name' }"
                @node-click="handleNodeClick"
              ></el-tree>
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="页面路径" v-if="menu.type === '4'">
          <el-select
            v-model="menu.url"
            filterable
            @change="changePage"
            placeholder="请选择"
            style="width: 480px"
          >
            <el-option-group
              v-for="group in pages"
              :key="group.id"
              :label="findPortalName(group.id)"
            >
              <el-option
                v-for="item in group.children"
                :key="item.id"
                :value="item.url"
              >
                <span style="float: left">{{ item.name }}</span>
                <span style="float: right; color: #8492a6; font-size: 13px">{{
                  item.url
                }}</span>
              </el-option>
            </el-option-group>
          </el-select>
        </el-form-item>
        <el-form-item label="所属门户">
          <el-select
            v-model="menu.portalId"
            v-bind:disabled="!menu.isRoot"
            placeholder="请选择"
            style="width: 480px"
          >
            <el-option
              v-for="item in menu.isRoot ? filterPortals : portals"
              :key="item.id"
              :label="item.name"
              :value="item.id"
            >
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="备注">
          <el-input
            v-model="menu.remarks"
            :autosize="{ minRows: 2, maxRows: 4 }"
            type="textarea"
            placeholder="备注"
          />
        </el-form-item>
      </el-form>
      <div style="text-align:right;">
        <el-button type="danger" size="small" @click="dialogVisible = false"
          >取消</el-button
        >
        <el-button type="primary" size="small" @click="handleConfirm"
          >确认</el-button
        >
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { Message } from 'element-ui'
import { findArrayItem, removeEmptyItem, deepClone } from '@/utils'
import { queryAll as queryAllPortals } from '@/api/sys/portal'
import { group } from '@/api/sys/page'
import { queryList, save, del } from '@/api/sys/menu'

const defaultMenu = {
  id: '',
  name: '测试',
  code: 'menu.test',
  type: '3',
  url: '',
  parentId: '',
  remarks: '测试部门'
}
export default {
  data() {
    return {
      menus: [],
      menu: {},
      dialogVisible: false,
      portals: [],
      searchObj: {
        portalId: ''
      },
      pages: [],
      rules: {
        name: [
          { required: true, message: '请输入名称', trigger: 'blur' },
          { min: 2, max: 64, message: '长度在 2 到 64 个字符', trigger: 'blur' }
        ],
        code: [{ max: 64, message: '最多只能输入64个字符', trigger: 'blur' }]
      }
    }
  },
  created() {
    queryAllPortals().then(res => {
      if (res.success) {
        this.portals = res.data
        this.queryList()
      }
    })

    this.loadPages()
  },

  watch: {
    dialogVisible: function(v){
      if(v){
        this.loadPages()
      }
    }
  },

  methods: {
    loadPages(){
      group().then(res => {
        if (res.success) {
          this.pages = res.data
        }
      })
    },

    tableRowClassName({ row, rowIndex }) {
      return row.parentId ? '' : 'success-row'
    },

    findPortalName(id) {
      const portal = findArrayItem(this.portals || [], 'id', id)
      return portal ? portal.name : ''
    },

    findParentName(id) {
      const p = findArrayItem(this.menus || [], 'id', id)
      return p ? p.name : ''
    },

    checkDisable(row) {
      return {
        disabled: row.type === 4
      }
    },
    handleNodeClick(node) {
      if (node.id !== this.menu.id) {
        this.menu.parentId = node.id
        this.menu.parentName = node.name
      }

      this.$refs.selectReport.blur()
    },

    handleAddRoot() {
      this.dialogVisible = true
      this.menu = { ...defaultMenu, isRoot: true }
    },

    handleAdd(row) {
      this.dialogVisible = true
      this.menu = { ...defaultMenu }
      if (row) {
        this.menu.parentId = row.id
        this.menu.parentName = row.name
        this.menu.portalId = row.portalId
      }
    },
    handleEdit(row) {
      this.dialogVisible = true
      this.menu = {
        ...row,
        type: `${row.type}`,
        parentName: this.findParentName(row.parentId)
      }
    },
    handleDelete(row) {
      const ids = { ids: row.id }
      del(ids).then(res => {
        if (res.success) {
          this.queryList()
        }

        Message({
          message: '删除' + (res.success ? '成功' : '失败'),
          type: res.success ? 'success' : 'error',
          duration: 3 * 1000
        })
      })
    },

    handleConfirm() {
      const obj = { ...this.menu }
      if (obj.id && obj.type === 4) {
        obj.url = obj.page.url
        obj.relationId = obj.page.id
      }
      save(obj).then(res => {
        if (res.success) {
          this.queryList()
          this.dialogVisible = false
        } else {
          Message({
            message: (obj.id ? '更新' : '新增') + '失败',
            type: 'error',
            duration: 3 * 1000
          })
        }
      })
    },

    changePage(url) {
      const item = findArrayItem(this.pages, 'url', url)
      if (item) {
        this.menu.relationId = item.id
      }
    },

    queryList(pageId) {
      const param = {
        ...removeEmptyItem(this.searchObj)
      }
      queryList(param, pageId).then(res => {
        if (res.success) {
          this.menus = res.data
        }
      })
    }
  },
  computed: {
    filterMenus() {
      const menus = this.menus
      let rootItem
      for (let i = 0, len = menus.length; i < len; i++) {
        if (menus[i].id === this.menu.parentId) {
          rootItem = menus[i]
          break
        }
        const match = findArrayItem(menus[i].children, 'id', this.menu.parentId)
        if (match) {
          rootItem = menus[i]
          break
        }
      }

      const copyMenus = deepClone(rootItem ? [rootItem] : [])
      const filter = function(list = []) {
        return list.filter(item => {
          if (item.children) {
            item.children = filter(item.children)
          }
          return item.type === 3
        })
      }
      return filter(copyMenus)
    },

    filterPortals() {
      const ids = this.menus.map(item => {
        return item.portalId
      })

      return this.portals.filter(item => {
        return ids.indexOf(item.id) < 0
      })
    }
  }
}
</script>

<style>
.el-table .success-row {
  background: #f0f9eb;
}
</style>
