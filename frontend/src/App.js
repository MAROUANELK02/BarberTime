import React from "react";
import Header from "./components/Header";
import { Route, Routes } from "react-router-dom";
import Home from "./pages/Home";
import Login from "./pages/Login";
import CreateAccount from "./pages/CreateAccount";
import CreateAccountClient from "./pages/CreateAccountClient";
import CreateAccountBarber from "./pages/CreateAccountBarber";
import BarberShop from "./pages/BarberShop";
import CreateAccountBarber2 from "./pages/CreateAccountBarber2";
import CreateAccountBarber3 from "./pages/CreateAccountBarber3";
import CreateAccountBarber4 from "./pages/CreateAccountBarber4";
import CreateAccountBarber5 from "./pages/CreateAccountBarber5";
import BarberShopSpace from "./pages/BarberShopSpace";

function App() {
  return (
    <>
      <Header />
      <Routes>
        <Route path="/" element={<BarberShopSpace />}></Route>
        <Route path="/home" element={<Home />}></Route>
        <Route path="/login" element={<Login />}></Route>
        <Route path="/create-account" element={<CreateAccount />}></Route>
        <Route
          path="/create-account-client"
          element={<CreateAccountClient />}
        ></Route>
        <Route
          path="/create-account-barber"
          element={<CreateAccountBarber />}
        ></Route>
        <Route
          path="/create-account-barber2"
          element={<CreateAccountBarber2 />}
        ></Route>
        <Route
          path="/create-account-barber3"
          element={<CreateAccountBarber3 />}
        ></Route>
        <Route
          path="/create-account-barber4"
          element={<CreateAccountBarber4 />}
        ></Route>
        <Route
          path="/create-account-barber5"
          element={<CreateAccountBarber5 />}
        ></Route>
        <Route
          path="/barber-shop-space/*"
          element={<BarberShopSpace />}
        ></Route>
        <Route path="/barber-shop/:id" element={<BarberShop />}></Route>
      </Routes>
    </>
  );
}

export default App;
