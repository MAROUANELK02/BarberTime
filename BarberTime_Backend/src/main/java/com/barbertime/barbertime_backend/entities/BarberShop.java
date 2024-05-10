package com.barbertime.barbertime_backend.entities;

import com.barbertime.barbertime_backend.enums.ENeighborhood;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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

    @NotBlank
    @Column(unique = true)
    @Size(min = 4, max = 40)
    private String name;

    @NotBlank
    @Column(unique = true)
    @Size(min = 4, max = 100)
    private String address;

    @Size(min = 10, max = 20)
    @Column(unique = true)
    private String phone;

    @Enumerated(EnumType.STRING)
    private ENeighborhood neighborhood;

    @NotBlank
    @Column(unique = true)
    private String authorizationNumber;

    private int capacity;

    private String dayOff;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private LocalTime startTime;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private LocalTime endTime;

    private int ratings;
    private int totalVoters;

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

    @OneToMany(mappedBy = "barberShop", fetch = FetchType.EAGER)
    private List<FileData> images;
}
