<template>
  <el-container class="layout">
    <el-aside width="200px">
      <div class="logo">医院挂号</div>
      <el-menu :default-active="active" router>
        <el-menu-item index="/dashboard">仪表盘</el-menu-item>
        <el-menu-item index="/doctors">医生管理</el-menu-item>
        <el-menu-item index="/patients">患者管理</el-menu-item>
        <el-menu-item index="/registrations">挂号管理</el-menu-item>
      </el-menu>
    </el-aside>
    <el-container>
      <el-header>
        <div class="flex-between">
          <div>欢迎您，{{ userStore.profile?.realName || userStore.profile?.username }}</div>
          <el-button type="primary" link @click="logout">退出登录</el-button>
        </div>
      </el-header>
      <el-main>
        <router-view />
      </el-main>
    </el-container>
  </el-container>
</template>

<script setup lang="ts">
import { useRoute, useRouter } from 'vue-router';
import { computed } from 'vue';
import { useUserStore } from '../store/user';

const route = useRoute();
const router = useRouter();
const userStore = useUserStore();

const active = computed(() => route.path);

const logout = () => {
  userStore.reset();
  router.replace('/login');
};
</script>

<style scoped>
.layout {
  min-height: 100vh;
}

.logo {
  color: #fff;
  font-size: 18px;
  text-align: center;
  padding: 20px 0;
  background: #409eff;
}

.el-aside {
  background: #1f2d3d;
}

.el-menu {
  border-right: none;
}

.el-header {
  background: #fff;
  border-bottom: 1px solid #f0f0f0;
}
</style>

