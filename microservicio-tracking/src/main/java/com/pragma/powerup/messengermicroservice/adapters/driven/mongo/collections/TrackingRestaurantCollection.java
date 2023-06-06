package com.pragma.powerup.messengermicroservice.adapters.driven.mongo.collections;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "TrackingRestaurants")
@Getter
@Setter
@AllArgsConstructor
public class TrackingRestaurantCollection {

    @Id
    private ObjectId id;
    private Long idRestaurant;
    private Date date;
    private String previousStatus;
    private String currentStatus;
}
