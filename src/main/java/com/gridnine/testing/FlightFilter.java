package com.gridnine.testing;

import java.util.List;

public interface FlightFilter {

    // искючение перелетов с датой и временем вылета до текущего момента времени
    List<Flight> getFlightsWithDepartureDateAfterNow(List<Flight> flights);

    // исключение перелетов, имеющих сегменты с датой прилёта раньше даты вылета
    List<Flight> getFlightsWithoutArrivalDateBeforeDepartureDate(List<Flight> flights);

    // исключение перелетов, у которых общее время, проведённое на земле, превышает два часа
    List<Flight> getFlightsTotalTimeOnGroundLessTwoHours(List<Flight> flights);
}
