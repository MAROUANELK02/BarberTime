import axios from "axios";
import React, { useEffect, useState } from "react";
import { RxUpdate } from "react-icons/rx";
import { Link } from "react-router-dom";

const WorkTime = () => {
  const days = [
    "Monday",
    "Tuesday",
    "Wednesday",
    "Thursday",
    "Friday",
    "Saturday",
    "Sunday",
  ];

  const [startTime, setStartTime] = useState("");
  const [endTime, setEndTime] = useState("");

  const [selectedDays, setSelectedDays] = useState([]);
  const handleSelectDate = (day) => {
    console.log(startTime, endTime, selectedDays);
    if (selectedDays.includes(day)) {
      setSelectedDays(selectedDays.filter((d) => d !== day));
    } else {
      setSelectedDays([...selectedDays, day]);
    }
  };

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await axios.get(
          "http://localhost:5000/api/owner/barberShop/2"
        );
        const data = response.data;
        setSelectedDays(data.dayOff);
        setStartTime(data.startTime.slice(0, 5));
        setEndTime(data.endTime.slice(0, 5));
        console.log(startTime, endTime, selectedDays);

        console.log(data);
      } catch (error) {
        console.error("Error fetching holidays:", error);
      }
    };

    fetchData();

    return () => {};
  }, []);
  return (
    <section class="card">
      <div class="card-body" style={{ minHeight: "80vh" }}>
        <div class="card-title">
          <h4>Work Time</h4>
        </div>
        <div class="row  align-items-center">
          <div class="col-12  col-xl-10 mx-auto">
            <div class="card border-0 rounded-4">
              <div class="card-body p-3 p-md-4 p-xl-5">
                <form action="#!">
                  <div class="row gy-3 ">
                    <div class="col-12 d-flex mb-3">
                      <div class="col-6">Starting Time</div>
                      <div class="col-6">
                        <div class="cs-form">
                          <input
                            type="time"
                            class="form-control"
                            onChange={(e) => setStartTime(e.target.value)}
                            value={startTime}
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
                            value={endTime}
                            onChange={(e) => setEndTime(e.target.value)}
                            //value="10:05 AM"
                          />
                        </div>
                      </div>
                    </div>

                    <div class="col-12 mt-5">
                      <h5 className="mb-4">Select Days off</h5>
                      <h5 className="d-flex justify-content-between">
                        {days.map((d) => (
                          <span
                            class={`btn btn-${
                              selectedDays.includes(d)
                                ? "primary"
                                : "outline-primary"
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
                    <div className="row justify-content-between">
                      <div className=" ms-2">
                        <div className="">
                          <Link to={"/create-account-barber4"}>
                            <button
                              className="btn btn-primary btn-lg"
                              type="submit"
                              style={{ width: "100%" }}
                            >
                              Update <RxUpdate className="ms-1" />
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

export default WorkTime;
