package com.example.BusRouteAPIv1.service;

import com.example.BusRouteAPIv1.dto.AddBusRequest;
import com.example.BusRouteAPIv1.dto.AddStopageRequest;
import com.example.BusRouteAPIv1.exception.EmptyFieldException;
import com.example.BusRouteAPIv1.exception.GeneralUsageException;
import com.example.BusRouteAPIv1.exception.WhiteSpaceException;
import com.example.BusRouteAPIv1.model.Bus;
import com.example.BusRouteAPIv1.model.City;
import com.example.BusRouteAPIv1.model.RouteCategory;
import com.example.BusRouteAPIv1.model.Stopage;
import com.example.BusRouteAPIv1.repositiry.CityRepository;
import com.example.BusRouteAPIv1.repositiry.RouteCategoryRepository;
import com.example.BusRouteAPIv1.repositiry.StopageRepository;
import com.example.BusRouteAPIv1.resource.CityResource;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StopageService {
    private final StopageRepository stopageRepository;
    private final RouteCategoryRepository categoryRepository;
    private final CityRepository cityRepository;

    public Stopage addStopage(AddStopageRequest stopageRequest) throws GeneralUsageException, EmptyFieldException, WhiteSpaceException {
        // check if Route category and city exists or not as they will be foreign keys to Stopage table
        Long routeCategoryId = stopageRequest.getCategoryId();
        Long cityId = stopageRequest.getCityId();

        RouteCategory category = categoryRepository.findById(routeCategoryId).orElseThrow(
                ()-> new GeneralUsageException("Invalid Route Category !")
        );
        City city = cityRepository.findById(cityId).orElseThrow(
                ()-> new GeneralUsageException("Invalid City !")
        );

        // check if stopage is blank or not
        if(stopageRequest.getStopage().isEmpty()){
            throw new EmptyFieldException("Empty Field is not allowed!");
        }
        if (stopageRequest.getStopage().trim().length()==0){
            throw new WhiteSpaceException("Only Whitespace is not allowed!");
        }

        // check if the stopage is already in the database
        String stopageName = "%"+stopageRequest.getStopage().trim()+"%";
        Stopage duplicateStopage = stopageRepository.findDuplicateStopageRow(stopageName);
        if (duplicateStopage != null){
            throw new GeneralUsageException("The stoppage already exists!");
        }


        Stopage stopage = new Stopage();
        stopage.setStopage(stopageRequest.getStopage().trim());
        stopage.setCategory_id(category.getId());
        stopage.setCity_id(city.getId());

        stopageRepository.save(stopage);

        return stopage;
    }

    public List<Stopage> getAllStopages() {
        return stopageRepository.findAll();
    }

    public Stopage getStopageById(Long id) throws GeneralUsageException {
        Stopage stopage = stopageRepository.findById(id).orElseThrow(
                ()-> new GeneralUsageException("This Stopage Doesn't Exist")
        );
        return stopage;
    }

    public Stopage editStopageById(Long id, AddStopageRequest stopageRequest) throws GeneralUsageException, EmptyFieldException, WhiteSpaceException {
        Stopage stopage= stopageRepository.findById(id).orElseThrow(
                ()-> new GeneralUsageException("This Stopage Doesn't Exist")
        );

        Long routeCategoryId = stopageRequest.getCategoryId();
        Long cityId = stopageRequest.getCityId();

        RouteCategory category = categoryRepository.findById(routeCategoryId).orElseThrow(
                ()-> new GeneralUsageException("Invalid Route Category !")
        );
        City city = cityRepository.findById(cityId).orElseThrow(
                ()-> new GeneralUsageException("Invalid City !")
        );

        if (stopageRequest.getStopage().isEmpty()){
            throw new EmptyFieldException("Empty Field is not allowed!");
        }
        if (stopageRequest.getStopage().trim().length()==0){
            throw new WhiteSpaceException("Only Whitespace is not allowed!");
        }

        stopage.setStopage(stopageRequest.getStopage().trim());
        stopage.setCategory_id(category.getId());
        stopage.setCity_id(city.getId());

        stopageRepository.save(stopage);

        return stopage;
    }
}
