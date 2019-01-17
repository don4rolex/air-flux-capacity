package com.andrew.controller;

import com.andrew.dto.Flight;
import com.andrew.dto.OperatingInstruction;
import com.andrew.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/**
 * @author andrew
 */
@RestController
public class FlightController {

  private final FlightService flightService;

  @Autowired
  public FlightController(FlightService flightService) {
    this.flightService = flightService;
  }

  @GetMapping(path = {"/flightplan/{airport}", "/flightplan/"})
  public ResponseEntity<List<Flight>> getFlightPlan(@PathVariable Optional<String> airport) {

    return ResponseEntity.ok(flightService.getFlightPlan(airport));
  }

  @GetMapping(path = "/operationsplan/{registration}")
  public ResponseEntity<List<OperatingInstruction>> getOperationsPlan(@PathVariable(name = "registration") String registration) {

    return ResponseEntity.ok(flightService.getOperationsPlan(registration));
  }
}
