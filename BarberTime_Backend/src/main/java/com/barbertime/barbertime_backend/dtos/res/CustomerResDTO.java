package com.barbertime.barbertime_backend.dtos.res;

import com.barbertime.barbertime_backend.dtos.RoleDTO;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class CustomerResDTO extends UserResDTO {

    public CustomerResDTO(Long id, String firstName, String lastName, String phoneNumber,
                          String email, String username, List<RoleDTO> roleDTO) {
        super(id, firstName, lastName, phoneNumber, email, username, roleDTO);
    }

}
