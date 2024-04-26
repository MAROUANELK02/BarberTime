package com.barbertime.barbertime_backend.services;

import com.barbertime.barbertime_backend.dtos.AppointmentDTO;
import com.barbertime.barbertime_backend.dtos.BarberShopDTO;
import com.barbertime.barbertime_backend.dtos.HairdresserDTO;
import com.barbertime.barbertime_backend.dtos.OwnerDTO;
import com.barbertime.barbertime_backend.enums.EStatus;
import com.barbertime.barbertime_backend.exceptions.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.management.ServiceNotFoundException;
import java.time.LocalDate;
import java.util.List;

public interface OwnerService {
    OwnerDTO createOwner(OwnerDTO ownerDTO);
    BarberShopDTO createBarberShop(BarberShopDTO barberShopDTO);
    BarberShopDTO updateBarberShop(BarberShopDTO barberShopDTO);
    void deleteBarberShop(Long idBarberShop) throws BarberShopNotFoundException;
    BarberShopDTO getBarberShopByOwnerId(Long idOwner) throws OwnerNotFoundException;
    void addDayOff(LocalDate dayOff, Long idBarberShop) throws BarberShopNotFoundException;
    void removeDayOff(LocalDate dayOff, Long idBarberShop) throws BarberShopNotFoundException;
    void updateDayOff(LocalDate newDayOff, Long idBarberShop) throws BarberShopNotFoundException;
    Page<AppointmentDTO> getAppointmentsAllByBarberShop(Long barberId, int page, int size);
    Page<AppointmentDTO> getAppointmentsByBarberShopAndDate(Long barberId, LocalDate date, int page, int size);
    void changeAppointmentStatus(Long idAppointment, EStatus status) throws AppointmentNotFoundException;
    void addService(Long barberShopId, Long idService) throws BarberShopNotFoundException, ServiceNotFoundException, BarberShopServiceNotFoundException;
    void removeService(Long barberShopId, Long idService) throws BarberShopNotFoundException, BarberShopServiceNotFoundException;
    void addHairdresser(HairdresserDTO hairdresserDTO);
    void updateHairdresser(HairdresserDTO hairdresserDTO);
    void assignHairdresserToBarberShop(Long idHairdresser, Long idBarberShop) throws BarberShopNotFoundException, HairdresserNotFoundException;
    void removeHairdresserFromBarberShop(Long idHairdresser, Long idBarberShop) throws BarberShopNotFoundException, HairdresserNotFoundException;
}
