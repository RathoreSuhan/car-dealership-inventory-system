import useAuth from "../../hooks/useAuth";

// User dashboard
export default function Dashboard() {

    const { logout } = useAuth();

    return (

        <div className="p-10">

            <h1 className="text-4xl font-bold">

                Dashboard
            </h1>

            <button
                onClick={logout} // Remove JWT
                className="mt-6 bg-red-600 text-white px-4 py-2 rounded"
            >

                Logout

            </button>

        </div>

    );

}