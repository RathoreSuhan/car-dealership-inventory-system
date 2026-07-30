import { useEffect } from "react";
import { useForm } from "react-hook-form";

/*
 * Reusable form used for both
 * Add Vehicle and Update Vehicle.
 */
export default function VehicleForm({

    initialValues,   // Existing vehicle (edit mode)

    onSubmit,        // Parent callback

    buttonText,      // Button label

}) {

    /*
     * React Hook Form setup
     */
    const {

        register,

        handleSubmit,

        reset,

        formState: { errors },

    } = useForm({

        defaultValues: {

            make: "",

            model: "",

            category: "",

            price: "",

            quantity: 0,

        },

    });

    /*
     * Populate form when editing.
     */
    useEffect(() => {

        if (initialValues) {

            reset(initialValues);

        }

    }, [initialValues, reset]);

    return (

        <form

            onSubmit={handleSubmit(onSubmit)}

            className="space-y-4 bg-white shadow rounded-lg p-6"

        >

            {/* Vehicle Make */}

            <div>

                <input

                    type="text"

                    placeholder="Vehicle Make"

                    className="border w-full rounded p-2"

                    {...register(

                        "make",

                        {

                            required: "Make is required",

                        }

                    )}

                />

                <p className="text-red-500 text-sm">

                    {errors.make?.message}

                </p>

            </div>

            {/* Vehicle Model */}

            <div>

                <input

                    type="text"

                    placeholder="Vehicle Model"

                    className="border w-full rounded p-2"

                    {...register(

                        "model",

                        {

                            required: "Model is required",

                        }

                    )}

                />

                <p className="text-red-500 text-sm">

                    {errors.model?.message}

                </p>

            </div>

            {/* Vehicle Category */}

            <div>

                <input

                    type="text"

                    placeholder="Category"

                    className="border w-full rounded p-2"

                    {...register(

                        "category",

                        {

                            required: "Category is required",

                        }

                    )}

                />

                <p className="text-red-500 text-sm">

                    {errors.category?.message}

                </p>

            </div>

            {/* Vehicle Price */}

            <div>

                <input

                    type="number"

                    step="0.01"

                    placeholder="Price"

                    className="border w-full rounded p-2"

                    {...register(

                        "price",

                        {

                            required: "Price is required",

                            min: {

                                value: 1,

                                message: "Price must be greater than 0",

                            },

                            valueAsNumber: true, // Convert string -> number

                        }

                    )}

                />

                <p className="text-red-500 text-sm">

                    {errors.price?.message}

                </p>

            </div>

            {/* Vehicle Quantity */}

            <div>

                <input

                    type="number"

                    placeholder="Quantity"

                    className="border w-full rounded p-2"

                    {...register(

                        "quantity",

                        {

                            required: "Quantity is required",

                            min: {

                                value: 0,

                                message: "Quantity cannot be negative",

                            },

                            valueAsNumber: true,

                        }

                    )}

                />

                <p className="text-red-500 text-sm">

                    {errors.quantity?.message}

                </p>

            </div>

            {/* Submit button */}

            <button

                type="submit"

                className="bg-blue-600 text-white px-6 py-2 rounded hover:bg-blue-700"

            >

                {buttonText}

            </button>

        </form>

    );

}