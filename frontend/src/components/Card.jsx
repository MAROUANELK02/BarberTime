import React, { useEffect, useState } from "react";
import { Button } from "react-bootstrap";

export default function Card({ course }) {
  //const {} = course;

  const [courseBG, setCourseBG] = useState("text-white bg-dark");

  return (
    <li className={`card ${courseBG} mb-3`} style={{ width: 250 }}>
      <div className="card-header">TITLE</div>
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
        <h6>12MAD</h6>
        <Button>Hi</Button>
      </div>
    </li>
  );
}
