package com.luong.tripadvisor.controller;

import com.luong.tripadvisor.dto.FlightReservationResponse;
import com.luong.tripadvisor.dto.TripPlan;
import com.luong.tripadvisor.dto.TripReservationRequest;
import com.luong.tripadvisor.service.TripPlanService;
import com.luong.tripadvisor.service.TripReservationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("trip")
@RequiredArgsConstructor
@Slf4j
public class TripController {

    private final TripPlanService planService;
    private final TripReservationService reservationService;

    @GetMapping("{airportCode}")
    public TripPlan planTrip(@PathVariable String airportCode) {
        return this.planService.getTripPlan(airportCode);
    }

    @PostMapping("reserve")
    public FlightReservationResponse reserveFlight(@RequestBody TripReservationRequest request) {
        return this.reservationService.reserve(request);
    }

}
