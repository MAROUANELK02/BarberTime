import React, { useState } from "react";
import { Link } from "react-router-dom";
import Dropdown from "react-bootstrap/Dropdown";
import neighborhoods from "../components/neighborhoods";

const CreateAccountBarber2 = () => {
  const [neghborhood, setNeghborhood] = useState("Select neighborhood");
  return (
    <section className="bg-primary py-3 py-md-5 py-xl-8">
      <div className="container">
        <div className="row gy-4 align-items-center">
          <div className="col-12 col-md-8 col-xl-8 mx-auto">
            <div className="card border-0 rounded-4">
              <div className="card-body p-3 p-md-4 p-xl-5">
                <div className="row">
                  <div className="col-12">
                    <div className="mb-4">
                      <h3>BarberShop Informations</h3>
                    </div>
                  </div>
                </div>
                <form action="#!">
                  <div className="row gy-3 ">
                    <div className="col-12 d-flex">
                      <div className="w-100">
                        <div className="form-floating mb-3 me-3">
                          <input
                            type="text"
                            className="form-control"
                            name="email"
                            id="email"
                            placeholder="name@example.com"
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
                            name="cin"
                            id="cin"
                            placeholder="name@example.com"
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
                          name="email"
                          id="email"
                          placeholder="name@example.com"
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
                          name="password"
                          id="password"
                          placeholder="Password"
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
                          {neghborhood}
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
                              onClick={() => setNeghborhood(n.name)}
                            >
                              {n.name}
                            </Dropdown.Item>
                          ))}
                        </Dropdown.Menu>
                      </Dropdown>
                    </div>
                    <div className="col-12"></div>
                    <div className="col-12">
                      <div className="d-grid">
                        <Link to={"/create-account-barber3"}>
                          <button
                            className="btn btn-primary btn-lg"
                            type="submit"
                            style={{ width: "100%" }}
                          >
                            Next
                          </button>
                        </Link>
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

export default CreateAccountBarber2;
