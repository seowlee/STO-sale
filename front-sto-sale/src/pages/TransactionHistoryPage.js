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
import Paging from "../components/Paging";
import { useSearchParams } from "react-router-dom";

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
  const [count, setCount] = useState(5);
  const [currentPage, setCurrentPage] = useState(1);
  const [postPerPage] = useState(10);
  const [searchParams, setSearchParams] = useSearchParams();

  useEffect(() => {
    axios
      .all([
        axios.get(`/transaction/all`, {
          params: {
            page: currentPage - 1,
            size: postPerPage,
          },
        }),
        axios.get(`/transaction/all/count`),
      ])
      .then(
        axios.spread((res1, res2) => {
          setTransactions(res1.data);
          setCount(res2.data);
          console.log("res1", res1, "res2", res2);
        })
      )
      .catch((error) => {
        console.log("error", error);
      });
  }, [currentPage, postPerPage]);

  const handlePageChange = (currentPage) => {
    setCurrentPage(currentPage);
    console.log(currentPage);
    axios
      .get(`/transaction/all`, {
        params: {
          page: currentPage - 1,
          size: postPerPage,
        },
      })
      .then((res) => {
        setTransactions(res.data);
        setSearchParams({ page: currentPage });
        console.log("current page : ", searchParams.get("page"));
      })
      .catch((error) => {
        console.log("error", error);
      });
  };

  return (
    <div>
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
              {transactions.length <= 0 && currentPage === 1 ? (
                <StyledTableRow key={0}>
                  <StyledTableCell component="th">
                    No Transaction History
                  </StyledTableCell>
                </StyledTableRow>
              ) : (
                transactions.map((trnsc) => (
                  <StyledTableRow key={trnsc.transactionId}>
                    <StyledTableCell component="th" scope="trnsc">
                      {trnsc.transactionId}
                    </StyledTableCell>
                    <StyledTableCell align="right">
                      {trnsc.goodsId}
                    </StyledTableCell>
                    <StyledTableCell align="right">
                      {trnsc.userId}
                    </StyledTableCell>
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
                      <StyledTableCell align="right">
                        구매후 취소됨
                      </StyledTableCell>
                    )}
                    <StyledTableCell align="right">
                      {trnsc.transactionDt}
                    </StyledTableCell>
                  </StyledTableRow>
                ))
              )}
            </TableBody>
          </Table>
        </TableContainer>
      </Box>
      <Paging
        page={currentPage}
        postPerPage={postPerPage}
        count={count}
        setPage={handlePageChange}
      />
    </div>
  );
};

export default TransactionHistoryPage;
