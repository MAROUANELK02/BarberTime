package com.barbertime.barbertime_backend.dtos;

import com.barbertime.barbertime_backend.entities.BarberShop;
import com.barbertime.barbertime_backend.entities.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ReviewDTO {
    private Long idReview;
    private String comment;
    private int rating;
    private Customer customer;
    private BarberShop barberShop;
}
