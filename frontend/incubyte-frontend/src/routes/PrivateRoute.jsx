import { Navigate } from "react-router-dom";
import useAuth from "../hooks/useAuth";

// Protects private pages
export default function PrivateRoute({ children }) {

    const { isAuthenticated } = useAuth();

    if (!isAuthenticated) {

        return <Navigate to="/login" replace />; // Redirect if not logged in

    }

    return children; // Render protected component

}