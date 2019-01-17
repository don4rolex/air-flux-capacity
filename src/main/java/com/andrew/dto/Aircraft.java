package com.andrew.dto;

import java.io.Serializable;

/**
 * @author andrew
 */
public class Aircraft implements Serializable {

  private static final long serialVersionUID = 1L;

  private String name;
  private String equipment;
  private String location;
  private String registration;

  //Default constructor needed for JSON Serialization/Deserialization
  public Aircraft() {
  }

  public Aircraft(String name, String equipment, String location, String registration) {
    this.name = name;
    this.equipment = equipment;
    this.location = location;
    this.registration = registration;
  }

  public String getName() {
    return name;
  }

  public String getEquipment() {
    return equipment;
  }

  public String getLocation() {
    return location;
  }

  public String getRegistration() {
    return registration;
  }

  @Override
  public String toString() {
    return "Aircraft{" +
        "name='" + name + '\'' +
        ", equipment='" + equipment + '\'' +
        ", location='" + location + '\'' +
        ", registration='" + registration + '\'' +
        '}';
  }
}
