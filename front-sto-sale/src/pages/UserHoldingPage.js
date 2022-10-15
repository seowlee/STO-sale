import React, { useState, useEffect } from "react";
import {
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

const UserHoldingPage = () => {
  const [holdings, setHoldings] = useState([]);
  const [expandedAccordion, setExpandedAccordion] = useState(false);

  useEffect(() => {
    axios
      .get("/holding/user")
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

  return (
    <div>
      {holdings.length > 0 ? (
        holdings.map((user, idx) => (
          <Accordion
            key={user.userId}
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
                  유저 아이디 : {user.userId}, 유저이름 : {user.userNm}
                </Typography>
              </Stack>
            </AccordionSummary>
            <AccordionDetails>
              <Typography variant="h6">
                상품목록 : {user.goodsIds} <br />
                상품별 개수 : {user.goodsCnts}
              </Typography>
              <br />
            </AccordionDetails>

            <Divider />
            <AccordionActions></AccordionActions>
          </Accordion>
        ))
      ) : (
        <div> No product found </div>
      )}
    </div>
  );
};

export default UserHoldingPage;
