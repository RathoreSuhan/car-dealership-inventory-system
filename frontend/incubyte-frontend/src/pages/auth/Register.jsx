import { useState } from "react";
import toast from "react-hot-toast";
import { useNavigate } from "react-router-dom";
import { registerUser } from "../../services/authService";

export default function Register() {

    const navigate = useNavigate();

    // form state
    const [formData, setFormData] = useState({

        name: "",
        email: "",
        password: "",

    });


    const handleChange = (e) => {

        setFormData({

            ...formData,
            [e.target.name]: e.target.value,

        });

    };


    const handleSubmit = async (e) => {

        e.preventDefault();

        try {

            const response = await registerUser(formData);

            toast.success(response.message);
            navigate("/login"); // Redirect after successful registration

        }
        catch (error) {

            toast.error(

                error.response?.data?.message ||
                "Registration Failed"

            );

        }

    };


    return (

        <div className="flex justify-center items-center min-h-screen">

            <form
                onSubmit={handleSubmit}
                className="w-[400px] bg-white shadow-lg rounded-lg p-8 space-y-4"
            >

                <h2 className="text-3xl font-bold text-center">

                    Register

                </h2>

                <input
                    name="name"
                    placeholder="Name"
                    className="border w-full p-2 rounded"
                    onChange={handleChange}
                />

                <input
                    name="email"
                    placeholder="Email"
                    className="border w-full p-2 rounded"
                    onChange={handleChange}
                />

                <input
                    name="password"
                    type="password"
                    placeholder="Password"
                    className="border w-full p-2 rounded"
                    onChange={handleChange}
                />

                <button
                    className="bg-green-600 text-white w-full p-2 rounded"
                >

                    Register

                </button>

            </form>

        </div>

    );

}