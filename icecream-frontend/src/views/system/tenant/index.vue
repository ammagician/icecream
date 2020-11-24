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
      <el-form-item label="KEY">
        <el-input v-model="searchObj.uKey" placeholder="唯一标识" />
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
      <el-table-column
          v-for="col in columns"
          :key="col.prop"
          :prop="col.prop"
          :label="col.label"
          :width="col.width"
          :fixed="col.fixed"
        >
          <template slot-scope="scope">
            <CustomColumn
              v-if="col.render"
              :render="col.render"
              :row="scope.row"
              :column="col"
            />
            <span v-else>{{ scope.row[col.prop] }}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="操作">
        <template slot-scope="scope">
          <el-button
            type="danger"
            size="mini"
            icon="el-icon-key"
            @click="handleReset(scope.row)"
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
    >
    </el-pagination>

    <el-dialog
      :visible.sync="dialogVisible"
      :title="!!tenant.id ? '编辑' : '新增'"
      width="500px"
      size="small"
    >
      <el-form :model="tenant" :rules="rules" label-width="80px" size="small">
        <el-form-item label="名称" prop="name">
          <el-input v-model="tenant.name" placeholder="名称" />
        </el-form-item>
        <el-form-item label="组织名称" prop="rootOrg">
          <el-input v-model="tenant.rootOrg" placeholder="示例：portal.xx" :disabled="!!tenant.id"/>
        </el-form-item>
        <el-form-item label="组织编码" prop="rootOrgCode" v-if="!tenant.id">
          <el-input v-model="tenant.rootOrgCode" placeholder="示例：portal.xx" />
        </el-form-item>
        <el-form-item label="授权门户">
          <el-select
            v-model="tenant.portals"
            multiple
            placeholder="请选择..."
            style="width:380px;"
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
            v-model="tenant.remarks"
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
import CustomColumn from '@/components/CustomColumn'
import { removeEmptyItem } from '@/utils'
import { queryAll as queryAllPortals } from '@/api/sys/portal'
import { queryList, save, del, resetPwd } from '@/api/sys/tenant'
const defaultTenant = {
  id: '',
  name: '测试租户',
  key: '',
  remarks: '测试租户',
  rootOrg: "根组织"
}
export default {
  components:{
    CustomColumn
  },
  data() {
    return {
      columns: [
        {
          prop: 'name',
          label: '名称'
        },
        {
          prop: 'uKey',
          label: '唯一标识'
        },
        {
          prop: 'rootOrg',
          label: '根组织',
          width: '140px'
        },
        {
          prop: 'portals',
          label: '授权门户',
          render: (r, params)=>{
            const ps = this.portals;
            const names = (params.row.portals.map(p=>{
              const m = ps.find(e=>{
                return e.id === p
              });
              return m.name
            })).join(",")
            return <span>{names}</span>
          }
        },
        {
          prop: 'remarks',
          label: '备注'
        }
      ],
      pagination: {
        current: 1,
        size: 10,
        total: 0,
        list: []
      },
      tenant: {},
      portals: [],
      dialogVisible: false,
      dialogType: 'add',
      searchObj: {
        name: '',
        key: ''
      },
      rules: {
        name: [
          { required: true, message: '请输入名称', trigger: 'blur' },
          { min: 2, max: 64, message: '长度在 2 到 64 个字符', trigger: 'blur' }
        ],
        rootOrg: [{ max: 64, message: '最多只能输入64个字符', trigger: 'blur' }]
      }
    }
  },
  created() {
    queryAllPortals().then(res => {
      if (res.success) {
        this.portals = res.data.map(p=>{
          return {
            id: p.id + '',
            name: p.name
          }
        })
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
      this.tenant = { ...defaultTenant }
    },
    handleEdit(row) {
      this.dialogVisible = true
      this.tenant = { ...row }
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

    handleReset(row){
      resetPwd(row).then(res => {
        Message({
            message: "重置" + (res.success ? '成功' : '失败'),
            type: res.success ?'success': 'error',
            duration: 3 * 1000
          })
      })
    },

    handleConfirm() {
      save(this.tenant).then(res => {
        if (res.success) {
          this.queryList()
          this.dialogVisible = false
        } else {
          Message({
            message: (this.tenant.id ? '更新' : '新增') + '失败',
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
