package com.barbertime.barbertime_backend.exceptions;

public class BarberShopServiceNotFoundException extends Exception {
    public BarberShopServiceNotFoundException(String serviceNotFound) {
        super(serviceNotFound);
    }
}
