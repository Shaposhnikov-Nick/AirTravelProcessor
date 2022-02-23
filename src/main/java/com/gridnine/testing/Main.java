package com.gridnine.testing;


import java.util.List;

public class Main {
    public static void main(String[] args) {

        // получение тестового набора перелетов
        List<Flight> flights = FlightBuilder.createFlights();
        System.out.println("Тестовый набор перелетов");
        flights.forEach(System.out::println);
        System.out.println("--------------------");

        // фильтр перелетов
        FlightFilter flightFilter = new FlightFilterImpl();

        // получение списка перелетов, искючая перелеты с датой и временем вылета до текущего момента времени
        List<Flight> flightsWithDepartureDateAfterNow = flightFilter.getFlightsWithDepartureDateAfterNow(flights);
        System.out.println("Список перелетов, искючая перелеты с датой и временем вылета до текущего момента времени");
        flightsWithDepartureDateAfterNow.forEach(System.out::println);
        System.out.println("--------------------");

        // получение списка перелетов, искючая перелеты с сегментами с датой прилёта раньше даты вылета
        List<Flight> flightsWithoutArrivalDateBeforeDepartureDate = flightFilter
                .getFlightsWithoutArrivalDateBeforeDepartureDate(flights);
        System.out.println("Список перелетов, искючая перелеты с сегментами с датой прилёта раньше даты вылета");
        flightsWithoutArrivalDateBeforeDepartureDate.forEach(System.out::println);
        System.out.println("--------------------");

        // получение списка перелетов, исключая перелеты с общим временем, проведённым на земле более двух часов
        List<Flight> flightsTotalTimeOnGroundLessTwoHours = flightFilter
                .getFlightsTotalTimeOnGroundLessTwoHours(flights);
        System.out.println("Список перелетов, исключая перелеты с общим временем, проведённым на земле более двух " +
                "часов");
        flightsTotalTimeOnGroundLessTwoHours.forEach(System.out::println);
    }
}
