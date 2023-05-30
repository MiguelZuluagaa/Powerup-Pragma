package com.pragma.powerup.messengermicroservice.configuration;

import com.pragma.powerup.messengermicroservice.adapters.driven.microservices.client.IUserFeignClient;
import com.pragma.powerup.messengermicroservice.adapters.driven.twilio.adapter.MessengerTwilioAdapter;
import com.pragma.powerup.messengermicroservice.domain.api.IMessengerServicePort;
import com.pragma.powerup.messengermicroservice.domain.spi.IMessengerPersistencePort;
import com.pragma.powerup.messengermicroservice.domain.usecase.MessengerUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
@EnableFeignClients
public class BeanConfiguration {

    private final IUserFeignClient userFeignClient;

    //private final IMessengerRepository messengerRepository;
    //private final IMessengerEntityMapper messengerEntityMapper;

    @Bean
    public IMessengerServicePort messengerServicePort() {
        return new MessengerUseCase(messengerPersistencePort());
    }

    @Bean
    public IMessengerPersistencePort messengerPersistencePort() {
        return new MessengerTwilioAdapter();
    }

}
