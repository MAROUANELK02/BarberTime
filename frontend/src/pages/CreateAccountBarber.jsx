import React from "react";
import { Link } from "react-router-dom";

const CreateAccountBarber = () => {
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
                          type="text"
                          class="form-control"
                          name="phone"
                          id="phone"
                          required
                        />
                        <label for="password" class="form-label">
                          Phone number
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
                            Next
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
    </section>
  );
};

export default CreateAccountBarber;
