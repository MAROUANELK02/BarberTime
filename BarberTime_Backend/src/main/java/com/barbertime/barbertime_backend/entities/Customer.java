package com.barbertime.barbertime_backend.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "customers")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Customer extends User {

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    private List<Appointment> appointments;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    private List<Review> reviews;

    public Customer(Long userId, String firstName, String lastName, String telNumber, String email,
                    String username, String password) {
        super(userId, firstName, lastName, telNumber, email, username, password);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "User{" +
                super.toString() +
                "}, " +
                "appointments=" + appointments +
                ", reviews=" + reviews +
                '}';
    }
}
