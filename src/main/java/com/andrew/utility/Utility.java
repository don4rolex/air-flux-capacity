package com.andrew.utility;

import com.andrew.dto.Aircraft;
import com.andrew.dto.Flight;
import com.andrew.dto.FlightSchedule;
import com.andrew.dto.OperatingInstruction;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author andrew
 */
final public class Utility {

  private Utility() {
  }

  public static Flight toFlight(FlightSchedule schedule) {
    final LocalDateTime now = LocalDateTime.now();

    final Date departureTime = Date.from(now.with(schedule.getTimeOfDeparture())
        .atZone(ZoneId.systemDefault())
        .toInstant());

    final Date arrivalTime = Date.from(departureTime.toInstant()
        .atZone(ZoneId.systemDefault())
        .toLocalDateTime()
        .plus(schedule.getFlightTime())
        .atZone(ZoneId.systemDefault())
        .toInstant());

    final String origin = schedule.getOrigin();
    final String destination = schedule.getDestination();
    final String equipment = schedule.getAircraft().getEquipment();

    return new Flight(origin, destination, departureTime, arrivalTime, equipment);
  }

  public static OperatingInstruction toOperatingInstruction(FlightSchedule schedule) {
    final LocalDateTime now = LocalDateTime.now();

    final Date departureTime = Date.from(now.with(schedule.getTimeOfDeparture())
        .atZone(ZoneId.systemDefault())
        .toInstant());

    final String origin = schedule.getOrigin();
    final String destination = schedule.getDestination();

    return new OperatingInstruction(origin, destination, departureTime);
  }

  public static List<FlightSchedule> listOfFlightSchedule() {
    final Aircraft boeing737 = new Aircraft("Boeing", "737", "Berlin", "FL-0001");
    final Aircraft airbusA321 = new Aircraft("Airbus", "A321", "Munich", "FL-0002");
    final Aircraft boeing747 = new Aircraft("Boeing", "747-400", "London", "FL-0003");
    final Aircraft airbusA320 = new Aircraft("Airbus", "A320", "Hamburg,", "FL-0004");

    return Arrays.asList(
        new FlightSchedule(LocalTime.of(10, 0), "TXL", "MUC", Duration.ofHours(1), boeing737),
        new FlightSchedule(LocalTime.of(15, 0), "TXL", "MUC", Duration.ofHours(1), boeing737),
        new FlightSchedule(LocalTime.of(16, 0), "TXL", "MUC", Duration.ofHours(1), boeing737),
        new FlightSchedule(LocalTime.of(18, 0), "TXL", "MUC", Duration.ofHours(1), boeing737),
        new FlightSchedule(LocalTime.of(21, 0), "TXL", "HAM", Duration.ofMinutes(40), boeing737),

        new FlightSchedule(LocalTime.of(10, 0), "MUC", "TXL", Duration.ofHours(2), airbusA321),
        new FlightSchedule(LocalTime.of(13, 0), "MUC", "LHR", Duration.ofHours(1), airbusA321),
        new FlightSchedule(LocalTime.of(15, 0), "MUC", "TXL", Duration.ofHours(1), airbusA321),
        new FlightSchedule(LocalTime.of(15, 30), "MUC", "TXL", Duration.ofHours(2), airbusA321),
        new FlightSchedule(LocalTime.of(17, 30), "MUC", "LHR", Duration.ofHours(1), airbusA321),
        new FlightSchedule(LocalTime.of(18, 0), "MUC", "HAM", Duration.ofHours(2).plusMinutes(30), airbusA321),
        new FlightSchedule(LocalTime.of(20, 0), "MUC", "LHR", Duration.ofHours(2), airbusA321),
        new FlightSchedule(LocalTime.of(22, 0), "MUC", "LHR", Duration.ofHours(1), airbusA321),

        new FlightSchedule(LocalTime.of(9, 0), "LHR", "HAM", Duration.ofHours(2).plusMinutes(30), boeing747),
        new FlightSchedule(LocalTime.of(12, 0), "LHR", "TXL", Duration.ofHours(2), boeing747),
        new FlightSchedule(LocalTime.of(17, 0), "LHR", "TXL", Duration.ofHours(2), boeing747),
        new FlightSchedule(LocalTime.of(20, 30), "LHR", "MUC", Duration.ofHours(2), boeing747),

        new FlightSchedule(LocalTime.of(10, 00), "HAM", "MUC", Duration.ofHours(1), airbusA320),
        new FlightSchedule(LocalTime.of(13, 00), "HAM", "MUC", Duration.ofHours(1), airbusA320),
        new FlightSchedule(LocalTime.of(20, 00), "HAM", "MUC", Duration.ofHours(1), airbusA320)
    );
  }
}
