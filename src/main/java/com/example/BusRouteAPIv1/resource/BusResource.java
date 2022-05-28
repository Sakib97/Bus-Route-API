package com.example.BusRouteAPIv1.resource;

import com.example.BusRouteAPIv1.dto.AddBusRequest;
import com.example.BusRouteAPIv1.dto.AddCityRequest;
import com.example.BusRouteAPIv1.exception.EmptyFieldException;
import com.example.BusRouteAPIv1.exception.GeneralUsageException;
import com.example.BusRouteAPIv1.exception.WhiteSpaceException;
import com.example.BusRouteAPIv1.model.Bus;
import com.example.BusRouteAPIv1.service.BusService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/bus")
public class BusResource {
    private final BusService busService;

    @PostMapping("/add-bus")
    public ResponseEntity<Bus> addBus(@RequestBody AddBusRequest busRequest) throws EmptyFieldException, WhiteSpaceException {
        Bus newBus = busService.addBus(busRequest);
        return new ResponseEntity<>(newBus, HttpStatus.OK);
    }

    @GetMapping("/list/buses")
    public ResponseEntity<List<Bus>> getAllBuses(){
        List<Bus> busList = busService.getAllBuses();
        return new ResponseEntity<>(busList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bus> getBusById(@PathVariable Long id) throws GeneralUsageException {
        Bus bus = busService.getBusById(id);
        return new ResponseEntity<>(bus, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Bus> editBusId(@PathVariable Long id, @RequestBody AddBusRequest busRequest) throws GeneralUsageException, EmptyFieldException, WhiteSpaceException {
        Bus bus = busService.editBusById(id, busRequest);
        return new ResponseEntity<>(bus, HttpStatus.OK);
    }
}
