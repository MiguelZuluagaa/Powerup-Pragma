package com.pragma.powerup.messengermicroservice.adapters.driven.twilio.adapter;

import com.pragma.powerup.messengermicroservice.domain.spi.IMessengerPersistencePort;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.AllArgsConstructor;


@AllArgsConstructor
public class MessengerTwilioAdapter implements IMessengerPersistencePort {

    private final String ACCOUNT_SID = System.getenv("AUTH_SID");
    private final String AUTH_TOKEN = System.getenv("AUTH_TOKEN");
    private final String TWILIO_PHONE_NUMBER = System.getenv("TWILIO_PHONE_NUMBER");


    @Override
    public void sendMessage(String statusOrder, String phoneNumber) {
        Twilio.init(ACCOUNT_SID,AUTH_TOKEN);
        Message.creator(
                        new PhoneNumber(phoneNumber),
                        new PhoneNumber(TWILIO_PHONE_NUMBER),
                        statusOrder)
                .create();
    }
}
