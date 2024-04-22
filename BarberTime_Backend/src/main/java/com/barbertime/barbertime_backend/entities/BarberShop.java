package com.barbertime.barbertime_backend.entities;

import com.barbertime.barbertime_backend.enums.ENeighborhood;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

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
    private Long idBarberShop;

    @NotEmpty
    @Size(min = 4, max = 40)
    @Column(unique = true)
    private String name;

    @NotEmpty
    @Size(min = 4, max = 100)
    @Column(unique = true)
    private String address;

    @Enumerated(EnumType.STRING)
    private ENeighborhood neighborhood;

    @NotEmpty
    @Column(unique = true)
    private String authorizationNumber;

    private int capacity;

    private LocalDate dayOff;

    @OneToMany(mappedBy = "barberShop", fetch = FetchType.LAZY)
    private List<Appointment> appointments;

    @OneToOne(mappedBy = "barberShop")
    private Owner owner;

    @OneToMany(mappedBy = "barberShop", fetch = FetchType.LAZY)
    private List<Hairdresser> hairdressers;

    @OneToMany(mappedBy = "barberShop", fetch = FetchType.LAZY)
    private List<Holiday> holidays;

    @OneToMany(mappedBy = "barberShop", fetch = FetchType.LAZY)
    private List<Service> services;
}
