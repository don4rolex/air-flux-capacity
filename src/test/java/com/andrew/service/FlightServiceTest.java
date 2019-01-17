package com.andrew.service;

import com.andrew.dto.Flight;
import com.andrew.dto.OperatingInstruction;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;

import static com.andrew.utility.Utility.listOfFlightSchedule;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author andrew
 */
@RunWith(MockitoJUnitRunner.class)
public class FlightServiceTest {

  @Mock
  private Bootstrap bootstrap;

  @InjectMocks
  private FlightServiceImpl sut;

  @Before
  public void init() {
    when(bootstrap.getFlightSchedule()).thenReturn(listOfFlightSchedule());
  }

  @Test
  public void getFlightPlan() {
    final List<Flight> flights = sut.getFlightPlan(Optional.empty());

    assertEquals(flights.size(), listOfFlightSchedule().size());
    verify(bootstrap, times(1)).getFlightSchedule();
  }

  @Test
  public void getFlightPlan_withAirport() {
    final List<Flight> flights = sut.getFlightPlan(Optional.of("TXL"));
    final Flight firstFlight = flights.get(1);

    assertEquals(firstFlight.getOrigin(), "TXL");
    assertEquals(firstFlight.getDestination(), "MUC");
    assertEquals(firstFlight.getEquipment(), "737");
    verify(bootstrap, times(1)).getFlightSchedule();
  }

  @Test
  public void getOperationsPlan() {
    final List<OperatingInstruction> instructionList = sut.getOperationsPlan("FL-0002");
    final OperatingInstruction instruction = instructionList.get(1);

    assertEquals(instruction.getOrigin(), "MUC");
    assertEquals(instruction.getDestination(), "LHR");
    verify(bootstrap, times(1)).getFlightSchedule();
  }
}