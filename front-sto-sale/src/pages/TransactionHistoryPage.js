import React, { useState, useEffect } from "react";
import {
  styled,
  Table,
  TableBody,
  TableCell,
  tableCellClasses,
  TableContainer,
  TableHead,
  TableRow,
  Paper,
  Box,
} from "@mui/material";
import axios from "axios";

const StyledTableCell = styled(TableCell)(({ theme }) => ({
  [`&.${tableCellClasses.head}`]: {
    backgroundColor: theme.palette.common.black,
    color: theme.palette.common.white,
  },
  [`&.${tableCellClasses.body}`]: {
    fontSize: 14,
  },
}));

const StyledTableRow = styled(TableRow)(({ theme }) => ({
  "&:nth-of-type(odd)": {
    backgroundColor: theme.palette.action.hover,
  },
  // hide last border
  "&:last-child td, &:last-child th": {
    border: 0,
  },
}));

const TransactionHistoryPage = () => {
  const [transactions, setTransactions] = useState([]);

  useEffect(() => {
    axios
      .get("/transaction/all")
      .then((res) => {
        setTransactions(res.data);
        // console.log("test", res);
      })
      .catch((error) => {
        console.log("error", error);
      });
  }, []);

  return (
    <Box sx={{ ml: 2, mr: 2, mt: 2, md: 2 }}>
      <TableContainer component={Paper}>
        <Table sx={{ minWidth: 700 }} aria-label="customized table">
          <TableHead>
            <TableRow>
              <StyledTableCell>거래번호</StyledTableCell>
              <StyledTableCell align="right">상품번호</StyledTableCell>
              <StyledTableCell align="right">유저번호</StyledTableCell>
              <StyledTableCell align="right">거래상품 개수</StyledTableCell>
              <StyledTableCell align="right">거래상태</StyledTableCell>
              <StyledTableCell align="right">거래시각</StyledTableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {transactions.map((trnsc) => (
              <StyledTableRow key={trnsc.transactionId}>
                <StyledTableCell component="th" scope="trnsc">
                  {trnsc.transactionId}
                </StyledTableCell>
                <StyledTableCell align="right">{trnsc.goodsId}</StyledTableCell>
                <StyledTableCell align="right">{trnsc.userId}</StyledTableCell>
                <StyledTableCell align="right">
                  {trnsc.transactionCnt}
                </StyledTableCell>
                {trnsc.transactionStat === 0 && (
                  <StyledTableCell align="right">정상판매</StyledTableCell>
                )}
                {trnsc.transactionStat === 1 && (
                  <StyledTableCell align="right">판매취소</StyledTableCell>
                )}
                {trnsc.transactionStat === 2 && (
                  <StyledTableCell align="right">구매후 취소됨</StyledTableCell>
                )}
                <StyledTableCell align="right">
                  {trnsc.transactionDt}
                </StyledTableCell>
              </StyledTableRow>
            ))}
          </TableBody>
        </Table>
      </TableContainer>
    </Box>
  );
};

export default TransactionHistoryPage;
