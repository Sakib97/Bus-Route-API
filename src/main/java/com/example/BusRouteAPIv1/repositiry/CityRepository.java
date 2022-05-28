package com.example.BusRouteAPIv1.repositiry;

import com.example.BusRouteAPIv1.model.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Long> {
}
