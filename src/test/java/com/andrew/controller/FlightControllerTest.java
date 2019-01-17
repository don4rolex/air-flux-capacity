package com.andrew.controller;

import com.andrew.dto.Flight;
import com.andrew.dto.OperatingInstruction;
import com.andrew.service.FlightService;
import com.andrew.utility.Utility;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static com.andrew.utility.Utility.listOfFlightSchedule;
import static java.util.stream.Collectors.toList;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author andrew
 */
@RunWith(SpringRunner.class)
@WebMvcTest(FlightController.class)
public class FlightControllerTest {

  @Autowired
  private MockMvc mvc;

  @MockBean
  private FlightService flightService;

  @Test
  public void getFlightPlan() throws Exception {
    final List<Flight> flightList = listOfFlightSchedule()
        .stream()
        .map(Utility::toFlight)
        .collect(toList());

    when(flightService.getFlightPlan(Optional.empty())).thenReturn(flightList);

    mvc.perform(get("/flightplan/").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].origin").value("TXL"))
        .andExpect(jsonPath("$[0].destination").value("MUC"));
  }

  @Test
  public void getFlightPlan_withAirport() throws Exception {
    final List<Flight> flightList = listOfFlightSchedule()
        .stream()
        .filter(e -> e.getOrigin().equals("TXL"))
        .map(Utility::toFlight)
        .collect(toList());

    when(flightService.getFlightPlan(Optional.of("TXL"))).thenReturn(flightList);

    mvc.perform(get("/flightplan/TXL").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[4].origin").value("TXL"))
        .andExpect(jsonPath("$[4].destination").value("HAM"));
  }

  @Test
  public void getOperationsPlan() throws Exception {
    final List<OperatingInstruction> operationsPlanList = listOfFlightSchedule()
        .stream()
        .filter(e -> e.getAircraft().getRegistration().equals("FL-0004"))
        .map(Utility::toOperatingInstruction)
        .collect(toList());

    when(flightService.getOperationsPlan("FL-0004")).thenReturn(operationsPlanList);

    mvc.perform(get("/operationsplan/FL-0004").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[2].origin").value("HAM"))
        .andExpect(jsonPath("$[2].destination").value("MUC"));
  }
}