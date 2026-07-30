import axiosInstance from "../api/axios";

export const login = (data) =>
    axiosInstance.post("/auth/login", data);

export const register = (data) =>
    axiosInstance.post("/auth/register", data);