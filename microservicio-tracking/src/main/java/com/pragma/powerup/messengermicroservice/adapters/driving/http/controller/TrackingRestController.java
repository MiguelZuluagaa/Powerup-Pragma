package com.pragma.powerup.messengermicroservice.adapters.driving.http.controller;


import com.pragma.powerup.messengermicroservice.adapters.driving.http.handlers.ITrackingHandler;
import com.pragma.powerup.messengermicroservice.configuration.Constants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RestController()
@RequestMapping("/tracking")
@RequiredArgsConstructor
public class TrackingRestController {

    private final ITrackingHandler trackingHandler;

    @Operation(summary = "Tracking order",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Data saved",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Map")))})
    @PostMapping("")
    @SecurityRequirement(name = "jwt")
    public ResponseEntity<Map<String, String>> trackingOrder() {
        trackingHandler.trackingOrder();
        return ResponseEntity.status(HttpStatus.OK)
                .body(Collections.singletonMap(Constants.RESPONSE_MESSAGE_KEY, Constants.MESSAGE_SENT));
    }
}
