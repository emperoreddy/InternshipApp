import axios from "axios";
import { useState } from "react";
import toReadableFormat from "../../util functions/toReadableFormat";

export default function GetInstrumentsOfIssuer<T>(props: any) {
  const [entities, setEntities] = useState<T[]>([]);
  const [issuerId, setIssuerId] = useState<number>();
  const [isVisible, setIsVisible] = useState<boolean>(false);
  const [isErrorVisible, setIsErrorVisible] = useState<boolean>(false);

  let handleSubmit = async (e: any) => {
    e.preventDefault();
    try {
      axios
        .get(`http://localhost:8080/api/issuers/${issuerId}/instruments`)
        .then((response) => {
          setEntities(response.data);
          setIsVisible(true);
          setIsErrorVisible(false);
          if (response.data.length === 0) {
            setIsErrorVisible(true);
          }
        })
        .catch((error) => {
          console.error(
            `Error fetching instruments of issuer ${issuerId}`,
            error
          );
          setIsVisible(false);
          setIsErrorVisible(true);
        });
    } catch (err) {
      setIsErrorVisible(true);
      console.log(err);
    }
  };

  return (
    <>
      <form onSubmit={handleSubmit}>
        <input
          type="text"
          name="issuerId"
          id="issuerId"
          placeholder="Issuer ID"
          onChange={(e) => setIssuerId(Number(e.target.value))}
          className="m-2 mt-8 p-2 bg-gray-200 text-black placeholder:text-gray-500 rounded-sm ring-1 ring-gray-300 flex-row"
        />
        <button
          type="submit"
          className="text-base mb-5 font-semibold focus:outline-none text-white transition bg-purple-700 hover:bg-purple-800 focus:ring-4 focus:ring-purple-300 rounded-lg px-5 py-2.5 dark:bg-purple-600 dark:hover:bg-purple-700 dark:focus:ring-purple-900"
        >
          Find
        </button>
      </form>
      <div className="relative items-center flex flex-col">
        <ul className="flex flex-wrap justify-center mb-8">
          {entities.map((entity: T) => (
            <ul
              className="p-5 shadow shadow-black outline outline-1 rounded-md bg-gray-200 m-10"
              key={entity.id}
            >
              {Object.entries(entity).map(([property, value]) => (
                <li key={property}>
                  <span className="font-semibold">
                    {toReadableFormat(property)}:{" "}
                  </span>
                  {value}
                </li>
              ))}
            </ul>
          ))}
        </ul>

        <div className="font-semibold text-lg text-red-500">
          {isErrorVisible
            ? `Error fetching instruments of issuer with id ${issuerId}`
            : null}
        </div>
      </div>
    </>
  );
}
