import request from '../request';

export interface Patient {
  id?: number;
  name: string;
  gender?: string;
  age?: number;
  phone?: string;
  idNumber?: string;
  address?: string;
}

export const fetchPatients = (params?: { name?: string; phone?: string }) =>
  request.get<Patient[]>('/patients', { params });

export const createPatient = (patient: Patient) => request.post('/patients', patient);

export const updatePatient = (id: number, patient: Patient) => request.put(`/patients/${id}`, patient);

export const removePatient = (id: number) => request.delete(`/patients/${id}`);

