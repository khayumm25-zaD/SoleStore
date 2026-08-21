import { Navigate, Outlet } from 'react-router-dom';
import { useAuth } from '../context/AuthContext';
export default function ProtectedRoute({ admin = false }) { const { isAuthenticated, isAdmin } = useAuth(); if (!isAuthenticated) return <Navigate to="/login" replace />; if (admin && !isAdmin) return <Navigate to="/" replace />; return <Outlet />; }
