<template>
  <div class="page-wrapper">
    <el-card>
      <div class="flex-between" style="margin-bottom: 16px">
        <el-form inline :model="query">
          <el-form-item label="姓名">
            <el-input v-model="query.name" placeholder="患者姓名" />
          </el-form-item>
          <el-form-item label="电话">
            <el-input v-model="query.phone" placeholder="联系电话" />
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="loadData">搜索</el-button>
            <el-button @click="reset">重置</el-button>
          </el-form-item>
        </el-form>
        <el-button type="primary" @click="openDialog()">新增患者</el-button>
      </div>
      <el-table :data="list" border>
        <el-table-column prop="name" label="姓名" width="120" />
        <el-table-column prop="gender" label="性别" width="100" />
        <el-table-column prop="age" label="年龄" width="100" />
        <el-table-column prop="phone" label="电话" width="150" />
        <el-table-column prop="idNumber" label="证件号" />
        <el-table-column prop="address" label="地址" />
        <el-table-column label="操作" width="180">
          <template #default="{ row }">
            <el-button type="primary" link @click="openDialog(row)">编辑</el-button>
            <el-button type="danger" link @click="remove(row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>

    <el-dialog v-model="dialog.visible" :title="dialog.title">
      <el-form :model="dialog.form" label-width="100px">
        <el-form-item label="姓名">
          <el-input v-model="dialog.form.name" />
        </el-form-item>
        <el-form-item label="性别">
          <el-select v-model="dialog.form.gender" placeholder="请选择">
            <el-option label="男" value="男" />
            <el-option label="女" value="女" />
          </el-select>
        </el-form-item>
        <el-form-item label="年龄">
          <el-input-number v-model="dialog.form.age" :min="0" :max="120" />
        </el-form-item>
        <el-form-item label="电话">
          <el-input v-model="dialog.form.phone" />
        </el-form-item>
        <el-form-item label="证件号">
          <el-input v-model="dialog.form.idNumber" />
        </el-form-item>
        <el-form-item label="地址">
          <el-input v-model="dialog.form.address" type="textarea" />
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="dialog.visible = false">取消</el-button>
        <el-button type="primary" @click="save">保存</el-button>
      </template>
    </el-dialog>
  </div>
</template>

<script setup lang="ts">
import { reactive, ref, onMounted } from 'vue';
import { fetchPatients, createPatient, updatePatient, removePatient, type Patient } from '../api/modules/patient';
import { ElMessage, ElMessageBox } from 'element-plus';

const query = reactive({
  name: '',
  phone: ''
});

const list = ref<Patient[]>([]);

const dialog = reactive<{
  visible: boolean;
  title: string;
  form: Patient;
}>({
  visible: false,
  title: '',
  form: { name: '' }
});

const loadData = async () => {
  list.value = await fetchPatients(query);
};

const reset = () => {
  query.name = '';
  query.phone = '';
  loadData();
};

const openDialog = (row?: Patient) => {
  if (row) {
    dialog.form = { ...row };
    dialog.title = '编辑患者';
  } else {
    dialog.form = { name: '', gender: '男', age: 18 };
    dialog.title = '新增患者';
  }
  dialog.visible = true;
};

const save = async () => {
  if (!dialog.form.name) {
    ElMessage.warning('请输入姓名');
    return;
  }
  if (dialog.form.id) {
    await updatePatient(dialog.form.id, dialog.form);
  } else {
    await createPatient(dialog.form);
  }
  ElMessage.success('保存成功');
  dialog.visible = false;
  loadData();
};

const remove = (row: Patient) => {
  ElMessageBox.confirm(`确认删除患者【${row.name}】吗？`, '提示', { type: 'warning' })
    .then(async () => {
      await removePatient(row.id!);
      ElMessage.success('删除成功');
      loadData();
    })
    .catch(() => undefined);
};

onMounted(loadData);
</script>

