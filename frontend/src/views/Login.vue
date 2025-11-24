<template>
  <div class="login-page">
    <el-card class="login-card">
      <h2>医院挂号管理系统</h2>
      <el-form :model="form" ref="formRef" :rules="rules" label-width="80px">
        <el-form-item label="账号" prop="username">
          <el-input v-model="form.username" placeholder="请输入账号" />
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input v-model="form.password" type="password" placeholder="请输入密码" />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" :loading="loading" @click="onSubmit" style="width: 100%">登录</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script setup lang="ts">
import { reactive, ref } from 'vue';
import { useRouter } from 'vue-router';
import { login } from '../api/modules/auth';
import { useUserStore } from '../store/user';
import type { FormInstance, FormRules } from 'element-plus';

const router = useRouter();
const store = useUserStore();
const loading = ref(false);

const form = reactive({
  username: 'admin',
  password: '123456'
});

const rules: FormRules = {
  username: [{ required: true, message: '请输入账号', trigger: 'blur' }],
  password: [{ required: true, message: '请输入密码', trigger: 'blur' }]
};

const formRef = ref<FormInstance>();

const onSubmit = () => {
  formRef.value?.validate(async (valid) => {
    if (!valid) return;
    loading.value = true;
    try {
      const data = await login(form);
      store.setLogin(data);
      router.replace('/dashboard');
    } finally {
      loading.value = false;
    }
  });
};
</script>

<style scoped>
.login-page {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background: linear-gradient(120deg, #3a7bd5, #3a6073);
}

.login-card {
  width: 400px;
}

h2 {
  text-align: center;
  margin-bottom: 20px;
}
</style>

