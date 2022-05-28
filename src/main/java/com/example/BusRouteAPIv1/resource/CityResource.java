package com.example.BusRouteAPIv1.resource;

import com.example.BusRouteAPIv1.dto.AddCityRequest;
import com.example.BusRouteAPIv1.dto.AddRouteCategoryRequest;
import com.example.BusRouteAPIv1.exception.EmptyFieldException;
import com.example.BusRouteAPIv1.exception.GeneralUsageException;
import com.example.BusRouteAPIv1.exception.WhiteSpaceException;
import com.example.BusRouteAPIv1.model.City;
import com.example.BusRouteAPIv1.model.RouteCategory;
import com.example.BusRouteAPIv1.service.CityService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/city")
public class CityResource {
    private final CityService cityService;

    @PostMapping("/add-city")
    public ResponseEntity<City> addcity(@RequestBody AddCityRequest cityRequest) throws EmptyFieldException, WhiteSpaceException {
        City newCity = cityService.addCity(cityRequest);
        return new ResponseEntity<>(newCity, HttpStatus.OK);
    }

    @GetMapping("/list/cities")
    public ResponseEntity<List<City>> getAllCities(){
        List<City> cityList = cityService.getAllCities();
        return new ResponseEntity<>(cityList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<City> getCityById(@PathVariable Long id) throws GeneralUsageException {
        City city = cityService.getCityById(id);
        return new ResponseEntity<>(city, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<City> editCityId(@PathVariable Long id, @RequestBody AddCityRequest cityRequest) throws GeneralUsageException, EmptyFieldException, WhiteSpaceException {
        City city = cityService.editCityById(id, cityRequest);
        return new ResponseEntity<>(city, HttpStatus.OK);
    }
}
