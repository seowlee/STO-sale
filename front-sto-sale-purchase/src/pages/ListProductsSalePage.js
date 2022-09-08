import * as React from "react";
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
import { useNavigate } from "react-router-dom";

const ListProductsSalePage = () => {
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
    </div>
  );
};

export default ListProductsSalePage;
