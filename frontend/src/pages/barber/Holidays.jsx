import axios from "axios";
import React, { useEffect, useState } from "react";
import { IoMdAddCircleOutline } from "react-icons/io";
import Button from "react-bootstrap/Button";
import Modal from "react-bootstrap/Modal";
import { FaSave, FaTrashAlt } from "react-icons/fa";
import Pagination from "react-bootstrap/Pagination";

const Holidays = () => {
  const [holidays, setHolidays] = useState([]);
  const [reason, setReason] = useState("");
  const [startingDate, setStartingDate] = useState("");
  const [endingDate, setEndingDate] = useState("");
  const [page, setPage] = useState(1);
  const [totalPages, setTotalPages] = useState(0);
  const url =
    "http://localhost:5000/api/owner/holiday/" +
    localStorage.getItem("id") +
    "?page=" +
    (page - 1) +
    "&size=5";
  const [show, setShow] = useState(false);
  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);

  const handleSave = async () => {
    try {
      const response = await axios.post(
        "http://localhost:5000/api/owner/holiday/2",
        {
          reason: reason,
          startDate: startingDate,
          endDate: endingDate,
        },
        {
          headers: {
            Authorization: `Bearer ${localStorage.getItem("token")}`,
          },
        }
      );
      fetchData();
      handleClose();
    } catch (error) {
      console.error("Error fetching holidays:", error);
    }
  };

  const handleDelete = async (id) => {
    if (!window.confirm("Are you sure you want to delete this holiday?"))
      return;
    try {
      const response = await axios.delete(
        "http://localhost:5000/api/owner/holiday/" +
          id +
          "/barberShop/" +
          localStorage.getItem("id"),
        {
          headers: {
            Authorization: `Bearer ${localStorage.getItem("token")}`,
          },
        }
      );
      //console.log(response.data);
      fetchData();
    } catch (error) {
      console.error("Error fetching holidays:", error);
    }
  };

  const fetchData = async () => {
    try {
      const response = await axios.get(url, {
        headers: {
          Authorization: `Bearer ${localStorage.getItem("token")}`,
        },
      });
      const data = response.data.content;
      setHolidays(data);
      setTotalPages(response.data.totalPages);
      console.log(totalPages);
    } catch (error) {
      console.error("Error fetching holidays:", error);
    }
  };

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await axios.get(url, {
          headers: {
            Authorization: `Bearer ${localStorage.getItem("token")}`,
          },
        });
        const data = response.data.content;
        setHolidays(data);
        setTotalPages(response.data.totalPages);
        console.log(totalPages);
      } catch (error) {
        console.error("Error fetching holidays:", error);
      }
    };

    fetchData();

    return () => {};
  }, [page]);

  let items = [];
  for (let number = 1; number <= totalPages; number++) {
    items.push(
      <Pagination.Item
        key={number}
        active={number === page}
        onClick={() => {
          setPage(number);
        }}
      >
        {number}
      </Pagination.Item>
    );
  }

  return (
    <div className="card" style={{ minHeight: "94%" }}>
      <div className="card-body position-relative">
        <h4 className="card-title">Holidays</h4>
        <p className="card-text">This is the holidays page</p>
        <div className="text-center">
          <Button variant="primary" onClick={handleShow} className="mb-4">
            <IoMdAddCircleOutline size={30} />
          </Button>
        </div>
        <div className="row mb-2">
          <div className="col-8 mx-auto card border-primary ">
            <table className="table">
              <tbody>
                {holidays.map((holiday, index) => (
                  <tr key={index} className="mb-2">
                    <td>{holiday.reason}</td>
                    <td>{holiday.holidayDate}</td>
                    <td>
                      <button
                        className="btn btn-outline-danger"
                        onClick={() => handleDelete(holiday.idHoliday)}
                      >
                        <FaTrashAlt />
                      </button>
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        </div>

        <div className="d-flex justify-content-center mt-5 position-absolute bottom-0 start-50 translate-middle-x">
          <Pagination>{items}</Pagination>
          <br />
        </div>
      </div>

      <Modal show={show} onHide={handleClose}>
        <Modal.Header closeButton>
          <Modal.Title>Add a holiday</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <div className="form-group p-3">
            <div className="row">
              <div className="col-10 mx-auto">
                <label htmlFor="holiday">Reason</label>
                <input
                  type="text"
                  className="form-control"
                  value={reason}
                  onChange={(e) => setReason(e.target.value)}
                  placeholder="Enter holiday"
                />
              </div>
            </div>
            <div className="row">
              <div className="col-10 mx-auto ">
                <label htmlFor="date">Starting Date</label>
                <input
                  type="date"
                  className="form-control"
                  placeholder="Enter date"
                  value={startingDate}
                  onChange={(e) => setStartingDate(e.target.value)}
                />
              </div>
            </div>
            <div className="row">
              <div className="col-10 mx-auto">
                <label htmlFor="date">Ending Date</label>
                <input
                  type="date"
                  className="form-control"
                  placeholder="Enter date"
                  value={endingDate}
                  onChange={(e) => setEndingDate(e.target.value)}
                />
              </div>
            </div>
          </div>
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={handleClose}>
            Close
          </Button>
          <Button variant="primary" onClick={handleSave}>
            Save
            <FaSave className="ms-1" />
          </Button>
        </Modal.Footer>
      </Modal>
    </div>
  );
};

export default Holidays;
