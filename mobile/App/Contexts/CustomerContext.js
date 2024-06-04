import React, { createContext, useState, useContext } from 'react';

const CustomerContext = createContext();

export const CustomerProvider = ({ children }) => {
    const [customer, setCustomer] = useState(null);

    return (
        <CustomerContext.Provider value={{ customer, setCustomer }}>
            {children}
        </CustomerContext.Provider>
    );
};

export const useCustomer = () => useContext(CustomerContext);
