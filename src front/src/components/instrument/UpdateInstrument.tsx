import { useState } from "react";
import axios from "axios";

export default function UpdateInstrument() {
  const [isin, setIsin] = useState("");
  const [currency, setCurrency] = useState("");
  const [type, setType] = useState("");
  const [description, setDescription] = useState("");
  const [effectiveDate, setEffectiveDate] = useState("");
  const [status, setStatus] = useState("");
  const [issuerId, setIssuerId] = useState("");
  const [message, setMessage] = useState("");
  const [instrumentId, setInstrumentId] = useState();

  const handleSubmit = async (e: any) => {
    e.preventDefault();

    const formData = {
      isin,
      currency,
      type,
      description,
      effectiveDate,
      status,
      issuerId,
    };

    try {
      const res = await axios.put(
        `http://localhost:8080/api/instruments/${instrumentId}`,
        formData,
        {
          headers: {
            "Content-Type": "application/json",
          },
        }
      );
      console.log(res.data);
      if (res.status === 200) {
        setMessage("Instrument updated successfully (refreshing)");
        setTimeout(() => {
          window.location.reload();
        }, 1500);
      } else {
        setMessage("Error occurred");
      }
    } catch (err) {
      console.log(err);
    }
  };

  return (
    <div className="">
      <form
        className="justify-center items-center flex flex-col mt-10"
        onSubmit={handleSubmit}
      >
        <input
          type="text"
          name="instrumentId"
          id="instrumentId"
          placeholder="Instrument ID"
          onChange={(e) => setInstrumentId(e.target.value)}
          className="m-2 p-2 bg-gray-200 text-black placeholder:text-gray-500 rounded-sm ring-1 ring-gray-300 flex-row"
        />
        <input
          type="text"
          name="isin"
          id="isin"
          placeholder="ISIN"
          onChange={(e) => setIsin(e.target.value)}
          className="m-2 p-2 bg-gray-200 text-black placeholder:text-gray-500 rounded-sm ring-1 ring-gray-300 flex-row"
        />

        <input
          type="text"
          name="currency"
          id="currency"
          placeholder="Currency"
          onChange={(e) => setCurrency(e.target.value)}
          className="m-2 p-2 bg-gray-200 text-black placeholder:text-gray-500 rounded-sm ring-1 ring-gray-300 flex-row"
        />

        <input
          type="text"
          name="type"
          id="type"
          placeholder="Type"
          onChange={(e) => setType(e.target.value)}
          className="m-2 p-2 bg-gray-200 text-black placeholder:text-gray-500 rounded-sm ring-1 ring-gray-300 flex-row"
        />

        <input
          type="text"
          name="description"
          id="description"
          placeholder="Description"
          onChange={(e) => setDescription(e.target.value)}
          className="m-2 p-2 bg-gray-200 text-black placeholder:text-gray-500 rounded-sm ring-1 ring-gray-300 flex-row"
        />

        <input
          type="text"
          name="effectiveDate"
          id="effectiveDate"
          placeholder="Effective Date"
          onChange={(e) => setEffectiveDate(e.target.value)}
          className="m-2 p-2 bg-gray-200 text-black placeholder:text-gray-500 rounded-sm ring-1 ring-gray-300 flex-row"
        />

        <input
          type="text"
          name="status"
          id="status"
          placeholder="Status"
          onChange={(e) => setStatus(e.target.value)}
          className="m-2 p-2 bg-gray-200 text-black placeholder:text-gray-500 rounded-sm ring-1 ring-gray-300 flex-row"
        />

        <input
          type="text"
          name="issuerId"
          id="issuerId"
          placeholder="Issuer ID"
          onChange={(e) => setIssuerId(e.target.value)}
          className="m-2 p-2 bg-gray-200 text-black placeholder:text-gray-500 rounded-sm ring-1 ring-gray-300 flex-row"
        />

        <button
          type="submit"
          className="flex mt-5 text-base font-semibold focus:outline-none text-white transition bg-purple-700 hover:bg-purple-800 focus:ring-4 focus:ring-purple-300 rounded-lg px-5 py-2.5 dark:bg-purple-600 dark:hover:bg-purple-700 dark:focus:ring-purple-900"
        >
          Update
        </button>
      </form>

      <div className="flex justify-center mt-5">
        {message ? <p>{message}</p> : null}
      </div>
    </div>
  );
}
