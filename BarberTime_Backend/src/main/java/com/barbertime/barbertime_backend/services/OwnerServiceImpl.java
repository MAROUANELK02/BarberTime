package com.barbertime.barbertime_backend.services;

import com.barbertime.barbertime_backend.dtos.BarberServiceDTO;
import com.barbertime.barbertime_backend.dtos.req.*;
import com.barbertime.barbertime_backend.dtos.res.*;
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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.management.ServiceNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
    private FileDataRepository fileDataRepository;
    private ImagesService imagesService;
    private Mappers mappers;
    private PasswordEncoder passwordEncoder;

    @Override
    public void createOwner(OwnerReqDTO ownerDTO) {
        log.info("Creating owner");
        Owner owner = mappers.toOwner(ownerDTO);
        owner.getRole().add(roleRepository.findByRoleName(ERole.ROLE_OWNER));
        owner.setPassword(passwordEncoder.encode(owner.getPassword()));
        owner.setPassword(owner.getPassword());
        ownerRepository.save(owner);
        log.info("Owner created");
        mappers.toOwnerResDTO(owner);
    }

    public void createService(List<BarberService> services, BarberShop barberShop) {
        log.info("Creating services");
        services.forEach(barberService -> barberService.setBarberShop(barberShop));
        barberServiceRepository.saveAll(services);
        log.info("Services created");
    }

    @Override
    public BarberShopResDTO createBarberShop(BarberShopReqDTO barberShopDTO) {
        log.info("Creating barber shop");
        BarberShop barberShop = mappers.toBarberShop(barberShopDTO);
        createOwner(barberShopDTO.getOwnerDTO());
        Owner owner = ownerRepository.findByCin(barberShopDTO.getOwnerDTO().getCin());
        barberShop.setOwner(owner);
        BarberShop save = barberShopRepository.save(barberShop);
        createService(barberShop.getBarberServices(), save);
        log.info("Barber shop created");
        return mappers.toBarberShopResDTO(barberShop);
    }

    @Override
    public void saveImageOfBarberShop(Long idBarberShop, MultipartFile image) throws BarberShopNotFoundException, IOException {
        log.info("Saving image of barber shop");
        BarberShop barberShop = barberShopRepository.findById(idBarberShop)
                .orElseThrow(() -> new BarberShopNotFoundException("Barber shop not found"));
        imagesService.uploadImageToStorage(barberShop, image);
        log.info("Image of barber shop saved");
    }

    @Override
    public List<byte[]> getImagesOfBarberShop(Long idBarberShop) throws BarberShopNotFoundException {
        log.info("Getting images of barber shop");
        BarberShop barberShop = barberShopRepository.findById(idBarberShop)
                .orElseThrow(() -> new BarberShopNotFoundException("Barber shop not found"));
        List<FileData> images = fileDataRepository.findAllByBarberShopIdBarberShop(barberShop.getIdBarberShop());
        if (images.isEmpty()) {
            throw new BarberShopNotFoundException("No images found for this barber shop");
        }
        List<byte[]> imagesBytes = new ArrayList<>();
        for (FileData image : images) {
            try {
                imagesBytes.add(imagesService.downloadImageFromStorage(image.getId()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return imagesBytes;
    }

    @Override
    public byte[] getImageById(Long idImage) {
        log.info("Getting image by id");
        try {
            return imagesService.downloadImageFromStorage(idImage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public BarberShopResDTO updateBarberShop(Long idBarberShop,BarberShopReqDTO barberShopDTO) throws BarberShopNotFoundException {
        log.info("Updating barber shop");
        BarberShop barberFound = barberShopRepository.findById(idBarberShop)
                .orElseThrow(() -> new BarberShopNotFoundException("Barber shop not found"));
        BarberShop barberShop = mappers.toBarberShop(barberShopDTO);
        barberFound.setAddress(barberShop.getAddress());
        barberFound.setAuthorizationNumber(barberShop.getAuthorizationNumber());
        barberFound.setDayOff(barberShop.getDayOff());
        barberFound.setStartTime(barberShop.getStartTime());
        barberFound.setEndTime(barberShop.getEndTime());
        barberFound.setName(barberShop.getName());
        barberFound.setPhone(barberShop.getPhone());
        barberFound.setNeighborhood(barberShop.getNeighborhood());
        if(barberShop.getOwner() != null) {
            Owner owner = ownerRepository.findByCin(barberShopDTO.getOwnerDTO().getCin());
            owner.setFirstName(barberShopDTO.getOwnerDTO().getFirstName());
            owner.setLastName(barberShopDTO.getOwnerDTO().getLastName());
            owner.setTelNumber(barberShopDTO.getOwnerDTO().getTelNumber());
            owner.setEmail(barberShopDTO.getOwnerDTO().getEmail());
            owner.setUsername(barberShopDTO.getOwnerDTO().getUsername());
            barberFound.setOwner(owner);
        }
        BarberShop save = barberShopRepository.save(barberFound);
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
        BarberShop barberShop = barberShopRepository.findById(idBarberShop)
                .orElseThrow(() -> new BarberShopNotFoundException("Barber shop not found"));
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
    public HolidayResDTO addHolidayToBarberShop(HolidayReqDTO holidayReqDTO, Long idBarberShop) throws BarberShopNotFoundException {
        log.info("Adding holiday to barber shop");
        BarberShop barberShop = barberShopRepository.findById(idBarberShop)
                .orElseThrow(() -> new BarberShopNotFoundException("Barber shop not found"));
        Holiday holiday = mappers.toHoliday(holidayReqDTO);
        holiday.setBarberShop(barberShop);
        Holiday save = holidayRepository.save(holiday);
        log.info("Holiday added to barber shop");
        return mappers.toHolidayResDTO(save);
    }

    @Override
    public void removeHolidayFromBarberShops(Long idHoliday, Long idBarberShop) throws HolidayNotFoundException {
        log.info("Removing holiday from barber shop");
        Holiday holiday = holidayRepository.findById(idHoliday)
                    .orElseThrow(() -> new HolidayNotFoundException("Holiday not found"));
        if(holiday.getBarberShop().getIdBarberShop().equals(idBarberShop)) {
                holidayRepository.deleteById(idHoliday);
                log.info("Holiday removed from barber shop");
        }else {
                throw new HolidayNotFoundException("Holiday not found in this barber shop");
        }
    }

    @Override
    public List<HolidayResDTO> addHolidayRangeToBarberShop(HolidayRangeReqDTO holidayRangeReqDTO, Long idBarberShop) throws BarberShopNotFoundException {
        log.info("Adding holiday range to barber shop");
        BarberShop barberShop = barberShopRepository.findById(idBarberShop)
                .orElseThrow(() -> new BarberShopNotFoundException("Barber shop not found"));
        List<HolidayResDTO> holidayResDTOS = new ArrayList<>();
        for (LocalDate date = holidayRangeReqDTO.getStartDate(); !date.isAfter(holidayRangeReqDTO.getEndDate()); date = date.plusDays(1)) {
            HolidayReqDTO holidayReqDTO = new HolidayReqDTO();
            holidayReqDTO.setHolidayDate(date);
            holidayReqDTO.setReason(holidayRangeReqDTO.getReason());
            holidayResDTOS.add(addHolidayToBarberShop(holidayReqDTO, idBarberShop));
        }
        log.info("Holiday range added to barber shop");
        return holidayResDTOS;
    }

    @Override
    public Page<HolidayResDTO> getHolidaysByBarberShop(Long idBarberShop, int page, int size) {
        log.info("Getting holidays by barber shop");
        LocalDate currentDate = LocalDate.now();
        return holidayRepository.findAllByBarberShopIdBarberShopAndHolidayDateAfter(idBarberShop,
                        currentDate,
                        PageRequest.of(page, size)).map(mappers::toHolidayResDTO);
    }

    @Override
    public Page<AppointmentResDTO> getAppointmentsAllByBarberShop(Long barberId, int page, int size) {
        log.info("Getting appointments by barber shop");
        return appointmentRepository.findAllByBarberShopIdBarberShop(barberId,
                            PageRequest.of(page, size)).map(mappers::toAppointmentResDTO);
    }

    @Override
    public Page<AppointmentResDTO> getAppointmentsByBarberShopAndDate(Long barberId, LocalDate date, int page, int size) {
        log.info("Getting appointments by barber shop and date");
        return appointmentRepository.findAllByBarberShopIdBarberShopAndDate(barberId, date,
                            PageRequest.of(page, size)).map(mappers::toAppointmentResDTO);
    }

    @Override
    public void changeAppointmentStatus(Long idAppointment, EStatus status) throws AppointmentNotFoundException {
        log.info("Changing appointment status");
        Appointment appointmentFound = appointmentRepository.findById(idAppointment)
                    .orElseThrow(() -> new AppointmentNotFoundException("Appointment not found"));
        appointmentFound.setStatus(status);
        appointmentRepository.save(appointmentFound);
        log.info("Appointment status changed");
    }

    @Override
    public void createService(BarberServiceDTO barberServiceDTO, Long idBarberShop) throws BarberShopNotFoundException {
        log.info("Adding service to barber shop");
        BarberShop barberShop = barberShopRepository.findById(idBarberShop)
                .orElseThrow(() -> new BarberShopNotFoundException("Barber shop not found"));
        BarberService barberService = mappers.toBarberService(barberServiceDTO);
        barberService.setBarberShop(barberShop);
        barberServiceRepository.save(barberService);
        log.info("Service added to barber shop");
    }

    @Override
    public void removeServiceFromBarberShop(Long idService) {
        log.info("Removing service");
        barberServiceRepository.deleteById(idService);
        log.info("Service removed");
    }

    @Override
    public void editService(Long idService, BarberServiceDTO barberServiceDTO) throws ServiceNotFoundException {
        log.info("Editing service");
        BarberService barberService = barberServiceRepository.findById(idService)
                .orElseThrow(() -> new ServiceNotFoundException("Service not found"));
        barberService.setServiceName(barberServiceDTO.getServiceName());
        barberService.setPrice(barberServiceDTO.getPrice());
        barberServiceRepository.save(barberService);
        log.info("Service edited");
    }

    @Override
    public Hairdresser addHairdresser(HairdresserReqDTO hairdresserDTO) {
        log.info("Adding hairdresser");
        Hairdresser save = hairdresserRepository.save(mappers.toHairdresser(hairdresserDTO));
        log.info("Hairdresser added");
        return save;
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
    public HairdresserResDTO assignHairdresserToBarberShop(HairdresserReqDTO hairdresserDTO, Long idBarberShop) throws BarberShopNotFoundException, HairdresserNotFoundException {
        log.info("Assigning hairdresser to barber shop");
        Hairdresser hairdresser = addHairdresser(hairdresserDTO);
        BarberShop barberShop = barberShopRepository.findById(idBarberShop)
                .orElseThrow(() -> new BarberShopNotFoundException("Barber shop not found"));
        barberShop.setCapacity(barberShop.getCapacity() + 1);
        hairdresser.setBarberShop(barberShopRepository.save(barberShop));
        Hairdresser save = hairdresserRepository.save(hairdresser);
        log.info("Hairdresser assigned to barber shop");
        return mappers.toHairdresserResDTO(save);
    }

    @Override
    public void removeHairdresserFromBarberShop(Long idHairdresser, Long idBarberShop) throws BarberShopNotFoundException, HairdresserNotFoundException {
        log.info("Removing hairdresser from barber shop");
        BarberShop barberShop = barberShopRepository.findById(idBarberShop)
                .orElseThrow(() -> new BarberShopNotFoundException("Barber shop not found"));
        Hairdresser hairdresser = hairdresserRepository.findById(idHairdresser)
                .orElseThrow(() -> new HairdresserNotFoundException("Hairdresser not found"));
        if (!barberShop.getIdBarberShop().equals(hairdresser.getBarberShop().getIdBarberShop())) {
            throw new HairdresserNotFoundException("Hairdresser not found in this barber shop");
        }else {
            barberShop.setCapacity(barberShop.getCapacity() - 1);
            hairdresserRepository.delete(hairdresser);
            log.info("Hairdresser removed from barber shop");
        }
    }

    @Override
    public Page<HairdresserResDTO> getHairdressersByBarberShop(Long idBarberShop, int page, int size) {
        return hairdresserRepository.findAllByBarberShopIdBarberShop(idBarberShop, PageRequest.of(page, size))
                .map(mappers::toHairdresserResDTO);
    }
}
