package com.pragma.powerup.messengermicroservice.configuration;

import com.pragma.powerup.messengermicroservice.adapters.driven.microservices.client.IUserFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
@EnableFeignClients
public class BeanConfiguration {

    private final IUserFeignClient userFeignClient;

}
