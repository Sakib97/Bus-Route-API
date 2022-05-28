package com.example.BusRouteAPIv1.service;

import com.example.BusRouteAPIv1.dto.AddRouteCategoryRequest;
import com.example.BusRouteAPIv1.exception.EmptyFieldException;
import com.example.BusRouteAPIv1.exception.GeneralUsageException;
import com.example.BusRouteAPIv1.exception.WhiteSpaceException;
import com.example.BusRouteAPIv1.model.RouteCategory;
import com.example.BusRouteAPIv1.repositiry.RouteCategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RouteCategoryService {

    private final RouteCategoryRepository routeCategoryRepository;

    public RouteCategory addRouteCategory(AddRouteCategoryRequest routeCategoryRequest) throws EmptyFieldException, WhiteSpaceException {

        RouteCategory routeCategory = new RouteCategory();
        if (routeCategoryRequest.getCategory().isEmpty()){
            throw new EmptyFieldException("Empty Field is not allowed!");
        }
        if (routeCategoryRequest.getCategory().trim().length()==0){
            throw new WhiteSpaceException("Only Whitespace is not allowed!");
        }
        routeCategory.setCategory(routeCategoryRequest.getCategory().trim()); // trim() removes all whitespaces
        routeCategoryRepository.save(routeCategory);

        return routeCategory;
    }

    public List<RouteCategory> getAllRouteCategories() {
        return routeCategoryRepository.findAll();
    }

    public RouteCategory getRouteCategoryById(Long id) throws GeneralUsageException {
        RouteCategory category = routeCategoryRepository.findById(id).orElseThrow(
                ()-> new GeneralUsageException("This Route Doesnot Exist")
        );
        return category;
    }

    public RouteCategory editRouteCategoryById(Long id, AddRouteCategoryRequest routeCategoryRequest) throws GeneralUsageException, EmptyFieldException, WhiteSpaceException {
        RouteCategory category = routeCategoryRepository.findById(id).orElseThrow(
                ()-> new GeneralUsageException("This Route Doesnot Exist")
        );

        if (routeCategoryRequest.getCategory().isEmpty()){
            throw new EmptyFieldException("Empty Field is not allowed!");
        }
        if (routeCategoryRequest.getCategory().trim().length()==0){
            throw new WhiteSpaceException("Only Whitespace is not allowed!");
        }

        category.setCategory(routeCategoryRequest.getCategory().trim()); // trim() removes all whitespaces
        routeCategoryRepository.save(category);

        return category;
    }

}
