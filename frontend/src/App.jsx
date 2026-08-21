import { ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import AppRoutes from './routes/AppRoutes';
import Navbar from './components/Navbar';
import Footer from './components/Footer';

export default function App() { return <><Navbar /><AppRoutes /><Footer /><ToastContainer position="bottom-right" /></>; }
