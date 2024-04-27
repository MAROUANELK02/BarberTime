package com.barbertime.barbertime_backend.web;

import com.barbertime.barbertime_backend.dtos.req.BarberShopReqDTO;
import com.barbertime.barbertime_backend.dtos.req.HairdresserReqDTO;
import com.barbertime.barbertime_backend.dtos.res.AppointmentResDTO;
import com.barbertime.barbertime_backend.dtos.res.BarberShopResDTO;
import com.barbertime.barbertime_backend.enums.EStatus;
import com.barbertime.barbertime_backend.exceptions.*;
import com.barbertime.barbertime_backend.mappers.Mappers;
import com.barbertime.barbertime_backend.services.OwnerService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;

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
    public BarberShopResDTO updateBarberShop(@RequestParam(name = "idBarberShop") Long idBarberShop,
                                             @RequestBody BarberShopReqDTO barberShopDTO) {
        return ownerService.updateBarberShop(idBarberShop, barberShopDTO);
    }

    @DeleteMapping("/barberShop/{idBarberShop}")
    public void deleteBarberShop(@PathVariable Long idBarberShop) {
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
                          @RequestBody String dayOff) {
        try {
            ownerService.addDayOff(dayOff, idBarberShop);
        } catch (BarberShopNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/barberShop/{idBarberShop}")
    public void removeDayOff(@PathVariable(name = "idBarberShop") Long idBarberShop) {
        try {
            ownerService.removeDayOff(idBarberShop);
        } catch (BarberShopNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @PatchMapping("/barberShop/{idBarberShop}/dayOff")
    public void updateDayOff(@PathVariable(name = "idBarberShop") Long idBarberShop,
                             @RequestBody String newDayOff) {
        try {
            ownerService.updateDayOff(newDayOff, idBarberShop);
        } catch (BarberShopNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/barberShop/{barberId}/appointments")
    public Page<AppointmentResDTO> getAppointmentsAllByBarberShop(@PathVariable(name = "barberId") Long barberId,
                                                                  @RequestParam int page,
                                                                  @RequestParam int size) {
        return ownerService.getAppointmentsAllByBarberShop(barberId, page, size);
    }

    @GetMapping("/barberShop/{barberId}/appointmentsPerDate")
    public Page<AppointmentResDTO> getAppointmentsByBarberShopAndDate(@PathVariable(name = "barberId") Long barberId,
                                                                      @RequestBody Date date,
                                                                      @RequestParam int page,
                                                                      @RequestParam int size) {
        return ownerService.getAppointmentsByBarberShopAndDate(barberId, date, page, size);
    }

    @PatchMapping("/appointment/{idAppointment}/status")
    public void changeAppointmentStatus(@PathVariable(name = "idAppointment") Long idAppointment,
                                        @RequestBody EStatus status) {
        try {
            ownerService.changeAppointmentStatus(idAppointment, status);
        } catch (AppointmentNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/barberShop/{barberShopId}/service/{idService}")
    public void addService(@PathVariable(name = "barberShopId") Long barberShopId,
                           @PathVariable(name = "idService") Long idService) {
        try {
            ownerService.addService(barberShopId, idService);
        } catch (BarberShopNotFoundException | BarberShopServiceNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/barberShop/{barberShopId}/service/{idService}")
    public void removeService(@PathVariable(name = "barberShopId") Long barberShopId,
                              @PathVariable(name = "idService") Long idService) {
        try {
            ownerService.removeService(barberShopId, idService);
        } catch (BarberShopNotFoundException | BarberShopServiceNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/hairdresser")
    public void addHairdresser(@RequestBody HairdresserReqDTO hairdresserDTO) {
        ownerService.addHairdresser(hairdresserDTO);
    }

    @PatchMapping("/hairdresser/{idHairdresser}")
    public void updateHairdresser(@RequestParam(name = "idHairdresser") Long idHairdresser,
                                  @RequestBody HairdresserReqDTO hairdresserDTO) {
        ownerService.updateHairdresser(idHairdresser, hairdresserDTO);
    }

    @PostMapping("/hairdresser/{idHairdresser}/barberShop/{idBarberShop}")
    public void assignHairdresserToBarberShop(@PathVariable(name = "idHairdresser") Long idHairdresser,
                                              @PathVariable(name = "idBarberShop") Long idBarberShop) {
        try {
            ownerService.assignHairdresserToBarberShop(idHairdresser, idBarberShop);
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

}
