import React, { useState } from "react";
import { Link } from "react-router-dom";
import services from "../../components/services";

const Services = () => {
  const [selectedServices, setSelectedServices] = useState([]);
  const handleSelectService = (service) => {
    if (selectedServices.includes(service)) {
      setSelectedServices(selectedServices.filter((s) => s !== service));
    } else {
      setSelectedServices([...selectedServices, service]);
    }
  };
  return (
    <section class="card">
      <div class="card-body">
        <div className="card-title">
          <h4>Services</h4>
        </div>
        <div class="row gy-4 align-items-center">
          <div class="col-12 col-md-8 col-xl-8 mx-auto">
            <div class="card border-0 rounded-4">
              <div class="card-body p-3 p-md-4 p-xl-5">
                <form action="#!">
                  <div class="row gy-3 overflow-hidden">
                    <div class="col-12 d-flex"></div>
                    <div class="col-12">
                      <table className="table">
                        <thead>
                          <tr class="">
                            <th>#</th>
                            <th>Service</th>
                            <th>Price</th>
                          </tr>
                        </thead>
                        <tbody>
                          {services.map((s) => (
                            <tr
                              class={` ${
                                selectedServices.includes(s)
                                  ? "table-primary"
                                  : ""
                              }`}
                            >
                              <td>
                                <input
                                  type="checkbox"
                                  onClick={() => handleSelectService(s)}
                                />
                              </td>
                              <td>{s}</td>
                              <td>
                                <input
                                  type="text"
                                  disabled={!selectedServices.includes(s)}
                                  style={{
                                    border: "none",
                                    outline: "none",
                                    background: `${
                                      selectedServices.includes(s)
                                        ? "primary"
                                        : "transparent"
                                    }`,
                                  }}
                                />
                              </td>
                            </tr>
                          ))}
                        </tbody>
                      </table>
                    </div>

                    <div class="col-12"></div>
                    <div className="row justify-content-between">
                      <div className="col-sm">
                        <div className="">
                          <Link to={"/create-account-barber3"}>
                            <button
                              className="btn btn-secondary btn-lg"
                              type="submit"
                              style={{ width: "100%" }}
                            >
                              Back
                            </button>
                          </Link>
                        </div>
                      </div>
                      <div className="col-10 p-0">
                        <div className="">
                          <Link to={"/create-account-barber5"}>
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

export default Services;
