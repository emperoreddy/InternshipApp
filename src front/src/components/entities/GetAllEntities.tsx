import axios from "axios";
import { useEffect, useState } from "react";
import toReadableFormat from "../../util functions/toReadableFormat";
import { Link } from "react-router-dom";

export default function GetAllEntities<T>(props: any) {
  const [allEntities, setAllEntities] = useState<T[]>([]);
  const [filteredEntities, setFilteredEntities] = useState<T[]>([]);
  const [showComponent, setShowComponent] = useState(false);
  const [filterProperty, setFilterProperty] = useState("");

  useEffect(() => {
    axios
      .get(`http://localhost:8080/api/${props.apiEndpoint}`)
      .then((response) => {
        setAllEntities(response.data);
      })
      .catch((error) => {
        console.error(`Error fetching ${props.apiEndpoint}:`, error);
      });
  }, []);

  useEffect(() => {
    const filtered = allEntities.filter((entity) => {
      const lowerCaseProperty = filterProperty.toLowerCase();
      return Object.values(entity).some((value) =>
        value.toString().toLowerCase().includes(lowerCaseProperty)
      );
    });
    setFilteredEntities(filtered);
  }, [allEntities, filterProperty]);

  const handleShowComponent = () => {
    setShowComponent(!showComponent);
  };

  return (
    <div className="relative items-center flex flex-col">
      <input
        type="text"
        placeholder="Filter by property value"
        value={filterProperty}
        onChange={(e) => setFilterProperty(e.target.value)}
        className="m-2 p-2 bg-gray-200 text-black placeholder:text-gray-500 rounded-sm ring-1 ring-gray-300 flex-row"
      />
      <button
        type="button"
        className="text-base my-5 font-semibold focus:outline-none text-white transition bg-purple-700 hover:bg-purple-800 focus:ring-4 focus:ring-purple-300 rounded-lg px-5 py-2.5 dark:bg-purple-600 dark:hover:bg-purple-700 dark:focus:ring-purple-900"
        onClick={handleShowComponent}
      >
        {showComponent
          ? `Hide ${props.apiEndpoint}`
          : `Show ${props.apiEndpoint}`}
      </button>
      {showComponent && (
        <ul className="flex flex-wrap justify-center mb-8">
          {filteredEntities.map((entity) => (
            <ul
              className="p-5 shadow shadow-black outline outline-1 rounded-md bg-gray-200  m-10"
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
      )}
    </div>
  );
}
