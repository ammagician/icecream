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
          @click="handleAdd"
          >新增</el-button
        >
      </el-form-item>
    </el-form>

    <el-table :data="pagination.list" style="width: 100%;" border size="small">
      <el-table-column align="center" label="名称" width="220">
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
    >
    </el-pagination>

    <el-dialog
      :visible.sync="dialogVisible"
      :title="portal.id ? '编辑' : '新增'"
      width="500px"
      size="small"
    >
      <el-form :model="portal" :rules="rules" label-width="80px" size="small">
        <el-form-item label="名称" prop="name">
          <el-input v-model="portal.name" placeholder="名称" />
        </el-form-item>
        <el-form-item label="编码" prop="code">
          <el-input v-model="portal.code" placeholder="示例：portal.xx" />
        </el-form-item>
        <el-form-item label="备注">
          <el-input
            v-model="portal.remarks"
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
          >确定</el-button
        >
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { Message } from 'element-ui'
import { removeEmptyItem } from '@/utils'
import { queryList, save, del } from '@/api/sys/portal'
const defaultPortal = {
  id: '',
  name: '测试',
  code: 'portal.xx',
  remarks: '测试部门'
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
      portal: {},
      dialogVisible: false,
      searchObj: {
        name: '',
        code: ''
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
  created() {
    this.queryList()
  },
  methods: {
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
      this.portal = { ...defaultPortal }
    },
    handleEdit(row) {
      this.dialogVisible = true
      this.portal = { ...row }
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
      save(this.portal).then(res => {
        if (res.success) {
          this.queryList()
          this.dialogVisible = false
        } else {
          Message({
            message: (this.portal.id ? '更新' : '新增') + '失败',
            type: 'error',
            duration: 3 * 1000
          })
        }
      })
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

<style></style>
