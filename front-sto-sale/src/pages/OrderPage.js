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
  Dialog,
  DialogActions,
  DialogContent,
  DialogContentText,
  DialogTitle,
  Alert,
  AlertTitle,
} from "@mui/material";
// import { DetailProductContext } from "./ListProductsSalePage";

const OrderPage = () => {
  const { goods_id } = useParams();
  // const { detailProduct } = useContext(DetailProductContext);
  const [detailProduct, setDetailProduct] = useState({});
  const [userIds, setUserIds] = useState([]);
  const [purchaseQuantity, setPurchaseQuantity] = useState("");
  const [user, setUser] = useState("");
  const [open, setOpen] = useState(false);
  const [open2, setOpen2] = useState(false);

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

  const totalPurchasePrice = purchaseQuantity * detailProduct.unit_amt;

  const handleClickPurchase = (event, id) => {
    setOpen(true);

    //   // `한 조각 당 가격 : ${unitPrice}\n

    // axios
    //   .get("/product/insert", {
    //     params: { purchaseQuantity: purchaseQuantity },
    //   })
    //   .catch(function () {
    //     console.log("실패");
    //   });
  };
  const handleClickFailPurchase = (event, id) => {
    setOpen2(true);
  };
  const handleCloseFailPurchase = (event, id) => {
    setOpen2(false);
  };

  const handleCloseCancel = () => {
    setUser("");
    setPurchaseQuantity("");
    setOpen(false);
  };

  const handleCloseCheck = () => {
    axios
      .all([
        axios.post(`/holding/add`, {
          userId: user,
          goodsId: goods_id,
          goods_cnt: purchaseQuantity,
        }),
        axios.post(`/sale/update`, {
          sale_goods_id: goods_id,
          sale_cnt: purchaseQuantity,
          sale_rate: detailProduct.sale_rate,
          total_cnt: detailProduct.total_cnt,
        }),
      ])
      .then(
        axios.spread((response1, response2) => {
          console.log("response1", response1, "response2", response2);
        })
      )
      .catch((error) => {
        console.log("error", error);
      });
    setOpen(false);
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
          <h2>단위 가격 : {detailProduct.unit_amt}</h2>
          <h2>전체 수량 : {detailProduct.total_cnt}</h2>
          <h2>구매 가능 수량 : {availableQuantity}</h2>
          <h2>구매 수수료: {detailProduct.order_fee}</h2>
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
        <Button
          onClick={(event) =>
            purchaseQuantity > availableQuantity
              ? handleClickFailPurchase(event)
              : handleClickPurchase(event)
          }
        >
          구매
        </Button>
      </Paper>
      <Dialog
        fullWidth={true}
        open={open}
        onClose={handleCloseCancel}
        aria-labelledby="alert-dialog-title"
        aria-describedby="alert-dialog-description"
      >
        <DialogTitle id="alert-dialog-title">{"구매 내역"}</DialogTitle>
        <DialogContent>
          <DialogContentText id="alert-dialog-description">
            <span style={{ color: "black" }}>
              user id : {user} <br />
              구매 수량 : {purchaseQuantity} 개
              <br />총 구매 가격 : {totalPurchasePrice} 원
              <br />
            </span>
          </DialogContentText>
        </DialogContent>
        <DialogActions>
          <Button onClick={handleCloseCancel}>취소</Button>
          <Button onClick={handleCloseCheck} autoFocus>
            확인
          </Button>
        </DialogActions>
      </Dialog>
      <Dialog fullWidth={true} open={open2} onClose={handleCloseFailPurchase}>
        <Alert severity="error">
          <AlertTitle>Error</AlertTitle>
          This is an error alert — <strong>check it out!</strong>
        </Alert>
      </Dialog>
    </div>
  );
};

export default OrderPage;
