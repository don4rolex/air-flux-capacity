package com.andrew.dto;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalTime;

/**
 * @author andrew
 */
public class FlightSchedule implements Serializable {

  private static final long serialVersionUID = 1L;

  private LocalTime timeOfDeparture;
  private String origin;
  private String destination;
  private Duration flightTime;
  private Aircraft aircraft;

  //Default constructor needed for JSON Serialization/Deserialization
  public FlightSchedule() {
  }

  public FlightSchedule(LocalTime timeOfDeparture, String origin, String destination, Duration flightTime, Aircraft aircraft) {
    this.timeOfDeparture = timeOfDeparture;
    this.origin = origin;
    this.destination = destination;
    this.flightTime = flightTime;
    this.aircraft = aircraft;
  }

  public LocalTime getTimeOfDeparture() {
    return timeOfDeparture;
  }

  public String getOrigin() {
    return origin;
  }

  public String getDestination() {
    return destination;
  }

  public Duration getFlightTime() {
    return flightTime;
  }

  public Aircraft getAircraft() {
    return aircraft;
  }

  public void setAircraft(Aircraft aircraft) {
    this.aircraft = aircraft;
  }

  @Override
  public String toString() {
    return "FlightSchedule{" +
        "timeOfDeparture=" + timeOfDeparture +
        ", origin='" + origin + '\'' +
        ", destination='" + destination + '\'' +
        ", flightTime=" + flightTime +
        ", aircraft=" + aircraft +
        '}';
  }
}
