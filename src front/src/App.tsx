import { BrowserRouter, Routes, Route } from "react-router-dom";
import Member from "./components/member/Member";
import Venue from "./components/venue/Venue";
import PageNotFound from "./components/PageNotFound";
import Navbar from "./components/Navbar";
import Instrument from "./components/instrument/Instrument";
import Issuer from "./components/issuer/Issuer";

function App() {
  return (
    <div className="">
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<Navbar />}>
            <Route path="members" element={<Member />} />
            <Route path="venues" element={<Venue />} />
            <Route path="instruments" element={<Instrument />} />
            <Route path="issuers" element={<Issuer />} />
            <Route path="*" element={<PageNotFound />} />
          </Route>
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
