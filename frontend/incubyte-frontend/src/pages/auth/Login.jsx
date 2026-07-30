import { useState } from "react";
import { useNavigate } from "react-router-dom"; // used for navigation
import toast from "react-hot-toast"; // popup messages
import useAuth from "../../hooks/useAuth";

import { loginUser } from "../../services/authService";

export default function Login() {

    const navigate = useNavigate();

    // stores email entered by user
    const [email, setEmail] = useState("");

    // stores password entered by user
    const [password, setPassword] = useState("");

    // loading button state
    const [loading, setLoading] = useState(false);


    // called when Login button is pressed
    const handleSubmit = async (e) => {

        e.preventDefault();

        try {

            setLoading(true);

            // calling backend login api
            const response = await loginUser({
                email,
                password,
            });

            // save JWT token in browser
            const { login } = useAuth(); // AuthContext login function
            login(response.token); // Save JWT via AuthContext

            toast.success(response.message);

            // go to dashboard
            navigate("/dashboard");

        }
        catch (error) {

            toast.error(
                error.response?.data?.message || "Login Failed"
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
                    className="bg-blue-600 text-white w-full p-2 rounded"
                >

                    {loading ? "Logging..." : "Login"}

                </button>

            </form>

        </div>

    );

}