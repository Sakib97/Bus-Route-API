package com.example.BusRouteAPIv1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddStopageRequest {
    private String stopage;
    private Long categoryId;
    private Long cityId;
}
