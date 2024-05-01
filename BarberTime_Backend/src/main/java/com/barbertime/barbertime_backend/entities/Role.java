package com.barbertime.barbertime_backend.entities;

import com.barbertime.barbertime_backend.enums.ERole;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "roles")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Role {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRole;

    @Enumerated(EnumType.STRING)
    private ERole roleName;
}