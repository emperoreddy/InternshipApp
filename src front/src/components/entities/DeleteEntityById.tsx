import axios from "axios";
import { useState } from "react";

export default function DeleteEntityById(props: any) {
  const [entityId, setEntityId] = useState<Number>();
  const [isErrorVisible, setIsErrorVisible] = useState<Boolean>(false);
  const [message, setMessage] = useState<String>("");

  let handleSubmit = async (e: any) => {
    e.preventDefault();

    try {
      axios
        .delete(`http://localhost:8080/api/${props.apiEndpoint}/${entityId}`)
        .then(() => {
          setMessage("Succesfully deleted entity (refreshing)");
          setIsErrorVisible(false);
          setTimeout(() => {
            window.location.reload();
          }, 1500);
        })
        .catch((error) => {
          setMessage("");
          setIsErrorVisible(true);
          console.error("Error deleting entity:", error);
        });
    } catch (err) {
      console.log(err);
    }
  };

  return (
    <>
      <form onSubmit={handleSubmit}>
        <input
          type="text"
          name="entityId"
          id="entityId"
          placeholder="ID"
          onChange={(e) => setEntityId(e.target.value)}
          className="m-2 mt-8 p-2 bg-gray-200 text-black placeholder:text-gray-500 rounded-sm ring-1 ring-gray-300 flex-row"
        />
        <button
          type="submit"
          className="text-base mb-5 font-semibold focus:outline-none text-white transition bg-red-700  hover:bg-red-800 focus:ring-4 focus:ring-red-300 rounded-lg px-5 py-2.5  dark:bg-red-600 dark:hover:bg-red-700 dark:focus:ring-red-900"
        >
          Delete
        </button>
      </form>

      <div className="font-semibold text-lg ">
        <span className="text-red-500">{isErrorVisible ? `Error deleting member with id ${entityId}` : null}</span>
        <span>{message}</span>
      </div>
    </>
  );
}
