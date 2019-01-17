package com.andrew.dto;

import com.andrew.utility.DateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;
import java.util.Date;

/**
 * @author andrew
 */
public class OperatingInstruction implements Serializable {

  private static final long serialVersionUID = 1L;

  private String origin;

  private String destination;

  @JsonSerialize(using = DateSerializer.class)
  private Date departure;

  //Default constructor needed for JSON Serialization/Deserialization
  public OperatingInstruction() {
  }

  public OperatingInstruction(String origin, String destination, Date departure) {
    this.origin = origin;
    this.destination = destination;
    this.departure = departure;
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

  @Override
  public String toString() {
    return "OperatingInstruction{" +
        "origin='" + origin + '\'' +
        ", destination='" + destination + '\'' +
        ", departure=" + departure +
        '}';
  }
}
