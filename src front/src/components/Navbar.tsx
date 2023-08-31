import { Link, Outlet } from "react-router-dom";

export default function Navbar() {
  return (
    <>
      <nav className="bg-gray-800 text-xl p-4 flex justify-center">
        <ul className="flex space-x-10  text-white">
          <li className="hover:text-gray-400">
            <Link to="/members">Members</Link>
          </li>
          <li className="hover:text-gray-400">
            <Link to="/venues">Venues</Link>
          </li>
          <li className="hover:text-gray-400">
            <Link to="/instruments">Instruments</Link>
          </li>
          <li className="hover:text-gray-400">
            <Link to="/issuers">Issuers</Link>
          </li>
        </ul>
      </nav>
      <Outlet />
    </>
  );
}
