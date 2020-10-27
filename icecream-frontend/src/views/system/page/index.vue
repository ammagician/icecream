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
          @click="queryList(false)"
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

    <el-table
      :data="pagination.list"
      @expand-change="getPageBtns"
      style="width: 100%;"
      row-key="id"
      border
      size="small"
    >
      <el-table-column type="expand">
        <template slot-scope="scope">
          <el-table
            :data="scope.row.btns"
            style="width: 100%;margin-top:0px;"
            border
            size="mini"
          >
            <el-table-column align="center" label="名称" width="220">
              <template slot-scope="btnRow">
                {{ btnRow.row.name }}
              </template>
            </el-table-column>
            <el-table-column align="center" label="编码" width="220">
              <template slot-scope="btnRow">
                {{ btnRow.row.code }}
              </template>
            </el-table-column>
            <el-table-column align="center" label="API" width="220">
              <template slot-scope="btnRow">
                {{ btnRow.row.url }}
              </template>
            </el-table-column>
            <el-table-column align="header-center" label="备注">
              <template slot-scope="btnRow">
                {{ btnRow.row.remarks }}
              </template>
            </el-table-column>
            <el-table-column align="center" label="操作">
              <template slot-scope="btnRow">
                <el-button
                  type="primary"
                  size="mini"
                  icon="el-icon-edit"
                  @click="handleEdit(btnRow.row)"
                  >编辑</el-button
                >
                <el-button
                  type="danger"
                  size="mini"
                  icon="el-icon-delete"
                  @click="handleDelete(btnRow.row)"
                  >删除</el-button
                >
              </template>
            </el-table-column>
          </el-table>
        </template>
      </el-table-column>
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
      <el-table-column align="center" label="路径" width="220">
        <template slot-scope="scope">
          {{ scope.row.url }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="所属门户" width="220">
        <template slot-scope="scope">
          {{ scope.row.portalName }}
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
            @click="handleAddBtn(scope.row)"
            >新增权限</el-button
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
      :title="page.id ? '编辑' : '新增'"
      width="600px"
    >
      <el-form :model="page" :rules="rules" label-width="80px" size="small">
        <el-form-item label="名称" prop="name">
          <el-input v-model="page.name" placeholder="名称" />
        </el-form-item>
        <el-form-item label="编码" prop="code">
          <el-input v-model="page.code" placeholder="pege.xx" />
        </el-form-item>
        <el-form-item label="路径">
          <el-input v-model="page.url" placeholder="示例: /门户/模块/页面" />
        </el-form-item>
        <el-form-item label="所属门户">
          <el-select
            v-model="page.portalId"
            placeholder="请选择"
            style="width: 480px"
          >
            <el-option
              v-for="item in portals"
              :key="item.id"
              :label="item.name"
              :value="item.id"
            >
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="备注">
          <el-input
            v-model="page.remarks"
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
        <el-button type="primary" size="small" @click="handleConfirm(true)"
          >确定</el-button
        >
      </div>
    </el-dialog>

    <el-dialog
      :visible.sync="btnDialogVisible"
      :title="btn.id ? '编辑' : '新增'"
      width="600px"
    >
      <el-form
        :model="btn"
        :rules="rules"
        label-width="80px"
        label-position="left"
        size="small"
      >
        <el-form-item label="名称" prop="name">
          <el-input v-model="btn.name" placeholder="名称" />
        </el-form-item>
        <el-form-item label="授权编码">
          <el-input
            v-model="btn.code"
            placeholder="唯一权限编码，示例：perm.btn.xx"
          />
        </el-form-item>
        <el-form-item label="授权API">
          <el-input v-model="btn.url" placeholder="对应后台API" />
        </el-form-item>
        <el-form-item label="所属页面">
          <el-input v-model="btn.parentName" disabled />
        </el-form-item>
        <el-form-item label="备注">
          <el-input
            v-model="btn.remarks"
            :autosize="{ minRows: 2, maxRows: 4 }"
            type="textarea"
            placeholder="备注"
          />
        </el-form-item>
      </el-form>
      <div style="text-align:right;">
        <el-button type="danger" size="small" @click="btnDialogVisible = false"
          >取消</el-button
        >
        <el-button type="primary" size="small" @click="handleConfirm(false)"
          >确定</el-button
        >
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { Message } from 'element-ui'
import { findArrayItem, removeEmptyItem } from '@/utils'
import { queryAll as queryAllPortals } from '@/api/sys/portal'
import { queryList, save, del } from '@/api/sys/page'

const defaultPage = {
  id: '',
  name: '测试',
  code: 'page.xx',
  url: '/test',
  portalId: '',
  remarks: '测试部门'
}

const defaultBtn = {
  id: '',
  name: '测试按钮',
  code: 'perm.btn.xx',
  url: '',
  parentId: '',
  parentName: '',
  remarks: '测试按钮'
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
      page: {},
      btn: {},
      dialogVisible: false,
      btnDialogVisible: false,
      dialogType: 'add',
      searchObj: {
        name: '',
        code: '',
        portalId: ''
      },
      portals: [],
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
      }
    })
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
      this.page = { ...defaultPage }
    },

    handleAddBtn(p) {
      this.btnDialogVisible = true
      this.btn = { ...defaultBtn, parentId: p.id, parentName: p.name }
    },
    handleEdit(row) {
      if (row.type === 5) {
        this.dialogVisible = true
        this.page = { ...row }
      } else {
        this.btnDialogVisible = true
        this.btn = { ...row }
      }
    },
    handleDelete(row, isPage) {
      const ids = { ids: row.id }
      del(ids, isPage).then(res => {
        if (res.success) {
          this.queryList(row.parentId)
        }

        Message({
          message: '删除' + (res.success ? '成功' : '失败'),
          type: res.success ? 'success' : 'error',
          duration: 3 * 1000
        })
      })
    },

    handleConfirm(isPage) {
      const obj = isPage ? this.page : this.btn
      save(obj, isPage).then(res => {
        if (res.success) {
          this.queryList(obj.parentId)
          this[isPage ? 'dialogVisible' : 'btnDialogVisible'] = false
        } else {
          Message({
            message: (obj.id ? '更新' : '新增') + '失败' + ' --> ' + res.msg,
            type: 'error',
            duration: 3 * 1000
          })
        }
      })
    },

    getPageBtns(row) {
      const page = findArrayItem(this.pagination.list || [], 'id', row.id)
      if (!page.btns) {
        this.queryList(row.id)
      }
    },

    queryList(pageId) {
      const param = {
        ...removeEmptyItem(this.searchObj),
        size: this.pagination.size,
        current: this.pagination.current
      }
      queryList(param, pageId).then(res => {
        if (res.success) {
          if (pageId) {
            const page = findArrayItem(this.pagination.list || [], 'id', pageId)
            this.$set(page, 'btns', res.data)
          } else {
            const { current, size, total, records } = res.data
            this.pagination = {
              ...this.pagination,
              current,
              size,
              total,
              list: records
            }
          }
        }
      })
    }
  }
}
</script>

<style></style>
