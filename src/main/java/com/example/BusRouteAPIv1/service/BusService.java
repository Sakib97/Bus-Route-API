package com.example.BusRouteAPIv1.service;

import com.example.BusRouteAPIv1.dto.AddBusRequest;
import com.example.BusRouteAPIv1.exception.EmptyFieldException;
import com.example.BusRouteAPIv1.exception.GeneralUsageException;
import com.example.BusRouteAPIv1.exception.WhiteSpaceException;
import com.example.BusRouteAPIv1.model.Bus;
import com.example.BusRouteAPIv1.repositiry.BusRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BusService {
    private final BusRepository busRepository;

    public Bus addBus(AddBusRequest busRequest) throws EmptyFieldException, WhiteSpaceException {

        Bus bus = new Bus();
        if (busRequest.getBus().isEmpty()){
            throw new EmptyFieldException("Empty Field is not allowed!");
        }
        if (busRequest.getBus().trim().length()==0){
            throw new WhiteSpaceException("Only Whitespace is not allowed!");
        }
        bus.setBus(busRequest.getBus().trim()); // trim() removes all whitespaces
        busRepository.save(bus);

        return bus;
    }

    public List<Bus> getAllBuses() {
        return busRepository.findAll();
    }

    public Bus getBusById(Long id) throws GeneralUsageException {
        Bus bus = busRepository.findById(id).orElseThrow(
                ()-> new GeneralUsageException("This Bus Doesn't Exist")
        );
        return bus;
    }

    public Bus editBusById(Long id, AddBusRequest busRequest) throws GeneralUsageException, EmptyFieldException, WhiteSpaceException {
        Bus bus = busRepository.findById(id).orElseThrow(
                ()-> new GeneralUsageException("This Bus Doesn't Exist")
        );

        if (busRequest.getBus().isEmpty()){
            throw new EmptyFieldException("Empty Field is not allowed!");
        }
        if (busRequest.getBus().trim().length()==0){
            throw new WhiteSpaceException("Only Whitespace is not allowed!");
        }
        bus.setBus(busRequest.getBus().trim()); // trim() removes all whitespaces
        busRepository.save(bus);

        return bus;
    }
}
