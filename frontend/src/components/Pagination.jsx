import React, { useState } from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import { Pagination } from 'react-bootstrap';

function PaginationBar({ totalPages, onPageChange }) {
  const [currentPage, setCurrentPage] = useState(1);

  const handlePaginationClick = (pageNumber) => {
    setCurrentPage(pageNumber);
    onPageChange(pageNumber);
  };

  return (
    <div className="d-flex justify-content-center align-items-center mt-1">
      <Pagination>
        {Array.from({ length: totalPages }, (_, index) => (
          <Pagination.Item 
              key={index + 1} 
              onClick={() => handlePaginationClick(index + 1)}
              active={index + 1 === currentPage}
              >

            {index + 1}

          </Pagination.Item>
        ))}
      </Pagination>
    </div>
  );
}

export default PaginationBar;