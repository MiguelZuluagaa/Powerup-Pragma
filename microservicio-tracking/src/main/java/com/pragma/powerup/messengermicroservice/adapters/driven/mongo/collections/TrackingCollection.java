package com.pragma.powerup.messengermicroservice.adapters.driven.mongo.collections;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "tracking")
@Getter
@Setter
@AllArgsConstructor
public class TrackingCollection {

    @Id
    private Long id;
    private Long idOrder;
    private Long idCustomer;
    private Long idEmployee;
    private String emailCustomer;
    private String emailEmployee;
    private Date date;
    private String previousStatus;
    private String currentStatus;
}
