<template>
  <div class="page-wrapper">
    <el-row :gutter="16">
      <el-col :span="8" v-for="card in cards" :key="card.title">
        <el-card class="stat-card" @click="go(card.route)">
          <div class="card-title">{{ card.title }}</div>
          <div class="card-value">{{ card.value }}</div>
          <div class="card-desc">{{ card.desc }}</div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue';
import { useRouter } from 'vue-router';
import { fetchDoctors } from '../api/modules/doctor';
import { fetchPatients } from '../api/modules/patient';
import { fetchRegistrations } from '../api/modules/registration';

const router = useRouter();

const cards = ref([
  { title: '医生数量', value: 0, desc: '当前可预约医生', route: '/doctors' },
  { title: '患者数量', value: 0, desc: '已登记患者', route: '/patients' },
  { title: '挂号记录', value: 0, desc: '历史挂号次数', route: '/registrations' }
]);

onMounted(async () => {
  const [doctors, patients, registrations] = await Promise.all([
    fetchDoctors(),
    fetchPatients(),
    fetchRegistrations()
  ]);
  cards.value[0].value = doctors.length;
  cards.value[1].value = patients.length;
  cards.value[2].value = registrations.length;
});

const go = (path: string) => {
  router.push(path);
};
</script>

<style scoped>
.stat-card {
  cursor: pointer;
  transition: transform 0.15s ease, box-shadow 0.15s ease;
}

.stat-card:hover {
  transform: translateY(-4px);
  box-shadow: 0 12px 24px rgba(64, 158, 255, 0.2);
}

.card-title {
  font-size: 16px;
  margin-bottom: 8px;
}

.card-value {
  font-size: 32px;
  color: #409eff;
}

.card-desc {
  color: #909399;
}
</style>

