package com.barbertime.barbertime_backend.entities;

import com.barbertime.barbertime_backend.enums.EService;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "services")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Service {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idService;

    @Enumerated(EnumType.STRING)
    private EService serviceName;

    private double price;
}