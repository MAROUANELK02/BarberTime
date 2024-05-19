import React, { useState } from "react";
import { Link } from "react-router-dom";
import { IoIosClose } from "react-icons/io";
import { MdFileUpload } from "react-icons/md";

const CreateAccountBarber5 = () => {
  const [images, setImages] = useState([]);
  const removeImage = (key) => {
    const updatedImages = images.filter((image, index) => index !== key);
    setImages(updatedImages);
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
                      <h3>Upload some images</h3>
                    </div>
                  </div>
                </div>
                <form action="#!">
                  <div class="row gy-3 overflow-hidden">
                    <div class="col-12"></div>
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
                            multiple
                            onChange={(e) => {
                              const files = Array.from(e.target.files);
                              setImages([...images, ...files]);
                            }}
                            style={{ display: "none" }}
                          />
                          <span>
                            Upload
                            <MdFileUpload />
                          </span>
                        </label>
                      </div>
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
                                <IoIosClose />
                              </button>
                            </div>
                          </div>
                        ))}
                      </div>
                    </div>
                    <div className="row justify-content-between">
                      <div className="col-sm">
                        <div className="">
                          <Link to={"/create-account-barber4"}>
                            <button
                              className="btn btn-secondary btn-lg"
                              type="submit"
                              style={{ width: "100%" }}
                            >
                              Back
                            </button>
                          </Link>
                        </div>
                      </div>
                      <div className="col-10 p-0">
                        <div className="">
                          <Link to={"/create-account-barber3"}>
                            <button
                              className="btn btn-primary btn-lg"
                              type="submit"
                              style={{ width: "100%" }}
                            >
                              Submit
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

export default CreateAccountBarber5;
