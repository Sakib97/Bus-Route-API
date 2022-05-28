package com.example.BusRouteAPIv1.resource;

import com.example.BusRouteAPIv1.dto.AddRouteRequest;
import com.example.BusRouteAPIv1.dto.AddStopageRequest;
import com.example.BusRouteAPIv1.dto.BusOfStopage;
import com.example.BusRouteAPIv1.exception.EmptyFieldException;
import com.example.BusRouteAPIv1.exception.GeneralUsageException;
import com.example.BusRouteAPIv1.exception.WhiteSpaceException;
import com.example.BusRouteAPIv1.model.Route;
import com.example.BusRouteAPIv1.model.Stopage;
import com.example.BusRouteAPIv1.service.RouteService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/route")
public class RouteResource {
    private final RouteService routeService;

    @PostMapping("/add-route")
    public ResponseEntity<Route> addRoute(@RequestBody AddRouteRequest routeRequest) throws EmptyFieldException, WhiteSpaceException, GeneralUsageException {
        Route newRoute = routeService.addRoute(routeRequest);
        return new ResponseEntity<>(newRoute, HttpStatus.OK);
    }

    @GetMapping("/list/routes") // 815759 // buet serial no: 050905
    public ResponseEntity<List<Route>> getAllRoutes(){
        List<Route> routeList = routeService.getAllRoutes();
        return new ResponseEntity<>(routeList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Route> getRouteById(@PathVariable Long id) throws GeneralUsageException {
        Route route = routeService.getRouteById(id);
        return new ResponseEntity<>(route, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Route> editRouteId(@PathVariable Long id, @RequestBody AddRouteRequest routeRequest) throws GeneralUsageException, EmptyFieldException, WhiteSpaceException {
        Route route= routeService.editRouteById(id, routeRequest);
        return new ResponseEntity<>(route, HttpStatus.OK);
    }

    @GetMapping("/stoppage/{stopageId}/category/{routeCategoryId}")
    public ResponseEntity<List<String>> getBusNamesOfStopage(@PathVariable Long stopageId, @PathVariable Long routeCategoryId) throws GeneralUsageException {
        List<String> buses = routeService.getBusNamesOfStopage(stopageId, routeCategoryId);
        return new ResponseEntity<>(buses, HttpStatus.OK);
    }

    @GetMapping("/requestedRoute/busName")
    public ResponseEntity<List<String>> getBusNameOfRequestedRoute(@RequestBody BusOfStopage busOfStopage) throws GeneralUsageException {
        List<String> busList1 = new ArrayList<String>(Arrays.asList(busOfStopage.getBusOfStopage1().split(",")));
        List<String> busList2 = new ArrayList<String>(Arrays.asList(busOfStopage.getBusOfStopage2().split(",")));

        List<String> common = new ArrayList<String>(busList1);
        common.retainAll(busList2);

        return new ResponseEntity<>(common, HttpStatus.OK);
    }

}
