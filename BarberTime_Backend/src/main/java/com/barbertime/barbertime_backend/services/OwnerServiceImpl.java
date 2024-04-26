package com.barbertime.barbertime_backend.services;

import com.barbertime.barbertime_backend.dtos.AppointmentDTO;
import com.barbertime.barbertime_backend.dtos.BarberShopDTO;
import com.barbertime.barbertime_backend.dtos.HairdresserDTO;
import com.barbertime.barbertime_backend.dtos.OwnerDTO;
import com.barbertime.barbertime_backend.entities.Appointment;
import com.barbertime.barbertime_backend.entities.BarberShop;
import com.barbertime.barbertime_backend.enums.ERole;
import com.barbertime.barbertime_backend.enums.EStatus;
import com.barbertime.barbertime_backend.exceptions.*;
import com.barbertime.barbertime_backend.mappers.Mappers;
import com.barbertime.barbertime_backend.repositories.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class OwnerServiceImpl implements OwnerService {
    private RoleRepository roleRepository;
    private AppointmentRepository appointmentRepository;
    private BarberShopRepository barberShopRepository;
    private HairdresserRepository hairdresserRepository;
    private HolidayRepository holidayRepository;
    private OwnerRepository ownerRepository;
    private BarberServiceRepository barberServiceRepository;
    private Mappers mappers;

    @Override
    public OwnerDTO createOwner(OwnerDTO ownerDTO) {
        log.info("Creating owner");
        ownerDTO.setRoleDTO(mappers.toRoleDTO(roleRepository.findByRoleNameContains(ERole.ROLE_OWNER)));
        ownerRepository.save(mappers.toOwner(ownerDTO));
        log.info("Owner created");
        return ownerDTO;
    }

    @Override
    public BarberShopDTO createBarberShop(BarberShopDTO barberShopDTO) {
        log.info("Creating barber shop");
        BarberShop barberShop = mappers.toBarberShop(barberShopDTO);
        createOwner(barberShopDTO.getOwnerDTO());
        barberShop.setOwner(ownerRepository.findByCin(barberShopDTO.getOwnerDTO().getCin()));
        barberShopRepository.save(barberShop);
        log.info("Barber shop created");
        return barberShopDTO;
    }

    @Override
    public BarberShopDTO updateBarberShop(BarberShopDTO barberShopDTO) {
        log.info("Updating barber shop");
        BarberShop barberShop = mappers.toBarberShop(barberShopDTO);
        barberShopRepository.save(barberShop);
        log.info("Barber shop updated");
        return barberShopDTO;
    }

    @Override
    public void deleteBarberShop(Long idBarberShop) throws BarberShopNotFoundException {
        log.info("Deleting barber shop");
        try {
            barberShopRepository.deleteById(idBarberShop);
            log.info("Barber shop deleted");
        }catch (Exception e){
            throw new BarberShopNotFoundException("Barber shop not found");
        }
    }

    @Override
    public BarberShopDTO getBarberShopByOwnerId(Long idOwner) throws OwnerNotFoundException {
        log.info("Getting barber shop by owner id");
        try {
            return mappers.toBarberShopDTO(barberShopRepository.findByOwnerIdUser(idOwner));
        }catch (Exception e){
            throw new OwnerNotFoundException("Barber Shop not found");
        }
    }

    @Override
    public void addDayOff(LocalDate dayOff, Long idBarberShop) throws BarberShopNotFoundException {
        log.info("Adding day off");
        BarberShop barberShop = barberShopRepository.findById(idBarberShop)
                .orElseThrow(() -> new BarberShopNotFoundException("Barber shop not found"));
        barberShop.setDayOff(dayOff);
        barberShopRepository.save(barberShop);
        log.info("Day off added");
    }

    @Override
    public void removeDayOff(LocalDate dayOff, Long idBarberShop) throws BarberShopNotFoundException {
        log.info("Removing day off");
        BarberShop barberShop = barberShopRepository.findById(idBarberShop).orElseThrow(() -> new BarberShopNotFoundException("Barber shop not found"));
        barberShop.setDayOff(null);
        barberShopRepository.save(barberShop);
        log.info("Day off removed");
    }

    @Override
    public void updateDayOff(LocalDate newDayOff, Long idBarberShop) throws BarberShopNotFoundException {
        log.info("Updating day off");
        BarberShop barberShop = barberShopRepository.findById(idBarberShop).orElseThrow(() -> new BarberShopNotFoundException("Barber shop not found"));
        barberShop.setDayOff(newDayOff);
        barberShopRepository.save(barberShop);
        log.info("Day off updated");
    }

    @Override
    public Page<AppointmentDTO> getAppointmentsAllByBarberShop(Long barberId, int page, int size) {
        log.info("Getting appointments by barber shop");
        try {
            return appointmentRepository.findAllByBarberShopIdBarberShop(barberId, PageRequest.of(page, size)).map(mappers::toAppointmentDTO);
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return Page.empty();
    }

    @Override
    public Page<AppointmentDTO> getAppointmentsByBarberShopAndDate(Long barberId, LocalDate date, int page, int size) {
        log.info("Getting appointments by barber shop and date");
        try {
            return appointmentRepository.findAllByBarberShopIdBarberShopAndDate(barberId, date, PageRequest.of(page, size)).map(mappers::toAppointmentDTO);
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return Page.empty();
    }

    @Override
    public void changeAppointmentStatus(Long idAppointment, EStatus status) throws AppointmentNotFoundException {
        log.info("Changing appointment status");
        try {
            Appointment appointmentNotFound = appointmentRepository.findById(idAppointment).orElseThrow(() -> new AppointmentNotFoundException("Appointment not found"));
            appointmentNotFound.setStatus(status);
            appointmentRepository.save(appointmentNotFound);
            log.info("Appointment status changed");
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void addService(Long barberShopId, Long idService) throws BarberShopNotFoundException, BarberShopServiceNotFoundException {
        log.info("Adding service");
        BarberShop barberShop = barberShopRepository.findById(barberShopId)
                .orElseThrow(() -> new BarberShopNotFoundException("Barber shop not found"));
        barberShop.getBarberServices().add(barberServiceRepository.findById(idService)
                .orElseThrow(() -> new BarberShopServiceNotFoundException("Service not found")));
        barberShopRepository.save(barberShop);
        log.info("Service added");
    }

    @Override
    public void removeService(Long barberShopId, Long idService) throws BarberShopNotFoundException, BarberShopServiceNotFoundException {
        log.info("Removing service");
        BarberShop barberShop = barberShopRepository.findById(barberShopId)
                .orElseThrow(() -> new BarberShopNotFoundException("Barber shop not found"));
        barberShop.getBarberServices().remove(barberServiceRepository.findById(idService)
                .orElseThrow(() -> new BarberShopServiceNotFoundException("Service not found")));
        barberShopRepository.save(barberShop);
        log.info("Service removed");
    }

    @Override
    public void addHairdresser(HairdresserDTO hairdresserDTO) {
        log.info("Adding hairdresser");
        hairdresserRepository.save(mappers.toHairdresser(hairdresserDTO));
        log.info("Hairdresser added");
    }

    @Override
    public void updateHairdresser(HairdresserDTO hairdresserDTO) {
        log.info("Updating hairdresser");
        hairdresserRepository.save(mappers.toHairdresser(hairdresserDTO));
        log.info("Hairdresser updated");
    }

    @Override
    public void assignHairdresserToBarberShop(Long idHairdresser, Long idBarberShop) throws BarberShopNotFoundException, HairdresserNotFoundException {
        log.info("Assigning hairdresser to barber shop");
        BarberShop barberShop = barberShopRepository.findById(idBarberShop)
                .orElseThrow(() -> new BarberShopNotFoundException("Barber shop not found"));
        barberShop.getHairdressers().add(hairdresserRepository.findById(idHairdresser)
                .orElseThrow(() -> new HairdresserNotFoundException("Hairdresser not found")));
        barberShopRepository.save(barberShop);
        log.info("Hairdresser assigned to barber shop");
    }

    @Override
    public void removeHairdresserFromBarberShop(Long idHairdresser, Long idBarberShop) throws BarberShopNotFoundException, HairdresserNotFoundException {
        log.info("Removing hairdresser from barber shop");
        BarberShop barberShop = barberShopRepository.findById(idBarberShop)
                .orElseThrow(() -> new BarberShopNotFoundException("Barber shop not found"));
        barberShop.getHairdressers().remove(hairdresserRepository.findById(idHairdresser)
                .orElseThrow(() -> new HairdresserNotFoundException("Hairdresser not found")));
        barberShopRepository.save(barberShop);
        log.info("Hairdresser removed from barber shop");
    }
}
