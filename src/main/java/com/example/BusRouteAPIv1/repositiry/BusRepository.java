package com.example.BusRouteAPIv1.repositiry;

import com.example.BusRouteAPIv1.model.Bus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusRepository extends JpaRepository<Bus, Long> {
}
