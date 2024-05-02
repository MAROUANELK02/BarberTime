import React from "react";
import Header from "./components/Header";
import { Route, Routes } from "react-router-dom";
import Home from "./pages/Home";
import Login from "./pages/Login";
import Profile from "./pages/Profile";
import CreateAccount from "./pages/CreateAccount";
import CreateAccountClient from "./pages/CreateAccountClient";
import CreateAccountBarber from "./pages/CreateAccountBarber";

function App() {
  return (
    <>
      <Header />
      <Routes>
        <Route path="/" element={<Home />}></Route>
        <Route path="/home" element={<Home />}></Route>
        <Route path="/login" element={<Login />}></Route>
        <Route path="/profile" element={<Profile />}></Route>
        <Route path="/create-account" element={<CreateAccount />}></Route>
        <Route
          path="/create-account-client"
          element={<CreateAccountClient />}
        ></Route>
        <Route
          path="/create-account-barber"
          element={<CreateAccountBarber />}
        ></Route>
      </Routes>
    </>
  );
}

export default App;
