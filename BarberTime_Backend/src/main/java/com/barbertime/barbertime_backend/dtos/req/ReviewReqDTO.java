package com.barbertime.barbertime_backend.dtos.req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ReviewReqDTO {
    private Long idReview;
    private String comment;
    private int rating;
    private CustomerReqDTO customerDTO;
    private BarberShopReqDTO barberShopDTO;
}
