export default function VehicleCard({

    vehicle, // Vehicle object received from parent

    onPurchase, // Function executed when Buy button is clicked

}) {

    return (

        <div className="border rounded-lg shadow-md p-5 bg-white">

            {/* Vehicle Brand */}

            <h2 className="text-xl font-bold">

                {vehicle.make}

            </h2>

            {/* Vehicle Model */}

            <p className="text-gray-600">

                {vehicle.model}

            </p>

            {/* Vehicle Category */}

            <p>

                Category :

                <span className="font-semibold">

                    {" "}

                    {vehicle.category}

                </span>

            </p>

            {/* Vehicle Price */}

            <p>

                Price :

                <span className="font-semibold text-green-700">

                    ₹ {vehicle.price.toLocaleString()}

                </span>

            </p>

            {/* Available Stock */}

            <p>

                Stock :

                <span className="font-semibold">

                    {vehicle.quantity}

                </span>

            </p>

            <button

                onClick={() => onPurchase(vehicle.id)} // Purchase selected vehicle

                disabled={vehicle.quantity === 0} // Disable if out of stock

                className="mt-4 w-full bg-blue-600 text-white py-2 rounded hover:bg-blue-700 disabled:bg-gray-400"

            >

                {

                    vehicle.quantity === 0

                        ? "Out of Stock"

                        : "Purchase"

                }

            </button>

        </div>

    );

}