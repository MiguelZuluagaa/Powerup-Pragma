package com.pragma.powerup.plazoletamicroservice.adapters.driving.http.controller;

import com.pragma.powerup.plazoletamicroservice.adapters.driven.microservices.dto.UserResponseDto;
import com.pragma.powerup.plazoletamicroservice.adapters.driven.microservices.client.IUserFeignClient;
import com.pragma.powerup.plazoletamicroservice.adapters.driving.http.dto.request.RestaurantRequestDto;
import com.pragma.powerup.plazoletamicroservice.adapters.driving.http.dto.response.RestaurantResponseDto;
import com.pragma.powerup.plazoletamicroservice.adapters.driving.http.handlers.IRestaurantHandler;
import com.pragma.powerup.plazoletamicroservice.configuration.Constants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController()
@RequestMapping("/restaurant")
@RequiredArgsConstructor
public class RestaurantRestController {
    private final IRestaurantHandler restaurantHandler;

    @Autowired
    IUserFeignClient userFeignClient;

    @Operation(summary = "Get all restaurants",
            responses = {
                    @ApiResponse(responseCode = "200", description = "All restaurants returned",
                            content = @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = RestaurantResponseDto.class)))),
                    @ApiResponse(responseCode = "404", description = "No data found",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error")))})
    @GetMapping("")
    public ResponseEntity<List<RestaurantResponseDto>> getAllRestaurants() {
        return ResponseEntity.ok(restaurantHandler.getAllRestaurants());
    }

    @Operation(summary = "Add a new restaurant",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Restaurant created",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Map"))),
                    @ApiResponse(responseCode = "409", description = "Restaurant already exists",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error")))})
    @PostMapping("")
    public ResponseEntity<Map<String, String>> saveRestaurant(@Valid @RequestBody RestaurantRequestDto restaurantRequestDto) {
        restaurantHandler.saveRestaurant(restaurantRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Collections.singletonMap(Constants.RESPONSE_MESSAGE_KEY, Constants.RESTAURANT_CREATED_MESSAGE));
    }

    @GetMapping("/getUserByDni/{dni}")
    public ResponseEntity<UserResponseDto> getUserByDni(@PathVariable String dni){
        //System.out.println(dni);
        UserResponseDto test = userFeignClient.getUserByDni(dni);

        return ResponseEntity.ok(test);
    }
}
