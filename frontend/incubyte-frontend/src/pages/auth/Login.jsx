import { useState } from "react";
import { useNavigate } from "react-router-dom";
import toast from "react-hot-toast";

import useAuth from "../../hooks/useAuth";
import { loginUser } from "../../services/authService";

export default function Login() {

    const navigate = useNavigate();

    // ✅ Hook must be called here
    const { login } = useAuth();

    const [email, setEmail] = useState("");

    const [password, setPassword] = useState("");

    const [loading, setLoading] = useState(false);

    const handleSubmit = async (e) => {

        e.preventDefault();

        try {

            setLoading(true);

            const response = await loginUser({

                email,

                password,

            });

            console.log("LOGIN RESPONSE :", response);

            login(response.token);

            toast.success(response.message);

            navigate("/dashboard");

        }

        catch (error) {

            console.log("FULL ERROR :", error);

            console.log("ERROR RESPONSE :", error.response);

            console.log("ERROR DATA :", error.response?.data);

            toast.error(

                error.response?.data?.message ||

                "Login Failed"

            );

        }

        finally {

            setLoading(false);

        }

    };

    return (

        <div className="flex justify-center items-center min-h-screen">

            <form

                onSubmit={handleSubmit}

                className="w-[400px] bg-white shadow-lg rounded-lg p-8 space-y-4"

            >

                <h2 className="text-3xl font-bold text-center">

                    Login

                </h2>

                <input

                    type="email"

                    placeholder="Email"

                    className="border w-full p-2 rounded"

                    value={email}

                    onChange={(e) => setEmail(e.target.value)}

                />

                <input

                    type="password"

                    placeholder="Password"

                    className="border w-full p-2 rounded"

                    value={password}

                    onChange={(e) => setPassword(e.target.value)}

                />

                <button

                    type="submit"

                    className="bg-blue-600 text-white w-full p-2 rounded"

                >

                    {

                        loading

                            ? "Logging..."

                            : "Login"

                    }

                </button>

            </form>

        </div>

    );

}