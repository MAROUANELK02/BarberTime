package com.barbertime.barbertime_backend.exceptions;

public class CustomerNotFoundException extends Exception {
    public CustomerNotFoundException(String idCustomer) {
        super("Customer with id " + idCustomer + " not found");
    }
}
