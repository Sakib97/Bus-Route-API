package com.example.BusRouteAPIv1.resource;

import com.example.BusRouteAPIv1.dto.AddRouteCategoryRequest;
import com.example.BusRouteAPIv1.exception.EmptyFieldException;
import com.example.BusRouteAPIv1.exception.GeneralUsageException;
import com.example.BusRouteAPIv1.exception.WhiteSpaceException;
import com.example.BusRouteAPIv1.model.RouteCategory;
import com.example.BusRouteAPIv1.service.RouteCategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/route-category")
public class RouteCategoryResource {
    private final RouteCategoryService routeCategoryService;

    @PostMapping("/add-route-category")
    public ResponseEntity<RouteCategory> addRouteCategory(@RequestBody AddRouteCategoryRequest routeCategoryRequest) throws EmptyFieldException, WhiteSpaceException {
        RouteCategory newRouteCategory = routeCategoryService.addRouteCategory(routeCategoryRequest);
        return new ResponseEntity<>(newRouteCategory, HttpStatus.OK);
    }

    @GetMapping("/list/route-categories")
    public ResponseEntity<List<RouteCategory>> getAllRouteCategories(){
        List<RouteCategory> routeCategoryList = routeCategoryService.getAllRouteCategories();
        return new ResponseEntity<>(routeCategoryList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RouteCategory> getRouteCategoryById(@PathVariable Long id) throws GeneralUsageException {
        RouteCategory category = routeCategoryService.getRouteCategoryById(id);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RouteCategory> editRouteCategoryById(@PathVariable Long id, @RequestBody AddRouteCategoryRequest routeCategoryRequest) throws GeneralUsageException, EmptyFieldException, WhiteSpaceException {
        RouteCategory category = routeCategoryService.editRouteCategoryById(id, routeCategoryRequest);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }



}
