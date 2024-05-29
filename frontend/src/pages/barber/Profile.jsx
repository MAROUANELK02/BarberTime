import React, { useEffect, useState } from "react";
//import { Link } from "react-router-dom";
import { RxUpdate } from "react-icons/rx";
import axios from "axios";

const Profile = () => {
    const [owner, setOwner] = useState({});
    const [barberShop, setBarberShop] = useState({});

    useEffect(() => {
        const fetchData = async () => {
            try {
                const response = await axios.get(
                    "http://localhost:5000/api/owner/barberShop/" +
                    localStorage.getItem("id"),
                    {
                        headers: {
                            Authorization: `Bearer ${localStorage.getItem("token")}`,
                        },
                    }
                );
                const data = response.data;
                setBarberShop(data);
                setOwner(data.ownerDTO);
                console.log(data);
            } catch (error) {
                console.error("Error fetching holidays:", error);
            }
        };

        fetchData();
    }, []);

    // Add a function to handle form submission
    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            await axios.patch(
                "http://localhost:5000/api/owner/barberShop/" +
                barberShop.idBarberShop,
                {
                    name: barberShop.name,
                    address: barberShop.address,
                    phone: barberShop.phone,
                    neighborhood: barberShop.neighborhood,
                    authorizationNumber: barberShop.authorizationNumber,
                    dayOff: barberShop.dayOff,
                    startTime: barberShop.startTime,
                    endTime: barberShop.endTime,
                    ownerDTO: {
                        firstName: owner.firstName,
                        lastName: owner.lastName,
                        telNumber: owner.telNumber,
                        email: owner.email,
                        username: owner.username,
                        password: "",
                        cin: owner.cin,
                    },
                },
                {
                    headers: {
                        Authorization: `Bearer ${localStorage.getItem("token")}`,
                    },
                }
            );
            alert("Profile updated successfully!");
        } catch (error) {
            console.error("Error updating profile:", error);
        }
    };

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
                                    <form onSubmit={(e) => handleSubmit(e)}>
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
                                                    {/*<Link to={"/create-account-barber2"}>*/}
                                                    <button
                                                        class="btn btn-primary btn-lg"
                                                        type="submit"
                                                        style={{ width: "100%" }}
                                                    >
                                                        Update
                                                        <RxUpdate className="ms-1" />
                                                    </button>
                                                    {/*</Link > */}
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