import { useState } from "react";

/*
 * Search form shown above vehicle list.
 */
export default function SearchBar({

    onSearch, // Function received from Dashboard

}) {

    // Stores all filter values

    const [filters, setFilters] = useState({

        make: "",

        model: "",

        category: "",

        minPrice: "",

        maxPrice: "",

    });

    /*
     * Update filter whenever user types.
     */
    const handleChange = (e) => {

        setFilters({

            ...filters,

            [e.target.name]: e.target.value,

        });

    };

    /*
     * Prevent page refresh and send filters.
     */
    const handleSubmit = (e) => {

        e.preventDefault();

        onSearch(filters);

    };

    return (

        <form

            onSubmit={handleSubmit}

            className="grid md:grid-cols-5 gap-4 mb-8"

        >

            <input
                type="text"
                name="make"
                placeholder="Make"
                value={filters.make}
                onChange={handleChange}
                className="border rounded p-2"
            />

            <input
                type="text"
                name="model"
                placeholder="Model"
                value={filters.model}
                onChange={handleChange}
                className="border rounded p-2"
            />

            <input
                type="text"
                name="category"
                placeholder="Category"
                value={filters.category}
                onChange={handleChange}
                className="border rounded p-2"
            />

            <input
                type="number"
                name="minPrice"
                placeholder="Min Price"
                value={filters.minPrice}
                onChange={handleChange}
                className="border rounded p-2"
            />

            <input
                type="number"
                name="maxPrice"
                placeholder="Max Price"
                value={filters.maxPrice}
                onChange={handleChange}
                className="border rounded p-2"
            />

            <button

                type="submit"

                className="bg-blue-600 text-white rounded p-2 hover:bg-blue-700"

            >

                Search

            </button>

        </form>

    );

}