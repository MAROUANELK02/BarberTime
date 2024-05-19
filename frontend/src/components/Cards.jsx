import React, { useEffect, useState } from 'react';
import PaginationBar from './Pagination';
import axios from 'axios';
import { Link } from 'react-router-dom';
import { Button } from 'react-bootstrap';

async function fetchImage(image) {
  const response = await axios.get(`http://localhost:5000/api/owner/image/${image.id}`, { responseType: 'arraybuffer' });
  const base64 = btoa(
    new Uint8Array(response.data).reduce((data, byte) => data + String.fromCharCode(byte), '')
  );
  return `data:${image.type};base64,${base64}`;
}

export default function Cards({ list }) {
  const [barberShops, setBarberShops] = useState([]);
  const [currentPage, setCurrentPage] = useState(1);
  const [totalPages, setTotalPages] = useState(0);
  const url = "http://localhost:5000/api/admin/barberShops";

  useEffect(() => {
    const fetchOperations = async () => {
      try {
        const response = await axios.get(`${url}?page=${currentPage - 1}&size=6`);
        const data = response.data;
        setTotalPages(data.totalPages);
        const shops = await Promise.all(data.content.map(async shop => {
          let image;
          if (shop.images.length > 0) {
            image = await fetchImage(shop.images[0]);
          } else {
            image = undefined; // or set a default image URL
          }
          return { ...shop, image };
        }));
        setBarberShops(shops);
      } catch (error) {
        console.error('Erreur lors de la récupération des opérations :', error);
      }
    };
    fetchOperations();
  }, [currentPage]);

  const handlePaginationClick = (pageNumber) => {
    setCurrentPage(pageNumber);
  };

  return (
    <div style={{ minHeight: '100vh', display: 'flex', flexDirection: 'column' }}>
      <div className="p-1">
        <div style={{ display: 'flex', flexWrap: 'wrap', justifyContent: 'space-between' }}>
          {barberShops.map((barberShop) => (
            <div style={{ flex: '0 0 auto', width: 'calc(33% - 1em)', margin: '0.5em' }} key={barberShop.idBarberShop}>
              <img className="card-img-top" src={barberShop.image} alt="Card" style={{ width: '100%', height: '200px', objectFit: 'cover' }} />
              <div className="card-body">
                <h5 className="card-title">{barberShop.name}</h5>
                <p className="card-text">{barberShop.address}<br />{barberShop.phone}</p>
                <p className="card-text"><small className="text-muted">{barberShop.neighborhood}</small></p>
              </div>
              <Link to={"barber-shop/" + barberShop.idBarberShop}>
                <Button>Reserver</Button>
              </Link>
            </div>
          ))}
        </div>
        <PaginationBar totalPages={totalPages} onPageChange={handlePaginationClick} />
      </div>
    </div>
  );
};
