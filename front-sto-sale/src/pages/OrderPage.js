import React, { useState } from "react";
import { useParams } from "react-router-dom";

const OrderPage = (props) => {
  const { goods_id } = useParams();

  return (
    <div>
      <div>{goods_id} 페이지</div>
    </div>
  );
};

export default OrderPage;
