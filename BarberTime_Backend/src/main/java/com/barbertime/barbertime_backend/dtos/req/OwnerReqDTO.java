package com.barbertime.barbertime_backend.dtos.req;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OwnerReqDTO extends UserReqDTO {
    private String cin;

    public OwnerReqDTO(String firstName, String lastName, String telNumber, String email,
                       String username, String password, String cin) {
        super(firstName, lastName, telNumber, email, username, password);
        this.cin = cin;
    }
}