package com.barbertime.barbertime_backend.entities;

import jakarta.persistence.*;
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
    private String comment;
    private int rating;
    @ManyToOne
    private Customer customer;
    @ManyToOne
    private BarberShop barberShop;
}
