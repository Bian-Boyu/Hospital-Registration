import request from '../request';

interface LoginPayload {
  username: string;
  password: string;
}

export const login = (data: LoginPayload) => request.post('/auth/login', data);

