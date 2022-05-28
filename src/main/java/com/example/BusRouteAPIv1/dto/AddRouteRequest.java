package com.example.BusRouteAPIv1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddRouteRequest {
    private Long bus;
    private Long stopageId;
    private Long categoryId;

}
