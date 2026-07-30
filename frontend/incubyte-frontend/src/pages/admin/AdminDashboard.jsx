import toast from "react-hot-toast";
import { addVehicle } from "../../services/vehicleService";
import VehicleForm from "../../components/vehicle/VehicleForm";

/*
 * Admin Dashboard
 */
export default function AdminDashboard() {

    /*
     * Save new vehicle.
     */
    const handleAddVehicle = async (data) => {

        try {

            // Call backend create API
            await addVehicle(data);

            toast.success("Vehicle added successfully.");

        }

        catch (error) {

            toast.error(

                error.response?.data?.message ||

                "Unable to add vehicle."

            );

        }

    };

    return (

        <div className="max-w-3xl mx-auto mt-10">

            <h1 className="text-3xl font-bold mb-6">

                Add Vehicle

            </h1>

            <VehicleForm

                onSubmit={handleAddVehicle}

                buttonText="Add Vehicle"

            />

        </div>

    );

}