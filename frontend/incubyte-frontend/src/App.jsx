import Navbar from "./components/layout/Navbar";

import Dashboard from "./pages/user/Dashboard";

function App() {

    return (

        <>

            {/* Visible on every page */}

            <Navbar />

            <Routes>

                {/* Home page */}

                <Route

                    path="/"

                    element={<Dashboard />}

                />

                {/* Existing routes */}

                <Route

                    path="/login"

                    element={<Login />}

                />

                <Route

                    path="/register"

                    element={<Register />}

                />

            </Routes>

        </>

    );

}

export default App;