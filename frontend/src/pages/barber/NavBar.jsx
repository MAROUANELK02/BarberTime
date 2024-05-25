import React from "react";
import { CgProfile } from "react-icons/cg";
import { MdCelebration } from "react-icons/md";
import { ImScissors } from "react-icons/im";
import { IoPersonSharp } from "react-icons/io5";
import { FaImages } from "react-icons/fa";
import { FaBusinessTime } from "react-icons/fa6";
import { FaCalendarCheck } from "react-icons/fa";
import { FaShop } from "react-icons/fa6";

import { Link } from "react-router-dom";

const NavBar = () => {
  const navs = [
    { name: "Profile", icon: <CgProfile />, link: "profile" },
    { name: "Holidays", icon: <MdCelebration />, link: "holiday" },
    { name: "Services", icon: <ImScissors />, link: "services" },
    { name: "Hairdressers", icon: <IoPersonSharp />, link: "hairdressers" },
    { name: "Images", icon: <FaImages />, link: "images" },
    { name: "Work Time", icon: <FaBusinessTime />, link: "worktime" },
    { name: "Appointments", icon: <FaCalendarCheck />, link: "appointments" },
    { name: "Barber Shop", icon: <FaShop />, link: "barbershop" },
  ];
  return (
    <React.Fragment>
      {navs.map((nav, index) => (
        <Link to={nav.link} className="nav-link my-3" key={index}>
          <div
            className="btn text-primary"
            style={{ width: "100%", display: "flex", justifyContent: "center" }}
          >
            {nav.name}
            <div className="ms-1">{nav.icon}</div>
          </div>
        </Link>
      ))}
    </React.Fragment>
  );
};

export default NavBar;
