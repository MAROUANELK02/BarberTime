import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import Dropdown from "react-bootstrap/Dropdown";
import neighborhoods from "../../components/neighborhoods";
import { RxUpdate } from "react-icons/rx";
import axios from "axios";

const BarberShop = () => {
  const [name, setName] = useState("");
  const [phone, setPhone] = useState("");
  const [address, setAddress] = useState("");
  const [authorizationNumber, setAuthorizationNumber] = useState("");
  const [neighborhood, setNeighborhood] = useState("Select neighborhood");

  const handleUpdate = async () => {
    try {
      const response = await axios.patch(
        "http://localhost:5000/api/owner/barberShop/2",
        {
          name: name,
          phone: phone,
          address: address,
          authorizationNumber: authorizationNumber,
          neighborhood: neighborhood,
        }
      );
      console.log(response.data);
    } catch (error) {
      console.error("Error fetching holidays:", error);
    }
  };

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await axios.get(
          "http://localhost:5000/api/owner/barberShop/2"
        );
        const data = response.data;
        setName(data.name);
        setPhone(data.phone);
        setAddress(data.address);
        setAuthorizationNumber(data.authorizationNumber);
        setNeighborhood(data.neighborhood.replace(/_/g, " "));

        console.log(data);
      } catch (error) {
        console.error("Error fetching holidays:", error);
      }
    };

    fetchData();

    return () => {};
  }, []);
  return (
    <section className="card">
      <div className="card-body">
        <div className="card-title">
          <h4>BarberShop Informations</h4>
        </div>
        <div className="row gy-4 align-items-center">
          <div className="col-12 col-md-8 col-xl-8 mx-auto">
            <div className="card border-0 rounded-4">
              <div className="card-body p-3 p-md-4 p-xl-5">
                <div className="row">
                  <div className="col-12"></div>
                </div>
                <form action="#!">
                  <div className="row gy-3 ">
                    <div className="col-12 d-flex">
                      <div className="w-100">
                        <div className="form-floating mb-3 me-3">
                          <input
                            type="text"
                            className="form-control"
                            placeholder="name@example.com"
                            value={name}
                            onChange={(e) => setName(e.target.value)}
                            required
                          />
                          <label htmlFor="email" className="form-label">
                            Name
                          </label>
                        </div>
                      </div>

                      <div className="w-100">
                        <div className="form-floating mb-3">
                          <input
                            type="text"
                            className="form-control"
                            placeholder="name@example.com"
                            value={phone}
                            onChange={(e) => setPhone(e.target.value)}
                            required
                          />
                          <label htmlFor="cin" className="form-label">
                            Phone Number
                          </label>
                        </div>
                      </div>
                    </div>
                    <div className="col-12">
                      <div className="form-floating mb-3">
                        <input
                          type="text"
                          className="form-control"
                          placeholder="name@example.com"
                          value={address}
                          onChange={(e) => setAddress(e.target.value)}
                          required
                        />
                        <label htmlFor="email" className="form-label">
                          Address
                        </label>
                      </div>
                    </div>
                    <div className="col-12">
                      <div className="form-floating mb-3">
                        <input
                          type="text"
                          className="form-control"
                          placeholder="Password"
                          value={authorizationNumber}
                          onChange={(e) =>
                            setAuthorizationNumber(e.target.value)
                          }
                          required
                        />
                        <label htmlFor="password" className="form-label">
                          Authorization Number
                        </label>
                      </div>
                    </div>
                    <div className="col-12">
                      <Dropdown>
                        <Dropdown.Toggle
                          variant="secondary"
                          id="dropdown-basic"
                          style={{ width: "100%" }}
                        >
                          {neighborhood}
                        </Dropdown.Toggle>

                        <Dropdown.Menu
                          style={{
                            width: "100%",
                            overflow: "scroll",
                            height: "200px",
                          }}
                        >
                          {neighborhoods.map((n) => (
                            <Dropdown.Item
                              href="#/action-1"
                              onClick={() => setNeighborhood(n.name)}
                            >
                              {n.name}
                            </Dropdown.Item>
                          ))}
                        </Dropdown.Menu>
                      </Dropdown>
                    </div>
                    <div className="col-12"></div>
                    <div className="row justify-content-between">
                      <div className="p-0 ms-2">
                        <div className="">
                          <button
                            className="btn btn-primary btn-lg"
                            type="submit"
                            style={{ width: "100%" }}
                            onClick={() => handleUpdate()}
                          >
                            Update <RxUpdate className="ms-1" />
                          </button>
                        </div>
                      </div>
                    </div>
                  </div>
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
  );
};

export default BarberShop;
