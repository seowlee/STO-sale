import React, { useState, useEffect } from "react";
import axios from "axios";

const HoldingListPage = () => {
  const [holdings, setHoldings] = useState([]);
  useEffect(() => {
    axios
      .get("/holding/all")
      .then((res) => {
        setHoldings(res.data);
        // console.log("test", res);
      })
      .catch((error) => {
        console.log("error", error);
      });
  }, []);
  return <div>HoldingListPage</div>;
};

export default HoldingListPage;
