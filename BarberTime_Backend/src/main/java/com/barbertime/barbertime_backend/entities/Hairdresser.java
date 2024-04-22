package com.barbertime.barbertime_backend.entities;

import jakarta.persistence.*;
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
    private String firstName;
    private String lastName;
}
