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

  const [name, setName] = useState("");
  const [phone, setPhone] = useState("");
  const [address, setAddress] = useState("");
  const [dayOff, setDayOff] = useState("");
  const [startTime, setStartTime] = useState("");
  const [endTime, setEndTime] = useState("");
  const [authorizationNumber, setAuthorizationNumber] = useState("");
  const [neighborhood, setNeighborhood] = useState("Select neighborhood");
  const [barberShopId, setBarberShopId] = useState(0);
  const handleUpdate = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.patch(
        "http://localhost:5000/api/owner/barberShop/" + barberShopId,
        {
          name: name,
          phone: phone,
          address: address,
          authorizationNumber: authorizationNumber,
          neighborhood: neighborhood.replace(/\s/g, "_"),
          dayOff: dayOff,
          startTime: startTime,
          endTime: endTime,
        },
        {
          headers: {
            Authorization: `Bearer ${localStorage.getItem("token")}`,
          },
        }
      );
      console.log(response.data);
      alert("Updated successfully");
    } catch (error) {
      console.error("Error fetching holidays:", error);
    }
  };

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await axios.get(
          "http://localhost:5000/api/owner/barberShop/" +
            localStorage.getItem("id"),
          {
            headers: {
              Authorization: `Bearer ${localStorage.getItem("token")}`,
            },
          }
        );
        const data = response.data;
        setBarberShopId(data.idBarberShop);
        setName(data.name);
        setPhone(data.phone);
        setAddress(data.address);
        setAuthorizationNumber(data.authorizationNumber);
        setDayOff(data.dayOff);
        setStartTime(data.startTime);
        setEndTime(data.endTime);
        setNeighborhood(data.neighborhood.replace(/_/g, " "));
        console.log(barberShopId);
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
                <form>
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
                              dayOff === d ? "primary" : "outline-primary"
                            }`}
                            style={{ cursor: "pointer" }}
                            onClick={() => setDayOff(d)}
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
                          <button
                            className="btn btn-primary btn-lg"
                            style={{ width: "100%" }}
                            onClick={(e) => handleUpdate(e)}
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

export default WorkTime;
