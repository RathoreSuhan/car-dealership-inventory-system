import axiosInstance from "../api/axios";

export const getVehicles = async () => {

    const response = await axiosInstance.get("/vehicles");

    return response.data;

};

export const searchVehicles = async (params) => {

    const response = await axiosInstance.get(
        "/vehicles/search",
        {
            params,
        }
    );

    return response.data;

};

export const addVehicle = (data) =>
    axiosInstance.post("/vehicles", data);

/*
 * Update existing vehicle.
 */
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

