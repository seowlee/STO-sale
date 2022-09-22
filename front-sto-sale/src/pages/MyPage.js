import React from "react";
import {
  Card,
  CardActions,
  CardContent,
  Divider,
  Button,
  Typography,
} from "@mui/material";
const MyPage = () => {
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
        </CardContent>
        {/* <Divider />
        <CardActions>
          <Button size="large">구매하기</Button>
        </CardActions> */}
      </Card>
    </div>
  );
};

export default MyPage;
