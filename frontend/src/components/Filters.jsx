import React from "react";
import { Dropdown } from "react-bootstrap";

const Filters = () => {
  return (
    <div className="card h-25 p-5 m-5" style={{ background: "blue" }}>
      Filters
      <div className="d-flex justify-content-center">
        <Dropdown className="me-4">
          <Dropdown.Toggle variant="success" id="dropdown-basic">
            Choisi service
          </Dropdown.Toggle>

          <Dropdown.Menu>
            <Dropdown.Item href="#/action-1">Action</Dropdown.Item>
            <Dropdown.Item href="#/action-2">Another action</Dropdown.Item>
            <Dropdown.Item href="#/action-3">Something else</Dropdown.Item>
          </Dropdown.Menu>
        </Dropdown>
        <Dropdown>
          <Dropdown.Toggle variant="success" id="dropdown-basic">
            Chosi secteur
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
