package com.barbertime.barbertime_backend.services;

import com.barbertime.barbertime_backend.dtos.req.BarberShopReqDTO;
import com.barbertime.barbertime_backend.dtos.req.HairdresserReqDTO;
import com.barbertime.barbertime_backend.dtos.req.OwnerReqDTO;
import com.barbertime.barbertime_backend.dtos.res.AppointmentResDTO;
import com.barbertime.barbertime_backend.dtos.res.BarberShopResDTO;
import com.barbertime.barbertime_backend.dtos.res.HairdresserResDTO;
import com.barbertime.barbertime_backend.dtos.res.OwnerResDTO;
import com.barbertime.barbertime_backend.enums.EStatus;
import com.barbertime.barbertime_backend.exceptions.*;
import org.springframework.data.domain.Page;

import java.util.Date;

public interface OwnerService {
    OwnerResDTO createOwner(OwnerReqDTO ownerDTO);
    BarberShopResDTO createBarberShop(BarberShopReqDTO barberShopDTO);
    BarberShopResDTO updateBarberShop(Long idBarberShop,BarberShopReqDTO barberShopDTO) throws BarberShopNotFoundException;
    void deleteBarberShop(Long idBarberShop) throws BarberShopNotFoundException;
    BarberShopResDTO getBarberShopByOwnerId(Long idOwner) throws OwnerNotFoundException;
    void addDayOff(String dayOff, Long idBarberShop) throws BarberShopNotFoundException;
    void removeDayOff(Long idBarberShop) throws BarberShopNotFoundException;
    void updateDayOff(String newDayOff, Long idBarberShop) throws BarberShopNotFoundException;
    Page<AppointmentResDTO> getAppointmentsAllByBarberShop(Long barberId, int page, int size);
    Page<AppointmentResDTO> getAppointmentsByBarberShopAndDate(Long barberId, Date date, int page, int size);
    void changeAppointmentStatus(Long idAppointment, EStatus status) throws AppointmentNotFoundException;
    void addService(Long barberShopId, Long idService) throws BarberShopNotFoundException, BarberShopServiceNotFoundException;
    void removeService(Long barberShopId, Long idService) throws BarberShopNotFoundException, BarberShopServiceNotFoundException;
    HairdresserResDTO addHairdresser(HairdresserReqDTO hairdresserDTO);
    HairdresserResDTO updateHairdresser(Long idHairdresser, HairdresserReqDTO hairdresserDTO) throws HairdresserNotFoundException;
    void assignHairdresserToBarberShop(Long idHairdresser, Long idBarberShop) throws BarberShopNotFoundException, HairdresserNotFoundException;
    void removeHairdresserFromBarberShop(Long idHairdresser, Long idBarberShop) throws BarberShopNotFoundException, HairdresserNotFoundException;
}
