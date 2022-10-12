import axios from "axios";
import React, { useState, useEffect } from "react";
import { useParams } from "react-router-dom";
import { Box, Button, Paper } from "@mui/material";
import { useNavigate } from "react-router-dom";

// import { DetailProductContext } from "./ListProductsSalePage";

const InfoProductSoldOutPage = () => {
  const { goods_id } = useParams();
  // const { detailProduct } = useContext(DetailProductContext);
  const [detailProduct, setDetailProduct] = useState({});
  // const [userIds, setUserIds] = useState([]);
  // const [purchaseQuantity, setPurchaseQuantity] = useState("");
  // const [user, setUser] = useState("");
  // const [open, setOpen] = useState(false);
  // const [open2, setOpen2] = useState(false);

  useEffect(() => {
    axios
      .all([axios.get(`/product/order/${goods_id}`)])
      .then(
        axios.spread((res1) => {
          setDetailProduct(res1.data);
          // console.log("detailtest", res);
        })
      )
      .catch((error) => {
        console.log("error", error);
      });
  }, [goods_id]);

  //   // `한 조각 당 가격 : ${unitPrice}\n

  // axios
  //   .get("/product/insert", {
  //     params: { purchaseQuantity: purchaseQuantity },
  //   })
  //   .catch(function () {
  //     console.log("실패");
  //   });

  const navigate = useNavigate();

  // navigate("/listOnSale");

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
          <div>상품 {goods_id} 페이지</div>
          <h2>상품 번호 : {detailProduct.goods_id}</h2>
          <h2>상품명 : {detailProduct.goods_nm}</h2>
          <h2>상품 상태 : {detailProduct.stat}</h2>
          <h2>전체 가격 : {detailProduct.total_amt}</h2>
          <h2>단위 가격 : {detailProduct.unit_amt}</h2>
          <h2>전체 수량 : {detailProduct.total_cnt}</h2>
          <h2>판매완료수량 : {detailProduct.sale_cnt}</h2>
          <h2>구매 수수료: {detailProduct.order_fee}</h2>
          <h2>거래 수수료: {detailProduct.trade_fee}</h2>
          <h2>생성일시 : {detailProduct.created_dt}</h2>
          <h2>생성자 : {detailProduct.created_by}</h2>
        </Paper>
      </Box>

      <Paper style={{ marginBottom: 50 }}>
        <Button
          size="large"
          variant="contained"
          onClick={() => navigate("/listSoldOut")}
        >
          돌아가기
        </Button>
      </Paper>
    </div>
  );
};

export default InfoProductSoldOutPage;
