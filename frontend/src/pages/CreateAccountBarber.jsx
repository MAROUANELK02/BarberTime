import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";

const CreateAccountBarber = () => {
  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const [cin, setCin] = useState("");
  const [email, setEmail] = useState("");
  const [phoneNumber, setPhoneNumber] = useState("");
  const [password, setPassword] = useState("");

  const navigate = useNavigate();

  const next = () => {
    localStorage.setItem(
      "owner",
      JSON.stringify({ firstName, lastName, cin, email, phoneNumber, password })
    );
    navigate("/create-account-barber2");
  };

  return (
    <section class="bg-primary py-3 py-md-5 py-xl-8">
      <div class="container">
        <div class="row gy-4 align-items-center">
          <div class="col-12 col-md-8 col-xl-8 mx-auto">
            <div class="card border-0 rounded-4">
              <div class="card-body p-3 p-md-4 p-xl-5">
                <div class="row">
                  <div class="col-12">
                    <div class="mb-4">
                      <h3>Create Account</h3>
                    </div>
                  </div>
                </div>
                <form action="#!">
                  <div class="row gy-3 overflow-hidden">
                    <div class="col-12 d-flex">
                      <div>
                        <div class="form-floating mb-3 me-3">
                          <input
                            type="text"
                            class="form-control"
                            placeholder="name@example.com"
                            required
                            value={firstName}
                            onChange={(e) => setFirstName(e.target.value)}
                          />
                          <label for="email" class="form-label">
                            First name
                          </label>
                        </div>
                      </div>
                      <div>
                        <div class="form-floating mb-3  me-3">
                          <input
                            type="text"
                            class="form-control"
                            placeholder="name@example.com"
                            required
                            value={lastName}
                            onChange={(e) => setLastName(e.target.value)}
                          />
                          <label for="email" class="form-label">
                            Last name
                          </label>
                        </div>
                      </div>
                      <div>
                        <div class="form-floating mb-3">
                          <input
                            type="text"
                            class="form-control"
                            placeholder="name@example.com"
                            required
                            value={cin}
                            onChange={(e) => setCin(e.target.value)}
                          />
                          <label for="cin" class="form-label">
                            CIN
                          </label>
                        </div>
                      </div>
                    </div>
                    <div class="col-12">
                      <div class="form-floating mb-3">
                        <input
                          type="email"
                          class="form-control"
                          placeholder="name@example.com"
                          required
                          value={email}
                          onChange={(e) => setEmail(e.target.value)}
                        />
                        <label for="email" class="form-label">
                          Email
                        </label>
                      </div>
                    </div>
                    <div class="col-12">
                      <div class="form-floating mb-3">
                        <input
                          type="text"
                          class="form-control"
                          placeholder="name@example.com"
                          required
                          value={phoneNumber}
                          onChange={(e) => setPhoneNumber(e.target.value)}
                        />
                        <label for="email" class="form-label">
                          Phone Number
                        </label>
                      </div>
                    </div>
                    <div class="col-12">
                      <div class="form-floating mb-3">
                        <input
                          type="password"
                          class="form-control"
                          placeholder="Password"
                          required
                          value={password}
                          onChange={(e) => setPassword(e.target.value)}
                        />
                        <label for="password" class="form-label">
                          Password
                        </label>
                      </div>
                    </div>
                    <div class="col-12"></div>
                    <div class="col-12">
                      <div class="d-grid">
                        <button
                          class="btn btn-primary btn-lg"
                          style={{ width: "100%" }}
                          onClick={next}
                        >
                          Next
                        </button>
                      </div>
                    </div>
                  </div>
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
  );
};

export default CreateAccountBarber;
