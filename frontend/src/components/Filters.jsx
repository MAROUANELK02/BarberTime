import axios from "axios";
import React, { useEffect, useState } from "react";
import { Dropdown } from "react-bootstrap";

const Filters = () => {
  const [locations, setLocations] = useState([]);
  const fetchLocations = async () => {
    try {
      const res = await axios.get(
        "http://localhost:5000/api/customer/barberShops?page=0&size=5"
      );
      console.log(res.data.content);
      setLocations(res.data.content);
    } catch (err) {
      console.log(err);
    }
  };

  useEffect(() => {
    fetchLocations();
  }, []);
  return (
    <div className="card h-25 p-5 m-5 bg-light">
      Filters
      <div className="d-flex justify-content-center">
        <Dropdown className="me-4">
          <Dropdown.Toggle variant="primary" id="dropdown-basic">
            Choisi service
          </Dropdown.Toggle>

          <Dropdown.Menu>
            <Dropdown.Item href="#/action-1">Action</Dropdown.Item>
            <Dropdown.Item href="#/action-2">Another action</Dropdown.Item>
            <Dropdown.Item href="#/action-3">Something else</Dropdown.Item>
          </Dropdown.Menu>
        </Dropdown>
        <Dropdown>
          <Dropdown.Toggle variant="primary" id="dropdown-basic">
            Select location
          </Dropdown.Toggle>

          <Dropdown.Menu>
            <Dropdown.Item href="#/action-1">Action</Dropdown.Item>
            <Dropdown.Item href="#/action-2">Another action</Dropdown.Item>
            <Dropdown.Item href="#/action-3">Something else</Dropdown.Item>
          </Dropdown.Menu>
        </Dropdown>
      </div>
    </div>
  );
};

export default Filters;
