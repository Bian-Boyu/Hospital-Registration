import request from '../request';

export interface Registration {
  id?: number;
  patientId: number;
  doctorId: number;
  patientName?: string;
  doctorName?: string;
  appointmentTime: string;
  status?: string;
  notes?: string;
  createdAt?: string;
}

export const fetchRegistrations = (params?: {
  patientName?: string;
  doctorName?: string;
  status?: string;
}) => request.get<Registration[]>('/registrations', { params });

export const createRegistration = (registration: Registration) => request.post('/registrations', registration);

export const updateRegistration = (id: number, registration: Registration) =>
  request.put(`/registrations/${id}`, registration);

export const removeRegistration = (id: number) => request.delete(`/registrations/${id}`);

