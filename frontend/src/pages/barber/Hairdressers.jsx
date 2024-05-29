import React, { useEffect, useState } from "react";
import axios from "axios";
import { Button } from "react-bootstrap";
import { IoMdAddCircleOutline } from "react-icons/io";
import Modal from "react-bootstrap/Modal";
import { FaPen, FaSave, FaTrashAlt } from "react-icons/fa";

const Hairdressers = () => {
  const [hairdressers, setHairdressers] = React.useState([]);
  const [show, setShow] = useState(false);
  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);
  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const [id, setID] = useState("");
  const [isUpdate, setIsUpdate] = useState(false);
  useEffect(() => {
    fetchData();
  }, []);

  const handleSave = async () => {
    console.log(isUpdate);
    if (isUpdate) {
      try {
        const response = await axios.patch(
          "http://localhost:5000/api/owner/hairdresser/" + id,
          {
            firstName,
            lastName,
          },
          {
            headers: {
              Authorization: `Bearer ${localStorage.getItem("token")}`,
            },
          }
        );
        console.log(response);
        setShow(false);
        setFirstName("");
        setLastName("");
        fetchData();
      } catch (error) {
        console.log(error);
      }
    } else {
      try {
        const response = await axios.post(
          "http://localhost:5000/api/owner/hairdresser/barberShop/" +
            localStorage.getItem("id"),
          {
            firstName,
            lastName,
          },
          {
            headers: {
              Authorization: `Bearer ${localStorage.getItem("token")}`,
            },
          }
        );
        console.log(response);
        setShow(false);
        setFirstName("");
        setLastName("");
        fetchData();
      } catch (error) {
        console.log(error);
      }
    }
  };

  const handleDelete = async (id) => {
    console.log(id);
    if (!window.confirm("Are you sure you want to delete this hairdresser?"))
      return;
    try {
      const response = await axios.delete(
        "http://localhost:5000/api/owner/hairdresser/" +
          id +
          "/barberShop/" +
          localStorage.getItem("id"),
        {
          headers: {
            Authorization: `Bearer ${localStorage.getItem("token")}`,
          },
        }
      );
      console.log(response);
      fetchData();
    } catch (error) {
      console.log(error);
    }
    console.log("delete");
  };

  const handleModify = (h) => {
    setIsUpdate(true);
    setFirstName(h.firstName);
    setLastName(h.lastName);
    setID(h.idHairdresser);
    setShow(true);
  };

  const handleCreate = () => {
    setIsUpdate(false);
    setFirstName("");
    setLastName("");
    setShow(true);
  };

  const fetchData = async () => {
    try {
      const response = await axios.get(
        "http://localhost:5000/api/owner/hairdresser/" +
          localStorage.getItem("id"),
        {
          headers: {
            Authorization: `Bearer ${localStorage.getItem("token")}`,
          },
        }
      );
      console.log(response.data);
      setHairdressers(response.data.content);
    } catch (error) {
      console.error("Error fetching hairdressers:", error);
    }
  };
  return (
    <div className="card" style={{ minHeight: "94%" }}>
      <div className="card-body">
        <h4 className="card-title">Hairdressers</h4>

        <p className="card-text">This is the Hairdressers page</p>
        <div className="p-4">
          <div className="text-center">
            <Button variant="primary" onClick={handleCreate} className="mb-4">
              <IoMdAddCircleOutline size={30} />
            </Button>
          </div>
          {hairdressers?.map((h) => (
            <div className="card col-8 mx-auto p-2 m-2 justify-content-center">
              <div className="d-flex  justify-content-between">
                <div>
                  <div>{h.firstName + " " + h.lastName}</div>
                </div>
                <div>
                  <button
                    className="btn btn-outline-primary mx-2"
                    onClick={() => handleModify(h)}
                  >
                    <FaPen />
                  </button>
                  <button
                    className="btn btn-outline-danger"
                    onClick={() => handleDelete(h.idHairdresser)}
                  >
                    <FaTrashAlt />
                  </button>
                </div>
              </div>
            </div>
          ))}
        </div>
      </div>
      <Modal show={show} onHide={handleClose}>
        <Modal.Header closeButton>
          <Modal.Title>{isUpdate ? "Modify" : "Add"} a Hairdresser</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <div className="form-group p-3">
            <div className="row">
              <div className="col-10 mx-auto">
                <label htmlFor="holiday">Firstname</label>
                <input
                  type="text"
                  className="form-control"
                  placeholder="Enter firstname"
                  value={firstName}
                  onChange={(e) => setFirstName(e.target.value)}
                />
              </div>
            </div>
            <div className="row">
              <div className="col-10 mx-auto">
                <label htmlFor="holiday">Lastname</label>
                <input
                  type="text"
                  className="form-control"
                  placeholder="Enter lastname"
                  value={lastName}
                  onChange={(e) => setLastName(e.target.value)}
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

export default Hairdressers;
