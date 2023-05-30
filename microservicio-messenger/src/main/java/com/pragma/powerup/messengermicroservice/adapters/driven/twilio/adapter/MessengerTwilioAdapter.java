package com.pragma.powerup.messengermicroservice.adapters.driven.twilio.adapter;


import com.pragma.powerup.messengermicroservice.domain.spi.IMessengerPersistencePort;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.AllArgsConstructor;

public class MessengerTwilioAdapter implements IMessengerPersistencePort {

    private final String ACCOUNT_SID = "ACdcad5eaf479cd8ecd6f0aa9973b6cbf5";
    private final String AUTH_TOKEN = "b2cf2bc7c3f77c4cbf946b73430af43e";
    private final String TWILIO_PHONE_NUMBER = "+13612645316";
    private final String MESSAGE = "test message";

    public MessengerTwilioAdapter() {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    }

    @Override
    public void sendMessage(Long idOrder) {
        Message.creator(
                new PhoneNumber("+573004469428"),
                new PhoneNumber(TWILIO_PHONE_NUMBER),
                MESSAGE)
                .create();
    }
}
