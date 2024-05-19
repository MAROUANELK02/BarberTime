import React from "react";
import NavBar from "./barber/NavBar";
import Profile from "./Profile";

const BarberShopSpace = () => {
  return (
    <div>
      <div className="row" style={{ height: "100vh" }}>
        <div className="col-2 bg-primary">
          <NavBar />
        </div>
        <div className="col-10">
          <Profile />
        </div>
      </div>
    </div>
  );
};

export default BarberShopSpace;
