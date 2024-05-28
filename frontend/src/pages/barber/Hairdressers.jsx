import React, { useEffect } from "react";
import axios from "axios";
const Hairdressers = () => {
  const [hairdressers, setHairdressers] = React.useState([]);
  useEffect(() => {
    fetchData();
  }, []);

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
      console.log(response.data.content);
      setHairdressers(response.content);
    } catch (error) {
      console.error("Error fetching hairdressers:", error);
    }
  };
  return (
    <div className="card">
      <div className="card-body">
        <h4 className="card-title">Hairdressers</h4>
        <p className="card-text">This is the Hairdressers page</p>
      </div>
    </div>
  );
};

export default Hairdressers;
