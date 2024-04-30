package com.barbertime.barbertime_backend.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "admins")
@NoArgsConstructor
@Getter
@Setter
public class Admin extends User{
    public Admin(Long userId, String firstName, String lastName, String telNumber, String email,
                    String username, String password) {
        super(userId, firstName, lastName, telNumber, email, username, password);
    }
}
