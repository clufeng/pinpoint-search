<template>
  <div>
    <el-row type="flex">
      <el-form
        ref="searchForm"
        :inline="true"
        :rules="rules"
        :model="searchForm"
        class="demo-form-inline"
      >
        <el-form-item prop="app" required>
          <!-- <el-input v-model="searchForm.app" placeholder="选择应用"></el-input> -->
          <el-select v-model="searchForm.app" filterable placeholder="请选择应用" @change="changeApp">
            <el-option v-for="app in apps" :key="app.name" :label="app.name" :value="app.name"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button
            :loading="agentIdsBtnLoading"
            :disabled="agentBtnStatus"
            @click="agentIdsBtnClick"
          >Agent ID (已选择{{selectAgentIds.length}}个)</el-button>
        </el-form-item>
        <el-form-item>
          <el-input v-model="searchForm.rpcName" placeholder="PATH" style="width:210px" clearable></el-input>
        </el-form-item>
        <el-form-item>
          <el-input v-model="searchForm.threshold" placeholder=" > exec(ms)" style="width:120px" clearable></el-input>
        </el-form-item>
        <el-form-item>
          <el-date-picker
            v-model="searchForm.datatime"
            type="datetimerange"
            :picker-options="pickerOptions"
            range-separator="至"
            start-placeholder="开始日期"
            end-placeholder="结束日期"
            align="right"
          ></el-date-picker>
        </el-form-item>

        <el-form-item>
          <el-select v-model="searchForm.hasErr" placeholder="有无异常" style="width:120px;" clearable>
            <el-option label="有异常" value="1"></el-option>
            <el-option label="无异常" value="0"></el-option>
          </el-select>
        </el-form-item>

        <el-form-item>
          <el-button type="primary" @click="onSubmit">查询</el-button>
        </el-form-item>
      </el-form>
    </el-row>

    <el-table :data="tableDatas" border style="width: 100%">
      <el-table-column label="Transaction ID" width="240">
        <template slot-scope="scope">
          <el-link :href="scope.row.link" target="_blank" type="primary">{{scope.row.transId}}</el-link>
        </template>
      </el-table-column>
      <el-table-column prop="requestTime" label="StartTime" width="180"></el-table-column>
      <el-table-column prop="rpcName" label="PATH" width="300"></el-table-column>
      <el-table-column prop="elapsed" label="Exec(ms)" width="120"></el-table-column>
      <el-table-column prop="agentId" label="Agent ID"></el-table-column>
      <el-table-column prop="appName" label="Application"></el-table-column>
      <el-table-column label="Exception" align="center" width="100">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.errorCode === 1" type="danger">有异常</el-tag>
        </template>
      </el-table-column>

    </el-table>
    <el-row>
      <el-pagination class="page"
            :page-size="pageSize"
            :page-sizes="[10, 20, 50, 100]"
            :pager-count="pagerCounter"
            layout="total, sizes, prev, pager, next, jumper"
            :total="total"
            @current-change="changePage"
            @size-change="changePageSize"
            >
          </el-pagination>
    </el-row>

    <el-dialog title="选择需要查询的 Agent ID" width="40%" :visible.sync="agentIdDialogFormVisible">
      <div class="agentIdContainer">
        <el-row type="flex">
          <el-checkbox-group v-model="tempAgentIds">
          <el-checkbox v-for="agentId in agentIds" :key="agentId" :label="agentId" border></el-checkbox>
          </el-checkbox-group>
        </el-row>
      </div>
      <div slot="footer" class="dialog-footer">
        <el-button @click="selectAll">全选</el-button>
        <el-button @click="agentIdDialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="confrimAgentIds">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { getApps, getPinpointDatas, getAgentIds } from '@/api/req'
import * as _ from 'lodash'

export default {
  methods: {
    onSubmit () {
      this.$refs.searchForm.validate(valid => {
        if (valid) {
          if (!_.has(this.searchForm, 'app')) {
            return
          }

          if (_.isEmpty(this.selectAgentIds)) {
            return
          }

          const from = this.searchForm.datatime[0]
          const to = this.searchForm.datatime[1]

          const params = {
            to: to.getTime(),
            from: from.getTime(),
            agentIds: this.selectAgentIds
          }

          if (_.has(this.searchForm, 'rpcName')) {
            params.rpcName = this.searchForm.rpcName
          }

          if (_.has(this.searchForm, 'hasErr')) {
            params.hasErr = this.searchForm.hasErr
          }

          if (_.has(this.searchForm, 'threshold')) {
            params.threshold = this.searchForm.threshold
          }

          getPinpointDatas(this.searchForm.app, params).then(reqs => {
            this.tableData = reqs
            this.total = this.tableData.length
          })
        }
      })
    },
    queryApps () {
      getApps().then(apps => {
        this.apps = apps
      })
    },
    createPickerOption (text, minute) {
      return {
        text: text,
        onClick (picker) {
          const end = new Date()
          const start = new Date()
          start.setTime(start.getTime() - minute * 60 * 1000)
          picker.$emit('pick', [start, end])
        }
      }
    },
    changeApp (app) {
      if (_.isNil(app)) {
        return
      }

      this.agentIdsBtnLoading = true

      getAgentIds(app)
        .then(datas => {
          this.agentIds = datas
          this.selectAgentIds = datas
          this.agentIdsBtnLoading = false
        })
        .catch(e => {
          this.agentIdsBtnLoading = false
        })
    },
    agentIdsBtnClick () {
      this.agentIdDialogFormVisible = true
      this.tempAgentIds = _.cloneDeep(this.selectAgentIds)
    },
    selectAll () {
      this.tempAgentIds = this.agentIds
    },
    confrimAgentIds () {
      this.selectAgentIds = this.tempAgentIds
      this.agentIdDialogFormVisible = false
    },
    changePage (page) {
      console.log('page', page)
      this.currPage = page
    },
    changePageSize (pageSize) {
      this.pageSize = pageSize
    }
  },

  mounted () {
    this.queryApps()
    const end = new Date()
    const start = new Date()
    start.setTime(start.getTime() - 5 * 60 * 1000)
    this.searchForm.datatime = [start, end]
  },

  computed: {
    agentBtnStatus () {
      return !_.has(this.searchForm, 'app')
    },
    tableDatas () {
      const index = (this.currPage - 1) * this.pageSize
      const end = index + this.pageSize > this.total ? this.total : index + this.pageSize
      return this.tableData.slice(index, end)
    }
  },

  data () {
    return {
      tableData: [],
      searchForm: {
        datatime: []
      },
      apps: {},
      agentIds: [],
      selectAgentIds: [],
      tempAgentIds: [],
      agentIdsBtnLoading: false,
      pickerOptions: {
        shortcuts: [
          this.createPickerOption('Last 5m', 5),
          this.createPickerOption('20m', 20),
          this.createPickerOption('1h', 60),
          this.createPickerOption('3h', 3 * 60),
          this.createPickerOption('6h', 6 * 60),
          this.createPickerOption('12h', 12 * 60),
          this.createPickerOption('1d', 24 * 60),
          this.createPickerOption('2d', 48 * 60)
        ]
      },
      rules: {
        app: [{ required: true, message: '请选先择应用', trigger: 'blur' }]
      },
      agentIdDialogFormVisible: false,
      total: 0,
      pageSize: 10,
      pagerCounter: 11,
      currPage: 1
    }
  }
}
</script>

<style scoped>
.agentIdContainer {
  margin-left: 20px;
  margin-right: 20px;
  margin-top: 20px;
}

.page {
  margin-top: 20px;
  text-align: right;
}

</style>
