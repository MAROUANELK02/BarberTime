import React from "react";
import Card from "./Card";

export default function Cards({ list }) {
  return (
    <div
      className="d-flex justify-content-center mt-2"
      style={{ flexWrap: "wrap", gap: 30, listStyleType: "none" }}
    >
      {[1, 1, 1].map((item) => (
        <Card />
      ))}
    </div>
  );
}
