package com.pragma.powerup.plazoletamicroservice.adapters.driven.microservices.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

import static com.pragma.powerup.plazoletamicroservice.configuration.Constants.*;

@FeignClient(name = NAME_MICROSERVICE_MESSENGER, url = RUTE_MICROSERVICE_MESSENGER)
public interface IMessengerFeignClient {
    @PostMapping("/messenger/sendMessage/{statusOrder}/{phoneNumber}")
    ResponseEntity<Map<String, String>> sendMessage(@PathVariable String statusOrder, @PathVariable String phoneNumber);
}
