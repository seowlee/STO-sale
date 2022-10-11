import React from "react";
import "./App.css";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import ResponsiveAppBar from "./components/ResponsiveAppBar";
// import NavigationBar from "./components/NavigationBar";
// import SalesRegistrationPage from "./pages/SalesRegistrationPage";
import ListProductsSalePage from "./pages/ListProductsSalePage";
import OrderPage from "./pages/OrderPage";
import HomePage from "./pages/HomePage";
import GoodsHoldingPage from "./pages/GoodsHoldingPage";
import TransactionHistoryPage from "./pages/TransactionHistoryPage";
import UserHoldingPage from "./pages/UserHoldingPage";
import ListProductsSoldOutPage from "./pages/ListProductsSoldOutPage";
import InfoProductSoldOutPage from "./pages/InfoProductSoldOutPage";

function App() {
  // const [message, setMessage] = useState([]);

  // useEffect(() => {
  //   fetch("/hello")
  //     .then((res) => {
  //       return res.json();
  //     })
  //     .then((data) => {
  //       setMessage(data);
  //     });
  // }, []);

  // const [member, setMember] = useState([]);
  // console.log("test", member);

  // useEffect(() => {
  //   fetch("/member/select")
  //     .then((res) => {
  //       return res.json();
  //     })
  //     .then((data) => {
  //       setMember(data);
  //     });
  // }, []);

  return (
    <div className="App">
      {/* <header className="App-header">
        <ul>
          {message.map((v, idx) => (
            <li key={`${idx}-${v}`}>{v}</li>
          ))}
        </ul>
        <ul>
          {member.map((v, idx) => (
            <li key={`${idx}-${v}`}>
              id: {v.id}, name : {v.name}, password : {v.password}
            </li>
          ))}
        </ul>
      </header> */}
      <BrowserRouter>
        <ResponsiveAppBar />
        <Routes>
          {/* <Route
            path="/salesRegistration"
            element={<SalesRegistrationPage />}
          ></Route> */}
          <Route path="/" element={<HomePage />}></Route>
          <Route path="/listOnSale" element={<ListProductsSalePage />}></Route>
          <Route path="/order/:goods_id" element={<OrderPage />}></Route>
          <Route
            path="/listSoldOut"
            element={<ListProductsSoldOutPage />}
          ></Route>
          <Route
            path="/listSoldOut/info/:goods_id"
            element={<InfoProductSoldOutPage />}
          ></Route>
          <Route path="/goodsHoldings" element={<GoodsHoldingPage />}></Route>
          <Route path="/userHoldings" element={<UserHoldingPage />}></Route>
          <Route
            path="/transactionHistory"
            element={<TransactionHistoryPage />}
          ></Route>
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
