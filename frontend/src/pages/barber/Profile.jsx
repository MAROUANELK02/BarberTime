import React from "react";
import { Link } from "react-router-dom";
import { RxUpdate } from "react-icons/rx";

const Profile = () => {
  return (
    <section class="card">
      <div className="card-body">
        <div className="card-title">
          <h4>Profile</h4>
        </div>

        <div class="container">
          <div class="row gy-4 ">
            <div class="col-12 col-md-8 col-xl-8 mx-auto">
              <div class="card border-0 rounded-4">
                <div class="   p-xl-5">
                  <form action="#!">
                    <div class="row gy-3 overflow-hidden">
                      <div class="col-12 d-flex">
                        <div>
                          <div class="form-floating mb-3 me-3">
                            <input
                              type="text"
                              class="form-control"
                              name="email"
                              id="email"
                              placeholder="name@example.com"
                              required
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
                              name="email"
                              id="email"
                              placeholder="name@example.com"
                              required
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
                              name="cin"
                              id="cin"
                              placeholder="name@example.com"
                              required
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
                            name="email"
                            id="email"
                            placeholder="name@example.com"
                            required
                          />
                          <label for="email" class="form-label">
                            Email
                          </label>
                        </div>
                      </div>
                      <div class="col-12">
                        <div class="form-floating mb-3">
                          <input
                            type="email"
                            class="form-control"
                            name="email"
                            id="email"
                            placeholder="name@example.com"
                            required
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
                            name="password"
                            id="password"
                            placeholder="Password"
                            required
                          />
                          <label for="password" class="form-label">
                            Password
                          </label>
                        </div>
                      </div>
                      <div class="col-12"></div>
                      <div class="col-12">
                        <div class="d-grid">
                          <Link to={"/create-account-barber2"}>
                            <button
                              class="btn btn-primary btn-lg"
                              type="submit"
                              style={{ width: "100%" }}
                            >
                              Update
                              <RxUpdate className="ms-1" />
                            </button>
                          </Link>
                        </div>
                      </div>
                    </div>
                  </form>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
  );
};

export default Profile;
