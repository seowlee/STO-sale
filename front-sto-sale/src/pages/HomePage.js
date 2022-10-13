import React, { useState, useEffect } from "react";
import {
  Button,
  Paper,
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
import { useNavigate } from "react-router-dom";
import axios from "axios";

const HomePage = () => {
  const [userIds, setUserIds] = useState([]);
  const [user, setUser] = useState("");
  const [open, setOpen] = useState(false);

  const handlePostSaleInfo = () => {
    axios
      .post(`/sales/all`, {})
      .then((res) => {})
      .catch((error) => {
        console.log("error", error);
      });
  };

  useEffect(() => {
    axios
      .get(`/user/ids`)
      .then((res) => {
        setUserIds(res.data);
        // console.log("test", res);
      })
      .catch((error) => {
        console.log("error", error);
      });
  }, []);

  const handleChangeUser = (event) => {
    setUser(event.target.value);
  };

  const handleClickLogIn = (event, id) => {
    setOpen(true);
  };

  const handleCloseCancel = () => {
    setUser("");
    setOpen(false);
  };

  const handleCloseCheck = () => {
    axios
      .post("/user/loggedIn", { userId: user })
      .then((res) => {
        console.log("logging id", res);
      })
      .catch((error) => {
        console.log("error", error);
      });
    setOpen(false);
  };

  return (
    <div>
      HomePage <br />
      <br />
      <Button variant="contained" size="large" onClick={handlePostSaleInfo}>
        판매정보 초기화
      </Button>
      <br />
      <Paper variant="outlined" sx={{ mt: 10, ml: 2, mr: 2 }}>
        <h3>구매 회원 선택</h3>
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
            <Button
              variant="contained"
              size="large"
              onClick={(event) => handleClickLogIn()}
            >
              회원 선택
            </Button>
          </Grid>
        </Grid>
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
    </div>
  );
};

export default HomePage;
