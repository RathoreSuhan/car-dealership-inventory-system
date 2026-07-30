import axiosInstance from "../api/axios";

export const getVehicles = () =>
    axiosInstance.get("/vehicles");

export const searchVehicles = (params) =>
    axiosInstance.get("/vehicles/search", {
        params,
    });

export const addVehicle = (data) =>
    axiosInstance.post("/vehicles", data);

export const updateVehicle = (id, data) =>
    axiosInstance.put(`/vehicles/${id}`, data);

export const deleteVehicle = (id) =>
    axiosInstance.delete(`/vehicles/${id}`);

export const purchaseVehicle = (id) =>
    axiosInstance.post(`/vehicles/${id}/purchase`);

export const restockVehicle = (id, quantity) =>
    axiosInstance.post(
        `/vehicles/${id}/restock`,
        null,
        {
            params: { quantity },
        }
    );