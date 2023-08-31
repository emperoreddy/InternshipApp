import axios from "axios";
import { useState } from "react";
import toReadableFormat from "../../util functions/toReadableFormat";

export default function GetEntityById<T>(props: any) {
  const [entity, setEntity] = useState<T | null>(null);
  const [entityId, setEntityId] = useState<Number>();
  const [isVisible, setIsVisible] = useState<Boolean>(false);
  const [isErrorVisible, setIsErrorVisible] = useState<Boolean>(false);

  let handleSubmit = async (e: any) => {
    e.preventDefault();

    try {
      axios
        .get(`http://localhost:8080/api/${props.apiEndpoint}/${entityId}`)
        .then((response) => {
          setEntity(response.data);
          setIsVisible(true);
          setIsErrorVisible(false);
        })
        .catch((error) => {
          setIsErrorVisible(true);
          setIsVisible(false);
          console.error("Error fetching entity:", error);
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
          className="text-base mb-5 font-semibold focus:outline-none text-white transition bg-purple-700 hover:bg-purple-800 focus:ring-4 focus:ring-purple-300 rounded-lg px-5 py-2.5  dark:bg-purple-600 dark:hover:bg-purple-700 dark:focus:ring-purple-900"
        >
          Find
        </button>
      </form>

      {entity && isVisible && (
        <div>
          <ul className="flex flex-wrap justify-center  mb-8 ">
            <ul
              className="p-10 shadow shadow-black w-auto outline outline-1 rounded-md text-center    bg-gray-200  m-10"
              key={entity.id}
            >
              {Object.entries(entity).map(([property, value]) => (
                <li>
                  <span className="font-semibold">
                    {toReadableFormat(property)}:{" "}
                  </span>
                  {value}
                </li>
              ))}
            </ul>
          </ul>
        </div>
      )}
      <div className="font-semibold text-lg text-red-500">
        {isErrorVisible ? `Error fetching member with id ${entityId}` : null}
      </div>
    </>
  );
}
