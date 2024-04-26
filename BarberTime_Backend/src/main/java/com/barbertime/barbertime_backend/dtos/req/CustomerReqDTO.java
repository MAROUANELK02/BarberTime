package com.barbertime.barbertime_backend.dtos.req;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CustomerReqDTO extends UserReqDTO {
    public CustomerReqDTO(Long idUser, String firstName, String lastName, String telNumber, String email,
                          String username, String password) {
        super(idUser, firstName, lastName, telNumber, email, username, password);
    }
}
