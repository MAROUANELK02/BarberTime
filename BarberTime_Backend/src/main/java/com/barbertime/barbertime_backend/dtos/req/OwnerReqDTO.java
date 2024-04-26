package com.barbertime.barbertime_backend.dtos.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OwnerReqDTO extends UserReqDTO {
    private String cin;

    public OwnerReqDTO(Long idUser, String firstName, String lastName, String telNumber, String email,
                       String username, String password) {
        super(idUser, firstName, lastName, telNumber, email, username, password);
    }

    public OwnerReqDTO(Long idUser, String firstName, String lastName, String telNumber, String email,
                       String username, String password, String cin) {
        super(idUser, firstName, lastName, telNumber, email, username, password);
        this.cin = cin;
    }
}