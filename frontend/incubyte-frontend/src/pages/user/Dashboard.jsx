import { useEffect, useState } from "react"; // React hooks
import toast from "react-hot-toast"; // Notification messages

import { getVehicles, purchaseVehicle } from "../../services/vehicleService";

import VehicleCard from "../../components/vehicle/VehicleCard";

export default function Dashboard() {

    // Stores all vehicles

    const [vehicles, setVehicles] = useState([]);

    // Loading state while API is running

    const [loading, setLoading] = useState(true);

    /*
     * Fetch vehicles from backend
     */

    const loadVehicles = async () => {

        try {

            const data = await getVehicles(); // API call

            setVehicles(data); // Store vehicles

        }

        catch (error) {

            toast.error("Unable to load vehicles.");

        }

        finally {

            setLoading(false); // Hide loading

        }

    };

    /*
     * Purchase selected vehicle
     */

    const handlePurchase = async (id) => {

        try {

            await purchaseVehicle(id); // Purchase API

            toast.success("Vehicle purchased successfully.");

            loadVehicles(); // Refresh list

        }

        catch (error) {

            toast.error("Purchase failed.");

        }

    };

    /*
     * Runs only once after page loads
     */

    useEffect(() => {

        loadVehicles();

    }, []);

    if (loading) {

        return (

            <h2 className="text-center text-xl mt-10">

                Loading Vehicles...

            </h2>

        );

    }

    return (

        <div className="max-w-7xl mx-auto p-6">

            <h1 className="text-3xl font-bold mb-6">

                Available Vehicles

            </h1>

            {

                vehicles.length === 0 ? (

                    <p>No vehicles available.</p>

                ) : (

                    <div className="grid md:grid-cols-2 lg:grid-cols-3 gap-6">

                        {

                            vehicles.map((vehicle) => (

                                <VehicleCard

                                    key={vehicle.id}

                                    vehicle={vehicle}

                                    onPurchase={handlePurchase}

                                />

                            ))

                        }

                    </div>

                )

            }

        </div>

    );

}