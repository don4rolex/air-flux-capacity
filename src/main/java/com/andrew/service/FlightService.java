package com.andrew.service;

import com.andrew.dto.Flight;
import com.andrew.dto.OperatingInstruction;

import java.util.List;
import java.util.Optional;

/**
 * @author andrew
 */
public interface FlightService {

  List<Flight> getFlightPlan(Optional<String> airport);

  List<OperatingInstruction> getOperationsPlan(String registration);
}
