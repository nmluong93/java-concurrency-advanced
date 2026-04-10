package com.luong.tripadvisor.client;

import com.luong.tripadvisor.dto.FlightReservationRequest;
import com.luong.tripadvisor.dto.FlightReservationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestClient;

@RequiredArgsConstructor
public class FlightReservationServiceClient {

    private final RestClient client;

    public FlightReservationResponse reserve(FlightReservationRequest request) {
        return this.client.post()
                          .body(request)
                          .retrieve()
                          .body(FlightReservationResponse.class);
    }

}
