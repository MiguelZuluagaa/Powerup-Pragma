package com.pragma.powerup.plazoletamicroservice.domain.usecase;

import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.entity.PrincipalUser;
import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.entity.RestaurantEntity;
import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.exceptions.*;
import com.pragma.powerup.plazoletamicroservice.adapters.driven.microservices.client.ITrackingFeignClient;
import com.pragma.powerup.plazoletamicroservice.adapters.driven.microservices.client.IUserFeignClient;
import com.pragma.powerup.plazoletamicroservice.adapters.driven.microservices.dto.UserFeignDto;
import com.pragma.powerup.plazoletamicroservice.configuration.security.jwt.JwtTokenFilter;
import com.pragma.powerup.plazoletamicroservice.domain.api.IRestaurantServicePort;
import com.pragma.powerup.plazoletamicroservice.domain.exceptions.PageAndOffsetMustBePositive;
import com.pragma.powerup.plazoletamicroservice.domain.exceptions.UserCantDeleteRestaurantException;
import com.pragma.powerup.plazoletamicroservice.domain.model.Restaurant;
import com.pragma.powerup.plazoletamicroservice.domain.model.RestaurantStatus;
import com.pragma.powerup.plazoletamicroservice.domain.model.TrackingRestaurant;
import com.pragma.powerup.plazoletamicroservice.domain.spi.IOrderDishPersistencePort;
import com.pragma.powerup.plazoletamicroservice.domain.spi.IOrderPersistencePort;
import com.pragma.powerup.plazoletamicroservice.domain.spi.IRestaurantPersistencePort;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.pragma.powerup.plazoletamicroservice.configuration.Constants.*;

public class RestaurantUseCase implements IRestaurantServicePort {
    private final IRestaurantPersistencePort restaurantPersistencePort;

    @Autowired
    private JwtTokenFilter jwtTokenFilter;
    @Autowired
    private IOrderPersistencePort orderPersistencePort;
    @Autowired
    private IOrderDishPersistencePort orderDishPersistencePort;
    @Autowired
    private IUserFeignClient userFeignClient;
    @Autowired
    private ITrackingFeignClient trackingFeignClient;

    public RestaurantUseCase(IRestaurantPersistencePort restaurantPersistencePort) {
        this.restaurantPersistencePort = restaurantPersistencePort;
    }

    @Override
    public List<Restaurant> getAllRestaurants() {
        return restaurantPersistencePort.getAllRestaurants();
    }

    @Override
    public List<Restaurant> getRestaurantsWithPagination(Long pageSize, Long offset) {
        if(pageSize <= 0 || offset < 0){
            throw new PageAndOffsetMustBePositive();
        }

        return restaurantPersistencePort.getRestaurantsWithPagination(pageSize,offset);
    }

    @Override
    public void saveRestaurant(Restaurant restaurant){
        String TOKEN = jwtTokenFilter.getGLOBAL_TOKEN();
        Optional<UserFeignDto> userRequested = null;
        try {
            userRequested = Optional.ofNullable(userFeignClient.getUserById(restaurant.getIdUserOwner(),TOKEN));
        }catch (FeignException e) {
            HttpStatus statusCode = HttpStatus.valueOf(e.status());
            if (statusCode == HttpStatus.NOT_FOUND) {
                throw new UserNotFoundException();
            }
            throw new MicroserviceUserNotWorking();
        }catch (Exception e){
            throw new MicroserviceUserNotWorking();
        }

        if(userRequested.get().getIdRole().getName().contains(ROLE_OWNER)){
            if(restaurantPersistencePort.findByNit(restaurant.getNit()).isPresent()){
                throw new RestaurantAlreadyExistException();
            }
            restaurantPersistencePort.saveRestaurant(restaurant);
        }else{
            throw new UserItsNotOwner();
        }

    }

    @Override
    @Transactional
    public void deleteRestaurant(Long idRestaurant){
        Optional<Restaurant> restaurantFound = restaurantPersistencePort.findRestaurantById(idRestaurant);
        if(!canDeleteRestaurant(idRestaurant)){
            restaurantFound.get().setIdRestaurantStatus(new RestaurantStatus(RESTAURANT_STATUS_PENDING_DELETED_ID,null,null));
            restaurantPersistencePort.saveRestaurant(restaurantFound.get());
            trackingFeignClient.trackingRestaurant(new TrackingRestaurant(idRestaurant,new Date(),RESTAURANT_STATUS_ACTIVE_NAME,RESTAURANT_STATUS_PENDING_DELETED_NAME));
        }else{
            restaurantFound.get().setIdRestaurantStatus(new RestaurantStatus(RESTAURANT_STATUS_DELETED_ID,null,null));
            restaurantPersistencePort.saveRestaurant(restaurantFound.get());
        }
    }

    /*@Transactional
    public void deleteRestaurantAndComplements(Long idRestaurant){
        orderPersistencePort.getOrdersByIdRestaurant(idRestaurant).forEach(order -> {
            orderDishPersistencePort.deleteOrderByOrderId(order.getId());
            if(order.getIdChef() != null || order.getIdChef().equals(0L)){
                userFeignClient.deleteById(order.getIdChef(), jwtTokenFilter.getGLOBAL_TOKEN());
            }
        });
        orderPersistencePort.deleteByIdRestaurant(idRestaurant);
        restaurantPersistencePort.deleteById(idRestaurant);
    }*/

    private Boolean canDeleteRestaurant(Long idRestaurant){
        return !orderPersistencePort.existsOrdersInCurseByIdRestaurant(idRestaurant);
    }

}
