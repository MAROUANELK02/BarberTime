import axios from "axios";
import React, { useEffect, useState } from "react";
import { IoMdAddCircleOutline } from "react-icons/io";

const Holidays = () => {
  const [addHoliday, setAddHoliday] = useState(false);
  const [holidays, setHolidays] = useState([]);
  const url = "http://localhost:5000/api/owner/holiday/1";
  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await axios.get(url);
        const data = response.data.content;
        setHolidays(data);
        console.log(data);
      } catch (error) {
        console.error("Error fetching holidays:", error);
      }
    };

    fetchData();

    // Clean-up function if needed
    return () => {
      // Perform clean-up tasks here if necessary
    };
  }, []);

  return (
    <div className="card" style={{ minHeight: "85%" }}>
      <div className="card-body">
        <h4 className="card-title">Holidays</h4>
        <p className="card-text">This is the holidays page</p>
        <div className="text-center">
          <button
            className="btn btn-primary rounded"
            onClick={() => setAddHoliday(!addHoliday)}
          >
            <IoMdAddCircleOutline size={30} />{" "}
          </button>
        </div>
        {addHoliday && (
          <div className="form-group p-3">
            <div className="row">
              <div className="col-6 mx-auto">
                <label htmlFor="holiday">Reason</label>
                <input
                  type="text"
                  className="form-control"
                  id="holiday"
                  placeholder="Enter holiday"
                />
              </div>
            </div>
            <div className="row">
              <div className="col-6 mx-auto">
                <label htmlFor="date">Starting Date</label>
                <input
                  type="date"
                  className="form-control"
                  id="date"
                  placeholder="Enter date"
                />
              </div>
            </div>
            <div className="row">
              <div className="col-6 mx-auto">
                <label htmlFor="date">Ending Date</label>
                <input
                  type="date"
                  className="form-control"
                  id="date"
                  placeholder="Enter date"
                />
              </div>
            </div>
            <div className="row">
              <div className="col-6 mx-auto text-center">
                <button className="btn btn-primary mt-2">Add</button>
              </div>
            </div>
          </div>
        )}
        <div>
          {holidays.map((holiday, index) => (
            <div key={index}>
              <p>{holiday.reason}</p>
              <p>{holiday.starting_date}</p>
              <p>{holiday.ending_date}</p>
            </div>
          ))}
        </div>
      </div>
    </div>
  );
};

export default Holidays;
