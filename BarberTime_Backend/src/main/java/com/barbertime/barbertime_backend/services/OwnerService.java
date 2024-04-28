package com.barbertime.barbertime_backend.services;

import com.barbertime.barbertime_backend.dtos.req.*;
import com.barbertime.barbertime_backend.dtos.res.*;
import com.barbertime.barbertime_backend.entities.Hairdresser;
import com.barbertime.barbertime_backend.enums.EStatus;
import com.barbertime.barbertime_backend.exceptions.*;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;

public interface OwnerService {
    OwnerResDTO createOwner(OwnerReqDTO ownerDTO);
    BarberShopResDTO createBarberShop(BarberShopReqDTO barberShopDTO);
    BarberShopResDTO updateBarberShop(Long idBarberShop,BarberShopReqDTO barberShopDTO) throws BarberShopNotFoundException;
    void deleteBarberShop(Long idBarberShop) throws BarberShopNotFoundException;
    BarberShopResDTO getBarberShopByOwnerId(Long idOwner) throws OwnerNotFoundException;
    void addDayOff(String dayOff, Long idBarberShop) throws BarberShopNotFoundException;
    void removeDayOff(Long idBarberShop) throws BarberShopNotFoundException;
    void updateDayOff(String newDayOff, Long idBarberShop) throws BarberShopNotFoundException;
    HolidayResDTO addHolidayToBarberShop(HolidayReqDTO holidayReqDTO, Long idBarberShop) throws BarberShopNotFoundException;
    void removeHolidayFromBarberShops(Long idHoliday, Long idBarberShop) throws HolidayNotFoundException;
    List<HolidayResDTO> addHolidayRangeToBarberShop(HolidayRangeReqDTO holidayRangeReqDTO, Long idBarberShop) throws BarberShopNotFoundException;
    Page<HolidayResDTO> getHolidaysByBarberShop(Long idBarberShop, int page, int size);
    Page<AppointmentResDTO> getAppointmentsAllByBarberShop(Long barberId, int page, int size);
    Page<AppointmentResDTO> getAppointmentsByBarberShopAndDate(Long barberId, LocalDate date, int page, int size);
    void changeAppointmentStatus(Long idAppointment, EStatus status) throws AppointmentNotFoundException;
    void assignServiceToBarberShop(Long barberShopId, Long idService) throws BarberShopNotFoundException, BarberShopServiceNotFoundException;
    void removeServiceFromBarberShop(Long barberShopId, Long idService) throws BarberShopNotFoundException, BarberShopServiceNotFoundException;
    Hairdresser addHairdresser(HairdresserReqDTO hairdresserDTO);
    HairdresserResDTO updateHairdresser(Long idHairdresser, HairdresserReqDTO hairdresserDTO) throws HairdresserNotFoundException;
    HairdresserResDTO assignHairdresserToBarberShop(HairdresserReqDTO hairdresserDTO, Long idBarberShop) throws BarberShopNotFoundException, HairdresserNotFoundException;
    void removeHairdresserFromBarberShop(Long idHairdresser, Long idBarberShop) throws BarberShopNotFoundException, HairdresserNotFoundException;
}
