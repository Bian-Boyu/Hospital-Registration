import axios from 'axios';
import { ElMessage } from 'element-plus';
import { useUserStore } from '../store/user';

const instance = axios.create({
  baseURL: '/api',
  timeout: 10000
});

instance.interceptors.request.use((config) => {
  const store = useUserStore();
  if (store.token) {
    config.headers.Authorization = `Bearer ${store.token}`;
  }
  return config;
});

instance.interceptors.response.use(
  (response) => {
    const payload = response.data;
    if (payload.code !== 0) {
      ElMessage.error(payload.message || '请求失败');
      return Promise.reject(payload);
    }
    return payload.data;
  },
  (error) => {
    ElMessage.error(error.response?.data?.message || error.message || '网络错误');
    return Promise.reject(error);
  }
);

export default instance;

