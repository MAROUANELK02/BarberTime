import axios from "axios";
import React, { useEffect, useState } from "react";
import { Dropdown, Pagination } from "react-bootstrap";

const Appointments = () => {
  const [appointments, setAppointments] = useState([]);
  const [page, setPage] = useState(1);
  const [totalPages, setTotalPages] = useState(0);
  const [status, setStatus] = useState("Select status");
  const statusList = ["CONFIRMED", "CANCELED", "COMPLETED"];
  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await axios.get(
          "http://localhost:5000/api/owner/barberShop/" +
            localStorage.getItem("id") +
            "/appointments?page=0&size=3",
          {
            headers: {
              Authorization: `Bearer ${localStorage.getItem("token")}`,
            },
          }
        );
        const data = response.data.content;
        console.log(data);
        setTotalPages(response.data.totalPages);
        setAppointments(data);
      } catch (error) {
        console.error("Error fetching appointments:", error);
      }
    };

    fetchData();

    return () => {};
  }, []);

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

  const handleConfirmation = async (id, status) => {
    try {
      console.log(status);
      await axios.patch(
        "http://localhost:5000/api/owner/appointment/" +
          id +
          "/status?status=" +
          status,
        {},
        {
          headers: {
            Authorization: `Bearer ${localStorage.getItem("token")}`,
          },
        }
      );

      fetchData();
    } catch (error) {
      console.error("Error fetching appointments:", error);
    }
  };

  const fetchData = async () => {
    try {
      const response = await axios.get(
        "http://localhost:5000/api/owner/barberShop/" +
          localStorage.getItem("id") +
          "/appointments?page=" +
          (page - 1) +
          "&size=3",
        {
          headers: {
            Authorization: `Bearer ${localStorage.getItem("token")}`,
          },
        }
      );
      const data = response.data.content;
      setAppointments(data);
      console.log(data);
    } catch (error) {
      console.error("Error fetching appointments:", error);
    }
  };

  const getAppointmentsByDate = async (date) => {
    console.log(date);
    try {
      const response = await axios.get(
        "http://localhost:5000/api/owner/barberShop/" +
          localStorage.getItem("id") +
          "/appointmentsPerDate?page=" +
          (page - 1) +
          "&size=3&date=" +
          date,
        {
          headers: {
            Authorization: `Bearer ${localStorage.getItem("token")}`,
          },
        }
      );
      const data = response.data.content;
      setAppointments(data);
      setTotalPages(response.data.totalPages);
      setAppointments(data);
    } catch (error) {}
  };

  return (
    <div className="card" style={{ minHeight: "94%" }}>
      <div className="card-body position-relative">
        <h4 className="card-title">Appointments</h4>
        <div className="row mb-4">
          <div className="col-5 mx-auto">
            <input
              type="date"
              className="form-control"
              onChange={(e) => getAppointmentsByDate(e.target.value)}
            />
          </div>
        </div>
        <div className="row">
          <div className="col-8 mx-auto">
            <div className="row">
              {appointments.map((a) => (
                <div className="col-6">
                  <div className="card mb-3" key={a.id}>
                    <div className="card-body">
                      <div className="d-flex justify-content-between">
                        <div className="d-flex">
                          <h5 className="card-title">{a.date}</h5>
                          <p className="card-text">{a.time}</p>
                        </div>
                        <div>
                          {"By" +
                            " " +
                            a.customerDTO.firstName +
                            " " +
                            a.customerDTO.lastName}
                        </div>
                      </div>

                      <p className="card-text">{a.service}</p>
                      <Dropdown>
                        <Dropdown.Toggle variant="primary" id="dropdown-basic">
                          {a.status}
                        </Dropdown.Toggle>

                        <Dropdown.Menu>
                          {statusList.map((s) => (
                            <Dropdown.Item
                              onClick={() =>
                                handleConfirmation(a.idAppointment, s)
                              }
                            >
                              {s}
                            </Dropdown.Item>
                          ))}
                        </Dropdown.Menu>
                      </Dropdown>
                    </div>
                  </div>
                </div>
              ))}
              <div className="col-6"></div>
            </div>
          </div>
        </div>
        <div className="d-flex justify-content-center mt-5 position-absolute bottom-0 start-50 translate-middle-x">
          <Pagination>{items}</Pagination>
          <br />
        </div>
      </div>
    </div>
  );
};

export default Appointments;
