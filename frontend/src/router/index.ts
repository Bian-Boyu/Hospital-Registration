import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router';
import { useUserStore } from '../store/user';
import MainLayout from '../layouts/MainLayout.vue';

const routes: RouteRecordRaw[] = [
  {
    path: '/login',
    component: () => import('../views/Login.vue')
  },
  {
    path: '/',
    component: MainLayout,
    redirect: '/dashboard',
    children: [
      {
        path: 'dashboard',
        component: () => import('../views/Dashboard.vue')
      },
      {
        path: 'doctors',
        component: () => import('../views/DoctorManage.vue')
      },
      {
        path: 'patients',
        component: () => import('../views/PatientManage.vue')
      },
      {
        path: 'registrations',
        component: () => import('../views/RegistrationManage.vue')
      }
    ]
  }
];

const router = createRouter({
  history: createWebHistory(),
  routes
});

router.beforeEach((to, _from, next) => {
  const store = useUserStore();
  if (to.path !== '/login' && !store.token) {
    next('/login');
  } else if (to.path === '/login' && store.token) {
    next('/dashboard');
  } else {
    next();
  }
});

export default router;

