package com.pragma.powerup.plazoletamicroservice.adapters.driving.http.controller;

import com.pragma.powerup.plazoletamicroservice.adapters.driving.http.dto.request.DishRequestDto;
import com.pragma.powerup.plazoletamicroservice.adapters.driving.http.dto.request.RestaurantRequestDto;
import com.pragma.powerup.plazoletamicroservice.adapters.driving.http.dto.request.UpdateDishRequestDto;
import com.pragma.powerup.plazoletamicroservice.adapters.driving.http.dto.response.DishResponseDto;
import com.pragma.powerup.plazoletamicroservice.adapters.driving.http.dto.response.RestaurantResponseDto;
import com.pragma.powerup.plazoletamicroservice.adapters.driving.http.handlers.IDishHandler;
import com.pragma.powerup.plazoletamicroservice.configuration.Constants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController()
@RequestMapping("/dish")
@RequiredArgsConstructor
public class DishRestController {

    private final IDishHandler dishHandler;

    @Operation(summary = "Get all dishes",
            responses = {
                    @ApiResponse(responseCode = "200", description = "All dishes returned",
                            content = @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = DishResponseDto.class)))),
                    @ApiResponse(responseCode = "404", description = "No data found",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error")))})
    @GetMapping("/getAllDishes/")
    @SecurityRequirement(name = "jwt")
    public ResponseEntity<List<DishResponseDto>> getAllDishes() {
        return ResponseEntity.ok(dishHandler.getAllDishes());
    }

    @Operation(summary = "Add a new dish",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Dish created",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Map"))),
                    @ApiResponse(responseCode = "409", description = "Dish already exists",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error")))})
    @PostMapping("/saveDish/")
    @SecurityRequirement(name = "jwt")
    public ResponseEntity<Map<String, String>> saveDish(@Valid @RequestBody DishRequestDto dishRequestDto) {
        dishHandler.saveDish(dishRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Collections.singletonMap(Constants.RESPONSE_MESSAGE_KEY, Constants.DISH_CREATED_MESSAGE));
    }

    @Operation(summary = "Update a new dish",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Dish created",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Map"))),
                    @ApiResponse(responseCode = "409", description = "Dish already exists",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error")))})
    @PutMapping("/updateDish/")
    @SecurityRequirement(name = "jwt")
    public ResponseEntity<Map<String, String>> updateDish(@Valid @RequestBody UpdateDishRequestDto updateDishRequestDto) {
        dishHandler.updateDish(updateDishRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Collections.singletonMap(Constants.RESPONSE_MESSAGE_KEY, Constants.DISH_UPDATED_MESSAGE));
    }

}
