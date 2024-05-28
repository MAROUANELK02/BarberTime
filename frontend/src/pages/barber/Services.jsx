import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import services from "../../components/services";
import axios from "axios";
import { RxUpdate } from "react-icons/rx";
const Services = () => {
  const [selectedServices, setSelectedServices] = useState([]);

  const handleSelectService = (service) => {
    if (selectedServices.some((s) => s.serviceName === service)) {
      setSelectedServices(
        selectedServices.filter((s) => s.serviceName !== service)
      );
    } else {
      setSelectedServices([
        ...selectedServices,
        { serviceName: service, price: 0 },
      ]);
    }
  };

  const handeChangePrice = (service, price) => {
    setSelectedServices(
      selectedServices.map((s) => {
        if (s.serviceName === service) {
          s.price = price;
        }
        return s;
      })
    );
  };

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await axios.get(
          "http://localhost:5000/api/owner/barberShop/2"
        );
        const data = response.data.services;

        setSelectedServices(data);
        console.log(selectedServices);
      } catch (error) {
        console.error("Error fetching holidays:", error);
      }
    };

    fetchData();

    return () => {};
  }, []);
  return (
    <section class="card">
      <div class="card-body">
        <div className="card-title">
          <h4>Services</h4>
        </div>
        <div class="row gy-4 align-items-center">
          <div class="col-12 col-md-8 col-xl-8 mx-auto card border-primary">
            <div class="card border-0 rounded-4 ">
              <div class="card-body p-3 p-md-4 p-xl-5">
                <form action="#!">
                  <div class="row gy-3 overflow-hidden">
                    <div class="col-12 d-flex"></div>
                    <div class="col-12">
                      <table className="table">
                        <thead>
                          <tr class="">
                            <th>#</th>
                            <th>Service</th>
                            <th>Price</th>
                          </tr>
                        </thead>
                        <tbody>
                          {services.map((s) => {
                            return (
                              <tr
                                className={`${
                                  selectedServices.some(
                                    (sr) => sr.serviceName === s.toUpperCase()
                                  )
                                    ? "table-primary"
                                    : ""
                                }`}
                              >
                                <td>
                                  <input
                                    checked={selectedServices.some(
                                      (sr) => sr.serviceName === s.toUpperCase()
                                    )}
                                    type="checkbox"
                                    onClick={() =>
                                      handleSelectService(s.toUpperCase())
                                    }
                                  />
                                </td>
                                <td>{s}</td>
                                <td>
                                  <input
                                    value={
                                      selectedServices.find(
                                        (sr) =>
                                          sr.serviceName === s.toUpperCase()
                                      )
                                        ? selectedServices
                                            .find(
                                              (sr) =>
                                                sr.serviceName ===
                                                s.toUpperCase()
                                            )
                                            .price.toString()
                                        : ""
                                    }
                                    type="text"
                                    disabled={
                                      !selectedServices.some(
                                        (sr) =>
                                          sr.serviceName === s.toUpperCase()
                                      )
                                    }
                                    style={{
                                      border: "none",
                                      outline: "none",
                                      background: `${
                                        selectedServices.includes(
                                          s.toUpperCase()
                                        )
                                          ? "primary"
                                          : "transparent"
                                      }`,
                                    }}
                                    onChange={(e) =>
                                      handeChangePrice(
                                        s.toUpperCase(),
                                        e.target.value
                                      )
                                    }
                                  />
                                </td>
                              </tr>
                            );
                          })}
                        </tbody>
                      </table>
                    </div>

                    <div class="col-12"></div>
                    <div className="row justify-content-between">
                      <div className=" p-0">
                        <div className="">
                          <Link to={"/create-account-barber5"}>
                            <button
                              className="btn btn-primary btn-lg"
                              type="submit"
                              style={{ width: "100%" }}
                            >
                              Update <RxUpdate className="ms-1" />
                            </button>
                          </Link>
                        </div>
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

export default Services;
