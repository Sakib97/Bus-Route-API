package com.example.BusRouteAPIv1.service;

import com.example.BusRouteAPIv1.dto.AddRouteRequest;
import com.example.BusRouteAPIv1.dto.AddStopageRequest;
import com.example.BusRouteAPIv1.dto.DuplicateRouteRow;
import com.example.BusRouteAPIv1.exception.EmptyFieldException;
import com.example.BusRouteAPIv1.exception.GeneralUsageException;
import com.example.BusRouteAPIv1.exception.WhiteSpaceException;
import com.example.BusRouteAPIv1.model.*;
import com.example.BusRouteAPIv1.repositiry.BusRepository;
import com.example.BusRouteAPIv1.repositiry.RouteCategoryRepository;
import com.example.BusRouteAPIv1.repositiry.RouteRepository;
import com.example.BusRouteAPIv1.repositiry.StopageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.Tuple;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RouteService {
    private final RouteRepository routeRepository;
    private final BusRepository busRepository;
    private final RouteCategoryRepository categoryRepository;
    private final StopageRepository stopageRepository;

    public Route addRoute(AddRouteRequest routeRequest) throws GeneralUsageException {
        // check if Route category, stopage, bus exists or not as they will be foreign keys to Route table
        Long routeCategoryId = routeRequest.getCategoryId();
        Long stopageId = routeRequest.getStopageId();
        Long busId = routeRequest.getBus();

        RouteCategory category = categoryRepository.findById(routeCategoryId).orElseThrow(
                ()-> new GeneralUsageException("Invalid Route Category !")
        );
        Stopage stopage = stopageRepository.findById(stopageId).orElseThrow(
                ()-> new GeneralUsageException("Invalid Stopage !")
        );
        Bus bus = busRepository.findById(busId).orElseThrow(
                ()-> new GeneralUsageException("Invalid Bus !")
        );

        Tuple duplicateRouteRow = routeRepository.findDuplicateRouteRow(routeRequest.getBus(), routeRequest.getCategoryId(), routeRequest.getStopageId());
        if (duplicateRouteRow!= null){
            throw new GeneralUsageException("These data already exists in the database !");
        }

        // Example of mapping a tuple list's elements to a DTO
        //https://stackoverflow.com/questions/64762080/how-to-map-sql-native-query-result-into-dto-in-spring-jpa-repository
        //List<Tuple> stockTotalTuples = stockRepository.findStocktotal();
        //
        //    List<StockTotalResponseDto> stockTotalDto = stockTotalTuples.stream()
        //            .map(t -> new StockTotalResponseDto(
        //                    t.get(0, String.class),
        //                    t.get(1, String.class),
        //                    t.get(2, BigInteger.class)
        //                    ))
        //            .collect(Collectors.toList());

//        Tuple demo = routeRepository.demoQueryMethod(1L);
//        BigInteger x = demo.get(0, BigInteger.class);
//        BigInteger y = demo.get(1, BigInteger.class);
//        BigInteger z = demo.get(2, BigInteger.class);


        Route route = new Route();
        route.setCategory_id(category.getId());
        route.setStopage_id(stopage.getId());
        route.setBus(bus.getId());

        routeRepository.save(route);
        return route;
    }

    public List<Route> getAllRoutes() {
        return routeRepository.findAll();
    }

    public Route getRouteById(Long id) throws GeneralUsageException {
        return routeRepository.findById(id).orElseThrow(
                ()-> new GeneralUsageException("This route Doesn't Exist")
        );
    }

    public Route editRouteById(Long id, AddRouteRequest routeRequest) throws GeneralUsageException, EmptyFieldException, WhiteSpaceException {
        Route route= routeRepository.findById(id).orElseThrow(
                ()-> new GeneralUsageException("This Route Doesn't Exist")
        );

        Long routeCategoryId = routeRequest.getCategoryId();
        Long busId = routeRequest.getBus();
        Long stopageId = routeRequest.getStopageId();

        RouteCategory category = categoryRepository.findById(routeCategoryId).orElseThrow(
                ()-> new GeneralUsageException("Invalid Route Category !")
        );
        Bus bus = busRepository.findById(busId).orElseThrow(
                ()-> new GeneralUsageException("Invalid Bus !")
        );
        Stopage stopage = stopageRepository.findById(stopageId).orElseThrow(
                ()-> new GeneralUsageException("Invalid Stopage !")
        );

        Tuple duplicateRouteRow = routeRepository.findDuplicateRouteRow(routeRequest.getBus(), routeRequest.getCategoryId(), routeRequest.getStopageId());
        if (duplicateRouteRow!= null){
            throw new GeneralUsageException("These data already exists in the database !");
        }

        route.setCategory_id(category.getId());
        route.setStopage_id(stopage.getId());
        route.setBus(bus.getId());

        routeRepository.save(route);

        return route;
    }

    // get buses of an specific stopage
    public List<String> getBusNamesOfStopage(Long stopageId, Long routeCategoryId) throws GeneralUsageException { // user will provide the stopage and if that stopage is for interCity or IntraCity
        // check if the stopage is in database
        Stopage stopage = stopageRepository.findById(stopageId).orElseThrow(
                ()-> new GeneralUsageException("Invalid Stopage !")
        );
        // check if the category is in database
        RouteCategory category = categoryRepository.findById(routeCategoryId).orElseThrow(
                ()-> new GeneralUsageException("Invalid Route Category !")
        );

        Long cityIdOfStopage = stopage.getCity_id();
        List<Tuple> busNamesOfStopage = routeRepository.findBusNamesOfStopage(stopageId, cityIdOfStopage, routeCategoryId);
        if (busNamesOfStopage == null || busNamesOfStopage.isEmpty()) throw new GeneralUsageException("No bus in the database !");

        List<String> buses = busNamesOfStopage.stream().map(
                tuple -> new String(
                        tuple.get(0, String.class)
                )
        ).collect(Collectors.toList());

        return buses;
    }
}
