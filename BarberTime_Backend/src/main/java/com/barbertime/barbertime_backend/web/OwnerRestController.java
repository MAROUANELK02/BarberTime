package com.barbertime.barbertime_backend.web;

import com.barbertime.barbertime_backend.dtos.req.BarberShopReqDTO;
import com.barbertime.barbertime_backend.dtos.req.HairdresserReqDTO;
import com.barbertime.barbertime_backend.dtos.req.HolidayRangeReqDTO;
import com.barbertime.barbertime_backend.dtos.res.AppointmentResDTO;
import com.barbertime.barbertime_backend.dtos.res.BarberShopResDTO;
import com.barbertime.barbertime_backend.dtos.res.HairdresserResDTO;
import com.barbertime.barbertime_backend.dtos.res.HolidayResDTO;
import com.barbertime.barbertime_backend.enums.EStatus;
import com.barbertime.barbertime_backend.exceptions.*;
import com.barbertime.barbertime_backend.mappers.Mappers;
import com.barbertime.barbertime_backend.services.OwnerService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api/owner")
public class OwnerRestController {
    private OwnerService ownerService;

    @PostMapping("/barberShop")
    public BarberShopResDTO createBarberShop(@RequestBody BarberShopReqDTO barberShopDTO) {
        return ownerService.createBarberShop(barberShopDTO);
    }

    @PatchMapping("/barberShop/{idBarberShop}")
    public BarberShopResDTO updateBarberShop(@PathVariable(name = "idBarberShop") Long idBarberShop,
                                             @RequestBody BarberShopReqDTO barberShopDTO) {
        try {
            return ownerService.updateBarberShop(idBarberShop, barberShopDTO);
        } catch (BarberShopNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/barberShop/{idBarberShop}")
    public void deleteBarberShop(@PathVariable(name = "idBarberShop") Long idBarberShop) {
        try {
            ownerService.deleteBarberShop(idBarberShop);
        } catch (BarberShopNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/barberShop/{idOwner}")
    public BarberShopResDTO getBarberShopByOwnerId(@PathVariable(name = "idOwner") Long idOwner) {
        try {
            return ownerService.getBarberShopByOwnerId(idOwner);
        } catch (OwnerNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/barberShop/{idBarberShop}/dayOff")
    public void addDayOff(@PathVariable(name = "idBarberShop") Long idBarberShop,
                          @RequestParam String dayOff) {
        try {
            ownerService.addDayOff(dayOff, idBarberShop);
        } catch (BarberShopNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/barberShop/{idBarberShop}/dayOff")
    public void removeDayOff(@PathVariable(name = "idBarberShop") Long idBarberShop) {
        try {
            ownerService.removeDayOff(idBarberShop);
        } catch (BarberShopNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @PatchMapping("/barberShop/{idBarberShop}/dayOff")
    public void updateDayOff(@PathVariable(name = "idBarberShop") Long idBarberShop,
                             @RequestParam String newDayOff) {
        try {
            ownerService.updateDayOff(newDayOff, idBarberShop);
        } catch (BarberShopNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/barberShop/{barberId}/appointments")
    public Page<AppointmentResDTO> getAppointmentsAllByBarberShop(@PathVariable(name = "barberId") Long barberId,
                                                                  @RequestParam(defaultValue = "0") int page,
                                                                  @RequestParam(defaultValue = "3") int size) {
        return ownerService.getAppointmentsAllByBarberShop(barberId, page, size);
    }

    @GetMapping("/barberShop/{barberId}/appointmentsPerDate")
    public Page<AppointmentResDTO> getAppointmentsByBarberShopAndDate(@PathVariable(name = "barberId") Long barberId,
                                                                      @RequestParam("date") Optional<LocalDate> date,
                                                                      @RequestParam(defaultValue = "0") int page,
                                                                      @RequestParam(defaultValue = "3") int size) {
        LocalDate dateValue = date.orElse(LocalDate.now());
        return ownerService.getAppointmentsByBarberShopAndDate(barberId, dateValue, page, size);
    }

    @PatchMapping("/appointment/{idAppointment}/status")
    public void changeAppointmentStatus(@PathVariable(name = "idAppointment") Long idAppointment,
                                        @RequestParam(defaultValue = "COMPLETED") EStatus status) {
        try {
            ownerService.changeAppointmentStatus(idAppointment, status);
        } catch (AppointmentNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/barberShop/{barberShopId}/service/{idService}")
    public void assignServiceToBarberShop(@PathVariable(name = "barberShopId") Long barberShopId,
                                          @PathVariable(name = "idService") Long idService) {
        try {
            ownerService.assignServiceToBarberShop(barberShopId, idService);
        } catch (BarberShopNotFoundException | BarberShopServiceNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/barberShop/{barberShopId}/service/{idService}")
    public void removeServiceFromBarberShop(@PathVariable(name = "barberShopId") Long barberShopId,
                                            @PathVariable(name = "idService") Long idService) {
        try {
            ownerService.removeServiceFromBarberShop(barberShopId, idService);
        } catch (BarberShopNotFoundException | BarberShopServiceNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @PatchMapping("/hairdresser/{idHairdresser}")
    public HairdresserResDTO updateHairdresser(@PathVariable(name = "idHairdresser") Long idHairdresser,
                                               @RequestBody HairdresserReqDTO hairdresserDTO) {
        try {
            return ownerService.updateHairdresser(idHairdresser, hairdresserDTO);
        } catch (HairdresserNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/hairdresser/barberShop/{idBarberShop}")
    public HairdresserResDTO assignHairdresserToBarberShop(@RequestBody HairdresserReqDTO hairdresserReqDTO,
                                                           @PathVariable(name = "idBarberShop") Long idBarberShop) {
        try {
            return ownerService.assignHairdresserToBarberShop(hairdresserReqDTO, idBarberShop);
        } catch (BarberShopNotFoundException | HairdresserNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/hairdresser/{idHairdresser}/barberShop/{idBarberShop}")
    public void removeHairdresserFromBarberShop(@PathVariable(name = "idHairdresser") Long idHairdresser,
                                                @PathVariable(name = "idBarberShop") Long idBarberShop) {
        try {
            ownerService.removeHairdresserFromBarberShop(idHairdresser, idBarberShop);
        } catch (BarberShopNotFoundException | HairdresserNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/holiday/{idBarberShop}")
    public List<HolidayResDTO> addHolidayRangeToBarberShop(@RequestBody HolidayRangeReqDTO holidayReqDTO,
                                                      @PathVariable(name = "idBarberShop") Long idBarberShop) {
        try {
            return ownerService.addHolidayRangeToBarberShop(holidayReqDTO, idBarberShop);
        } catch (BarberShopNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/holiday/{idHoliday}/barberShop/{idBarberShop}")
    public void removeHolidayFromBarberShops(@PathVariable(name = "idHoliday") Long idHoliday,
                                             @PathVariable(name = "idBarberShop") Long idBarberShop) {
        try {
            ownerService.removeHolidayFromBarberShops(idHoliday, idBarberShop);
        } catch (HolidayNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
