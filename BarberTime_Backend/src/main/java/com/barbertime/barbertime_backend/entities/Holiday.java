package com.barbertime.barbertime_backend.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate holidayDate;

    private String reason;

    @ManyToOne
    private BarberShop barberShop;
}