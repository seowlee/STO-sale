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
import Paging from "../components/Paging";

// export const DetailProductContext = createContext();
const ListProductsSalePage = () => {
  const [products, setProducts] = useState([]);
  const [count, setCount] = useState(5);
  const [currentPage, setCurrentPage] = useState(1);
  const [postPerPage] = useState(5);
  // const [indexOfLastPost, setIndexOfLastPost] = useState(0);
  // const [indexOfFirstPost, setIndexOfFirstPost] = useState(0);
  // const [currentPosts, setCurrentPosts] = useState(0);

  // const [progress, setProgress] = React.useState(10.0);
  // console.log("mem", products);
  useEffect(() => {
    axios
      .all([
        axios.get(`/product/on-sale`, {
          params: {
            page: currentPage - 1,
            size: postPerPage,
          },
        }),
        axios.get(`/product/on-sale/count`),
      ])
      .then(
        axios.spread((res1, res2) => {
          setProducts(res1.data);
          setCount(res2.data);
          console.log("res1", res1, "res2", res2);
        })
      )
      .catch((error) => {
        console.log("error", error);
      });
    // setCount(products.length);
    // setIndexOfLastPost(currentPage * postPerPage);
    // setIndexOfFirstPost(indexOfLastPost - postPerPage);
    // setCurrentPosts(products.slice(indexOfFirstPost, indexOfLastPost));
  }, []);
  // }, [currentPage, indexOfLastPost, indexOfFirstPost, products, postPerPage]);

  const setPage = (error) => {
    setCurrentPage(error);
  };

  const handlePageChange = (currentPage) => {
    setCurrentPage(currentPage);
    console.log(currentPage);
    axios
      .get(`/product/on-sale`, {
        params: {
          page: currentPage - 1,
          size: postPerPage,
        },
      })
      .then((res) => {
        setProducts(res.data);
        // console.log("test", res);
      })
      .catch((error) => {
        console.log("error", error);
      });
    // setCount(products.length);
    // setIndexOfLastPost(currentPage * postPerPage);
    // setIndexOfFirstPost(indexOfLastPost - postPerPage);
  };

  // const navigate = useNavigate();

  // const navigateToOrder = () => {
  //   navigate("/order", { state: products });
  // };

  return (
    <div style={{ marginBottom: 150 }}>
      {/* {currentPosts && products.length > 0 ? (
        currentPosts.map((productData, idx) => ( */}
      {products.length > 0 ? (
        products.map((productData, idx) => (
          <Card key={idx} sx={{ minWidth: 275 }} variant="outlined">
            <CardContent>
              <Typography variant="h5" component="div">
                id: {productData.goods_id}, 상품명 : {productData.goods_nm}
              </Typography>
              {productData.stat === 0 && (
                <Typography variant="body1">상품상태 : 판매중</Typography>
              )}
              {productData.stat === 1 && (
                <Typography variant="body1">상품상태 : 판매완료</Typography>
              )}
              <Typography variant="body1">
                전체 가격 : {productData.total_amt}
              </Typography>
              {/* <Typography variant="body1">
              stat : {productData.stat}
              <br />
              전체 가격 : {productData.total_amt}
              <br />
              order_fee : {productData.ordr_fee}
              <br />
              trade_fee : {productData.trade_fee}
              <br />
              created_dt : {productData.created_dt}
              <br />
              created_by : {productData.created_by}
              <br />
              sale_cnt : {productData.sale_cnt}
            </Typography> */}
              <br />
              <br />
              <Grid container spacing={2}>
                <Grid item={true} xs={3}>
                  <Typography variant="body2">판매율 :</Typography>
                </Grid>
                <Grid item={true} xs={9}>
                  {/* <ProgressBar /> */}
                  <Box sx={{ width: "100%" }}>
                    <ProgressBar value={productData.sale_rate} />
                  </Box>
                </Grid>
              </Grid>
            </CardContent>
            <Divider />
            <CardActions>
              <Link
                to={`/order/${productData.goods_id}`}
                style={{ textDecoration: "none" }}
              >
                <Button variant="contained" size="large">
                  구매하기
                </Button>
              </Link>
              {/* <Button size="large" onClick={navigateToOrder}>
              구매하기
            </Button> */}
            </CardActions>
          </Card>
        ))
      ) : (
        <div> No product found </div>
      )}
      {/* ) : (
        <div> No posts.</div>
      )} */}
      <Paging
        page={currentPage}
        postPerPage={postPerPage}
        count={count}
        setPage={handlePageChange}
      />
    </div>
  );
};

export default ListProductsSalePage;
