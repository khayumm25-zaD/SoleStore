import api from './api';
export const getProducts = () => api.get('/products');
export const getProduct = (id) => api.get(`/products/${id}`);
export const createProduct = (payload) => api.post('/products', payload);
export const updateProduct = (id, payload) => api.put(`/products/${id}`, payload);
export const deleteProduct = (id) => api.delete(`/products/${id}`);
export const createVariant = (productId, payload) => api.post(`/products/${productId}/variants`, payload);
export const updateVariant = (id, payload) => api.put(`/variants/${id}`, payload);
export const deleteVariant = (id) => api.delete(`/variants/${id}`);
