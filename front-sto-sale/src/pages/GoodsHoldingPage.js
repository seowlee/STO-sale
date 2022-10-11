import React, { useState, useEffect } from "react";
import {
  Button,
  Accordion,
  AccordionSummary,
  AccordionDetails,
  AccordionActions,
  Divider,
  Stack,
  Typography,
} from "@mui/material";
import ExpandMoreIcon from "@mui/icons-material/ExpandMore";
import axios from "axios";

const GoodsHoldingPage = () => {
  const [holdings, setHoldings] = useState([]);
  const [expandedAccordion, setExpandedAccordion] = useState(false);
  const [goodsId, setGoodsId] = useState(0);

  useEffect(() => {
    axios
      .get("/holding/goods")
      .then((res) => {
        setHoldings(res.data);
        // console.log("test", res);
      })
      .catch((error) => {
        console.log("error", error);
      });
  }, []);

  const handleChangeAccordion = (panel) => (event, isExpanded) => {
    setExpandedAccordion(isExpanded ? panel : false);
  };

  const handleClickCancelSale = async (goodsId) => {
    setGoodsId(goodsId);
    alert(`cancel ${goodsId}`);
    try {
      const [res1, res2, res3, res4] = await Promise.all([
        axios.post(`/sale/delete`, {
          goodsId: goodsId,
        }),
        axios.post(`/transaction/cancel`, {
          goodsId: goodsId,
        }),
        axios.post(`/user/delete`, {
          goodsId: goodsId,
        }),
        axios.post(`/product/stat/reset`, {
          goodsId: goodsId,
        }),
      ]);
      console.log("res1", res1, "res2", res2, "res3", res3, "res4", res4);
      const res5 = await axios.delete(`/holding/delete`, {
        data: { goodsId: goodsId },
      });
      console.log("res5", res5);
    } catch (error) {
      console.log("error", error);
    }
  };

  // const handleClickCancelSaleHolding = (goodsId) => {
  //   axios
  //     .delete(`/holding/delete`, {
  //       data: { goodsId: goodsId },
  //     })
  //     .then((res) => {
  //       console.log("delete holding", res);
  //     })
  //     .catch((error) => {
  //       console.log("error", error);
  //     });
  // };

  return (
    <div>
      {holdings.map((goods, idx) => (
        <Accordion
          key={goods.goodsId}
          expanded={expandedAccordion === `panel_${idx}`}
          onChange={handleChangeAccordion(`panel_${idx}`)}
        >
          <AccordionSummary
            expandIcon={<ExpandMoreIcon />}
            aria-controls="panel1a-content"
            id="panel1a-header"
          >
            <Stack spacing={2}>
              <Typography variant="h5" component="div">
                상품 아이디 : {goods.goodsId}, 상품명 : {goods.goodsNm}
              </Typography>
            </Stack>
          </AccordionSummary>
          <AccordionDetails>
            <Typography variant="h6">
              판매상품 개수 : {goods.sumGoodsCnt} <br />
              보유자들 : {goods.userIds}
            </Typography>
            <br />
          </AccordionDetails>

          <Divider />
          <AccordionActions>
            <Button
              variant="contained"
              size="large"
              onClick={() => {
                handleClickCancelSale(goods.goodsId);
                // handleClickCancelSaleHolding(goods.goodsId);
              }}
            >
              판매취소
            </Button>
          </AccordionActions>
        </Accordion>
      ))}
    </div>
  );
};

export default GoodsHoldingPage;
