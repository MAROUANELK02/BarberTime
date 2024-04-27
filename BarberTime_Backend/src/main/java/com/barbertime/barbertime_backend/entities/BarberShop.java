package com.barbertime.barbertime_backend.entities;

import com.barbertime.barbertime_backend.enums.ENeighborhood;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Time;
import java.time.LocalTime;
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

    @Size(min = 10, max = 20)
    @Column(unique = true)
    private String phone;

    @Enumerated(EnumType.STRING)
    private ENeighborhood neighborhood;

    @NotEmpty
    @Column(unique = true)
    private String authorizationNumber;

    private int capacity;

    private String dayOff;

    @NotEmpty
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private LocalTime startTime;

    @NotEmpty
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private LocalTime endTime;

    private int ratings;

    @OneToMany(mappedBy = "barberShop", fetch = FetchType.LAZY)
    private List<Appointment> appointments;

    @OneToOne
    private Owner owner;

    @OneToMany(mappedBy = "barberShop", fetch = FetchType.LAZY)
    private List<Hairdresser> hairdressers;

    @OneToMany(mappedBy = "barberShop", fetch = FetchType.LAZY)
    private List<Holiday> holidays;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<BarberService> barberServices;

    @OneToMany(mappedBy = "barberShop", fetch = FetchType.LAZY)
    private List<Review> reviews;
}
