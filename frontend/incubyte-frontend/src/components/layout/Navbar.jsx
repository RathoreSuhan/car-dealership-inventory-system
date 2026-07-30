import { Link } from "react-router-dom"; // Navigation between pages
import { useContext } from "react"; // Access authentication context
import { AuthContext } from "../../context/AuthContext"; // Logged-in user state

export default function Navbar() {

    // Get authentication details
    const { token, logout } = useContext(AuthContext);

    return (

        <nav className="bg-blue-600 text-white shadow-md">

            <div className="max-w-7xl mx-auto px-6 py-4 flex justify-between items-center">

                {/* Project Logo */}

                <Link
                    to="/"
                    className="text-2xl font-bold"
                >
                    Incubyte Cars
                </Link>

                {/* Right Side */}

                <div className="flex items-center gap-4">

                    {

                        token ? (

                            <button

                                onClick={logout} // Clear JWT and logout

                                className="bg-red-500 px-4 py-2 rounded hover:bg-red-600 transition"

                            >

                                Logout

                            </button>

                        ) : (

                            <>

                                <Link

                                    to="/login"

                                    className="hover:text-gray-200"

                                >

                                    Login

                                </Link>

                                <Link

                                    to="/register"

                                    className="hover:text-gray-200"

                                >

                                    Register

                                </Link>

                            </>

                        )

                    }

                </div>

            </div>

        </nav>

    );

}