import { useState } from "react";
import axios from "axios";

export default function UpdateIssuer() {
  const [lei, setLEI] = useState("");
  const [legalName, setLegalName] = useState("");
  const [description, setDescription] = useState("");
  const [message, setMessage] = useState("");

  const handleSubmit = async (e: any) => {
    e.preventDefault();

    const formData = {
      lei,
      legalName,
      description,
    };

    try {
      const res = await axios.post(
        `http://localhost:8080/api/issuers/`,
        formData,
        {
          headers: {
            "Content-Type": "application/json",
          },
        }
      );
      console.log(res.data);
      if (res.status === 200) {
        setMessage("Issuer created successfully (refreshing)");
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
        className="justify-center items-center flex flex-wrap "
        onSubmit={handleSubmit}
      >
       
        <input
          type="text"
          name="lei"
          id="lei"
          placeholder="LEI"
          onChange={(e) => setLEI(e.target.value)}
          className="m-2 p-2 bg-gray-200 text-black placeholder:text-gray-500 rounded-sm ring-1 ring-gray-300 flex-row"
        />
        <input
          type="text"
          name="legalName"
          id="legalName"
          placeholder="Legal Name"
          onChange={(e) => setLegalName(e.target.value)}
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
        <button
          type="submit"
          className="flex mt-5 text-base font-semibold focus:outline-none text-white transition bg-purple-700 hover:bg-purple-800 focus:ring-4 focus:ring-purple-300 rounded-lg px-5 py-2.5 dark:bg-purple-600 dark:hover:bg-purple-700 dark:focus:ring-purple-900"
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
