package com.barbertime.barbertime_backend.services;

import com.barbertime.barbertime_backend.dtos.AppointmentDTO;
import com.barbertime.barbertime_backend.dtos.BarberShopDTO;
import com.barbertime.barbertime_backend.dtos.HairdresserDTO;

import java.time.LocalDate;
import java.util.List;

public interface OwnerService {
    BarberShopDTO createBarberShop(BarberShopDTO barberShopDTO);
    BarberShopDTO updateBarberShop(BarberShopDTO barberShopDTO);
    void deleteBarberShop(Long idBarberShop);
    BarberShopDTO getBarberShopByCurrentOwner(Long idOwner);
    void addDayOff(LocalDate dayOff);
    void removeDayOff(LocalDate dayOff);
    void updateDayOff(LocalDate newDayOff);
    List<AppointmentDTO> getAppointmentsAllByBarberShop(int page, int size);
    List<AppointmentDTO> getAppointmentsByBarberShopAndDate(LocalDate date, int page, int size);
    void changeAppointmentStatus(Long idAppointment, Long idStatus);
    void addService(Long idService);
    void removeService(Long idService);
    void addHairdresser(HairdresserDTO hairdresserDTO);
    void updateHairdresser(HairdresserDTO hairdresserDTO);
    void assignHairdresserToBarberShop(Long idHairdresser, Long idBarberShop);
    void removeHairdresserFromBarberShop(Long idHairdresser, Long idBarberShop);
}
