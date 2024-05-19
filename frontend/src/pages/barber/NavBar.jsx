import React from "react";
import { CgProfile } from "react-icons/cg";

const NavBar = () => {
  return (
    <React.Fragment>
      <div
        className="btn btn-primary"
        style={{ width: "100%", display: "flex", justifyContent: "center" }}
      >
        Profile
        <CgProfile />
      </div>
    </React.Fragment>
  );
};

export default NavBar;
