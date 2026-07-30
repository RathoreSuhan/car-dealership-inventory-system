import { useEffect, useState } from "react";
import toast from "react-hot-toast";

import {
    addVehicle,
    deleteVehicle,
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
 *
 * Co-Author:
 * OpenAI ChatGPT
 */
export default function AdminDashboard() {

    /*
     * Stores all vehicles.
     */
    const [vehicles, setVehicles] = useState([]);

    /*
     * Stores currently selected vehicle.
     *
     * null -> Add Mode
     * object -> Edit Mode
     */
    const [editingVehicle, setEditingVehicle] = useState(null);

    /*
     * Load all vehicles.
     */
    const loadVehicles = async () => {

        try {

            const data = await getVehicles();

            setVehicles(data);

        }

        catch {

            toast.error("Unable to load vehicles.");

        }

    };

    /*
     * Handles both
     * Add Vehicle
     * Update Vehicle
     */
    const handleSubmitVehicle = async (data) => {

        try {

            /*
             * Edit Mode
             */
            if (editingVehicle) {

                await updateVehicle(

                    editingVehicle.id,

                    data

                );

                toast.success("Vehicle updated successfully.");

                /*
                 * Back to Add Mode
                 */
                setEditingVehicle(null);

            }

            /*
             * Add Mode
             */
            else {

                await addVehicle(data);

                toast.success("Vehicle added successfully.");

            }

            /*
             * Refresh table.
             */
            loadVehicles();

        }

        catch (error) {

            toast.error(

                error.response?.data?.message ||

                "Operation failed."

            );

        }

    };

    /*
     * Edit button.
     *
     * Opens form in edit mode.
     */
    const handleEditVehicle = (vehicle) => {

        setEditingVehicle(vehicle);

        /*
         * Scroll to form.
         */
        window.scrollTo({

            top: 0,

            behavior: "smooth",

        });

    };


    /*
    * Deletes a vehicle.
    */
    const handleDeleteVehicle = async (id) => {

        const confirmed = window.confirm(

            "Are you sure you want to delete this vehicle?"

        );

        if (!confirmed) {

            return;

        }

        try {

            await deleteVehicle(id);

            toast.success(

                "Vehicle deleted successfully."

            );

            /*
            * Refresh table.
            */
            loadVehicles();

        }

        catch (error) {

            toast.error(

                error.response?.data?.message ||

                "Delete failed."

            );

        }

    };

    /*
     * Placeholder.
     */
    const handleRestockVehicle = (vehicle) => {

        console.log(vehicle);

    };

    useEffect(() => {

        loadVehicles();

    }, []);

    return (

        <div className="max-w-7xl mx-auto p-8">

            <h1 className="text-3xl font-bold mb-8">

                Admin Dashboard

            </h1>

            <VehicleForm

                /*
                 * null -> Add
                 * object -> Edit
                 */
                initialValues={editingVehicle}

                onSubmit={handleSubmitVehicle}

                buttonText={

                    editingVehicle

                        ? "Update Vehicle"

                        : "Add Vehicle"

                }

            />

            <VehicleTable

                vehicles={vehicles}

                onEdit={handleEditVehicle}

                onDelete={handleDeleteVehicle}

                onRestock={handleRestockVehicle}

            />

        </div>

    );

}