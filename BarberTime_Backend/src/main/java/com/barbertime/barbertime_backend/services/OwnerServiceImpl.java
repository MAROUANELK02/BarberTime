package com.barbertime.barbertime_backend.services;

import com.barbertime.barbertime_backend.dtos.AppointmentDTO;
import com.barbertime.barbertime_backend.dtos.BarberShopDTO;
import com.barbertime.barbertime_backend.dtos.HairdresserDTO;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class OwnerServiceImpl implements OwnerService {
    @Override
    public BarberShopDTO createBarberShop(BarberShopDTO barberShopDTO) {
        log.info("Creating barber shop");
        return null;
    }

    @Override
    public BarberShopDTO updateBarberShop(BarberShopDTO barberShopDTO) {
        return null;
    }

    @Override
    public void deleteBarberShop(Long idBarberShop) {

    }

    @Override
    public BarberShopDTO getBarberShopByCurrentOwner(Long idOwner) {
        return null;
    }

    @Override
    public void addDayOff(LocalDate dayOff) {

    }

    @Override
    public void removeDayOff(LocalDate dayOff) {

    }

    @Override
    public void updateDayOff(LocalDate newDayOff) {

    }

    @Override
    public List<AppointmentDTO> getAppointmentsAllByBarberShop(int page, int size) {
        return List.of();
    }

    @Override
    public List<AppointmentDTO> getAppointmentsByBarberShopAndDate(LocalDate date, int page, int size) {
        return List.of();
    }

    @Override
    public void changeAppointmentStatus(Long idAppointment, Long idStatus) {

    }

    @Override
    public void addService(Long idService) {

    }

    @Override
    public void removeService(Long idService) {

    }

    @Override
    public void addHairdresser(HairdresserDTO hairdresserDTO) {

    }

    @Override
    public void updateHairdresser(HairdresserDTO hairdresserDTO) {

    }

    @Override
    public void assignHairdresserToBarberShop(Long idHairdresser, Long idBarberShop) {

    }

    @Override
    public void removeHairdresserFromBarberShop(Long idHairdresser, Long idBarberShop) {

    }
}
