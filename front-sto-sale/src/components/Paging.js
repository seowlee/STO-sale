import React from "react";
import Pagination from "react-js-pagination";
import "./Paging.css";

const Paging = ({ page, postPerPage, count, setPage }) => {
  return (
    <div>
      <Pagination
        activePage={page}
        itemsCountPerPage={postPerPage}
        totalItemsCount={count}
        pageRangeDisplayed={5}
        prevPageText={"<"}
        nextPageText={">"}
        onChange={setPage}
      />
    </div>
  );
};

export default Paging;
