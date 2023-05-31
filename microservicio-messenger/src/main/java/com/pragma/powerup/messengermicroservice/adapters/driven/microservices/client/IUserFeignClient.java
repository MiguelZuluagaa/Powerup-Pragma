package com.pragma.powerup.messengermicroservice.adapters.driven.microservices.client;

import com.pragma.powerup.messengermicroservice.adapters.driven.microservices.dto.UserFeignDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import static com.pragma.powerup.messengermicroservice.configuration.Constants.NAME_MICROSERVICE_USER;
import static com.pragma.powerup.messengermicroservice.configuration.Constants.RUTE_MICROSERVICE_USER;

@FeignClient(name = NAME_MICROSERVICE_USER, url = RUTE_MICROSERVICE_USER)
public interface IUserFeignClient {

    @GetMapping("/user/dni/{dni}")
    UserFeignDto getUserByDni(@PathVariable String dni, @RequestHeader("Authorization") String token);

    @GetMapping("/user/id/{id}")
    UserFeignDto getUserById(@PathVariable Long id, @RequestHeader("Authorization") String token);

    @GetMapping("/user/findByEmail/{email}")
    UserFeignDto getUserByEmail(@PathVariable String email, @RequestHeader("Authorization") String token);
}
