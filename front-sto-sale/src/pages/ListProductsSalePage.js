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
  Grid,
} from "@mui/material";
import axios from "axios";
import { useNavigate, Link } from "react-router-dom";

const ListProductsSalePage = (props) => {
  const [product, setProduct] = useState([]);

  // const [progress, setProgress] = React.useState(10.0);
  // console.log("mem", product);
  useEffect(() => {
    axios.get("/product/select").then((res) => {
      setProduct(res.data);
      console.log("test", res);
    });
  }, []);

  const navigate = useNavigate();

  const navigateToOrder = () => {
    navigate("/order", { state: product });
  };

  return (
    <div style={{ marginBottom: 150 }}>
      {product.map((productData, idx) => (
        <Card key={idx} sx={{ minWidth: 275 }} variant="outlined">
          <CardContent>
            <Typography variant="h5" component="div">
              id: {productData.goods_id}, name : {productData.goods_nm}
            </Typography>
            <Typography variant="body2">
              stat : {productData.stat}
              <br />
              total_amt : {productData.total_amt}
              <br />
              sale_amt : {productData.sale_amt}
              <br />
              order_fee : {productData.ordr_fee}
              <br />
              created_dt : {productData.created_dt}
              <br />
              created_by : {productData.created_by}
            </Typography>
            <br />
            <br />
            <Grid container spacing={2}>
              <Grid item={true} xs={3}>
                <Typography variant="body2">판매율 :</Typography>
              </Grid>
              <Grid item={true} xs={9}>
                {/* <ProgressBar /> */}
                <Box sx={{ width: "100%" }}>
                  <ProgressBar value={productData.sales_rate} />
                </Box>
              </Grid>
            </Grid>
          </CardContent>
          <Divider />
          <CardActions>
            <Link to={`/order/${productData.goods_id}`}>
              {productData.goods_nm}
            </Link>
            {/* <Button size="large" onClick={navigateToOrder}>
              구매하기
            </Button> */}
          </CardActions>
        </Card>
      ))}
    </div>
  );
};

export default ListProductsSalePage;
