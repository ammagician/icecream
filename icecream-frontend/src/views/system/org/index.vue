<template>
  <div class="app-container">
    <el-table
      :data="orgList"
      size="small"
      style="width: 100%;margin-bottom: 20px;margin-top: 10px;"
      row-key="id"
      :header-cell-style="{ backgroundColor: '#eee' }"
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
        <template slot-scope="scope">
          {{ scope.row.name }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="编码" width="220">
        <template slot-scope="scope">
          {{ scope.row.code }}
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
            @click="handleDelete(scope.row.id)"
            :disabled="scope.index === 0"
            >删除</el-button
          >
        </template>
      </el-table-column>
    </el-table>

    <el-dialog
      :visible.sync="dialogVisible"
      :title="org.id ? '编辑' : '新增'"
      width="600px"
    >
      <el-form :model="org" :rules="rules" label-width="80px" size="small">
        <el-form-item label="名称" prop="name">
          <el-input v-model="org.name" placeholder="组织名称" />
        </el-form-item>
        <el-form-item label="编码" prop="code">
          <el-input v-model="org.code" placeholder="示例：org.xx" />
        </el-form-item>
        <el-form-item label="上级组织">
          <el-input v-model="org.parentName" v-bind:disabled="true" />
        </el-form-item>
        <el-form-item label="备注">
          <el-input
            v-model="org.remarks"
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
import { findArrayItem } from '@/utils'
import { queryList, save, del } from '@/api/sys/org'

const defaultOrg = {
  parentId: '',
  parentName: '',
  id: '',
  name: '测试',
  code: 'org.xx',
  remarks: '测试部门'
}
export default {
  data() {
    return {
      orgList: [],
      org: {},
      dialogVisible: false,
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
    this.queryList()
  },

  methods: {
    queryList() {
      queryList().then(res => {
        if (res.success) {
          this.orgList = res.data
        }
      })
    },
    handleAdd(row) {
      this.dialogVisible = true
      this.org = {
        ...defaultOrg,
        parentId: row.id,
        parentName: row.name
      }
    },
    handleEdit(row) {
      this.dialogVisible = true
      this.org = { ...row }
    },
    handleDelete(id) {
      const o = findArrayItem(this.orgList, 'id', id)
      if (!o) {
        return
      }
      if (o.children && o.children.length > 0) {
        this.$message({
          message: '不能删除包含子节点的组织!',
          type: 'error'
        })
        return
      }

      del(id).then(res => {
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
      save(this.org).then(res => {
        if (res.success) {
          this.queryList()
          this.dialogVisible = false
        } else {
          Message({
            message: (this.org.id ? '更新' : '新增') + '失败',
            type: 'error',
            duration: 3 * 1000
          })
        }
      })
    }
  }
}
</script>

<style></style>
