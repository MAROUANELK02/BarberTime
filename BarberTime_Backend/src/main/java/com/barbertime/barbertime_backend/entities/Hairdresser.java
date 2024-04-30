package com.barbertime.barbertime_backend.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "hairdressers")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Hairdresser {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idHairdresser;

    @NotBlank
    @Size(min = 4, max = 40)
    private String firstName;

    @NotBlank
    @Size(min = 4, max = 40)
    private String lastName;

    @ManyToOne(fetch = FetchType.EAGER)
    private BarberShop barberShop;
}
