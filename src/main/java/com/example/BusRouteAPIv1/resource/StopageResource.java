package com.example.BusRouteAPIv1.resource;

import com.example.BusRouteAPIv1.dto.AddBusRequest;
import com.example.BusRouteAPIv1.dto.AddStopageRequest;
import com.example.BusRouteAPIv1.exception.EmptyFieldException;
import com.example.BusRouteAPIv1.exception.GeneralUsageException;
import com.example.BusRouteAPIv1.exception.WhiteSpaceException;
import com.example.BusRouteAPIv1.model.Bus;
import com.example.BusRouteAPIv1.model.Stopage;
import com.example.BusRouteAPIv1.service.StopageService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/stopage")
public class StopageResource {
    private final StopageService stopageService;

    @PostMapping("/add-stopage")
    public ResponseEntity<Stopage> addStopage(@RequestBody AddStopageRequest stopageRequest) throws EmptyFieldException, WhiteSpaceException, GeneralUsageException {
        Stopage newStopage = stopageService.addStopage(stopageRequest);
        return new ResponseEntity<>(newStopage, HttpStatus.OK);
    }

    @GetMapping("/list/stopages")
    public ResponseEntity<List<Stopage>> getAllStopages(){
        List<Stopage> stopageList = stopageService.getAllStopages();
        return new ResponseEntity<>(stopageList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Stopage> getStopageById(@PathVariable Long id) throws GeneralUsageException {
        Stopage stopage = stopageService.getStopageById(id);
        return new ResponseEntity<>(stopage, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Stopage> editStopageId(@PathVariable Long id, @RequestBody AddStopageRequest stopageRequest) throws GeneralUsageException, EmptyFieldException, WhiteSpaceException {
        Stopage stopage= stopageService.editStopageById(id, stopageRequest);
        return new ResponseEntity<>(stopage, HttpStatus.OK);
    }

}
