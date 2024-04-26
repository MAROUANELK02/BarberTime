package com.barbertime.barbertime_backend.dtos.res;

import com.barbertime.barbertime_backend.dtos.RoleDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerResDTO extends UserResDTO {
    private List<AppointmentResDTO> appointmentsDTO;

    public CustomerResDTO(Long id, String firstName, String lastName, String phoneNumber,
                          String email, String username, RoleDTO roleDTO) {
        super(id, firstName, lastName, phoneNumber, email, username, roleDTO);
    }

    public CustomerResDTO(Long id, String firstName, String lastName, String phoneNumber,
                          String email, String username, RoleDTO roleDTO,
                          List<AppointmentResDTO> appointmentsDTO) {
        super(id, firstName, lastName, phoneNumber, email, username, roleDTO);
        this.appointmentsDTO = appointmentsDTO;
    }
}
