package com.barbertime.barbertime_backend.dtos.res;

import com.barbertime.barbertime_backend.dtos.RoleDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OwnerResDTO extends UserResDTO {
    private String cin;

    public OwnerResDTO(Long idUser, String firstName, String lastName, String telNumber, String email,
                       String username, RoleDTO roleDTO, String cin) {
        super(idUser, firstName, lastName, telNumber, email, username, roleDTO);
        this.cin = cin;
    }

    public OwnerResDTO(Long idUser, String firstName, String lastName, String telNumber, String email,
                       String username, RoleDTO roleDTO) {
        super(idUser, firstName, lastName, telNumber, email, username, roleDTO);
    }
}