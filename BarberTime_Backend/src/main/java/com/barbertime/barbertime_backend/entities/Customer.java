package com.barbertime.barbertime_backend.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "customers")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Customer {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCustomer;
    private String firstName;
    private String lastName;
    private String telNumber;
}
