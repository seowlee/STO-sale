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

  const handleClickNotRegister = () => {
    alert(`cancel`);
  };

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
              onClick={(event) => handleClickNotRegister(event, idx)}
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
