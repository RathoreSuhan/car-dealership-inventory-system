import { Routes, Route } from "react-router-dom"; // React Router

import Navbar from "./components/layout/Navbar";
import AdminDashboard from "./pages/admin/AdminDashboard";
import Dashboard from "./pages/user/Dashboard";
import Login from "./pages/auth/Login";
import Register from "./pages/auth/Register";

export default function App() {

    return (

        <>

            {/* Top navigation bar */}

            <Navbar />

            <Routes>

                {/* Home */}

                <Route

                    path="/"

                    element={<Dashboard />}

                />

                {/* Dashboard */}

                <Route

                    path="/dashboard"

                    element={<Dashboard />}

                />

                {/* Login */}

                <Route

                    path="/login"

                    element={<Login />}

                />

                {/* Register */}

                <Route

                    path="/register"

                    element={<Register />}

                />

                <Route

                    path="/admin"

                    element={<AdminDashboard />}

                />

            </Routes>

        </>

    );

}