<template>
  <div class="page-wrapper">
    <el-card>
      <div class="flex-between" style="margin-bottom: 16px">
        <el-form inline :model="query">
          <el-form-item label="姓名">
            <el-input v-model="query.name" placeholder="医生姓名" />
          </el-form-item>
          <el-form-item label="科室">
            <el-input v-model="query.department" placeholder="所属科室" />
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="loadData">搜索</el-button>
            <el-button @click="reset">重置</el-button>
          </el-form-item>
        </el-form>
        <el-button type="primary" @click="openDialog()">新增医生</el-button>
      </div>
      <el-table :data="list" border>
        <el-table-column prop="name" label="姓名" width="120" />
        <el-table-column prop="department" label="科室" width="140" />
        <el-table-column prop="title" label="职称" width="140" />
        <el-table-column prop="phone" label="电话" width="160" />
        <el-table-column prop="description" label="简介" />
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
        <el-form-item label="科室">
          <el-input v-model="dialog.form.department" />
        </el-form-item>
        <el-form-item label="职称">
          <el-input v-model="dialog.form.title" />
        </el-form-item>
        <el-form-item label="电话">
          <el-input v-model="dialog.form.phone" />
        </el-form-item>
        <el-form-item label="简介">
          <el-input v-model="dialog.form.description" type="textarea" />
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
import { fetchDoctors, createDoctor, updateDoctor, removeDoctor, type Doctor } from '../api/modules/doctor';
import { ElMessage, ElMessageBox } from 'element-plus';

const query = reactive({
  name: '',
  department: ''
});

const list = ref<Doctor[]>([]);

const dialog = reactive<{
  visible: boolean;
  title: string;
  form: Doctor;
}>({
  visible: false,
  title: '',
  form: { name: '' }
});

const loadData = async () => {
  list.value = await fetchDoctors(query);
};

const reset = () => {
  query.name = '';
  query.department = '';
  loadData();
};

const openDialog = (row?: Doctor) => {
  if (row) {
    dialog.form = { ...row };
    dialog.title = '编辑医生';
  } else {
    dialog.form = { name: '', department: '', title: '', phone: '' };
    dialog.title = '新增医生';
  }
  dialog.visible = true;
};

const save = async () => {
  if (!dialog.form.name) {
    ElMessage.warning('请输入姓名');
    return;
  }
  if (dialog.form.id) {
    await updateDoctor(dialog.form.id, dialog.form);
  } else {
    await createDoctor(dialog.form);
  }
  ElMessage.success('保存成功');
  dialog.visible = false;
  await loadData();
};

const remove = (row: Doctor) => {
  ElMessageBox.confirm(`确认删除医生【${row.name}】吗？`, '提示', { type: 'warning' })
    .then(async () => {
      await removeDoctor(row.id!);
      ElMessage.success('删除成功');
      loadData();
    })
    .catch(() => undefined);
};

onMounted(loadData);
</script>

