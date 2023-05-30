package com.pragma.powerup.plazoletamicroservice.adapters.driving.http.controller;

import com.pragma.powerup.plazoletamicroservice.adapters.driving.http.dto.request.CreateOrderRequestDto;
import com.pragma.powerup.plazoletamicroservice.adapters.driving.http.dto.response.OrderResponseDto;
import com.pragma.powerup.plazoletamicroservice.adapters.driving.http.handlers.IOrderHandler;
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
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderRestController {

    private final IOrderHandler orderHandler;

    @Operation(summary = "Get orders by status",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Orders returned",
                            content = @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = OrderResponseDto.class)))),
                    @ApiResponse(responseCode = "404", description = "No data found",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error")))})
    @GetMapping("/getOrdersByStatus/{idRestaurant}/{idStatus}/{pageSize}/{offset}")
    @SecurityRequirement(name = "jwt")
    public ResponseEntity<List<OrderResponseDto>> getOrdersByStatus(@PathVariable Long idRestaurant,
                                                                    @PathVariable Long idStatus,
                                                                    @PathVariable Long offset,
                                                                    @PathVariable Long pageSize) {
        return ResponseEntity.ok(orderHandler.getOrdersByStatus(idRestaurant,idStatus,offset,pageSize));
    }

    @Operation(summary = "Create a new order",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Order created",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Map"))),
                    @ApiResponse(responseCode = "409", description = "error creating order",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error")))})
    @PostMapping("/createOrder/")
    @SecurityRequirement(name = "jwt")
    public ResponseEntity<Map<String, String>> createOrder(@Valid @RequestBody CreateOrderRequestDto createOrderRequestDto) {
        orderHandler.createOrder(createOrderRequestDto);
        return ResponseEntity.status(HttpStatus.OK)
                .body(Collections.singletonMap(Constants.RESPONSE_MESSAGE_KEY, Constants.ORDER_CREATED_MESSAGE));
    }

    @Operation(summary = "Take a new order",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Order took",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Map"))),
                    @ApiResponse(responseCode = "409", description = "error creating order",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error")))})
    @PostMapping("/takeOrder/{idOrder}")
    @SecurityRequirement(name = "jwt")
    public ResponseEntity<Map<String, String>> takeOrder(@PathVariable Long idOrder) {
        orderHandler.takeOrder(idOrder);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Collections.singletonMap(Constants.RESPONSE_MESSAGE_KEY, Constants.ORDER_TOOK_MESSAGE));
    }



}