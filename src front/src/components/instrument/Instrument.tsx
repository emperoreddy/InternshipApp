import Instrument from "../../interfaces/InstrumentInterface";
import DeleteEntityById from "../entities/DeleteEntityById";
import GetAllEntities from "../entities/GetAllEntities";
import GetEntityById from "../entities/GetEntityById";
import CreateInstrument from "./CreateInstrument";
import UpdateInstrument from "./UpdateInstrument";

export default function Instrument() {
  return (
    <>
      <div className=" m-20  flex place-items-start flex-col ">
        <h1 className="font-bold text-3xl relative mb-8">Instruments</h1>
        <GetAllEntities<Instrument> apiEndpoint="instruments" />

        <h1 className="text-xl mt-14 mb-8 font-semibold">
          Create a new instrument
        </h1>
        <CreateInstrument />

        <h1 className="text-xl mt-14 mb-8 font-semibold">
          Delete an instrument
        </h1>
        <DeleteEntityById apiEndpoint="instruments" />

        <h1 className="text-xl mt-14 font-semibold">Get an instrument by id</h1>
        <GetEntityById<Instrument> apiEndpoint="instruments" />

        <h1 className="text-xl mt-14 font-semibold">Update an instrument</h1>
        <UpdateInstrument/>
      </div>
    </>
  );
}
