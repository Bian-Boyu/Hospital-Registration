<template>
  <div class="page-wrapper">
    <el-card>
      <div class="flex-between" style="margin-bottom: 16px">
        <el-form inline :model="query">
          <el-form-item label="患者">
            <el-input v-model="query.patientName" placeholder="患者姓名" />
          </el-form-item>
          <el-form-item label="医生">
            <el-input v-model="query.doctorName" placeholder="医生姓名" />
          </el-form-item>
          <el-form-item label="状态">
            <el-select v-model="query.status" placeholder="全部" clearable style="width: 140px">
              <el-option label="待就诊" value="待就诊" />
              <el-option label="已就诊" value="已就诊" />
              <el-option label="已取消" value="已取消" />
            </el-select>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="loadData">搜索</el-button>
            <el-button @click="reset">重置</el-button>
          </el-form-item>
        </el-form>
        <el-button type="primary" @click="openDialog()">新增挂号</el-button>
      </div>
      <el-table :data="list" border>
        <el-table-column prop="patientName" label="患者" width="140" />
        <el-table-column prop="doctorName" label="医生" width="140" />
        <el-table-column prop="appointmentTime" label="预约时间" width="190" />
        <el-table-column prop="status" label="状态" width="120" />
        <el-table-column prop="notes" label="备注" />
        <el-table-column label="操作" width="200">
          <template #default="{ row }">
            <el-button type="primary" link @click="openDialog(row)">编辑</el-button>
            <el-button type="danger" link @click="remove(row)">取消</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>

    <el-dialog v-model="dialog.visible" :title="dialog.title" width="520px">
      <el-form :model="dialog.form" label-width="100px">
        <el-form-item label="患者">
          <el-select v-model="dialog.form.patientId" filterable style="width: 100%">
            <el-option v-for="item in patients" :key="item.id" :label="item.name" :value="item.id" />
          </el-select>
        </el-form-item>
        <el-form-item label="医生">
          <el-select v-model="dialog.form.doctorId" filterable style="width: 100%">
            <el-option v-for="item in doctors" :key="item.id" :label="item.name" :value="item.id" />
          </el-select>
        </el-form-item>
        <el-form-item label="预约时间">
          <el-date-picker
            v-model="dialog.form.appointmentTime"
            type="datetime"
            format="YYYY-MM-DD HH:mm"
            value-format="YYYY-MM-DD HH:mm:ss"
            style="width: 100%"
          />
        </el-form-item>
        <el-form-item label="状态">
          <el-select v-model="dialog.form.status" style="width: 100%">
            <el-option label="待就诊" value="待就诊" />
            <el-option label="已就诊" value="已就诊" />
            <el-option label="已取消" value="已取消" />
          </el-select>
        </el-form-item>
        <el-form-item label="备注">
          <el-input v-model="dialog.form.notes" type="textarea" />
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
import {
  fetchRegistrations,
  createRegistration,
  updateRegistration,
  removeRegistration,
  type Registration
} from '../api/modules/registration';
import { fetchDoctors, type Doctor } from '../api/modules/doctor';
import { fetchPatients, type Patient } from '../api/modules/patient';
import { ElMessage, ElMessageBox } from 'element-plus';

const query = reactive({
  patientName: '',
  doctorName: '',
  status: ''
});

const list = ref<Registration[]>([]);
const doctors = ref<Doctor[]>([]);
const patients = ref<Patient[]>([]);

const dialog = reactive<{
  visible: boolean;
  title: string;
  form: Registration;
}>({
  visible: false,
  title: '',
  form: {
    patientId: 0,
    doctorId: 0,
    appointmentTime: '',
    status: '待就诊'
  }
});

const loadData = async () => {
  list.value = await fetchRegistrations(query);
};

const loadOptions = async () => {
  [doctors.value, patients.value] = await Promise.all([fetchDoctors(), fetchPatients()]);
};

const reset = () => {
  query.patientName = '';
  query.doctorName = '';
  query.status = '';
  loadData();
};

const openDialog = (row?: Registration) => {
  if (row) {
    dialog.form = { ...row };
    dialog.title = '编辑挂号';
  } else {
    dialog.form = {
      patientId: patients.value[0]?.id || 0,
      doctorId: doctors.value[0]?.id || 0,
      appointmentTime: '',
      status: '待就诊',
      notes: ''
    };
    dialog.title = '新增挂号';
  }
  dialog.visible = true;
};

const save = async () => {
  if (!dialog.form.patientId || !dialog.form.doctorId || !dialog.form.appointmentTime) {
    ElMessage.warning('请选择完整信息');
    return;
  }
  if (dialog.form.id) {
    await updateRegistration(dialog.form.id, dialog.form);
  } else {
    await createRegistration(dialog.form);
  }
  ElMessage.success('保存成功');
  dialog.visible = false;
  loadData();
};

const remove = (row: Registration) => {
  ElMessageBox.confirm('确认取消该挂号吗？', '提示', { type: 'warning' })
    .then(async () => {
      await removeRegistration(row.id!);
      ElMessage.success('已取消');
      loadData();
    })
    .catch(() => undefined);
};

onMounted(async () => {
  await Promise.all([loadOptions(), loadData()]);
});
</script>

