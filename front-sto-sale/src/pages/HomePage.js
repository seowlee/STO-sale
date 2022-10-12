import React from "react";
import { Button } from "@mui/material";
import axios from "axios";

const HomePage = () => {
  const handlePostSaleInfo = () => {
    axios
      .post(`/sales/all`, {})
      .then((res) => {})
      .catch((error) => {
        console.log("error", error);
      });
  };
  return (
    <div>
      HomePage <br />
      <br />
      <Button variant="contained" size="large" onClick={handlePostSaleInfo}>
        판매정보 초기화
      </Button>
    </div>
  );
};

export default HomePage;
