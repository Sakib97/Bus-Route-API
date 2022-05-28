package com.example.BusRouteAPIv1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DuplicateRouteRow {
//    private Long id;
    private Long bus;
    private Long stopage_id;
    private Long category_id;
//    private Long count;

}

