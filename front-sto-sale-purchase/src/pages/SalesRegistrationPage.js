import React, { useState, useEffect } from "react";
import {
  Button,
  Accordion,
  AccordionSummary,
  AccordionDetails,
  AccordionActions,
  TextField,
  Divider,
  InputAdornment,
} from "@mui/material";
import ExpandMoreIcon from "@mui/icons-material/ExpandMore";
import axios from "axios";

const SalesRegistrationPage = (props) => {
  const [isShownRegProducts, setIsShownRegProducts] = useState(false);

  const handleClickRegProducts = (event) => {
    setIsShownRegProducts((current) => !current);
  };

  const [product, setProduct] = useState([]);
  // console.log("mem", product);
  useEffect(() => {
    axios.get("/product/select").then((res) => {
      setProduct(res.data);
      console.log("test", res);
    });
  }, []);

  const [expandedAccordion, setExpandedAccordion] = React.useState(false);

  const handleChangeAccordion = (panel) => (event, isExpanded) => {
    setExpandedAccordion(isExpanded ? panel : false);
  };

  const [numberOfToken, setNumberOfToken] = useState();
  const handleClickRegister = (event, id) => {
    alert("save");
    axios
      .get("/product/insert", {
        params: { numberOfToken: numberOfToken },
      })
      .catch(function () {
        console.log("실패");
      });
    // setProduct(product.filter((product) => product.id !== id));
    console.log("after", product);
  };

  return (
    <div>
      <br />
      <Button size="large" variant="contained" onClick={handleClickRegProducts}>
        등록된 상품 보기
      </Button>
      {isShownRegProducts && (
        <div>
          {product.map((v, idx) => (
            <Accordion
              key={v.id}
              expanded={expandedAccordion === `panel_${idx}`}
              onChange={handleChangeAccordion(`panel_${idx}`)}
            >
              <AccordionSummary
                expandIcon={<ExpandMoreIcon />}
                aria-controls="panel1a-content"
                id="panel1a-header"
              >
                <div>id:{v.id}</div>
              </AccordionSummary>
              <AccordionDetails>
                <div>
                  name : {v.name}, password : {v.password}
                </div>
                <br />
              </AccordionDetails>
              <TextField
                id="outlined-number"
                label="Number"
                type="number"
                InputProps={{
                  endAdornment: (
                    <InputAdornment position="end">조각</InputAdornment>
                  ),
                }}
                onChange={(e) => {
                  setNumberOfToken(e.target.value);
                }}
              />
              <Divider />
              <AccordionActions>
                <Button size="small" onClick={() => alert("cancel")}>
                  취소
                </Button>
                <Button
                  size="small"
                  color="primary"
                  onClick={(event) => handleClickRegister(event, idx)}
                >
                  판매등록
                </Button>
              </AccordionActions>
            </Accordion>
          ))}
        </div>
      )}
      {isShownRegProducts && <div>hi</div>}
    </div>
  );
};
export default SalesRegistrationPage;
