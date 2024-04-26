package com.barbertime.barbertime_backend.web;

import com.barbertime.barbertime_backend.dtos.AppointmentDTO;
import com.barbertime.barbertime_backend.dtos.BarberShopDTO;
import com.barbertime.barbertime_backend.dtos.HairdresserDTO;
import com.barbertime.barbertime_backend.dtos.OwnerDTO;
import com.barbertime.barbertime_backend.enums.EStatus;
import com.barbertime.barbertime_backend.exceptions.*;
import com.barbertime.barbertime_backend.services.OwnerService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.management.ServiceNotFoundException;
import java.time.LocalDate;

@RestController
@AllArgsConstructor
@RequestMapping("/api/owner")
public class OwnerRestController {
    public OwnerService ownerService;

    @PostMapping("/barberShop")
    public BarberShopDTO createBarberShop(@RequestBody BarberShopDTO barberShopDTO) {
        ownerService.createBarberShop(barberShopDTO);
        return barberShopDTO;
    }

    @PatchMapping("/barberShop")
    public BarberShopDTO updateBarberShop(@RequestBody BarberShopDTO barberShopDTO) {
        ownerService.updateBarberShop(barberShopDTO);
        return barberShopDTO;
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
    public BarberShopDTO getBarberShopByOwnerId(@PathVariable(name = "idOwner") Long idOwner) {
        try {
            return ownerService.getBarberShopByOwnerId(idOwner);
        } catch (OwnerNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/barberShop/{idBarberShop}/dayOff")
    public void addDayOff(@PathVariable(name = "idBarberShop") Long idBarberShop, @RequestBody LocalDate dayOff) {
        try {
            ownerService.addDayOff(dayOff, idBarberShop);
        } catch (BarberShopNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/barberShop/{idBarberShop}/dayOff")
    public void removeDayOff(@PathVariable(name = "idBarberShop") Long idBarberShop, @RequestBody LocalDate dayOff) {
        try {
            ownerService.removeDayOff(dayOff, idBarberShop);
        } catch (BarberShopNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @PatchMapping("/barberShop/{idBarberShop}/dayOff")
    public void updateDayOff(@PathVariable(name = "idBarberShop") Long idBarberShop, @RequestBody LocalDate newDayOff) {
        try {
            ownerService.updateDayOff(newDayOff, idBarberShop);
        } catch (BarberShopNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/barberShop/{barberId}/appointments")
    public Page<AppointmentDTO> getAppointmentsAllByBarberShop(@PathVariable(name = "barberId") Long barberId, @RequestParam int page, @RequestParam int size) {
        return ownerService.getAppointmentsAllByBarberShop(barberId, page, size);
    }

    @GetMapping("/barberShop/{barberId}/appointmentsPerDate")
    public Page<AppointmentDTO> getAppointmentsByBarberShopAndDate(@PathVariable(name = "barberId") Long barberId, @RequestBody LocalDate date, @RequestParam int page, @RequestParam int size) {
        return ownerService.getAppointmentsByBarberShopAndDate(barberId, date, page, size);
    }

    @PatchMapping("/appointment/{idAppointment}/status")
    public void changeAppointmentStatus(@PathVariable(name = "idAppointment") Long idAppointment, @RequestBody EStatus status) {
        try {
            ownerService.changeAppointmentStatus(idAppointment, status);
        } catch (AppointmentNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/barberShop/{barberShopId}/service/{idService}")
    public void addService(@PathVariable(name = "barberShopId") Long barberShopId, @PathVariable(name = "idService") Long idService) {
        try {
            ownerService.addService(barberShopId, idService);
        } catch (BarberShopNotFoundException | BarberShopServiceNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/barberShop/{barberShopId}/service/{idService}")
    public void removeService(@PathVariable(name = "barberShopId") Long barberShopId, @PathVariable(name = "idService") Long idService) {
        try {
            ownerService.removeService(barberShopId, idService);
        } catch (BarberShopNotFoundException | BarberShopServiceNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/hairdresser")
    public void addHairdresser(@RequestBody HairdresserDTO hairdresserDTO) {
        ownerService.addHairdresser(hairdresserDTO);
    }

    @PatchMapping("/hairdresser")
    public void updateHairdresser(@RequestBody HairdresserDTO hairdresserDTO) {
        ownerService.updateHairdresser(hairdresserDTO);
    }

    @PostMapping("/hairdresser/{idHairdresser}/barberShop/{idBarberShop}")
    public void assignHairdresserToBarberShop(@PathVariable(name = "idHairdresser") Long idHairdresser, @PathVariable(name = "idBarberShop") Long idBarberShop) {
        try {
            ownerService.assignHairdresserToBarberShop(idHairdresser, idBarberShop);
        } catch (BarberShopNotFoundException | HairdresserNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/hairdresser/{idHairdresser}/barberShop/{idBarberShop}")
    public void removeHairdresserFromBarberShop(@PathVariable(name = "idHairdresser") Long idHairdresser, @PathVariable(name = "idBarberShop") Long idBarberShop) {
        try {
            ownerService.removeHairdresserFromBarberShop(idHairdresser, idBarberShop);
        } catch (BarberShopNotFoundException | HairdresserNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
