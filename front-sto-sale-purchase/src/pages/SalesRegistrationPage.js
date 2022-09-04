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

const SalesRegistrationPage = (props) => {
  const [isShown, setIsShown] = useState(false);

  const handleClick = (event) => {
    setIsShown((current) => !current);
  };
  const [member, setMember] = useState([]);

  useEffect(() => {
    fetch("/member/select")
      .then((res) => {
        return res.json();
      })
      .then((data) => {
        setMember(data);
      });
  }, []);
  const [expanded, setExpanded] = React.useState(false);

  const handleChange = (panel) => (event, isExpanded) => {
    setExpanded(isExpanded ? panel : false);
  };

  return (
    <div>
      <br />
      <Button size="large" variant="contained" onClick={handleClick}>
        등록된 상품 보기
      </Button>
      {isShown && (
        <div>
          {member.map((v, idx) => (
            <Accordion
              expanded={expanded === `panel_${idx}`}
              onChange={handleChange(`panel_${idx}`)}
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
              />
              <Divider />
              <AccordionActions>
                <Button size="small" onClick={() => alert("cancel")}>
                  취소
                </Button>
                <Button
                  size="small"
                  color="primary"
                  onClick={() => alert("save")}
                >
                  판매등록
                </Button>
              </AccordionActions>
            </Accordion>
          ))}
        </div>
      )}
      {isShown && <div>hi</div>}
    </div>
  );
};
export default SalesRegistrationPage;
