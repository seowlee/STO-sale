import axios from "axios";
import React, { useState, useEffect } from "react";
import { useParams } from "react-router-dom";
import {
  Box,
  Button,
  Paper,
  InputAdornment,
  TextField,
  Grid,
  Dialog,
  DialogActions,
  DialogContent,
  DialogContentText,
  DialogTitle,
  Alert,
  AlertTitle,
} from "@mui/material";
import { useNavigate } from "react-router-dom";

// import { DetailProductContext } from "./ListProductsSalePage";

const OrderPage = () => {
  const { goods_id } = useParams();
  // const { detailProduct } = useContext(DetailProductContext);
  const [detailProduct, setDetailProduct] = useState({});
  // const [userIds, setUserIds] = useState([]);
  const [purchaseQuantity, setPurchaseQuantity] = useState("");
  const [user, setUser] = useState({});
  const [openSuccess, setOpenSuccess] = useState(false);
  const [openFailQuantity, setOpenFailQuantity] = useState(false);
  const [openFailLackBalance, setOpenFailLackBalance] = useState(false);

  useEffect(() => {
    axios
      .all([
        axios.get(`/product/order/${goods_id}`),
        axios.get(`/user/loggedIn`),
      ])
      .then(
        axios.spread((res1, res2) => {
          setDetailProduct(res1.data);
          setUser(res2.data);
          // console.log("detailtest", res);
        })
      )
      .catch((error) => {
        console.log("error", error);
      });
  }, [goods_id]);

  const totalGoodsPrice = purchaseQuantity * detailProduct.unit_amt;
  const totalPurchasePrice = parseInt(
    purchaseQuantity *
      detailProduct.unit_amt *
      (1 + detailProduct.order_fee / 100)
  );
  const availableQuantity = detailProduct.total_cnt - detailProduct.sale_cnt;

  // const handleChangeUser = (event) => {
  //   setUser(event.target.value);
  // };

  const handleClickOptionPurchase = (event) => {
    if (purchaseQuantity > availableQuantity) {
      handleClickFailPurchaseQuantity();
    } else if (user.user_account < totalPurchasePrice) {
      handleClickFailPurchaseLackBalance();
    } else {
      handleClickPurchase();
    }
  };
  console.log("accccccount", user.user_account);

  const handleClickPurchase = (event, id) => {
    setOpenSuccess(true);
  };

  // 구매 가능 수량 초과 error dialog
  const handleClickFailPurchaseQuantity = (event, id) => {
    setOpenFailQuantity(true);
  };

  const handleCloseFailPurchaseQuantity = (event, id) => {
    setPurchaseQuantity("");
    setOpenFailQuantity(false);
  };

  // 구매 금액 잔액 부족 error dialog
  const handleClickFailPurchaseLackBalance = (event, id) => {
    setOpenFailLackBalance(true);
  };

  const handleCloseFailPurchaseLackBalance = (event, id) => {
    setPurchaseQuantity("");
    setOpenFailLackBalance(false);
  };

  const handleCloseCancel = () => {
    setUser("");
    setPurchaseQuantity("");
    setOpenSuccess(false);
  };

  const navigate = useNavigate();
  const handleCloseCheck = async () => {
    const transactionDate = new Date();
    try {
      const [res1, res2, res3, res4] = await Promise.all([
        axios.post(`/holding/add`, {
          userId: user.user_id,
          goodsId: goods_id,
          goods_cnt: purchaseQuantity,
        }),
        axios.post(`/sale/update`, {
          sale_goods_id: goods_id,
          sale_cnt: purchaseQuantity,
          sale_rate: detailProduct.sale_rate,
          total_cnt: detailProduct.total_cnt,
        }),
        axios.post(`/transaction/add`, {
          userId: user.user_id,
          goodsId: goods_id,
          transactionCnt: purchaseQuantity,
          transactionStat: 0,
          transactionDt: transactionDate,
        }),
        axios.post(`/user/update`, {
          user_id: user.user_id,
          price: totalPurchasePrice,
        }),
      ]);
      console.log("res1", res1, "res2", res2, "res3", res3, "res4", res4);
      const res5 = await axios.post(`/product/stat/update`, {
        goodsId: goods_id,
      });
      console.log("res5", res5);
    } catch (error) {
      console.log("error", error);
    }
    setOpenSuccess(false);
    navigate("/listOnSale");
  };

  const convertDate = (date) => {
    const newDate = date.slice(0, 19);
    return newDate;
  };
  // const handleClickChangeGoodsStat = () => {
  //   axios
  //     .post(`/product/stat/update`, {
  //       goodsId: goods_id,
  //     })
  //     .then((res) => {
  //       console.log("goods stat", res);
  //     })
  //     .catch((error) => {
  //       console.log("error", error);
  //     });
  //   navigate("/listOnSale");
  // };

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
          {/* <Grid item={true} xs={6}>
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
                ))} */}
          {/* <MenuItem value={1}>1</MenuItem>
                 <MenuItem value={2}>2</MenuItem>
                 <MenuItem value={3}>3</MenuItem> */}
          {/* </Select>
            </FormControl>
          </Grid> */}
          <Grid item={true} xs={12}>
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
      <Paper style={{ marginBottom: 50 }}>
        <Button
          variant="contained"
          size="large"
          onClick={(event) => handleClickOptionPurchase()}
        >
          구매
        </Button>
      </Paper>
      <Dialog
        fullWidth={true}
        open={openSuccess}
        onClose={handleCloseCancel}
        aria-labelledby="alert-dialog-title"
        aria-describedby="alert-dialog-description"
      >
        <DialogTitle id="alert-dialog-title">{"구매 내역"}</DialogTitle>
        <DialogContent>
          <DialogContentText id="alert-dialog-description">
            <span style={{ color: "black" }}>
              user id : {user.user_id} <br />
              구매 수량 : {purchaseQuantity} 개
              <br />총 상품 가격 : {totalGoodsPrice} 원
              <br />총 구매 가격 : {totalPurchasePrice} 원
              <br />
            </span>
          </DialogContentText>
        </DialogContent>
        <DialogActions>
          <Button onClick={handleCloseCancel}>취소</Button>
          <Button
            onClick={() => {
              handleCloseCheck();
            }}
            autoFocus
          >
            확인
          </Button>
        </DialogActions>
      </Dialog>
      <Dialog
        fullWidth={true}
        open={openFailQuantity}
        onClose={handleCloseFailPurchaseQuantity}
      >
        <Alert severity="error">
          <AlertTitle>구매 가능 수량 초과 Error</AlertTitle>
          구매 수량은 구매 가능 수량을 초과할 수 없습니다! —{" "}
          <strong>check it out!</strong>
        </Alert>
      </Dialog>
      <Dialog
        fullWidth={true}
        open={openFailLackBalance}
        onClose={handleCloseFailPurchaseLackBalance}
      >
        <Alert severity="error">
          <AlertTitle>계좌 잔액 부족 Error</AlertTitle>
          계좌 잔액이 부족하여 구매 할 수 없습니다! —{" "}
          <strong>check it out!</strong>
        </Alert>
      </Dialog>
    </div>
  );
};

export default OrderPage;
