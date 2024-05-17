import React, { useEffect, useState } from "react";
import { Button } from "react-bootstrap";
import { Link } from "react-router-dom";
import { FaStar } from "react-icons/fa";

export default function Card({ barberShop }) {
  //const {} = course;

  return (
    <li className={`card  bg-light mb-3`} style={{ width: 250 }}>
      <div className="card-header d-flex justify-content-between">
        <div>{barberShop.name}</div>

        <div>
          {[...Array(barberShop.ratings)].map((a) => (
            <FaStar className="text-warning" />
          ))}
        </div>
      </div>
      <img
        src=""
        alt="course img"
        style={{
          height: "150px",
          objectFit: "cover",
          width: "100%",
        }}
      />

      <p className="card-body">
        Lorem, ipsum dolor sit amet consectetur adipisicing elit. Culpa minima
        quibusdam alias corporis, illo quos.
      </p>
      <div className="card-footer d-flex justify-content-between">
        <h6>{barberShop.neighborhood}</h6>

        <Link to={"barber-shop/" + barberShop.idBarberShop}>
          <Button>Reserver</Button>
        </Link>
      </div>
    </li>
  );
}
