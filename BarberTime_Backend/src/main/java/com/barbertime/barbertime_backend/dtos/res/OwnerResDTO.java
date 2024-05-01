package com.barbertime.barbertime_backend.dtos.res;

import com.barbertime.barbertime_backend.dtos.RoleDTO;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OwnerResDTO extends UserResDTO {
    private String cin;

    public OwnerResDTO(Long idUser, String firstName, String lastName, String telNumber, String email,
                       String username, RoleDTO roleDTO, String cin) {
        super(idUser, firstName, lastName, telNumber, email, username, roleDTO);
        this.cin = cin;
    }
}