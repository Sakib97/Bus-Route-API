package com.example.BusRouteAPIv1.repositiry;

import com.example.BusRouteAPIv1.model.Stopage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StopageRepository extends JpaRepository<Stopage, Long> {
    @Query(value ="select * from stopage where stopage.stopage ilike ?1",  nativeQuery = true)
    Stopage findDuplicateStopageRow(String stopage);
}
