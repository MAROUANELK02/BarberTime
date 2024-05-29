import React, {useEffect, useState} from "react";
import { IoIosClose } from "react-icons/io";
import { MdFileUpload } from "react-icons/md";
import axios from "axios";

const Images = () => {
  const [images, setImages] = useState([]);
  const [barberShopId, setBarberShopId] = useState(null);

  const removeImage = (key) => {
    const updatedImages = images.filter((image, index) => index !== key);
    setImages(updatedImages);
  };

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
        const data = response.data.idBarberShop;
        setBarberShopId(data);
        console.log(data);
      } catch (error) {
        console.error("Error fetching holidays:", error);
      }
    }
    fetchData();
  }, []);

    const uploadImages = async () => {
      const token = localStorage.getItem("token");

      for (const image of images) {
        const formData = new FormData();
        formData.append("image", image);

        try {
          await axios.post(
              `http://localhost:5000/api/owner/barberShop/${barberShopId}/image`,
              formData,
              {
                headers: {
                  "Content-Type": "multipart/form-data",
                  Authorization: `Bearer ${token}`,
                },
              });
        } catch (error) {
          console.error(error);
        }
      }
    };

    return (
        <section className="card">
          <div className="card-body">
            <div className="card-title">
              <h4>Images</h4>
            </div>
            <div className="row gy-4 align-items-center">
              <div className="col-12 col-md-8 col-xl-8 mx-auto">
                <div className="card border-0 rounded-4">
                  <div className="card-body p-3 p-md-4 p-xl-5">
                    <form action="#!">
                      <div className="row gy-3 overflow-hidden">
                        <div className="col-12"></div>
                        <div className="col-12">
                          <div className="mb-3">
                            <label
                                htmlFor="file-input"
                                className="btn btn-primary btn-lg"
                            >
                              <input
                                  id="file-input"
                                  type="file"
                                  accept="image/*"
                                  onChange={(e) => {
                                    const file = e.target.files[0];
                                    setImages([file]);
                                  }}
                                  style={{display: "none"}}
                              />
                              <span>
                            Upload
                            <MdFileUpload/>
                          </span>
                            </label>
                          </div>
                          <button
                              type="button"
                              className="btn btn-success"
                              onClick={uploadImages}
                          >
                            Submit
                          </button>
                          <div className="row">
                            {images.map((image, index) => (
                                <div className="col-4" key={index}>
                                  <div className="position-relative">
                                    <img
                                        src={URL.createObjectURL(image)}
                                        alt={`Image ${index}`}
                                        className="img-fluid border border-primary m-2 rounded"
                                        style={{
                                          height: "200px",
                                          width: "200px",
                                          objectFit: "cover",
                                        }}
                                    />
                                    <button
                                        className="btn btn-danger btn-sm position-absolute top-0 end-0"
                                        onClick={() => removeImage(index)}
                                    >
                                      <IoIosClose/>
                                    </button>
                                  </div>
                                </div>
                            ))}
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

  export default Images;
