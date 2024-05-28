import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import { RxUpdate } from "react-icons/rx";
import axios from "axios";

const Profile = () => {
  const [owner, setOwner] = useState({});
  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const [cin, setCin] = useState("");
  const [email, setEmail] = useState("");
  const [phoneNumber, setPhoneNumber] = useState("");
  const [password, setPassword] = useState("");

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await axios.get(
          "http://localhost:5000/api/owner/barberShop/2"
        );
        const data = response.data.ownerDTO;
        setOwner(data);
        console.log(data);
      } catch (error) {
        console.error("Error fetching holidays:", error);
      }
    };

    fetchData();

    // Clean-up function if needed
    return () => {
      // Perform clean-up tasks here if necessary
    };
  }, []);
  return (
    <section class="card" style={{ minHeight: "95%" }}>
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
                              placeholder="First Name"
                              required
                              value={owner.firstName}
                              onChange={(e) =>
                                setOwner({
                                  ...owner,
                                  firstName: e.target.value,
                                })
                              }
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
                              placeholder=""
                              required
                              value={owner.lastName}
                              onChange={(e) =>
                                setOwner({
                                  ...owner,
                                  lastName: e.target.value,
                                })
                              }
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
                              placeholder=""
                              required
                              value={owner.cin}
                              onChange={(e) =>
                                setOwner({
                                  ...owner,
                                  cin: e.target.value,
                                })
                              }
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
                            placeholder=""
                            required
                            value={owner.email}
                            onChange={(e) =>
                              setOwner({
                                ...owner,
                                email: e.target.value,
                              })
                            }
                          />
                          <label for="email" class="form-label">
                            Email
                          </label>
                        </div>
                      </div>
                      <div class="col-12">
                        <div class="form-floating mb-3">
                          <input
                            placeholder="Phone Number"
                            type="text"
                            class="form-control"
                            required
                            value={owner.telNumber}
                            onChange={(e) =>
                              setOwner({
                                ...owner,
                                telNumber: e.target.value,
                              })
                            }
                          />
                          <label for="phone" class="form-label">
                            Phone Number
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
