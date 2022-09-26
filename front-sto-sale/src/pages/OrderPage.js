import axios from "axios";
import React, { useState, useContext, useEffect } from "react";
import { useParams } from "react-router-dom";
import { Box, Button, Paper } from "@mui/material";
// import { DetailProductContext } from "./ListProductsSalePage";

const OrderPage = () => {
  const { goods_id } = useParams();
  // const { detailProduct } = useContext(DetailProductContext);
  const [detailProduct, setDetailProduct] = useState({});

  // useEffect(() => {
  //   console.log("id : ", goods_id);
  //   fetch("http://localhost:8080/order/" + goods_id)
  //     .then((res) => res.json())
  //     .then((data) => {
  //       console.log("data : ", data);
  //       setDetailProduct(data);
  //     })
  //     .catch((error) => {
  //       console.log("에러", error);
  //     });
  // }, []);
  useEffect(() => {
    axios
      .get("/order/" + goods_id)
      .then((res) => {
        setDetailProduct(res.data);
        // console.log("detailtest", res);
      })
      .catch((error) => {
        console.log("error", error);
      });
  }, []);

  // console.log("param", goods_id);
  // console.log("Success", detailProduct);

  return (
    <div>
      <Box
        sx={{
          display: "flex",
          "& > :not(style)": {
            m: 1,
            width: 1,
            minWidth: 300,
            minheight: 270,
          },
        }}
      >
        <Paper variant="outlined">
          <div>{goods_id} 페이지</div>
          <h2>상품 번호 : {detailProduct.goods_id}</h2>
          <h2>상품명 : {detailProduct.goods_nm}</h2>
          <h2>상품 상태 : {detailProduct.stat}</h2>
          <h2>구매 가능 수량 : {detailProduct.stat}</h2>
          <h2>구매 수수료: {detailProduct.ordr_fee}</h2>
          <h2>거래 수수료: {detailProduct.trade_fee}</h2>
          <h2>생성일시 : {detailProduct.created_dt}</h2>
          <h2>생성자 : {detailProduct.created_by}</h2>
        </Paper>
      </Box>
      <Paper>
        <Button>구매</Button>
      </Paper>
    </div>
  );
};

export default OrderPage;
