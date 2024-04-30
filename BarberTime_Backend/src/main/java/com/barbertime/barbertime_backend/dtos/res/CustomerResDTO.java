package com.barbertime.barbertime_backend.dtos.res;

import com.barbertime.barbertime_backend.dtos.RoleDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class CustomerResDTO extends UserResDTO {

    public CustomerResDTO(Long id, String firstName, String lastName, String phoneNumber,
                          String email, String username, RoleDTO roleDTO) {
        super(id, firstName, lastName, phoneNumber, email, username, roleDTO);
    }

}
