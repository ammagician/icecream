<template>
  <div class="app-container">
    <el-form
      :model="searchObj"
      :inline="true"
      label-width="40px"
      label-position="left"
      size="small"
    >
      <el-form-item label="名称">
        <el-input v-model="searchObj.name" placeholder="名称" />
      </el-form-item>
      <el-form-item label="编码">
        <el-input v-model="searchObj.code" placeholder="编码" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" @click="queryList"
          >查询</el-button
        >
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-plus" @click="handleAdd"
          >新增</el-button
        >
      </el-form-item>
    </el-form>

    <el-table :data="pagination.list" style="width: 100%;" border size="small">
      <el-table-column align="center" label="名称" width="220">
        <template slot-scope="scope">{{ scope.row.name }}</template>
      </el-table-column>
      <el-table-column align="center" label="编码" width="220">
        <template slot-scope="scope">{{ scope.row.code }}</template>
      </el-table-column>
      <el-table-column align="center" label="授权门户" width="220">
        <template slot-scope="scope">{{
          findPortalNames(scope.row.portalList)
        }}</template>
      </el-table-column>
      <el-table-column align="header-center" label="备注">
        <template slot-scope="scope">{{ scope.row.remarks }}</template>
      </el-table-column>
      <el-table-column align="center" label="操作">
        <template slot-scope="scope">
          <el-button
            type="primary"
            size="mini"
            icon="el-icon-edit"
            @click="handlePromission(scope.row)"
            >授权</el-button
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
            @click="handleDelete(scope.row.id)"
            >删除</el-button
          >
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="pagination.current"
      :page-sizes="[10, 20, 30, 50]"
      :page-size="pagination.size"
      layout="total, sizes, prev, pager, next"
      :total="pagination.total"
      style="float:right;"
    ></el-pagination>

    <el-dialog
      :visible.sync="dialogVisible"
      :title="role.id ? '编辑' : '新增'"
      width="580px"
    >
      <el-form :model="role" :rules="rules" label-width="80px" size="small">
        <el-form-item label="名称" prop="name">
          <el-input v-model="role.name" placeholder="名称" />
        </el-form-item>
        <el-form-item label="编码" prop="code">
          <el-input v-model="role.code" placeholder="示例: role.xx" />
        </el-form-item>
        <el-form-item label="授权门户">
          <el-select
            v-model="role.portals"
            multiple
            placeholder="请选择..."
            style="width:460px;"
            size="small"
          >
            <el-option
              v-for="item in portals"
              :key="item.id"
              :label="item.name"
              :value="item.id"
              style="height:33px;"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="备注">
          <el-input
            v-model="role.remarks"
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

    <el-dialog
      :visible.sync="permDialogVisible"
      :title="'权限设置'"
      width="1000px"
    >
      <el-form
        :inline="true"
        label-width="80px"
        label-position="left"
        size="small"
        style="height:30px;"
      >
        <el-form-item label="设置门户">
          <el-select
            v-model="permission.portalId"
            placeholder="请选择..."
            style="width:260px;"
            size="small"
            @change="loadPermission"
          >
            <el-option
              v-for="item in rolePortals"
              :key="item.id"
              :label="item.name"
              :value="item.id"
              style="height:33px;"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item style="float: right;">
          <el-button
            type="primary"
            size="mini"
            icon="el-icon-save"
            @click="handleSavePermission"
            >保存</el-button
          >
        </el-form-item>
      </el-form>
      <div style="height:12px;">
        <el-divider style="margin:0px;">
          <i class="el-icon-setting"></i>
        </el-divider>
      </div>
      <el-form
        :model="permission"
        :inline="true"
        label-width="80px"
        label-position="top"
        size="small"
      >
        <el-form-item label="菜单权限:" style="width: 700px;">
          <el-table
            :data="permission.menus"
            style="width: 100%;margin-bottom: 20px;"
            max-height="500px"
            row-key="id"
            size="small"
            stripe
            border
            default-expand-all
            :tree-props="{ children: 'children', hasChildren: 'hasChildren' }"
          >
            <el-table-column
              label="名称"
              width="220"
              align="left"
              headerAlign="center"
            >
              <template slot-scope="scope">{{ scope.row.name }}</template>
            </el-table-column>
            <el-table-column align="center" label="类型" width="60">
              <template slot-scope="scope">{{
                scope.row.type === 3 ? '目录' : '菜单'
              }}</template>
            </el-table-column>
            <el-table-column align="center" label="可见" width="60">
              <template slot-scope="scope">
                <el-checkbox
                  label
                  v-model="scope.row.checked"
                  @change="checkMenuItem(scope.row)"
                ></el-checkbox>
              </template>
            </el-table-column>
            <el-table-column align="left" label="按钮">
              <template slot-scope="scope" v-if="scope.row.type === 4">
                <el-checkbox
                  v-for="item in scope.row.btns"
                  :key="item.id"
                  v-bind:disabled="!scope.row.checked"
                  label="item.id"
                  v-model="item.checked"
                  >{{ item.name }}</el-checkbox
                >
              </template>
            </el-table-column>
          </el-table>
        </el-form-item>
        <el-form-item label="Protle权限:" style="width: 200px;">
          <div v-for="item in permission.portlets" :key="item.id">
            <el-divider
              v-if="item.children.length > 0"
              content-position="left"
              >{{ item.name }}</el-divider
            >
            <el-checkbox
              v-for="p in item.children"
              label="p.id"
              :key="p.id"
              v-model="p.checked"
              >{{ p.name }}</el-checkbox
            >
          </div>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
