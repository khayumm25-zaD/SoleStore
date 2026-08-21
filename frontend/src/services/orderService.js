import api from './api';
export const getOrders = () => api.get('/orders');
export const getOrder = (id) => api.get(`/orders/${id}`);
export const createOrder = (payload) => api.post('/orders', payload);
export const getAdminOrders = () => api.get('/admin/orders');
export const updateOrderStatus = (id, status) => api.put(`/admin/orders/${id}/status`, { status });
export const getDashboard = () => api.get('/admin/dashboard');
