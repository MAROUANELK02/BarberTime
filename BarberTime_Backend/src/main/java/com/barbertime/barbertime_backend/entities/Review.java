package com.barbertime.barbertime_backend.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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

    @NotBlank
    private String comment;

    @Min(1)
    @Max(5)
    private int rating;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private BarberShop barberShop;
}
