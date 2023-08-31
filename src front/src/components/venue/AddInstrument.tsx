import axios from "axios";
import { useState } from "react";
import toReadableFormat from "../../util functions/toReadableFormat";

export default function AddInstrument(props: any) {
  const [venueId, setVenueId] = useState<Number>();
  const [isErrorVisible, setIsErrorVisible] = useState<Boolean>(false);
  const [instrumentId, setInstrumentId] = useState<Number>();
  const [message, setMessage] = useState<String>("");

  let handleSubmit = async (e: any) => {
    e.preventDefault();

    try {
      axios
        .put(
          `http://localhost:8080/api/venues/${venueId}/instruments/${instrumentId}`
        )
        .then((response) => {
          setIsErrorVisible(false);
          setMessage("Instrument added succesfully ");
        })
        .catch((error) => {
          setIsErrorVisible(true);
          console.error("Error adding entity:", error);
          setMessage("Error occured");
        });
    } catch (err) {
      setMessage("Error occured");
      console.log(err);
    }
   
  };

  return (
    <>
      <form onSubmit={handleSubmit}>
        <input
          type="text"
          name="venueId"
          id="venueId"
          placeholder="Venue ID"
          onChange={(e) => setVenueId(e.target.value)}
          className="m-2 mt-8 p-2 bg-gray-200 text-black placeholder:text-gray-500 rounded-sm ring-1 ring-gray-300 flex-row"
        />
        <input
          type="text"
          name="instrumentId"
          id="instrumentId"
          placeholder="Instrument ID"
          onChange={(e) => setInstrumentId(e.target.value)}
          className="m-2 mt-8 p-2 bg-gray-200 text-black placeholder:text-gray-500 rounded-sm ring-1 ring-gray-300 flex-row"
        />
        <button
          type="submit"
          className="text-base mb-5 font-semibold focus:outline-none text-white transition bg-purple-700 hover:bg-purple-800 focus:ring-4 focus:ring-purple-300 rounded-lg px-5 py-2.5  dark:bg-purple-600 dark:hover:bg-purple-700 dark:focus:ring-purple-900"
        >
          Add
        </button>
      </form>

      <div className="font-semibold text-lg text-red-500">
        {isErrorVisible ? `Error adding instrument with id ${venueId}` : null}
      </div>
      <div className="flex justify-center mt-5">
        {message ? <p>{message}</p> : null}
      </div>
    </>
  );
}
