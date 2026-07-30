import axios from "axios";

const axiosInstance = axios.create({
    baseURL: import.meta.env.VITE_API_BASE_URL,
    headers: {
        "Content-Type": "application/json",
    },
});

/*
 * Automatically attaches JWT before every request.
 */
axiosInstance.interceptors.request.use(

    (config) => {

        const token = localStorage.getItem("token"); // Read JWT from browser

        if (token) {

            config.headers.Authorization = `Bearer ${token}`; // Attach JWT

        }

        return config;

    },

    (error) => Promise.reject(error) // Forward request errors

);

export default axiosInstance;