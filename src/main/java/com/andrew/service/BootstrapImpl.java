package com.andrew.service;

import com.andrew.dto.FlightSchedule;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

import static com.andrew.utility.Utility.listOfFlightSchedule;

/**
 * @author andrew
 */
@Service
public class BootstrapImpl implements Bootstrap {

  private final List<FlightSchedule> flightScheduleList = new ArrayList<>();

  @PostConstruct
  private void init() {
    flightScheduleList.addAll(listOfFlightSchedule());
  }

  public List<FlightSchedule> getFlightSchedule() {
    return flightScheduleList;
  }
}
