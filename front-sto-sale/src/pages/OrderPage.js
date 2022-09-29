import axios from "axios";
import React, { useState, useContext, useEffect } from "react";
import { useParams } from "react-router-dom";
import {
  Box,
  Button,
  Paper,
  InputAdornment,
  TextField,
  InputLabel,
  MenuItem,
  FormControl,
  Select,
  Grid,
} from "@mui/material";
// import { DetailProductContext } from "./ListProductsSalePage";

const OrderPage = () => {
  const { goods_id } = useParams();
  // const { detailProduct } = useContext(DetailProductContext);
  const [detailProduct, setDetailProduct] = useState({});
  const [purchaseQuantity, setPurchaseQuantity] = useState(0);
  const [user, setUser] = useState("");

  useEffect(() => {
    axios
      .get(`/product/order/${goods_id}`)
      .then((res) => {
        setDetailProduct(res.data);
        // console.log("detailtest", res);
      })
      .catch((error) => {
        console.log("error", error);
      });
  }, []);
  const handleChangeUser = (event) => {
    setUser(event.target.value);
  };

  const availableQuantity = detailProduct.total_cnt - detailProduct.sale_cnt;
  console.log("availableQuantity", typeof detailProduct.sale_cnt);
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
          <div>상품 {goods_id} 페이지</div>
          <h2>상품 번호 : {detailProduct.goods_id}</h2>
          <h2>상품명 : {detailProduct.goods_nm}</h2>
          <h2>상품 상태 : {detailProduct.stat}</h2>
          <h2>전체 가격 : {detailProduct.total_amt}</h2>
          <h2>전체 수량 : {detailProduct.total_cnt}</h2>
          <h2>구매 가능 수량 : {availableQuantity}</h2>
          <h2>구매 수수료: {detailProduct.ordr_fee}</h2>
          <h2>거래 수수료: {detailProduct.trade_fee}</h2>
          <h2>생성일시 : {detailProduct.created_dt}</h2>
          <h2>생성자 : {detailProduct.created_by}</h2>
        </Paper>
      </Box>
      <Paper variant="outlined" sx={{ m: 1 }}>
        <br />
        <Grid container spacing={2}>
          <Grid item={true} xs={6}>
            <FormControl sx={{ minWidth: 230 }}>
              <InputLabel id="demo-simple-select-label">UserID</InputLabel>
              <Select
                labelId="demo-simple-select-label"
                id="demo-simple-select"
                value={user}
                label="User"
                onChange={handleChangeUser}
              >
                <MenuItem value={1}>1</MenuItem>
                <MenuItem value={2}>2</MenuItem>
                <MenuItem value={3}>3</MenuItem>
              </Select>
            </FormControl>
          </Grid>
          <Grid item={true} xs={6}>
            <TextField
              id="outlined-number"
              label="구매 수량"
              type="number"
              value={purchaseQuantity}
              InputProps={{
                endAdornment: (
                  <InputAdornment position="end">조각</InputAdornment>
                ),
              }}
              onChange={(e) => {
                setPurchaseQuantity(parseInt(e.target.value));
              }}
              InputLabelProps={{
                shrink: true,
              }}
            />
          </Grid>
        </Grid>
        <br />
      </Paper>
      <Paper>
        <Button>구매</Button>
      </Paper>
    </div>
  );
};

export default OrderPage;
