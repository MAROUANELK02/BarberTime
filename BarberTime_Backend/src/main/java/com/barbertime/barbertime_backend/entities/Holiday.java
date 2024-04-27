package com.barbertime.barbertime_backend.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

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
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date holidayDate;

    private String reason;

    @ManyToOne
    private BarberShop barberShop;
}