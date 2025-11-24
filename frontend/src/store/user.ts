import { defineStore } from 'pinia';

interface AdminProfile {
  id: number;
  username: string;
  realName?: string;
}

interface UserState {
  token: string | null;
  profile: AdminProfile | null;
}

export const useUserStore = defineStore('user', {
  state: (): UserState => ({
    token: localStorage.getItem('token'),
    profile: localStorage.getItem('profile') ? JSON.parse(localStorage.getItem('profile') as string) : null
  }),
  actions: {
    setLogin(profile: AdminProfile, token = 'static-token') {
      this.profile = profile;
      this.token = token;
      localStorage.setItem('token', token);
      localStorage.setItem('profile', JSON.stringify(profile));
    },
    reset() {
      this.profile = null;
      this.token = null;
      localStorage.removeItem('token');
      localStorage.removeItem('profile');
    }
  }
});

