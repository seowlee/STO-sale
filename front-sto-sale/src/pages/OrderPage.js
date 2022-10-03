import axios from "axios";
import React, { useState, useEffect } from "react";
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
  const [userIds, setUserIds] = useState([]);
  const [purchaseQuantity, setPurchaseQuantity] = useState("");
  const [user, setUser] = useState("");

  useEffect(() => {
    axios
      .all([axios.get(`/product/order/${goods_id}`), axios.get(`/user/ids`)])
      .then(
        axios.spread((res1, res2) => {
          setDetailProduct(res1.data);
          setUserIds(res2.data);
          // console.log("detailtest", res);
        })
      )
      .catch((error) => {
        console.log("error", error);
      });
  }, [goods_id]);

  const handleChangeUser = (event) => {
    setUser(event.target.value);
  };

  const totalPurchasePrice = purchaseQuantity * 2;

  const handleClickPurchase = (event, id) => {
    alert(
      // `한 조각 당 가격 : ${unitPrice}\n
      `
      user id : ${user}\n
      구매 수량 : ${purchaseQuantity}\n
      총 구매 가격 : ${totalPurchasePrice}\n
      save`
    );
    // axios
    //   .get("/product/insert", {
    //     params: { purchaseQuantity: purchaseQuantity },
    //   })
    //   .catch(function () {
    //     console.log("실패");
    //   });
  };

  const availableQuantity = detailProduct.total_cnt - detailProduct.sale_cnt;
  // console.log("Quantity", typeof purchaseQuantity);
  // console.log("param", goods_id);
  // console.log("Success", userIds);

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
              <InputLabel id="user-id-select-label">UserID</InputLabel>
              <Select
                labelId="user-id-select-label"
                id="user-id-select"
                value={user}
                label="User"
                onChange={handleChangeUser}
              >
                {userIds.map((userId, idx) => (
                  <MenuItem key={idx} value={userId}>
                    {userId}
                  </MenuItem>
                ))}
                {/* <MenuItem value={1}>1</MenuItem>
                 <MenuItem value={2}>2</MenuItem>
                 <MenuItem value={3}>3</MenuItem> */}
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
        <Button onClick={(event) => handleClickPurchase(event)}>구매</Button>
      </Paper>
    </div>
  );
};

export default OrderPage;
