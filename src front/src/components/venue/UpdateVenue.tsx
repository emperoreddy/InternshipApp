import axios from "axios";
import { useState } from "react";

export default function UpdateVenue() {
  const [venueId, setVenueId] = useState<Number>();
  const [name, setName] = useState<String>("");
  const [city, setCity] = useState<String>("");
  const [country, setCountry] = useState<String>("");
  const [message, setMessage] = useState<String>("");

  let handleSubmit = async (e: any) => {
    e.preventDefault();

    const formData = {
      name,
      city,
      country,
    };

    try {
      const res = await axios.put(
        `http://localhost:8080/api/venues/${venueId}`,
        formData,
        {
          headers: {
            "Content-Type": "application/json",
          },
        }
      );
      console.log(res.data);
      if (res.status === 200) {
        setMessage("Venue updated succesfully (refreshing)");
        setTimeout(() => {
          window.location.reload();
        }, 1500);
      } else {
        setMessage("Error occured");
      }
    } catch (err) {
      console.log(err);
      setMessage("Error occured");
    }

    <div className="text-lg font-medium">
      {message ? <p>{message}</p> : null}
    </div>;
  };

  return (
    <div className="">
      <form
        className="flex flex-wrap justify-center items-center"
        onSubmit={handleSubmit}
      >
        <input
          type="text"
          name="venueId"
          id="venueId"
          placeholder="Venue ID"
          onChange={(e) => setVenueId(e.target.value)}
          className="m-2  p-2 bg-gray-200 text-black placeholder:text-gray-500 rounded-sm ring-1 ring-gray-300 flex-row"
        />{" "}
        <input
          type="text"
          name="name"
          id="name"
          placeholder="Name"
          onChange={(e) => setName(e.target.value)}
          className="m-2  p-2 bg-gray-200 text-black placeholder:text-gray-500 rounded-sm ring-1 ring-gray-300 flex-row"
        />
        <input
          type="text"
          name="city"
          id="city"
          placeholder="City"
          onChange={(e) => setCity(e.target.value)}
          className="m-2  p-2 bg-gray-200 text-black placeholder:text-gray-500 rounded-sm ring-1 ring-gray-300 flex-row"
        />
        <input
          type="text"
          name="country"
          id="country"
          placeholder="Country"
          onChange={(e) => setCountry(e.target.value)}
          className="m-2 p-2 bg-gray-200 text-black placeholder:text-gray-500 rounded-sm ring-1 ring-gray-300 flex-row"
        />
        <button
          type="submit"
          className="text-base font-semibold focus:outline-none text-white transition bg-purple-700 hover:bg-purple-800 focus:ring-4 focus:ring-purple-300 rounded-lg px-5 py-2.5  dark:bg-purple-600 dark:hover:bg-purple-700 dark:focus:ring-purple-900"
        >
          Create
        </button>
      </form>
      <div className="flex justify-center mt-5">
        {message ? <p>{message}</p> : null}
      </div>
    </div>
  );
}
