package com.pragma.powerup.messengermicroservice.adapters.driving.http.mapper;

import com.pragma.powerup.messengermicroservice.adapters.driving.http.dto.request.CreateTrackingOrderDto;
import com.pragma.powerup.messengermicroservice.domain.model.Tracking;
import java.util.Date;
import javax.annotation.processing.Generated;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-31T16:12:18-0500",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.6.jar, environment: Java 17.0.4 (Oracle Corporation)"
)
@Component
public class ITrackingResponseMapperImpl implements ITrackingResponseMapper {

    @Override
    public Tracking toTracking(CreateTrackingOrderDto createTrackingOrderDto) {
        if ( createTrackingOrderDto == null ) {
            return null;
        }

        Long idOrder = null;
        Long idCustomer = null;
        Long idEmployee = null;
        Long idRestaurant = null;
        String previousStatus = null;
        String currentStatus = null;

        idOrder = createTrackingOrderDto.getIdOrder();
        idCustomer = createTrackingOrderDto.getIdCustomer();
        idEmployee = createTrackingOrderDto.getIdEmployee();
        idRestaurant = createTrackingOrderDto.getIdRestaurant();
        previousStatus = createTrackingOrderDto.getPreviousStatus();
        currentStatus = createTrackingOrderDto.getCurrentStatus();

        ObjectId id = null;
        Date date = null;

        Tracking tracking = new Tracking( id, idOrder, idCustomer, idEmployee, idRestaurant, date, previousStatus, currentStatus );

        return tracking;
    }
}
