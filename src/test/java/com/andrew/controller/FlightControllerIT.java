package com.andrew.controller;

import com.andrew.dto.Flight;
import com.andrew.dto.OperatingInstruction;
import com.andrew.service.Bootstrap;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author andrew
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FlightControllerIT {

  @Autowired
  private TestRestTemplate template;

  @Autowired
  private Bootstrap bootstrap;

  @Test
  public void getFlightPlan() {
    final ResponseEntity<List<Flight>> response = template.exchange(
        "/flightplan/",
        HttpMethod.GET,
        null,
        new ParameterizedTypeReference<List<Flight>>() {});

    final List<Flight> flights = response.getBody();

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(bootstrap.getFlightSchedule().size(), flights.size());
  }

  @Test
  public void getFlightPlan_withAirport() {
    final ResponseEntity<List<Flight>> response = template.exchange(
        "/flightplan/LHR",
        HttpMethod.GET,
        null,
        new ParameterizedTypeReference<List<Flight>>() {});

    final List<Flight> flights = response.getBody();
    final Flight firstFlight = flights.get(1);
    final long size = bootstrap.getFlightSchedule()
        .stream()
        .filter(e -> e.getOrigin().equals("LHR"))
        .count();

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(size, flights.size());
    assertEquals(firstFlight.getOrigin(), "LHR");
    assertEquals(firstFlight.getDestination(), "TXL");
    assertEquals(firstFlight.getEquipment(), "747-400");
  }

  @Test
  public void getOperationsPlan() {
    final ResponseEntity<List<OperatingInstruction>> response = template.exchange(
        "/operationsplan/FL-0002",
        HttpMethod.GET,
        null,
        new ParameterizedTypeReference<List<OperatingInstruction>>() {});

    final List<OperatingInstruction> flights = response.getBody();
    final long size = bootstrap.getFlightSchedule()
        .stream()
        .filter(e -> e.getAircraft().getRegistration().equals("FL-0002"))
        .count();

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(size, flights.size());
  }
}