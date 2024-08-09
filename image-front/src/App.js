import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Board from "./component/Board";
import Profile from "./component/Profile";
import Header from "./component/layout/Header";
import Main from "./component/Main";
import Footer from "./component/layout/Footer";
import Banner from "./component/layout/Banner";
// front end api url 설정
// -> react router dom Router

// front end api url
// Board path = "/board"
// Profile  path = "/profile"
function App() {
  return (
    <Router>
      <Banner />
      <Header />
      <Routes>
        <Route path="/" element={<Main />} />
        <Route path="/board" element={<Board />}></Route>
        <Route path="/profile" element={<Profile />}></Route>
      </Routes>
      <Footer />
    </Router>
  );
}

export default App;
