package com.andrew.service;

import com.andrew.dto.Flight;
import com.andrew.dto.FlightSchedule;
import com.andrew.dto.OperatingInstruction;
import com.andrew.utility.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

/**
 * @author andrew
 */
@Service
public class FlightServiceImpl implements FlightService {

  private final Bootstrap bootstrap;

  @Autowired
  public FlightServiceImpl(Bootstrap bootstrap) {
    this.bootstrap = bootstrap;
  }

  @Override
  public List<Flight> getFlightPlan(Optional<String> airport) {
    final List<FlightSchedule> flightSchedules = bootstrap.getFlightSchedule();

    return airport.map(s -> flightSchedules.stream()
        .filter(e -> e.getOrigin().equals(s))
        .map(Utility::toFlight)
        .collect(toList()))
        .orElseGet(() -> flightSchedules.stream()
            .map(Utility::toFlight)
            .collect(toList()));
  }

  @Override
  public List<OperatingInstruction> getOperationsPlan(String registration) {
    final List<FlightSchedule> flightSchedules = bootstrap.getFlightSchedule();

    return flightSchedules.stream()
        .filter(e -> e.getAircraft().getRegistration().equals(registration))
        .map(Utility::toOperatingInstruction)
        .collect(toList());
  }
}
