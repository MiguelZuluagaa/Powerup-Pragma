package com.pragma.powerup.messengermicroservice.adapters.driving.http.controller;

import com.pragma.powerup.messengermicroservice.adapters.driving.http.dto.request.CreateTrackingRestaurantDto;
import com.pragma.powerup.messengermicroservice.adapters.driving.http.handlers.ITrackingRestaurantHandler;
import com.pragma.powerup.messengermicroservice.configuration.Constants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController()
@RequestMapping("/trackingRestaurant")
@RequiredArgsConstructor
public class TrackingRestaurantRestController {
    private final ITrackingRestaurantHandler trackingRestaurantHandler;

    @Operation(summary = "Create restaurant tracking",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Tracking created",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Map")))})
    @PostMapping("/create/")
    @SecurityRequirement(name = "jwt")
    public ResponseEntity<Map<String, String>> trackingRestaurant(@Valid @RequestBody CreateTrackingRestaurantDto createTrackingRestaurantDto) {
        trackingRestaurantHandler.trackingRestaurant(createTrackingRestaurantDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Collections.singletonMap(Constants.RESPONSE_MESSAGE_KEY, Constants.TRACKING_RESTAURANT_CREATED));
    }
}
