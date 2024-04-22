package com.barbertime.barbertime_backend.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "holidays")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Holiday {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idHoliday;

    @NotEmpty
    private LocalDate holidayDate;

    private String reason;

    @ManyToOne
    private BarberShop barberShop;
}