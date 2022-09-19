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
  Stack,
} from "@mui/material";
import ExpandMoreIcon from "@mui/icons-material/ExpandMore";
import axios from "axios";
import NumberFormat from "react-number-format";

function NumberFormatCustom(props) {
  const { inputRef, onChange, ...other } = props;

  return (
    <NumberFormat
      {...other}
      getInputRef={inputRef}
      onValueChange={(values) => {
        onChange({
          target: {
            name: props.name,
            value: values.value,
          },
        });
      }}
      thousandSeparator
      // isNumericString
    />
  );
}

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

  const [expandedAccordion, setExpandedAccordion] = useState(false);

  const handleChangeAccordion = (panel) => (event, isExpanded) => {
    setExpandedAccordion(isExpanded ? panel : false);
  };

  const [unitPrice, setUnitPrice] = useState(0);

  const [numberOfToken, setNumberOfToken] = useState(0);

  const dateNow = new Date();
  const today = dateNow.toISOString().slice(0, 10);
  const [saleStartDate, setSaleStartDate] = useState(today);
  const handleClickRegister = (event, id) => {
    alert(
      `분할 조각 개수 : ${numberOfToken}\n판매시작일 : ${saleStartDate}\nsave`
    );
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
  const handleClickNotRegister = () => {
    setNumberOfToken("");
    alert(`cancel`);
  };

  // const passSaleStartDate = (saleDate) => {
  //   console.log("saleDate", saleDate);
  // };

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
                <Stack spacing={2}>
                  <div>id : {v.id}</div>
                </Stack>
              </AccordionSummary>
              <AccordionDetails>
                <div>
                  name : {v.name}, password : {v.password}
                </div>
                <br />
              </AccordionDetails>
              <TextField
                id="outlined-number"
                label="한 조각당 가격"
                type="number"
                InputProps={{
                  inputComponent: NumberFormatCustom,
                  endAdornment: (
                    <InputAdornment position="end">원</InputAdornment>
                  ),
                }}
                onChange={(e) => {
                  setUnitPrice(e.target.value);
                }}
                InputLabelProps={{
                  shrink: true,
                }}
              />
              <TextField
                id="outlined-number"
                label="조각 수"
                type="number"
                InputProps={{
                  endAdornment: (
                    <InputAdornment position="end">조각</InputAdornment>
                  ),
                }}
                onChange={(e) => {
                  setNumberOfToken(e.target.value);
                }}
                InputLabelProps={{
                  shrink: true,
                }}
              />
              <TextField
                id="date"
                label="판매 시작일"
                type="date"
                defaultValue={today}
                onChange={(e) => setSaleStartDate(e.target.value)}
                inputProps={{ min: today }}
                InputLabelProps={{
                  shrink: true,
                }}
              />
              <Divider />
              <AccordionActions>
                <Button size="small" onClick={handleClickNotRegister}>
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
