package com.barbertime.barbertime_backend.dtos.res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ReviewResDTO {
    private Long idReview;
    private String comment;
    private int rating;
    private CustomerResDTO customerDTO;
    private BarberShopResDTO barberShopDTO;
}
