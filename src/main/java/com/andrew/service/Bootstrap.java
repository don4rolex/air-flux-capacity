package com.andrew.service;

import com.andrew.dto.FlightSchedule;

import java.util.List;

/**
 * @author andrew
 */
public interface Bootstrap {

  List<FlightSchedule> getFlightSchedule();
}
