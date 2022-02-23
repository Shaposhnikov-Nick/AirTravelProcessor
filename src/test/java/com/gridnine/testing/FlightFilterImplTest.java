package com.gridnine.testing;

import junit.framework.TestCase;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.List;

public class FlightFilterImplTest extends TestCase {

    @Test
    public void testGetFlightsWithDepartureDateAfterNow() {
        FlightFilterImpl flightFilter = new FlightFilterImpl();
        List<Segment> segments = List.of(new Segment(LocalDateTime.now().minusHours(1), LocalDateTime.now()
                .plusHours(2)));
        List<Flight> flights = List.of(new Flight(segments));
        assertEquals(0, flightFilter.getFlightsWithDepartureDateAfterNow(flights).size());
    }

    @Test
    public void testGetFlightsWithoutArrivalDateBeforeDepartureDate() {
        FlightFilterImpl flightFilter = new FlightFilterImpl();
        List<Segment> segments = List.of(new Segment(LocalDateTime.now().plusHours(2), LocalDateTime.now()));
        List<Flight> flights = List.of(new Flight(segments));
        assertEquals(0, flightFilter.getFlightsWithoutArrivalDateBeforeDepartureDate(flights).size());
    }

    @Test
    public void testGetFlightsTotalTimeOnGroundLessTwoHours() {
        FlightFilterImpl flightFilter = new FlightFilterImpl();
        List<Segment> segments = List.of(new Segment(LocalDateTime.now(), LocalDateTime.now().plusHours(2)),
                new Segment(LocalDateTime.now().plusHours(5), LocalDateTime.now().plusHours(6)));
        List<Flight> flights = List.of(new Flight(segments));
        assertEquals(0, flightFilter.getFlightsTotalTimeOnGroundLessTwoHours(flights).size());
    }
}