import { Message } from 'element-ui'
import {
  findArrayItem,
  checkArray,
  removeEmptyItem,
  removeArrayItemValue,
  filterArrayItem,
  renameArrayItem,
  deepClone,
  setArrayItemValue
} from '@/utils'
import {
  queryList,
  save,
  del,
  queryRolePermList,
  updatePermision
} from '@/api/sys/role'
import { queryAllByTenant as queryAllPortals } from '@/api/sys/portal'
import { group as groupPortlet } from '@/api/sys/portlet'
import { queryPermissionTree } from '@/api/sys/menu'

const defaultRole = {
  id: '',
  name: 'test',
  code: 'role.test',
  remarks: 'defaultRole',
  portals: []
}

export default {
  data() {
    return {
      pagination: {
        current: 1,
        size: 10,
        total: 0,
        list: []
      },
      role: {},
      dialogVisible: false,
      searchObj: {
        name: '',
        code: ''
      },
      portals: [],
      portlets: [],

      permDialogVisible: false,
      permission: {
        roleId: '',
        portalId: '',
        menus: [],
        portlets: []
      },
      rules: {
        name: [
          { required: true, message: '请输入名称', trigger: 'blur' },
          { min: 2, max: 64, message: '长度在 2 到 64 个字符', trigger: 'blur' }
        ],
        code: [{ max: 64, message: '最多只能输入64个字符', trigger: 'blur' }]
      }
    }
  },
  computed: {
    rolePortals: function(){
      if(!this.role.portals){
        return []
      }
      const ps = this.portlets.filter((p)=>{
        return this.role.portals.includes(p.id) 
      })
      return ps;
    }
  },
  created() {
    queryAllPortals().then(res => {
      if (res.success) {
        this.portals = res.data
        this.queryList()
      }
    })

    groupPortlet().then(res => {
      if (res.success) {
        this.portlets = res.data
      }
    })
  },
  methods: {
    findPortalNames(portals = []) {
      const names = portals.map(p => {
        return p.name
      })
      return names.join(',')
    },

    handleSizeChange(size) {
      this.pagination.size = size
      this.queryList()
    },
    handleCurrentChange(current) {
      this.pagination.current = current
      this.queryList()
    },

    handleAdd() {
      this.dialogVisible = true
      this.role = { ...defaultRole }
    },
    handleEdit(row) {
      this.dialogVisible = true
      this.role = {
        ...row,
        portals: row.portalList.map(p => {
          return p.id
        })
      }
    },
    handleDelete(id) {
      const ids = { ids: id }
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
      save(this.role).then(res => {
        if (res.success) {
          this.queryList()
          this.dialogVisible = false
        } else {
          Message({
            message: (this.role.id ? '更新' : '新增') + '失败',
            type: 'error',
            duration: 3 * 1000
          })
        }
      })
    },

    handlePromission(row) {
      if (row.portalList.length === 0) {
        return
      }

      this.permDialogVisible = true
      const permission = this.permission
      const match = row.portalList.find(item => {
        return item.id === permission.portalId
      })

      if (permission.roleId === row.id && match) {
        return
      }

      permission.roleId = row.id
      permission.portalId = row.portalList[0].id

      this.role = {
        ...row,
        portals: row.portalList.map(p => {
          return p.id
        })
      }

      this.loadPermission(permission.portalId)
    },

    loadPermission(portalId) {
      const permission = this.permission
      queryPermissionTree(permission.portalId).then(res1 => {
        if (res1.success) {
          queryRolePermList(permission.roleId, permission.portalId).then(
            res2 => {
              if (res2.success) {
                const menus = res1.data
                const checks = res2.data.map(item => {
                  return item.resId
                })
                checkArray(menus, 'id', 'children', checks)
                const pages = filterArrayItem(menus, 'type', 4)
                renameArrayItem(pages, 'children', 'btns')
                const portlets = deepClone(this.portlets)
                removeArrayItemValue(portlets, 'checked')
                checkArray(portlets, 'id', 'children', checks)
                permission.portlets = portlets
                permission.menus = menus
                console.info(menus)
              }
            }
          )
        }
      })
    },

    handleSavePermission() {
      const permission = this.permission
      const data = {
        id: permission.roleId,
        portalId: permission.portalId,
        permissions: []
      }

      data.permissions = filterArrayItem(
        permission.portlets,
        'checked',
        true,
        'children'
      ).map(item => {
        return {
          roleId: data.id,
          resId: item.id,
          type: 2,
          portalId: data.portalId
        }
      })

      const menusChecked = filterArrayItem(
        permission.menus,
        'checked',
        true,
        'children',
        'btns'
      ).map(item => {
        return {
          roleId: data.id,
          resId: item.id,
          type: item.type,
          portalId: data.portalId
        }
      })

      data.permissions = data.permissions.concat(menusChecked)

      updatePermision(data).then(res => {
        Message({
          message: '更新权限' + (res.success ? '成功' : '失败'),
          type: res.success ? 'success' : 'error',
          duration: 3 * 1000
        })
      })
    },

    checkMenuItem(row) {
      const checked = row.checked
      // 勾选，判断上级是否勾选，如果没勾就忽略这个动作
      if (checked) {
        const pId = row.parentId
        const p = findArrayItem(this.permission.menus, 'id', pId)
        if (p && !p.checked) {
          row.checked = false
        }
      } else {
        setArrayItemValue(
          row.children || row.btns,
          'checked',
          false,
          'children',
          'btns'
        )
      }
    },

    queryList() {
      const param = {
        ...removeEmptyItem(this.searchObj),
        size: this.pagination.size,
        current: this.pagination.current
      }
      queryList(param).then(res => {
        if (res.success) {
          const { current, size, total, records } = res.data
          this.pagination = {
            ...this.pagination,
            current,
            size,
            total,
            list: records
          }
        }
      })
    }
  }
}
</script>

<style lang="scss" scoped>
.app-container {
  .roles-table {
    margin-top: 30px;
  }
  .permission-tree {
    margin-bottom: 30px;
  }
}
</style>
