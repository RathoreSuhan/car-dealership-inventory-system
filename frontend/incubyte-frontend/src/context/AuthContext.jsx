import { createContext, useState } from "react";

export const AuthContext = createContext(); // Global authentication context

export function AuthProvider({ children }) {

    // Load token once when application starts
    const [token, setToken] = useState(
        localStorage.getItem("token")
    );

    // Save token after successful login
    const login = (jwt) => {

        localStorage.setItem("token", jwt); // Persist JWT

        setToken(jwt); // Update React state

    };

    // Remove token during logout
    const logout = () => {

        localStorage.removeItem("token"); // Remove JWT

        setToken(null); // Clear state

    };

    return (

        <AuthContext.Provider
            value={{
                token,
                login,
                logout,
                isAuthenticated: !!token, // true if token exists
            }}
        >

            {children}

        </AuthContext.Provider>

    );

}