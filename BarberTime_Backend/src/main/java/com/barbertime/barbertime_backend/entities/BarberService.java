package com.barbertime.barbertime_backend.entities;

import com.barbertime.barbertime_backend.enums.EService;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "services")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class BarberService {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idService;

    @Enumerated(EnumType.STRING)
    private EService serviceName;

    @NotNull
    private double price;
}