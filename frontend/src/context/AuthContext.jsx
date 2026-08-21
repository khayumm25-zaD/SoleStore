import { createContext, useContext, useState } from 'react';
import { login as loginRequest, register as registerRequest } from '../services/authService';

const AuthContext = createContext(null);
export function AuthProvider({ children }) {
	const [user, setUser] = useState(() => JSON.parse(localStorage.getItem('solestore_user') || 'null'));
	const save = (response) => { localStorage.setItem('solestore_token', response.token); localStorage.setItem('solestore_user', JSON.stringify(response)); setUser(response); return response; };
	const login = async (payload) => save((await loginRequest(payload)).data);
	const register = async (payload) => save((await registerRequest(payload)).data);
	const logout = () => { localStorage.removeItem('solestore_token'); localStorage.removeItem('solestore_user'); setUser(null); };
	return <AuthContext.Provider value={{ user, login, register, logout, isAuthenticated: Boolean(user), isAdmin: user?.role === 'ROLE_ADMIN' }}>{children}</AuthContext.Provider>;
}
export const useAuth = () => useContext(AuthContext);
