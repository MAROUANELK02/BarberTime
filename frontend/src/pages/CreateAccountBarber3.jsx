import React, { useState } from "react";
import { Link } from "react-router-dom";

const CreateAccountBarber3 = () => {
  const days = [
    "Monday",
    "Tuesday",
    "Wednesday",
    "Thursday",
    "Friday",
    "Saturday",
    "Sunday",
  ];

  const [selectedDays, setSelectedDays] = useState([]);

  const handleSelectDate = (day) => {
    if (selectedDays.includes(day)) {
      setSelectedDays(selectedDays.filter((d) => d !== day));
    } else {
      setSelectedDays([...selectedDays, day]);
    }
  };
  return (
    <section class="bg-primary py-3 py-md-5 py-xl-8 m">
      <div class="container" style={{ minHeight: "80vh" }}>
        <div class="row gy-4 align-items-center">
          <div class="col-12 col-md-8 col-xl-8 mx-auto">
            <div class="card border-0 rounded-4">
              <div class="card-body p-3 p-md-4 p-xl-5">
                <div class="row">
                  <div class="col-12">
                    <div class="mb-4">
                      <h3>Create Account</h3>
                    </div>
                  </div>
                </div>
                <form action="#!">
                  <div class="row gy-3 overflow-hidden">
                    <div class="col-12 d-flex mb-3">
                      <div class="col-6">Starting Time</div>
                      <div class="col-6">
                        <div class="cs-form">
                          <input
                            type="time"
                            class="form-control"
                            //value="10:05 AM"
                          />
                        </div>
                      </div>
                    </div>
                    <div class="col-12 d-flex mb-3">
                      <div class="col-6">Ending Time</div>
                      <div class="col-6">
                        <div class="cs-form">
                          <input
                            type="time"
                            class="form-control"
                            //value="10:05 AM"
                          />
                        </div>
                      </div>
                    </div>

                    <div class="col-12">
                      <h5 className="mb-4">Select Days off</h5>
                      <h5 className="d-flex justify-content-between">
                        {days.map((d) => (
                          <span
                            class={`badge text-bg-${
                              selectedDays.includes(d) ? "primary" : "secondary"
                            }`}
                            style={{ cursor: "pointer" }}
                            onClick={() => handleSelectDate(d)}
                          >
                            {d}
                          </span>
                        ))}
                      </h5>
                    </div>
                    <div class="col-12"></div>
                    <div class="col-12">
                      <div class="d-grid">
                        <Link to={"/create-account-barber2"}>
                          <button
                            class="btn btn-primary btn-lg"
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

export default CreateAccountBarber3;
