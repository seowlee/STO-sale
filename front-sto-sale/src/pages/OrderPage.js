import React, { useState, useContext } from "react";
import { useParams } from "react-router-dom";

const OrderPage = ({ history, location, match }) => {
  const { goods_id } = useParams();

  return (
    <div>
      <div>{goods_id} 페이지</div>
    </div>
  );
};

export default OrderPage;
