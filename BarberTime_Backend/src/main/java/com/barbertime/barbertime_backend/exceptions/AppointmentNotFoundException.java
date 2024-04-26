package com.barbertime.barbertime_backend.exceptions;

public class AppointmentNotFoundException extends Exception {
    public AppointmentNotFoundException(String appointmentNotFound) {
        super(appointmentNotFound);
    }
}
