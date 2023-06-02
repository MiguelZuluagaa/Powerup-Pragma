package com.pragma.powerup.plazoletamicroservice.adapters.driving.http.controller;

import com.pragma.powerup.plazoletamicroservice.adapters.driving.http.dto.request.CreateOrderRequestDto;
import com.pragma.powerup.plazoletamicroservice.adapters.driving.http.dto.request.FinishOrderDto;
import com.pragma.powerup.plazoletamicroservice.adapters.driving.http.dto.response.OrderResponseDto;
import com.pragma.powerup.plazoletamicroservice.adapters.driving.http.dto.response.OrdersCompletedResponseDto;
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

    @Operation(summary = "Get reported of the orders completed",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Reported returned",
                            content = @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = OrderResponseDto.class)))),
                    @ApiResponse(responseCode = "404", description = "No data found",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error")))})
    @GetMapping("/getReportOfOrdersCompleted/{idRestaurant}/")
    @SecurityRequirement(name = "jwt")
    public ResponseEntity<List<OrdersCompletedResponseDto>> getReportOfOrdersCompleted(@PathVariable Long idRestaurant) {
        return ResponseEntity.ok(orderHandler.getReportOfOrdersCompleted(idRestaurant));
    }

    @Operation(summary = "Get reported of the orders completed by Employee",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Reported returned",
                            content = @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = OrderResponseDto.class)))),
                    @ApiResponse(responseCode = "404", description = "No data found",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error")))})
    @GetMapping("/getReportOfOrdersCompletedByEmployee/{idRestaurant}/")
    @SecurityRequirement(name = "jwt")
    public ResponseEntity<Map<Long, Double>> getReportOfOrdersCompletedByEmployee(@PathVariable Long idRestaurant) {
        return ResponseEntity.ok(orderHandler.getReportOfOrdersCompletedByEmployee(idRestaurant));
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
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Collections.singletonMap(Constants.RESPONSE_MESSAGE_KEY, Constants.ORDER_CREATED_MESSAGE));
    }

    @Operation(summary = "Take a new order",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Order took",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Map"))),
                    @ApiResponse(responseCode = "409", description = "error creating order",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error")))})
    @PostMapping("/takeOrder/{idOrder}")
    @SecurityRequirement(name = "jwt")
    public ResponseEntity<Map<String, String>> takeOrder(@PathVariable Long idOrder) {
        orderHandler.takeOrder(idOrder);
        return ResponseEntity.status(HttpStatus.OK)
                .body(Collections.singletonMap(Constants.RESPONSE_MESSAGE_KEY, Constants.ORDER_TOOK_MESSAGE));
    }

    @Operation(summary = "Mark order as ready",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Order ready",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Map"))),
                    @ApiResponse(responseCode = "409", description = "error marking order as ready",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error")))})
    @PostMapping("/markAsReady/{idOrder}")
    @SecurityRequirement(name = "jwt")
    public ResponseEntity<Map<String, String>> markAsReady(@PathVariable Long idOrder) {
        orderHandler.markAsReady(idOrder);
        return ResponseEntity.status(HttpStatus.OK)
                .body(Collections.singletonMap(Constants.RESPONSE_MESSAGE_KEY, Constants.ORDER_READY_MESSAGE));
    }

    @Operation(summary = "Mark order as finished",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Order finished",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Map"))),
                    @ApiResponse(responseCode = "409", description = "error marking order as finished",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error")))})
    @PostMapping("/markAsFinished/")
    @SecurityRequirement(name = "jwt")
    public ResponseEntity<Map<String, String>> markAsFinished(@Valid @RequestBody FinishOrderDto finishOrderDto) {
        orderHandler.finishOrder(finishOrderDto);
        return ResponseEntity.status(HttpStatus.OK)
                .body(Collections.singletonMap(Constants.RESPONSE_MESSAGE_KEY, Constants.ORDER_FINISHED_MESSAGE));
    }

    @Operation(summary = "Cancel order",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Order cancelled",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Map"))),
                    @ApiResponse(responseCode = "409", description = "error marking order as finished",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error")))})
    @PostMapping("/cancelOrder/{idOrder}")
    @SecurityRequirement(name = "jwt")
    public ResponseEntity<Map<String, String>> cancelOrder(@PathVariable Long idOrder) {
        orderHandler.cancelOrder(idOrder);
        return ResponseEntity.status(HttpStatus.OK)
                .body(Collections.singletonMap(Constants.RESPONSE_MESSAGE_KEY, Constants.ORDER_CANCELLED_MESSAGE));
    }

}
