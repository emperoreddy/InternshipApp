import axios from "axios";
import { useState } from "react";

export default function AddInstrumentToIssuer() {
  const [issuerId, setIssuerId] = useState<number>();
  const [instrumentId, setInstrumentId] = useState<number>();
  const [isSuccess, setIsSuccess] = useState<boolean>(false);
  const [isError, setIsError] = useState<boolean>(false);

  const handleSubmit = async (e: any) => {
    e.preventDefault();
    try {
      await axios.post(
        `http://localhost:8080/api/issuers/${issuerId}/instruments/${instrumentId}`
      );
      setIsSuccess(true);
      setIsError(false);
    } catch (err) {
      setIsSuccess(false);
      setIsError(true);
      console.error(`Error adding instrument to issuer:`, err);
    }
  };

  return (
    <div className="mt-10">
      <form onSubmit={handleSubmit}>
        <input
          type="text"
          name="issuerId"
          id="issuerId"
          placeholder="Issuer ID"
          onChange={(e) => setIssuerId(Number(e.target.value))}
          className="m-2 p-2 bg-gray-200 text-black placeholder:text-gray-500 rounded-sm ring-1 ring-gray-300 flex-row"
        />
        <input
          type="text"
          name="instrumentId"
          id="instrumentId"
          placeholder="Instrument ID"
          onChange={(e) => setInstrumentId(Number(e.target.value))}
          className="m-2 p-2 bg-gray-200 text-black placeholder:text-gray-500 rounded-sm ring-1 ring-gray-300 flex-row"
        />
        <button
          type="submit"
          className="text-base mb-5 font-semibold focus:outline-none text-white transition bg-purple-700 hover:bg-purple-800 focus:ring-4 focus:ring-purple-300 rounded-lg px-5 py-2.5 dark:bg-purple-600 dark:hover:bg-purple-700 dark:focus:ring-purple-900"
        >
          Add
        </button>
      </form>

      {isSuccess && (
        <p className="text-green-600">
          Instrument added to issuer successfully!
        </p>
      )}
      {isError && (
        <p className="text-red-600">Error adding instrument to issuer.</p>
      )}
    </div>
  );
}
