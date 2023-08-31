import Instrument from "../../interfaces/InstrumentInterface";
import Issuer from "../../interfaces/IssuerInterface";
import DeleteEntityById from "../entities/DeleteEntityById";
import GetAllEntities from "../entities/GetAllEntities";
import GetEntityById from "../entities/GetEntityById";
import AddInstrumentToIssuer from "./AddInstrumentToIssuer";
import CreateIssuer from "./CreateIssuer";
import GetInstrumentsOfIssuer from "./GetInstrumentsOfIssuer";
import UpdateIssuer from "./UpdateIssuer";

export default function Issuer() {
  return (
    <>
      <div className=" m-20  flex place-items-start flex-col ">
        <h1 className="font-bold text-3xl relative mb-8">Issuers</h1>
        <GetAllEntities<Issuer> apiEndpoint="issuers" />

        <h1 className="text-xl mt-14 mb-8 font-semibold">
          Create a new issuer
        </h1>
        <CreateIssuer />

        <h1 className="text-xl mt-14 mb-8 font-semibold">Delete an issuer</h1>
        <DeleteEntityById apiEndpoint="issuers" />

        <h1 className="text-xl mt-14 font-semibold">Get an instrument by id</h1>
        <GetEntityById<Issuer> apiEndpoint="issuers" />

        <h1 className="text-xl mt-14 font-semibold">Update an instrument</h1>
        <UpdateIssuer />

        <h1 className="text-xl mt-14 font-semibold">
          Get instruments of issuer
        </h1>
        <GetInstrumentsOfIssuer<Instrument> />

        <h1 className="text-xl mt-14 font-semibold">
         Add an instrument to the issuer 
        </h1>
        <AddInstrumentToIssuer />
      </div>
    </>
  );
}
