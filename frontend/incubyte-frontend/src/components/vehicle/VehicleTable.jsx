/*
 * Displays all vehicles in a table.
 */
export default function VehicleTable({

    vehicles,          // List of vehicles

    onEdit,            // Edit button callback

    onDelete,          // Delete button callback

    onRestock,         // Restock button callback

}) {

    return (

        <div className="overflow-x-auto mt-8">

            <table className="min-w-full border border-gray-300">

                <thead className="bg-blue-600 text-white">

                    <tr>

                        <th className="p-3 border">ID</th>

                        <th className="p-3 border">Make</th>

                        <th className="p-3 border">Model</th>

                        <th className="p-3 border">Category</th>

                        <th className="p-3 border">Price</th>

                        <th className="p-3 border">Stock</th>

                        <th className="p-3 border">Actions</th>

                    </tr>

                </thead>

                <tbody>

                    {

                        vehicles.map((vehicle) => (

                            <tr

                                key={vehicle.id}

                                className="text-center hover:bg-gray-100"

                            >

                                {/* Vehicle ID */}

                                <td className="border p-3">

                                    {vehicle.id}

                                </td>

                                {/* Vehicle Make */}

                                <td className="border p-3">

                                    {vehicle.make}

                                </td>

                                {/* Vehicle Model */}

                                <td className="border p-3">

                                    {vehicle.model}

                                </td>

                                {/* Vehicle Category */}

                                <td className="border p-3">

                                    {vehicle.category}

                                </td>

                                {/* Vehicle Price */}

                                <td className="border p-3">

                                    ₹ {vehicle.price.toLocaleString()}

                                </td>

                                {/* Available Quantity */}

                                <td className="border p-3">

                                    {vehicle.quantity}

                                </td>

                                {/* Action Buttons */}

                                <td className="border p-3 space-x-2">

                                    <button

                                        onClick={() => onEdit(vehicle)}

                                        className="bg-yellow-500 px-3 py-1 rounded text-white"

                                    >

                                        Edit

                                    </button>

                                    <button

                                        onClick={() => onDelete(vehicle.id)}

                                        className="bg-red-600 px-3 py-1 rounded text-white"

                                    >

                                        Delete

                                    </button>

                                    <button

                                        onClick={() => onRestock(vehicle)}

                                        className="bg-green-600 px-3 py-1 rounded text-white"

                                    >

                                        Restock

                                    </button>

                                </td>

                            </tr>

                        ))

                    }

                </tbody>

            </table>

        </div>

    );

}