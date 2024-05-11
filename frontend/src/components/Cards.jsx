import React, { useEffect, useState } from "react";
import Card from "./Card";
import axios from "axios";

export default function Cards({ list }) {
  const [shops, setShops] = useState([]);

  const fetchBarberShops = async () => {
    try {
      const res = await axios.get(
        "http://localhost:5000/api/customer/barberShops?page=0&size=5"
      );
      console.log(res.data.content);
      setShops(res.data.content);
    } catch (err) {
      console.log(err);
    }
  };

  useEffect(() => {
    fetchBarberShops();
  }, []);

  return (
    <div
      className="d-flex justify-content-center mt-2"
      style={{ flexWrap: "wrap", gap: 30, listStyleType: "none" }}
    >
      {shops?.map((item, index) => (
        <Card key={index} barberShop={item} />
      ))}
    </div>
  );
}
