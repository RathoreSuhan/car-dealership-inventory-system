import { useEffect, useState } from "react";
import toast from "react-hot-toast";

import {

    addVehicle,

    getVehicles,

    updateVehicle,

} from "../../services/vehicleService";

import VehicleForm from "../../components/vehicle/VehicleForm";
import VehicleTable from "../../components/vehicle/VehicleTable";

/*
 * Admin Dashboard
 *
 * Handles:
 * 1. Add Vehicle
 * 2. Update Vehicle
 * 3. Display Vehicles
 */
export default function AdminDashboard() {

    // Stores all vehicles

    const [vehicles, setVehicles] = useState([]);

    /*
     * Vehicle currently being edited.
     *
     * null  -> Add mode
     * object -> Edit mode
     */
    const [editingVehicle, setEditingVehicle] = useState(null);

    /*
     * Load all vehicles.
     */
    const loadVehicles = async () => {

        try {

            const response = await getVehicles();

            setVehicles(response.data);

        }

        catch (error) {

            toast.error("Unable to load vehicles.");

        }

    };

    /*
     * Add new vehicle.
     */
    const handleAddVehicle = async (data) => {

        try {

            await addVehicle(data);

            toast.success("Vehicle added successfully.");

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
     * Update existing vehicle.
     */
    const handleUpdateVehicle = async (data) => {

        try {

            await updateVehicle(

                editingVehicle.id,

                data

            );

            toast.success("Vehicle updated successfully.");

            /*
             * Exit edit mode.
             */
            setEditingVehicle(null);

            /*
             * Refresh latest data.
             */
            loadVehicles();

        }

        catch (error) {

            toast.error(

                error.response?.data?.message ||

                "Unable to update vehicle."

            );

        }

    };

    /*
     * Edit button clicked.
     *
     * Load selected vehicle
     * into form.
     */
    const handleEditVehicle = (vehicle) => {

        setEditingVehicle(vehicle);

        /*
         * Scroll to form
         * for better UX.
         */
        window.scrollTo({

            top: 0,

            behavior: "smooth",

        });

    };

    /*
     * Placeholder
     * Delete feature.
     */
    const handleDeleteVehicle = (id) => {

        console.log("Delete :", id);

    };

    /*
     * Placeholder
     * Restock feature.
     */
    const handleRestockVehicle = (vehicle) => {

        console.log("Restock :", vehicle);

    };

    /*
     * Load vehicles once.
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

                /*
                 * When null,
                 * Add mode.
                 *
                 * Otherwise
                 * Update mode.
                 */
                initialValues={editingVehicle}

                onSubmit={

                    editingVehicle

                        ? handleUpdateVehicle

                        : handleAddVehicle

                }

                buttonText={

                    editingVehicle

                        ? "Update Vehicle"

                        : "Add Vehicle"

                }

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