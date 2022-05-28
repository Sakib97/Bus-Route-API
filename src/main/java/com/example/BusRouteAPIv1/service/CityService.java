package com.example.BusRouteAPIv1.service;

import com.example.BusRouteAPIv1.dto.AddCityRequest;
import com.example.BusRouteAPIv1.exception.EmptyFieldException;
import com.example.BusRouteAPIv1.exception.GeneralUsageException;
import com.example.BusRouteAPIv1.exception.WhiteSpaceException;
import com.example.BusRouteAPIv1.model.City;
import com.example.BusRouteAPIv1.repositiry.CityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CityService {
    private final CityRepository cityRepository;

    public City addCity(AddCityRequest cityRequest) throws EmptyFieldException, WhiteSpaceException {

        City city = new City();
        if (cityRequest.getCity().isEmpty()){
            throw new EmptyFieldException("Empty Field is not allowed!");
        }
        if (cityRequest.getCity().trim().length()==0){
            throw new WhiteSpaceException("Only Whitespace is not allowed!");
        }
        city.setCity(cityRequest.getCity().trim()); // trim() removes all whitespaces
        cityRepository.save(city);

        return city;
    }

    public List<City> getAllCities() {
        return cityRepository.findAll();
    }

    public City getCityById(Long id) throws GeneralUsageException {
        City city = cityRepository.findById(id).orElseThrow(
                ()-> new GeneralUsageException("This City Doesn't Exist")
        );
        return city;
    }

    public City editCityById(Long id, AddCityRequest cityRequest) throws GeneralUsageException, EmptyFieldException, WhiteSpaceException {
        City city = cityRepository.findById(id).orElseThrow(
                ()-> new GeneralUsageException("This City Doesn't Exist")
        );

        if (cityRequest.getCity().isEmpty()){
            throw new EmptyFieldException("Empty Field is not allowed!");
        }
        if (cityRequest.getCity().trim().length()==0){
            throw new WhiteSpaceException("Only Whitespace is not allowed!");
        }
        city.setCity(cityRequest.getCity().trim()); // trim() removes all whitespaces
        cityRepository.save(city);

        return city;
    }

}
