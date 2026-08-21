import { createContext, useContext, useEffect, useState } from 'react';
import { addCartItem, clearCart, getCart, removeCartItem, updateCartItem } from '../services/cartService';

const CartContext = createContext(null);
export function CartProvider({ children }) {
	const [cart, setCart] = useState({ items: [], total: 0 });
	const refresh = async () => { if (localStorage.getItem('solestore_token')) setCart((await getCart()).data); };
	useEffect(() => { refresh().catch(() => {}); }, []);
	const run = async (request) => { const next = (await request).data; setCart(next); return next; };
	return <CartContext.Provider value={{ cart, refresh, addItem: (payload) => run(addCartItem(payload)), updateItem: (id, payload) => run(updateCartItem(id, payload)), removeItem: (id) => run(removeCartItem(id).then(() => getCart())), clear: () => run(clearCart().then(() => getCart())), count: cart.items?.reduce((sum, item) => sum + item.quantity, 0) || 0 }}>{children}</CartContext.Provider>;
}
export const useCart = () => useContext(CartContext);
