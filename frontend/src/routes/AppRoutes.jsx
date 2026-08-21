import { Routes, Route } from 'react-router-dom';
import ProtectedRoute from '../components/ProtectedRoute';
import Home from '../pages/Home';
import Login from '../pages/Login';
import Register from '../pages/Register';
import Products from '../pages/Products';
import ProductDetails from '../pages/ProductDetails';
import Cart from '../pages/Cart';
import Checkout from '../pages/Checkout';
import OrderHistory from '../pages/OrderHistory';
import OrderDetails from '../pages/OrderDetails';
import Profile from '../pages/Profile';
import AdminDashboard from '../pages/admin/AdminDashboard';
import ManageProducts from '../pages/admin/ManageProducts';
import ManageCategories from '../pages/admin/ManageCategories';
import ManageOrders from '../pages/admin/ManageOrders';
import ManageUsers from '../pages/admin/ManageUsers';
export default function AppRoutes() { return <Routes><Route path="/" element={<Home />} /><Route path="/login" element={<Login />} /><Route path="/register" element={<Register />} /><Route path="/products" element={<Products />} /><Route path="/products/:id" element={<ProductDetails />} /><Route element={<ProtectedRoute />}><Route path="/cart" element={<Cart />} /><Route path="/checkout" element={<Checkout />} /><Route path="/orders" element={<OrderHistory />} /><Route path="/orders/:id" element={<OrderDetails />} /><Route path="/profile" element={<Profile />} /></Route><Route element={<ProtectedRoute admin />}><Route path="/admin" element={<AdminDashboard />} /><Route path="/admin/products" element={<ManageProducts />} /><Route path="/admin/categories" element={<ManageCategories />} /><Route path="/admin/orders" element={<ManageOrders />} /><Route path="/admin/users" element={<ManageUsers />} /></Route></Routes>; }
