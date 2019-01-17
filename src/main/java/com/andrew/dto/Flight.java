package com.andrew.dto;

import com.andrew.utility.DateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;
import java.util.Date;

/**
 * @author andrew
 */
public class Flight implements Serializable {

  private static final long serialVersionUID = 7401548380514451401L;

  private String origin;

  private String destination;

  @JsonSerialize(using = DateSerializer.class)
  private Date departure;

  @JsonSerialize(using = DateSerializer.class)
  private Date arrival;

  private String equipment;

  //Default constructor needed for JSON Serialization/Deserialization
  public Flight() {
  }

  public Flight(String origin, String destination, Date departure, Date arrival, String equipment) {
    this.origin = origin;
    this.destination = destination;
    this.departure = departure;
    this.arrival = arrival;
    this.equipment = equipment;
  }

  public String getOrigin() {
    return origin;
  }

  public String getDestination() {
    return destination;
  }

  public Date getDeparture() {
    return departure;
  }

  public Date getArrival() {
    return arrival;
  }

  public String getEquipment() {
    return equipment;
  }

  @Override
  public String toString() {
    return "Flight{" +
        "origin='" + origin + '\'' +
        ", destination='" + destination + '\'' +
        ", departure=" + departure +
        ", arrival=" + arrival +
        ", equipment='" + equipment + '\'' +
        '}';
  }
}
