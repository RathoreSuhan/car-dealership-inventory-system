import { useContext } from "react";
import { AuthContext } from "../context/AuthContext";

// Custom hook for authentication
export default function useAuth() {

    return useContext(AuthContext);

}