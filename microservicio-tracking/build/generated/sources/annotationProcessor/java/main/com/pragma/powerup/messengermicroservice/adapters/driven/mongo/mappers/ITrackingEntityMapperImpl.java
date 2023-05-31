package com.pragma.powerup.messengermicroservice.adapters.driven.mongo.mappers;

import com.pragma.powerup.messengermicroservice.adapters.driven.mongo.collections.TrackingCollection;
import com.pragma.powerup.messengermicroservice.domain.model.Tracking;
import java.util.Date;
import javax.annotation.processing.Generated;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-31T15:29:25-0500",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.6.jar, environment: Java 17.0.4 (Oracle Corporation)"
)
@Component
public class ITrackingEntityMapperImpl implements ITrackingEntityMapper {

    @Override
    public TrackingCollection toCollection(Tracking tracking) {
        if ( tracking == null ) {
            return null;
        }

        ObjectId id = null;
        Long idOrder = null;
        Long idCustomer = null;
        Long idEmployee = null;
        String emailCustomer = null;
        String emailEmployee = null;
        Date date = null;
        String previousStatus = null;
        String currentStatus = null;

        id = tracking.getId();
        idOrder = tracking.getIdOrder();
        idCustomer = tracking.getIdCustomer();
        idEmployee = tracking.getIdEmployee();
        emailCustomer = tracking.getEmailCustomer();
        emailEmployee = tracking.getEmailEmployee();
        date = tracking.getDate();
        previousStatus = tracking.getPreviousStatus();
        currentStatus = tracking.getCurrentStatus();

        TrackingCollection trackingCollection = new TrackingCollection( id, idOrder, idCustomer, idEmployee, emailCustomer, emailEmployee, date, previousStatus, currentStatus );

        return trackingCollection;
    }

    @Override
    public Tracking ofTrackingCollectionToTracking(TrackingCollection trackingCollection) {
        if ( trackingCollection == null ) {
            return null;
        }

        ObjectId id = null;
        Long idOrder = null;
        Long idCustomer = null;
        Long idEmployee = null;
        String emailCustomer = null;
        String emailEmployee = null;
        Date date = null;
        String previousStatus = null;
        String currentStatus = null;

        id = trackingCollection.getId();
        idOrder = trackingCollection.getIdOrder();
        idCustomer = trackingCollection.getIdCustomer();
        idEmployee = trackingCollection.getIdEmployee();
        emailCustomer = trackingCollection.getEmailCustomer();
        emailEmployee = trackingCollection.getEmailEmployee();
        date = trackingCollection.getDate();
        previousStatus = trackingCollection.getPreviousStatus();
        currentStatus = trackingCollection.getCurrentStatus();

        Tracking tracking = new Tracking( id, idOrder, idCustomer, idEmployee, emailCustomer, emailEmployee, date, previousStatus, currentStatus );

        return tracking;
    }
}
