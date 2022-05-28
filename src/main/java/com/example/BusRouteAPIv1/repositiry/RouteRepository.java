package com.example.BusRouteAPIv1.repositiry;

import com.example.BusRouteAPIv1.dto.AddRouteRequest;
import com.example.BusRouteAPIv1.dto.DuplicateRouteRow;
import com.example.BusRouteAPIv1.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.Tuple;
import java.util.List;

public interface RouteRepository extends JpaRepository<Route, Long> {
//    Query to prevent from entering data that creates a duplicate row
    @Query(value ="select bus,category_id,stopage_id, count(*) from route " +
            "where bus = ?1 and category_id = ?2 and stopage_id = ?3 " +
            "group by bus,category_id,stopage_id",  nativeQuery = true)
    Tuple findDuplicateRouteRow(Long busId, Long categoryId, Long stopageId);

//    @Query(value ="select r.bus,r.category_id,r.stopage_id from route r where r.id = ?1",  nativeQuery = true)
////    Route demoQueryMethod(Long id);
////    DuplicateRouteRow demoQueryMethod(Long id);
////    List<Object[]> demoQueryMethod(Long id);
////    Object demoQueryMethod(Long id);
//    Tuple demoQueryMethod(Long id);

    @Query(value ="select bus from bus where id in (\n" +
            "    select r.bus\n" +
            "    from route r,\n" +
            "         stopage s\n" +
            "    where r.stopage_id = s.id\n" +
            "      and r.stopage_id = ?1\n" +
            "      and s.city_id = ?2\n" +
            "      and r.category_id = ?3\n" +
            ")",  nativeQuery = true)
    List<Tuple> findBusNamesOfStopage(Long stopageId, Long cityId, Long categoryId);


}
