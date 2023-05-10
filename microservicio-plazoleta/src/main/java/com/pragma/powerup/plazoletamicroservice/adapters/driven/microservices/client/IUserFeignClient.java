package com.pragma.powerup.plazoletamicroservice.adapters.driven.microservices.client;

import com.pragma.powerup.plazoletamicroservice.adapters.driven.microservices.dto.UserResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import static com.pragma.powerup.plazoletamicroservice.configuration.Constants.NAME_MICROSERVICE_USER;
import static com.pragma.powerup.plazoletamicroservice.configuration.Constants.RUTE_MICROSERVICE_USER;

@FeignClient(name = NAME_MICROSERVICE_USER, url = RUTE_MICROSERVICE_USER)
public interface IUserFeignClient {

    @GetMapping("/user/dni/{dni}")
    UserResponseDto getUserByDni(@PathVariable String dni);

    @GetMapping("/user/id/{id}")
    UserResponseDto getUserById(@PathVariable Long id);
}