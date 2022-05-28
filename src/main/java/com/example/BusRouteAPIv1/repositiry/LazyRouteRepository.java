package com.example.BusRouteAPIv1.repositiry;

import com.example.BusRouteAPIv1.model.ExperimentalLazyRoute;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LazyRouteRepository extends JpaRepository<ExperimentalLazyRoute, Long> {
}
