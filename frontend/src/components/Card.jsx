import React, { useEffect, useState } from "react";
import { Button } from "react-bootstrap";
import { Link } from "react-router-dom";

export default function Card({ barberShop }) {
  //const {} = course;

  return (
    <li className={`card text-white bg-dark mb-3`} style={{ width: 250 }}>
      <div className="card-header">{barberShop.name}</div>
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
