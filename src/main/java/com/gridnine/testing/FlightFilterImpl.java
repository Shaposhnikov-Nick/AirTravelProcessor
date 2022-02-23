package com.gridnine.testing;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FlightFilterImpl implements FlightFilter {

    @Override
    public List<Flight> getFlightsWithDepartureDateAfterNow(List<Flight> flights) {

        // Колекция для хранения отсортированного списка перелетов
        List<Flight> flightsWithDepartureDateAfterNow = new ArrayList<>();

        // получение каждого полета из тестового набора перелетов
        flights.forEach(flight -> {

            // получение набора сегметов полета
            List<Segment> segments = flight.getSegments();

            // отфильтровываем сегменты со сроком вылета до текущего момента времени
            List<Segment> segmentsBeforeNow = segments.stream().filter(x -> x.getDepartureDate()
                    .isBefore(LocalDateTime.now()))
                    .collect(Collectors.toList());

            // если сегменты со сроком вылета до текущего момента времени отсутствуют,
            // добавляем перелет
            if (segmentsBeforeNow.size() == 0)
                flightsWithDepartureDateAfterNow.add(flight);
        });

        return flightsWithDepartureDateAfterNow;
    }


    @Override
    public List<Flight> getFlightsWithoutArrivalDateBeforeDepartureDate(List<Flight> flights) {

        // Колекция для хранения отсортированного списка перелетов
        List<Flight> flightsWithoutArrivalDateBeforeDepartureDate = new ArrayList<>();

        // получение каждого полета из тестового набора перелетов
        flights.forEach(flight -> {

            // получение набора сегметов полета
            List<Segment> segments = flight.getSegments();

            // отфильтровываем сегменты с датой прилёта раньше даты вылета
            List<Segment> segmentsArrivalBeforeDeparture = segments.stream().filter(x -> x.getArrivalDate()
                    .isBefore(x.getDepartureDate()))
                    .collect(Collectors.toList());

            // если сегменты с датой прилёта раньше даты вылета отсутствуют,
            // добавляем перелет
            if (segmentsArrivalBeforeDeparture.size() == 0)
                flightsWithoutArrivalDateBeforeDepartureDate.add(flight);
        });

        return flightsWithoutArrivalDateBeforeDepartureDate;
    }


    @Override
    public List<Flight> getFlightsTotalTimeOnGroundLessTwoHours(List<Flight> flights) {

        // Колекция для хранения отсортированного списка перелетов
        List<Flight> flightsTotalTimeOnGroundLessTwoHours = new ArrayList<>();

        // получение каждого полета из тестового набора перелетов
        flights.forEach(flight -> {

            // получение набора сегметов полета
            List<Segment> segments = flight.getSegments();

            // общее количество времени в минутах, проведенного на земле
            long totalDuration = 0;

            // рассчет времени, проведенного на замле, между сегментами
            for (int i = 0; i < segments.size() - 1; i++) {

                // время прилета сегмента
                LocalTime timeOfArrival = segments.get(i).getArrivalDate().toLocalTime();

                // время вылета следующего сегмента
                LocalTime timeOfNextDeparture = segments.get(i + 1).getDepartureDate().toLocalTime();

                // время, проведённое на земле
                Duration duration = Duration.between(timeOfArrival, timeOfNextDeparture);
                long timeOnGround = duration.toMinutes();

                totalDuration += timeOnGround;
            }

            // если общее время, проведенное на земле менее 120 минут и количество сегментов в перелете более одного
            // добавляем перелет
            if ((totalDuration <= 120) && (segments.size() > 1))
                flightsTotalTimeOnGroundLessTwoHours.add(flight);
        });

        return flightsTotalTimeOnGroundLessTwoHours;
    }
}

