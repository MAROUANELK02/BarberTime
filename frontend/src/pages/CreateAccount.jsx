import React from "react";
import { HiOutlineFaceSmile } from "react-icons/hi2";
import { FaScissors } from "react-icons/fa6";
import { Link } from "react-router-dom";
import "./CreateAccount.css";

const CreateAccount = () => {
  return (
    <div className="p-5 m-5">
      <h1>Create Account</h1>
      <h3 className="mb-4">You Are ?</h3>
      <div className="d-flex justify-content-center">
        <Link className="nav-link" to={"/create-account-client"}>
          <div className="card mx-3 p-4 card1">
            <div className="card-body text-center">
              <p>
                <HiOutlineFaceSmile style={{ fontSize: "4em" }} />
              </p>
              <h4>Client</h4>
            </div>
          </div>
        </Link>
        <Link className="nav-link" to={"/create-account-barber"}>
          <div className="card mx-3 p-4 card1">
            <div className="card-body text-center">
              <p>
                <FaScissors style={{ fontSize: "4em" }} />
              </p>
              <h4>Barber</h4>
            </div>
          </div>
        </Link>
      </div>
    </div>
  );
};

export default CreateAccount;
