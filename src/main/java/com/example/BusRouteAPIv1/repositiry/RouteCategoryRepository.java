package com.example.BusRouteAPIv1.repositiry;

import com.example.BusRouteAPIv1.model.RouteCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RouteCategoryRepository extends JpaRepository<RouteCategory, Long> {
}
