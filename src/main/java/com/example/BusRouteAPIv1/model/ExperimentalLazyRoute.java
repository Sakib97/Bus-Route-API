package com.example.BusRouteAPIv1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "lazy_route_implementation") // inefficient for further usage, won't use it
public class ExperimentalLazyRoute implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;

    @Column(nullable = false)
    private Long bus;

    @Column(nullable = false)
    private String stopage_id; // stopages will be like: 1,2,3 etc.

    @Column(nullable = false)
    private Long category_id;
}
