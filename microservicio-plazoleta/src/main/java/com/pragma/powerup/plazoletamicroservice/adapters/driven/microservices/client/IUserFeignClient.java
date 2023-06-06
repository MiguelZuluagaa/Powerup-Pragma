package com.pragma.powerup.plazoletamicroservice.adapters.driven.microservices.client;

import com.pragma.powerup.plazoletamicroservice.adapters.driven.microservices.dto.UserFeignDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Collections;
import java.util.Map;

import static com.pragma.powerup.plazoletamicroservice.configuration.Constants.NAME_MICROSERVICE_USER;
import static com.pragma.powerup.plazoletamicroservice.configuration.Constants.RUTE_MICROSERVICE_USER;

@FeignClient(name = NAME_MICROSERVICE_USER, url = RUTE_MICROSERVICE_USER)
public interface IUserFeignClient {

    @GetMapping("/user/dni/{dni}")
    UserFeignDto getUserByDni(@PathVariable String dni, @RequestHeader("Authorization") String token);

    @GetMapping("/user/id/{id}")
    UserFeignDto getUserById(@PathVariable Long id, @RequestHeader("Authorization") String token);

    @GetMapping("/user/findByEmail/{email}")
    UserFeignDto getUserByEmail(@PathVariable String email, @RequestHeader("Authorization") String token);

    @DeleteMapping("/deleteById/{id}")
    ResponseEntity<Map<String, String>> deleteById(@PathVariable Long id, @RequestHeader("Authorization") String token);
}
