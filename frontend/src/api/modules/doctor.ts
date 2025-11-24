import request from '../request';

export interface Doctor {
  id?: number;
  name: string;
  department?: string;
  title?: string;
  phone?: string;
  description?: string;
}

export const fetchDoctors = (params?: { name?: string; department?: string }) => {
  return request.get<Doctor[]>('/doctors', { params });
};

export const createDoctor = (doctor: Doctor) => request.post('/doctors', doctor);

export const updateDoctor = (id: number, doctor: Doctor) => request.put(`/doctors/${id}`, doctor);

export const removeDoctor = (id: number) => request.delete(`/doctors/${id}`);

