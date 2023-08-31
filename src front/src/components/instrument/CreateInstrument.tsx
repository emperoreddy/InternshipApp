
import axios from "axios";
import { useState } from "react";

export default function CreateInstrument() {
  const [isin, setIsin] = useState<String>("");
  const [type, setType] = useState<String>("");
  const [currency, setCurrency] = useState<String>("");
  const [description, setDescription] = useState<String>("");
  const [status, setStatus] = useState<String>("");
  const [effectiveDate, setEffectiveDate] = useState<String>("");
  const [issuerId, setIssuerId] = useState<Number>();
  const [message, setMessage] = useState<String>("")

  let handleSubmit = async (e: any) => {
    e.preventDefault();

    const formData = {
    isin,
    currency,
    type,
    description,
    effectiveDate,
    status,
    issuerId
    };

    try {
      const res = await axios.post(
        "http://localhost:8080/api/instruments",
        formData,
        {
          headers: {
            "Content-Type": "application/json",
          },
        }
      );
      console.log(res.data);
      if (res.status === 200) {
        setMessage("Instrument created succesfully (refreshing)");
        setTimeout(() => {
          window.location.reload();
        }, 1500);
      } else {
        setMessage("Error occured");
      }
    } catch (err) {
      console.log(err);
    }

    <div className="text-lg font-medium">
      {message ? <p>{message}</p> : null}
    </div>;
  };

  return (
    <div>
      <form
        className="flex flex-col justify-center items-center"
        onSubmit={handleSubmit}
      >
        <input
          type="text"
          name="isin"
          id="isin"
          placeholder="ISIN"
          onChange={(e) => setIsin(e.target.value)}
          className="m-2  p-2 bg-gray-200 text-black placeholder:text-gray-500 rounded-sm ring-1 ring-gray-300 flex-row"
        />
        <input
          type="text"
          name="currency"
          id="currency"
          placeholder="Currency"
          onChange={(e) => setCurrency(e.target.value)}
          className="m-2  p-2 bg-gray-200 text-black placeholder:text-gray-500 rounded-sm ring-1 ring-gray-300 flex-row"
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
          placeholder="EffectiveDate"
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
          className="text-base mt-5 font-semibold focus:outline-none text-white transition bg-purple-700 hover:bg-purple-800 focus:ring-4 focus:ring-purple-300 rounded-lg px-5 py-2.5  dark:bg-purple-600 dark:hover:bg-purple-700 dark:focus:ring-purple-900"
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
