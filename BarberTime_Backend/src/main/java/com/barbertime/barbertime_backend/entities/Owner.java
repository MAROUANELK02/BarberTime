package com.barbertime.barbertime_backend.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "owners")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Owner extends User {
    @NotEmpty
    @Size(min = 4)
    @Column(unique = true)
    private String cin;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idBarberShop")
    private BarberShop barberShop;

    public Owner(String cin) {
        this.cin = cin;
    }
}