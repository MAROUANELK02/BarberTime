package com.barbertime.barbertime_backend.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "owners")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Owner extends User {
    @OneToOne(mappedBy = "owner", fetch = FetchType.LAZY)
    private BarberShop barberShop;
}