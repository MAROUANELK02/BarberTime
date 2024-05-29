import React, { useEffect, useState } from "react";
import Navbar from "react-bootstrap/Navbar";
import Nav from "react-bootstrap/Nav";
import NavDropdown from "react-bootstrap/NavDropdown";
import "bootstrap/dist/css/bootstrap.min.css";
import { Link, useNavigate } from "react-router-dom";

const Header = () => {
  const [login, setLogin] = useState(false);
  const navigate = useNavigate();
  useEffect(() => {
    if (localStorage.getItem("token")) {
      setLogin(true);
    } else {
      setLogin(false);
    }
  }, [login]);

  const logout = () => {
    localStorage.removeItem("token");
    localStorage.removeItem("id");
    setLogin(false);
    navigate("/login");
  };

  return (
    <Navbar
      bg="light"
      expand="lg"
      variant="light"
      className=" m-2"
      style={{ backgroundColor: "white" }}
    >
      <Navbar.Toggle aria-controls="basic-navbar-nav" />
      <Navbar.Collapse
        id="basic-navbar-nav"
        className="justify-content-md-center"
      >
        <Nav className="mr-auto">
          <Nav.Link href="/">Home</Nav.Link>
          <Nav.Link href="/barber-shop-space">BarberShop Space</Nav.Link>
          <Nav.Link href="#disabled">About us</Nav.Link>
          <NavDropdown title="Account" id="basic-nav-dropdown">
            {!login && (
              <NavDropdown.Item>
                <Link className="nav-link" to={"/login"}>
                  Login
                </Link>
              </NavDropdown.Item>
            )}
            {!login && (
              <NavDropdown.Item>
                <Link className="nav-link" to={"/create-account"}>
                  Create Account
                </Link>
              </NavDropdown.Item>
            )}
            {login && (
              <NavDropdown.Item onClick={logout}>
                <Link className="nav-link">Logout</Link>{" "}
              </NavDropdown.Item>
            )}
          </NavDropdown>
        </Nav>
      </Navbar.Collapse>
    </Navbar>
  );
};

export default Header;
