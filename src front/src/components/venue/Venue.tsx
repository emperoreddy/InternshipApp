import GetAllEntities from "../entities/GetAllEntities";
import GetEntityById from "../entities/GetEntityById";
import Venue from "../../interfaces/VenueInterface";
import CreateVenue from "./CreateVenue";
import UpdateVenue from "./UpdateVenue";
import GetMembersOrInstrumentsOfVenue from "./GetMembersOfVenue";
import AddInstrument from "./AddInstrument";
import Member from "../../interfaces/MemberInterface";
import Instrument from "../../interfaces/InstrumentInterface";

export default function Venue() {
  return (
    <>
      <div className=" m-20  flex place-items-start flex-col ">
        <h1 className="font-bold text-3xl relative mb-8">Venues</h1>
        <GetAllEntities<Venue> apiEndpoint="venues" />

        <h1 className="text-xl mt-14 mb-8 font-semibold">Create a new venue</h1>
        <CreateVenue />

        <h1 className="text-xl mt-14 font-semibold">Get a venue by id</h1>
        <GetEntityById<Venue> apiEndpoint="venues" />

        <h1 className="text-xl mt-14 font-semibold mb-8">Update a venue</h1>
        <UpdateVenue />

        <h1 className="text-xl mt-14 font-semibold mb-8">Get members of venue</h1>
        <GetMembersOrInstrumentsOfVenue<Member> getEntities="members"/>

        <h1 className="text-xl mt-14 font-semibold mb-8">Get instruments of venue</h1>
        <GetMembersOrInstrumentsOfVenue<Instrument> getEntities="instruments"/>

        <h1 className="text-xl mt-14 font-semibold mb-8">Add instrument by venue's id</h1>
      <AddInstrument/>
      </div>
    </>
  );
}
