package com.barbertime.barbertime_backend.dtos.res;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FileDataResDTO {
    private Long id;
    private String name;
    private String type;
}
