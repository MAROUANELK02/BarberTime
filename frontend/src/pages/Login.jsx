import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { FaScissors } from "react-icons/fa6";
import axios from "axios";

const Login = () => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const navigate = useNavigate();

  const handleLogin = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post(
        "http://localhost:5000/api/auth/signin",
        {
          username,
          password,
        }
      );
      localStorage.setItem("token", response.data.token);
      localStorage.setItem("id", response.data.id);
      console.log(response.data.token, response.data.id);
      navigate("/barber-shop-space");
    } catch (error) {
      console.error(error);
    }
  };

  return (
    <section class="bg-primary py-3 py-md-5 py-xl-8">
      <div class="container">
        <div class="row gy-4 align-items-center">
          <div class="col-12 col-md-6 col-xl-7">
            <div class="d-flex justify-content-center text-bg-primary">
              <div class="col-12 col-xl-9">
                <FaScissors />
                <h1>BarberTime</h1>
                <hr class="border-primary-subtle mb-4" />
                <h2 class="h1 mb-4">
                  Elevate your barber experience with our digital reservation
                  system.
                </h2>
                <p class="lead mb-5">
                  Seamlessly book appointments, manage your schedule, and
                  enhance your grooming routine.
                </p>
                <div class="text-endx">
                  <svg
                    xmlns="http://www.w3.org/2000/svg"
                    width="48"
                    height="48"
                    fill="currentColor"
                    class="bi bi-grip-horizontal"
                    viewBox="0 0 16 16"
                  >
                    <path d="M2 8a1 1 0 1 1 0 2 1 1 0 0 1 0-2zm0-3a1 1 0 1 1 0 2 1 1 0 0 1 0-2zm3 3a1 1 0 1 1 0 2 1 1 0 0 1 0-2zm0-3a1 1 0 1 1 0 2 1 1 0 0 1 0-2zm3 3a1 1 0 1 1 0 2 1 1 0 0 1 0-2zm0-3a1 1 0 1 1 0 2 1 1 0 0 1 0-2zm3 3a1 1 0 1 1 0 2 1 1 0 0 1 0-2zm0-3a1 1 0 1 1 0 2 1 1 0 0 1 0-2zm3 3a1 1 0 1 1 0 2 1 1 0 0 1 0-2zm0-3a1 1 0 1 1 0 2 1 1 0 0 1 0-2z" />
                  </svg>
                </div>
              </div>
            </div>
          </div>
          <div class="col-12 col-md-6 col-xl-5">
            <div class="card border-0 rounded-4">
              <div class="card-body p-3 p-md-4 p-xl-5">
                <div class="row">
                  <div class="col-12">
                    <div class="mb-4">
                      <h3>Login</h3>

                      <p className="d-flex">
                        Don't have an account ?{" "}
                        <Link to={"/create-account"} className="nav-link">
                          <span className="text-primary">Create Account</span>
                        </Link>{" "}
                      </p>
                    </div>
                  </div>
                </div>
                <form>
                  <div class="row gy-3 overflow-hidden">
                    <div class="col-12">
                      <div class="form-floating mb-3">
                        <input
                          type="text"
                          class="form-control"
                          placeholder="name@example.com"
                          required
                          value={username}
                          onChange={(e) => setUsername(e.target.value)}
                        />
                        <label for="email" class="form-label">
                          Username
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
                    <div class="col-12">
                      <div class="form-check">
                        <input
                          class="form-check-input"
                          type="checkbox"
                          value=""
                          name="remember_me"
                          id="remember_me"
                        />
                        <label
                          class="form-check-label text-secondary"
                          for="remember_me"
                        >
                          Keep me logged in
                        </label>
                      </div>
                    </div>
                    <div class="col-12">
                      <div class="d-grid">
                        <button
                          class="btn btn-primary btn-lg"
                          onClick={handleLogin}
                        >
                          Log in now
                        </button>
                      </div>
                    </div>
                  </div>
                </form>
                <div class="row">
                  <div class="col-12">
                    <div class="d-flex gap-2 gap-md-4 flex-column flex-md-row justify-content-md-end mt-4">
                      <a href="#!">Forgot password</a>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
  );
};

export default Login;
