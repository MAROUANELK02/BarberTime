package com.barbertime.barbertime_backend.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class Review {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReview;

    @NotEmpty
    private String comment;

    @Size(min = 1, max = 5)
    private int rating;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private BarberShop barberShop;
}
