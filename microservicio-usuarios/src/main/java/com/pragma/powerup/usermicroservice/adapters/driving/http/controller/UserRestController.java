package com.pragma.powerup.usermicroservice.adapters.driving.http.controller;

import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.UserRequestDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.response.UserResponseDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.handlers.IUserHandler;
import com.pragma.powerup.usermicroservice.configuration.Constants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserRestController {
    private final IUserHandler userHandler;

    @Operation(summary = "Add a new user",
            responses = {
                @ApiResponse(responseCode = "201", description = "User created",
                        content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Map"))),
                @ApiResponse(responseCode = "409", description = "User already exists",
                        content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error")))})
    @PostMapping("/saveUser/")
    public ResponseEntity<Map<String, String>> saveUser(@Valid @RequestBody UserRequestDto userRequestDto) {
        userHandler.saveUser(userRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Collections.singletonMap(Constants.RESPONSE_MESSAGE_KEY, Constants.PERSON_CREATED_MESSAGE));
    }

    @Operation(summary = "Get user by DNI",
            responses = {
                    @ApiResponse(responseCode = "200", description = "User found",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Map"))),
                    @ApiResponse(responseCode = "500", description = "Error internal Server",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error")))})
    @GetMapping("/dni/{dni}")
    @SecurityRequirement(name = "jwt")
    public ResponseEntity<UserResponseDto> getUserByDni(@PathVariable String dni){
        return ResponseEntity.ok(userHandler.findUserByDni(dni));
    }

    @Operation(summary = "Get user by ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "User found",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Map"))),
                    @ApiResponse(responseCode = "500", description = "Error internal Server",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error")))})
    @GetMapping("/id/{id}")
    @SecurityRequirement(name = "jwt")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable Long id){
        return ResponseEntity.ok(userHandler.findUserById(id));
    }

    @Operation(summary = "Get user by Email",
            responses = {
                    @ApiResponse(responseCode = "200", description = "User found",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Map"))),
                    @ApiResponse(responseCode = "500", description = "Error internal Server",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error")))})
    @GetMapping("/findByEmail/{email}")
    @SecurityRequirement(name = "jwt")
    public ResponseEntity<UserResponseDto> getUserByEmail(@PathVariable String email){
        return ResponseEntity.ok(userHandler.findUserByEmail(email));
    }

    @Operation(summary = "Delete user by id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "User deleted",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Map"))),
                    @ApiResponse(responseCode = "500", description = "Error internal Server",
                            content = @Content(mediaType = "application/json", schema = @Schema(ref = "#/components/schemas/Error")))})
    @DeleteMapping("/deleteById/{id}")
    @SecurityRequirement(name = "jwt")
    public ResponseEntity<Map<String, String>> deleteById(@PathVariable Long id){
        userHandler.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(Collections.singletonMap(Constants.RESPONSE_MESSAGE_KEY, Constants.USER_DELETED_MESSAGE));
    }
}
