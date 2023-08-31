import CreateMemberForm from "./CreateMember";
import GetEntityById from "../entities/GetEntityById";
import UpdateMember from "./UpdateMember";
import DeleteEntityById from "../entities/DeleteEntityById";
import GetAllEntities from "../entities/GetAllEntities";
import Member from "../../interfaces/MemberInterface";

export default function MemberList() {
  return (
    <>
      <div className=" m-20  flex place-items-start flex-col ">
        <h1 className="font-bold text-3xl relative mb-8">Members</h1>
        <GetAllEntities<Member> apiEndpoint = "members"/>

        <h1 className="text-xl mt-14 mb-8 font-semibold">
          Create a new member
        </h1>
        <CreateMemberForm />

        <h1 className="text-xl mt-14 font-semibold">Get a member by id</h1>
        <GetEntityById<Member>  apiEndpoint = "members"/>

        <h1 className="text-xl mt-14 font-semibold">Delete a member by id</h1>
        <DeleteEntityById  apiEndpoint = "members"/>

        <h1 className="text-xl mt-14 font-semibold mb-8">Update a member</h1>
        <UpdateMember />
      </div>
    </>
  );
}
