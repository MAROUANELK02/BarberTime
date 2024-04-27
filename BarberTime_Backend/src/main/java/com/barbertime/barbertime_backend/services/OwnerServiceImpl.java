package com.barbertime.barbertime_backend.services;

import com.barbertime.barbertime_backend.dtos.req.BarberShopReqDTO;
import com.barbertime.barbertime_backend.dtos.req.HairdresserReqDTO;
import com.barbertime.barbertime_backend.dtos.req.OwnerReqDTO;
import com.barbertime.barbertime_backend.dtos.res.AppointmentResDTO;
import com.barbertime.barbertime_backend.dtos.res.BarberShopResDTO;
import com.barbertime.barbertime_backend.dtos.res.HairdresserResDTO;
import com.barbertime.barbertime_backend.dtos.res.OwnerResDTO;
import com.barbertime.barbertime_backend.entities.*;
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
import java.util.Date;

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
    public OwnerResDTO createOwner(OwnerReqDTO ownerDTO) {
        log.info("Creating owner");
        Owner owner = mappers.toOwner(ownerDTO);
        owner.setRole(roleRepository.findByRoleName(ERole.ROLE_OWNER));
        ownerRepository.save(owner);
        log.info("Owner created");
        return mappers.toOwnerResDTO(owner);
    }

    @Override
    public BarberShopResDTO createBarberShop(BarberShopReqDTO barberShopDTO) {
        log.info("Creating barber shop");
        BarberShop barberShop = mappers.toBarberShop(barberShopDTO);
        createOwner(barberShopDTO.getOwnerDTO());
        Owner owner = ownerRepository.findByCin(barberShopDTO.getOwnerDTO().getCin());
        barberShop.setOwner(owner);
        barberShopRepository.save(barberShop);
        owner.setBarberShop(barberShop);
        ownerRepository.save(owner);
        log.info("Barber shop created");
        return mappers.toBarberShopResDTO(barberShop);
    }

    @Override
    public BarberShopResDTO updateBarberShop(Long idBarberShop,BarberShopReqDTO barberShopDTO) throws BarberShopNotFoundException {
        log.info("Updating barber shop");
        BarberShop barberFound = barberShopRepository.findById(idBarberShop)
                .orElseThrow(() -> new BarberShopNotFoundException("Barber shop not found"));
        BarberShop barberShop = mappers.toBarberShop(barberShopDTO);
        barberShop.setIdBarberShop(barberFound.getIdBarberShop());
        barberShop.setBarberServices(barberFound.getBarberServices());
        barberShop.setOwner(barberFound.getOwner());
        BarberShop save = barberShopRepository.save(barberShop);
        log.info("Barber shop updated");
        return mappers.toBarberShopResDTO(save);
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
    public BarberShopResDTO getBarberShopByOwnerId(Long idOwner) throws OwnerNotFoundException {
        log.info("Getting barber shop by owner id");
        try {
            return mappers.toBarberShopResDTO(barberShopRepository.findByOwnerIdUser(idOwner));
        }catch (Exception e){
            throw new OwnerNotFoundException("Barber Shop not found");
        }
    }

    @Override
    public void addDayOff(String dayOff, Long idBarberShop) throws BarberShopNotFoundException {
        log.info("Adding day off");
        BarberShop barberShop = barberShopRepository.findById(idBarberShop)
                .orElseThrow(() -> new BarberShopNotFoundException("Barber shop not found"));
        barberShop.setDayOff(dayOff);
        barberShopRepository.save(barberShop);
        log.info("Day off added");
    }

    @Override
    public void removeDayOff(Long idBarberShop) throws BarberShopNotFoundException {
        log.info("Removing day off");
        BarberShop barberShop = barberShopRepository.findById(idBarberShop).orElseThrow(() -> new BarberShopNotFoundException("Barber shop not found"));
        barberShop.setDayOff(null);
        barberShopRepository.save(barberShop);
        log.info("Day off removed");
    }

    @Override
    public void updateDayOff(String newDayOff, Long idBarberShop) throws BarberShopNotFoundException {
        log.info("Updating day off");
        removeDayOff(idBarberShop);
        addDayOff(newDayOff, idBarberShop);
        log.info("Day off updated");
    }

    @Override
    public Page<AppointmentResDTO> getAppointmentsAllByBarberShop(Long barberId, int page, int size) {
        log.info("Getting appointments by barber shop");
        try {
            return appointmentRepository.findAllByBarberShopIdBarberShop(barberId, PageRequest.of(page, size)).map(mappers::toAppointmentResDTO);
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return Page.empty();
    }

    @Override
    public Page<AppointmentResDTO> getAppointmentsByBarberShopAndDate(Long barberId, Date date, int page, int size) {
        log.info("Getting appointments by barber shop and date");
        try {
            return appointmentRepository.findAllByBarberShopIdBarberShopAndDate(barberId, date, PageRequest.of(page, size)).map(mappers::toAppointmentResDTO);
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
    public HairdresserResDTO addHairdresser(HairdresserReqDTO hairdresserDTO) {
        log.info("Adding hairdresser");
        Hairdresser save = hairdresserRepository.save(mappers.toHairdresser(hairdresserDTO));
        log.info("Hairdresser added");
        return mappers.toHairdresserResDTO(save);
    }

    @Override
    public HairdresserResDTO updateHairdresser(Long idHairdresser, HairdresserReqDTO hairdresserDTO) throws HairdresserNotFoundException {
        log.info("Updating hairdresser");
        Hairdresser hairdresser = hairdresserRepository.findById(idHairdresser)
                .orElseThrow(() -> new HairdresserNotFoundException("Hairdresser not found"));
        hairdresser.setFirstName(hairdresserDTO.getFirstName());
        hairdresser.setLastName(hairdresserDTO.getLastName());
        Hairdresser save = hairdresserRepository.save(hairdresser);
        log.info("Hairdresser updated");
        return mappers.toHairdresserResDTO(save);
    }

    @Override
    public void assignHairdresserToBarberShop(Long idHairdresser, Long idBarberShop) throws BarberShopNotFoundException, HairdresserNotFoundException {
        log.info("Assigning hairdresser to barber shop");
        BarberShop barberShop = barberShopRepository.findById(idBarberShop)
                .orElseThrow(() -> new BarberShopNotFoundException("Barber shop not found"));
        Hairdresser hairdresser = hairdresserRepository.findById(idHairdresser)
                .orElseThrow(() -> new HairdresserNotFoundException("Hairdresser not found"));
        hairdresser.setBarberShop(barberShop);
        hairdresserRepository.save(hairdresser);
        log.info("Hairdresser assigned to barber shop");
    }

    @Override
    public void removeHairdresserFromBarberShop(Long idHairdresser, Long idBarberShop) throws BarberShopNotFoundException, HairdresserNotFoundException {
        log.info("Removing hairdresser from barber shop");
        BarberShop barberShop = barberShopRepository.findById(idBarberShop)
                .orElseThrow(() -> new BarberShopNotFoundException("Barber shop not found"));
        Hairdresser hairdresser = hairdresserRepository.findById(idHairdresser)
                .orElseThrow(() -> new HairdresserNotFoundException("Hairdresser not found"));
        if (barberShop.getIdBarberShop() != hairdresser.getBarberShop().getIdBarberShop()) {
            throw new HairdresserNotFoundException("Hairdresser not found in this barber shop");
        }else {
            hairdresser.setBarberShop(null);
        }
        hairdresserRepository.save(hairdresser);
        log.info("Hairdresser removed from barber shop");
    }
}
