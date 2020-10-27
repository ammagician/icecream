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
        <el-input v-model="searchObj.userName" placeholder="名称" />
      </el-form-item>
      <el-form-item label="部门">
        <el-select
          v-model="searchObj.orgId"
          placeholder="请选择..."
          clearable
          style="width: 200px"
          ref="selectSearchOrg"
          @clear="clearSearchOrg"
        >
          <el-option
            :value="searchObj.orgId"
            :label="searchObj.orgName"
            style="width: 240px;height:200px;overflow: auto;background-color:#fff"
          >
            <el-tree
              :data="orgList"
              default-expand-all
              :props="{ children: 'children', label: 'name' }"
              @node-click="selectSearchOrg"
            ></el-tree>
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="状态">
        <el-select
          v-model="searchObj.isDisabled"
          placeholder="请选择..."
          clearable
        >
          <el-option label="禁用" value="1"></el-option>
          <el-option label="正常" value="0"></el-option>
        </el-select>
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

    <el-table
      :data="pagination.list"
      style="width: 100%;margin-top:0px;"
      border
      stripe
      size="small"
    >
      <el-table-column align="center" label="登录名" width="220">
        <template slot-scope="scope">{{ scope.row.loginName }}</template>
      </el-table-column>
      <el-table-column align="center" label="用户名" width="220">
        <template slot-scope="scope">{{ scope.row.userName }}</template>
      </el-table-column>
      <el-table-column align="center" label="电话" width="160">
        <template slot-scope="scope">{{ scope.row.mobile }}</template>
      </el-table-column>
      <el-table-column align="center" label="邮箱" width="200">
        <template slot-scope="scope">{{ scope.row.email }}</template>
      </el-table-column>
      <el-table-column align="header-center" label="所属部门">
        <template slot-scope="scope">{{ scope.row.orgName }}</template>
      </el-table-column>
      <el-table-column align="center" label="状态" width="80">
        <template slot-scope="scope">{{
          scope.row.isDisabled ? '禁用' : '正常'
        }}</template>
      </el-table-column>
      <el-table-column align="center" label="操作" width="360">
        <template slot-scope="scope">
          <el-button
            type="primary"
            size="mini"
            v-bind:icon="getLockIcon(scope.row.isDisabled)"
            @click="handleToggle(scope.row)"
            >{{ scope.row.isDisabled === 1 ? '解锁' : '锁定' }}</el-button
          >
          <el-button
            type="primary"
            size="mini"
            icon="el-icon-key"
            @click="handleRestPwd(scope.row)"
            >重置</el-button
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
      :title="user.id ? '编辑' : '新增'"
      width="580px"
    >
      <el-form :model="user" :rules="rules" label-width="80px" size="small">
        <el-form-item label="登录名" prop="loginName">
          <el-input
            v-model="user.loginName"
            placeholder="唯一登录名"
            :disabled="!!user.id"
          />
        </el-form-item>
        <el-form-item label="用户名" prop="userName">
          <el-input v-model="user.userName" placeholder="用户名" />
        </el-form-item>
        <el-form-item label="电话" prop="mobile">
          <el-input v-model.number="user.mobile" placeholder="手机号码" />
        </el-form-item>
        <el-form-item label="邮箱" prop="email">
          <el-input v-model="user.email" placeholder="xx@yy.com" />
        </el-form-item>
        <el-form-item label="部门">
          <el-select
            v-model="user.orgId"
            placeholder="请选择上级菜单"
            style="width: 460px"
            ref="selectOrg"
          >
            <el-option
              :value="user.orgId"
              :label="user.orgName"
              style="width: 460px;height:200px;overflow: auto;background-color:#fff"
            >
              <el-tree
                :data="orgList"
                default-expand-all
                :props="{ children: 'children', label: 'name' }"
                @node-click="selectOrg"
              ></el-tree>
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="角色">
          <el-select
            v-model="user.roles"
            multiple
            placeholder="请选择..."
            style="width:460px;"
            size="small"
          >
            <el-option
              v-for="item in roleList"
              :key="item.id"
              :label="item.name"
              :value="item.id"
              style="height:33px;"
            ></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div style="text-align:right;">
        <el-button type="danger" size="small" @click="dialogVisible = false"
          >取消</el-button
        >
        <el-button type="primary" size="small" @click="handleConfirm"
          >确定</el-button
        >
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { Message } from 'element-ui'
import { removeEmptyItem } from '@/utils'
import { isValidPhone } from '@/utils/validate'
import { queryList as queryOrgs } from '@/api/sys/org'
import {
  queryList,
  queryDetail,
  toggle,
  resetPwd,
  save,
  del
} from '@/api/sys/user'
import { queryAll as queryRoles } from '@/api/sys/role'
import { queryAll as queryAllPortals } from '@/api/sys/portal'

