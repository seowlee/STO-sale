import React from "react";
import { Link } from "react-router-dom";
import { BottomNavigation, BottomNavigationAction, Paper } from "@mui/material";
import PublishIcon from "@mui/icons-material/Publish";
import StoreIcon from "@mui/icons-material/Store";
import PersonIcon from "@mui/icons-material/Person";

const Navigation = () => {
  const [value, setValue] = React.useState(0);

  return (
    <Paper
      sx={{ position: "fixed", bottom: 0, left: 0, right: 0 }}
      elevation={3}
    >
      <BottomNavigation
        showLabels
        value={value}
        onChange={(event, newValue) => {
          setValue(newValue);
        }}
      >
        {/* <BottomNavigationAction
          component={Link}
          to="/salesRegistration"
          label="판매하기"
          icon={<PublishIcon />}
        /> */}
        <BottomNavigationAction
          component={Link}
          to="/listOnSale"
          label="판매상품목록"
          icon={<StoreIcon />}
        />
        <BottomNavigationAction
          component={Link}
          to="/myPage"
          label="마이페이지"
          icon={<PersonIcon />}
        />
      </BottomNavigation>
    </Paper>
  );
};

export default Navigation;
