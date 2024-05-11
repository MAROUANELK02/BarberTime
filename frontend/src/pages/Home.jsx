import React, { useEffect } from "react";
import Filters from "../components/Filters";
import Cards from "../components/Cards";

const Home = () => {
  return (
    <React.Fragment>
      <Filters />
      <Cards />
    </React.Fragment>
  );
};

export default Home;
