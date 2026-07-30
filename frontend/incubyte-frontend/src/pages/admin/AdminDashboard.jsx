import { useEffect, useState } from "react";
import toast from "react-hot-toast";

import {

    addVehicle,

    getVehicles,

} from "../../services/vehicleService";

import VehicleForm from "../../components/vehicle/VehicleForm";
import VehicleTable from "../../components/vehicle/VehicleTable";

/*
 * Admin Dashboard
 *
 * Handles:
 * 1. Add Vehicle
 * 2. Display Vehicles
 */
export default function AdminDashboard() {

    // Stores all vehicles from backend

    const [vehicles, setVehicles] = useState([]);

    /*
     * Load all vehicles from backend.
     */
    const loadVehicles = async () => {

        try {

            // Axios returns full response object
            const response = await getVehicles();

            // Store actual vehicle array
            setVehicles(response.data);

        }

        catch (error) {

            toast.error("Unable to load vehicles.");

        }

    };

    /*
     * Add a new vehicle.
     */
    const handleAddVehicle = async (data) => {

        try {

            // Create vehicle
            await addVehicle(data);

            toast.success("Vehicle added successfully.");

            // Refresh vehicle list
            loadVehicles();

        }

        catch (error) {

            toast.error(

                error.response?.data?.message ||

                "Unable to add vehicle."

            );

        }

    };

    /*
     * Placeholder for edit feature.
     */
    const handleEditVehicle = (vehicle) => {

        console.log("Edit :", vehicle);

    };

    /*
     * Placeholder for delete feature.
     */
    const handleDeleteVehicle = (id) => {

        console.log("Delete :", id);

    };

    /*
     * Placeholder for restock feature.
     */
    const handleRestockVehicle = (vehicle) => {

        console.log("Restock :", vehicle);

    };

    /*
     * Load vehicles once page opens.
     */
    useEffect(() => {

        loadVehicles();

    }, []);

    return (

        <div className="max-w-7xl mx-auto p-8">

            <h1 className="text-3xl font-bold mb-8">

                Admin Dashboard

            </h1>

            {/* Vehicle Form */}

            <VehicleForm

                onSubmit={handleAddVehicle}

                buttonText="Add Vehicle"

            />

            {/* Vehicle Table */}

            <VehicleTable

                vehicles={vehicles}

                onEdit={handleEditVehicle}

                onDelete={handleDeleteVehicle}

                onRestock={handleRestockVehicle}

            />

        </div>

    );

}