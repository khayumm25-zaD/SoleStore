import api from './api';
export const getCart = () => api.get('/cart');
export const addCartItem = (payload) => api.post('/cart/items', payload);
export const updateCartItem = (id, payload) => api.put(`/cart/items/${id}`, payload);
export const removeCartItem = (id) => api.delete(`/cart/items/${id}`);
export const clearCart = () => api.delete('/cart');