const defaultUser = {
  id: '',
  loginName: 'testUser',
  userName: '测试',
  mobile: '13800000000',
  email: '13800000000@qq.com',
  orgId: '',
  orgName: '',
  roles: []
}

const validPhone = (rule, value, callback) => {
  value = ('' + value).trim()
  if (!value) {
    callback(new Error('请输入电话号码'))
  } else if (!isValidPhone(value)) {
    callback(new Error('请输入正确的11位手机号码'))
  } else {
    callback()
  }
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
      user: {},
      dialogVisible: false,
      searchObj: {
        userName: '',
        orgId: '',
        orgName: '',
        portalId: '',
        isDisabled: '0'
      },
      orgList: [],
      roleList: [],
      rules: {
        loginName: [
          { required: true, message: '请输入登录名称', trigger: 'blur' },
          { min: 2, max: 64, message: '长度在 2 到 64 个字符', trigger: 'blur' }
        ],
        userName: [
          { required: true, message: '请输入用户名称', trigger: 'blur' },
          { min: 2, max: 64, message: '长度在 2 到 64 个字符', trigger: 'blur' }
        ],
        email: [
          { required: true, message: '请输入联系邮箱', trigger: 'blur' },
          { type: 'email', message: '请输入正确邮箱', trigger: 'blur' }
        ],
        mobile: [
          {
            trigger: 'blur',
            validator: validPhone
          }
        ]
      }
    }
  },
  created() {
    queryOrgs().then(res => {
      if (res.success) {
        this.orgList = res.data
      }
    })

    queryRoles().then(res => {
      if (res.success) {
        this.roleList = res.data
      }
    })

    this.queryList()
  },
  methods: {
    getLockIcon(isDisabled) {
      return isDisabled ? 'el-icon-unlock' : 'el-icon-lock'
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
      this.user = { ...defaultUser }
    },
    handleEdit(row) {
      queryDetail(row.id).then(res => {
        if (res.success) {
          this.dialogVisible = true
          const data = { ...res.data }
          delete data.password
          data.roles = data.roles.map(item => {
            return item.id
          })
          this.user = { ...data }
        }
      })
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
      const data = { ...this.user }
      data.roles = data.roles.map(item => {
        return {
          id: item
        }
      })

      save(data).then(res => {
        if (res.success) {
          this.queryList()
          this.dialogVisible = false
        } else {
          Message({
            message:
              (this.user.id ? '更新' : '新增') + '失败' + ' --> ' + res.msg,
            type: 'error',
            duration: 3 * 1000
          })
        }
      })
    },

    handleToggle(row) {
      const user = {
        id: row.id,
        isDisabled: row.isDisabled ? 0 : 1
      }
      toggle(user).then(res => {
        Message({
          message: '操作' + (res.success ? '成功' : '失败'),
          type: 'error',
          duration: 3 * 1000
        })
      })
    },

    handleRestPwd(id) {
      resetPwd(id).then(res => {
        Message({
          message: '重置密码' + (res.success ? '成功' : '失败'),
          type: 'error',
          duration: 3 * 1000
        })
      })
    },

    queryList() {
      const param = {
        ...removeEmptyItem(this.searchObj),
        size: this.pagination.size,
        current: this.pagination.current
      }
      delete param.orgName
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
    },

    selectSearchOrg(node) {
      this.searchObj.orgId = node.id
      this.searchObj.orgName = node.name
      this.$refs.selectSearchOrg.blur()
    },

    selectOrg(node) {
      this.user.orgId = node.id
      this.user.orgName = node.name
      this.$refs.selectOrg.blur()
    },

    clearSearchOrg() {
      this.searchObj.orgId = ''
      this.searchObj.orgName = ''
    }
  }
}
</script>

<style></style>
