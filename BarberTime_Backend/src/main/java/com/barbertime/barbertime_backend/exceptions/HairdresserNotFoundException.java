package com.barbertime.barbertime_backend.exceptions;

public class HairdresserNotFoundException extends Exception {
    public HairdresserNotFoundException(String hairdresserNotFound) {
        super(hairdresserNotFound);
    }
}
