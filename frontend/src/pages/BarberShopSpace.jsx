import React from "react";
import NavBar from "./barber/NavBar";
import Profile from "./barber/Profile";
import { Link, Route, Routes } from "react-router-dom";
import { CgProfile } from "react-icons/cg";
import Services from "./barber/Services";
import Hairdressers from "./barber/Hairdressers";
import Images from "./barber/Images";
import Holidays from "./barber/Holidays";
import Appointments from "./barber/Appointments";
import BarberShop from "./barber/BarberShop";
import WorkTime from "./barber/WorkTime";

const BarberShopSpace = () => {
  return (
    <div>
      <div className="row" style={{ height: "85vh" }}>
        <div className="col-2 card shadow">
          <NavBar />
        </div>
        <div className="col-10">
          <Routes>
            <Route path="/" element={<Profile />} />
            <Route path="profile" element={<Profile />} />
            <Route path="holiday" element={<Holidays />} />
            <Route path="services" element={<Services />} />
            <Route path="hairdressers" element={<Hairdressers />} />
            <Route path="images" element={<Images />} />
            <Route path="appointments" element={<Appointments />} />
            <Route path="barbershop" element={<BarberShop />} />
            <Route path="worktime" element={<WorkTime />} />
          </Routes>
        </div>
      </div>
    </div>
  );
};

export default BarberShopSpace;
