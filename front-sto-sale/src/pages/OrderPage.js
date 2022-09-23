import React, { useState, useContext, useEffect } from "react";
import { useParams } from "react-router-dom";
import { DetailProductContext } from "./ListProductsSalePage";

const OrderPage = () => {
  const { goods_id } = useParams();
  // const { detailProduct } = useContext(DetailProductContext);
  const [detailProduct, setDetailProduct] = useState({
    goods_id: "",
    goods_nm: "",
  });

  useEffect(() => {
    console.log("id : ", goods_id);
    fetch("http://localhost:8080/order/" + goods_id)
      .then((res) => res.json())
      .then((data) => {
        console.log("data : ", data);
        setDetailProduct(data);
      })
      .catch((error) => {
        console.log("에러", error);
      });
  }, []);
  console.log("param", goods_id);
  console.log("Success", detailProduct);

  return (
    <div>
      <div>{goods_id} 페이지</div>
      <h1>{detailProduct.goods_nm}</h1>
    </div>
  );
};

export default OrderPage;
