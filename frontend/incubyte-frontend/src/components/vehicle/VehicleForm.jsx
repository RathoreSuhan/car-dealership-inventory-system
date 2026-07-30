import { useEffect } from "react";
import { useForm } from "react-hook-form";

/*
 * Reusable form for
 * Add Vehicle
 * Update Vehicle
 */
export default function VehicleForm({

    initialValues,

    onSubmit,

    buttonText,

}) {

    /*
     * React Hook Form
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
     * Whenever edit mode starts,
     * populate form.
     */
    useEffect(() => {

        if (initialValues) {

            reset(initialValues);

        }

        else {

            reset({

                make: "",

                model: "",

                category: "",

                price: "",

                quantity: 0,

            });

        }

    }, [

        initialValues,

        reset,

    ]);

    /*
     * Submit form
     */
    const submitForm = (data) => {

        onSubmit(data);

        /*
         * Clear form only while adding.
         *
         * During update we wait for parent
         * component to reset edit mode.
         */
        if (!initialValues) {

            reset();

        }

    };

    return (

        <form

            onSubmit={handleSubmit(submitForm)}

            className="space-y-4 bg-white shadow rounded-lg p-6"

        >

            {/* Make */}

            <div>

                <input

                    type="text"

                    placeholder="Vehicle Make"

                    className="border w-full rounded p-2"

                    {...register("make", {

                        required: "Make is required",

                    })}

                />

                <p className="text-red-500 text-sm">

                    {errors.make?.message}

                </p>

            </div>

            {/* Model */}

            <div>

                <input

                    type="text"

                    placeholder="Vehicle Model"

                    className="border w-full rounded p-2"

                    {...register("model", {

                        required: "Model is required",

                    })}

                />

                <p className="text-red-500 text-sm">

                    {errors.model?.message}

                </p>

            </div>

            {/* Category */}

            <div>

                <input

                    type="text"

                    placeholder="Category"

                    className="border w-full rounded p-2"

                    {...register("category", {

                        required: "Category is required",

                    })}

                />

                <p className="text-red-500 text-sm">

                    {errors.category?.message}

                </p>

            </div>

            {/* Price */}

            <div>

                <input

                    type="number"

                    step="0.01"

                    placeholder="Price"

                    className="border w-full rounded p-2"

                    {...register("price", {

                        required: "Price is required",

                        min: {

                            value: 1,

                            message: "Price must be greater than 0",

                        },

                        valueAsNumber: true,

                    })}

                />

                <p className="text-red-500 text-sm">

                    {errors.price?.message}

                </p>

            </div>

            {/* Quantity */}

            <div>

                <input

                    type="number"

                    placeholder="Quantity"

                    className="border w-full rounded p-2"

                    {...register("quantity", {

                        required: "Quantity is required",

                        min: {

                            value: 0,

                            message: "Quantity cannot be negative",

                        },

                        valueAsNumber: true,

                    })}

                />

                <p className="text-red-500 text-sm">

                    {errors.quantity?.message}

                </p>

            </div>

            <button

                type="submit"

                className="bg-blue-600 text-white px-6 py-2 rounded hover:bg-blue-700"

            >

                {buttonText}

            </button>

        </form>

    );

}