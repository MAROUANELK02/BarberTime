package com.barbertime.barbertime_backend.entities;

import com.barbertime.barbertime_backend.enums.ENeighborhood;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "barbershops")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class BarberShop {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBarber;
    private String name;
    private String address;
    @Enumerated(EnumType.STRING)
    private ENeighborhood neighborhood;
    private String authorizationNumber;
    private int capacity;
    private LocalDate dayOff;
}
