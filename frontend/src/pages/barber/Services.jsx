import React, { useEffect, useState } from "react";
//import { Link } from "react-router-dom";
import services from "../../components/services";
import axios from "axios";
import { RxUpdate } from "react-icons/rx";
const Services = () => {
  const [selectedServices, setSelectedServices] = useState([]);
  const [ids, setIds] = useState([]);

  const handleSelectService = async (service) => {
    try {
      if (selectedServices.some((s) => s.serviceName === service)) {
        selectedServices.map(async (s) => {
          if (service === s.serviceName) {
            await deleteService(s.idService);
          }
        });
        setSelectedServices(
          selectedServices.filter((s) => s.serviceName !== service)
        );
      } else {
        console.log(service);
        addService(service);
        setSelectedServices([
          ...selectedServices,
          { serviceName: service, price: 0 },
        ]);
      }
    } catch (error) {
      console.log(error);
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

  const deleteService = async (id) => {
    console.log(id);
    try {
      const response = await axios.delete(
        "http://localhost:5000/api/owner/service/" + id,
        {
          headers: {
            Authorization: `Bearer ${localStorage.getItem("token")}`,
          },
        }
      );
    } catch (error) {
      console.error("Error fetching holidays:", error);
      alert("This service aleary has an appointment");
      fetchData();
    }
  };

  const addService = async (service) => {
    try {
      const response = await axios.post(
        "http://localhost:5000/api/owner/service/" + localStorage.getItem("id"),
        {
          serviceName: service,
          price: 0,
        },
        {
          headers: {
            Authorization: `Bearer ${localStorage.getItem("token")}`,
          },
        }
      );
      fetchData();
      console.log(response);
    } catch (error) {
      console.log(error);
    }
  };

  useEffect(() => {
    fetchData();

    return () => {};
  }, []);

  const fetchData = async () => {
    try {
      const response = await axios.get(
        "http://localhost:5000/api/owner/barberShop/" + 3,
        {
          headers: {
            Authorization: `Bearer ${localStorage.getItem("token")}`,
          },
        }
      );
      console.log(response);
      const data = response.data.services;
      setSelectedServices(data);
      let tab = [];
      data.map((s) => tab.push(s.idService));
      setIds(tab);
      console.log("the selected services");
      console.log(selectedServices);
    } catch (error) {
      console.error("Error fetching holidays:", error);
    }
  };

  const handleUpdateServices = async () => {
    selectedServices.map(async (service) => {
      try {
        const response = await axios.patch(
          "http://localhost:5000/api/owner/service/" + service.idService,
          {
            serviceName: service.serviceName,
            price: service.price,
          },
          {
            headers: {
              Authorization: `Bearer ${localStorage.getItem("token")}`,
            },
          }
        );
      } catch (error) {
        console.error("Error fetching holidays:", error);
        alert("This service aleary has an appointment");
      }
    });
    alert("Services updated successfully");
    //fetchData();
  };

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
                          <div
                            className="btn btn-primary btn-lg"
                            style={{ width: "100%" }}
                            onClick={handleUpdateServices}
                          >
                            Update <RxUpdate className="ms-1" />
                          </div>
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
