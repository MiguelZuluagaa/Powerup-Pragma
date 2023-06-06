package com.pragma.powerup.plazoletamicroservice.adapters.driven.microservices.client;

import com.pragma.powerup.plazoletamicroservice.domain.model.TrackingOrder;
import com.pragma.powerup.plazoletamicroservice.domain.model.TrackingRestaurant;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

import static com.pragma.powerup.plazoletamicroservice.configuration.Constants.NAME_MICROSERVICE_TRACKING;
import static com.pragma.powerup.plazoletamicroservice.configuration.Constants.RUTE_MICROSERVICE_TRACKING;

@FeignClient(name = NAME_MICROSERVICE_TRACKING, url = RUTE_MICROSERVICE_TRACKING)
public interface ITrackingFeignClient {
    @PostMapping("/trackingOrder/create/")
    ResponseEntity<Map<String, String>> trackingOrder(@RequestBody TrackingOrder trackingOrder);
    @PostMapping("/trackingRestaurant/create/")
    ResponseEntity<Map<String, String>> trackingRestaurant(@RequestBody TrackingRestaurant trackingRestaurant);
}
