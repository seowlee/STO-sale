import React, { useState, useEffect } from "react";
import ProgressBar from "../components/ProgressBar";
import {
  Box,
  Card,
  CardActions,
  CardContent,
  Divider,
  Button,
  Typography,
} from "@mui/material";
import axios from "axios";
import { useNavigate } from "react-router-dom";

const ListProductsSalePage = () => {
  const [product, setProduct] = useState([]);
  // console.log("mem", product);
  useEffect(() => {
    axios.get("/product/select").then((res) => {
      setProduct(res.data);
      console.log("test", res);
    });
  }, []);

  const navigate = useNavigate();

  const navigateToPurchase = () => {
    navigate("/purchase");
  };

  return (
    <div>
      <br />
      <Card sx={{ minWidth: 275 }}>
        <CardContent>
          <Typography variant="h5" component="div">
            benevolent
          </Typography>
          <Typography variant="body2">
            well meaning and kindly.
            <br />
            {'"a benevolent smile"'}
          </Typography>
          <ProgressBar />
        </CardContent>
        <Divider />
        <CardActions>
          <Button size="large" onClick={navigateToPurchase}>
            구매하기
          </Button>
        </CardActions>
      </Card>
      <ul>
        {product.map((v, idx) => (
          <li key={`${idx}-${v}`}>
            id: {v.goods_id}, name : {v.goods_nm}, stat : {v.stat}
          </li>
        ))}
      </ul>
    </div>
  );
};

export default ListProductsSalePage;
