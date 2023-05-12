package com.pragma.powerup.plazoletamicroservice.domain.usecase;

import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.exceptions.MicroserviceUserNotWorking;
import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.exceptions.RoleNotFoundException;
import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.exceptions.UserItsNotOwner;
import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.exceptions.UserNotFoundException;
import com.pragma.powerup.plazoletamicroservice.adapters.driven.microservices.client.IUserFeignClient;
import com.pragma.powerup.plazoletamicroservice.adapters.driven.microservices.dto.UserFeignDto;
import com.pragma.powerup.plazoletamicroservice.domain.api.IRestaurantServicePort;
import com.pragma.powerup.plazoletamicroservice.domain.model.Restaurant;
import com.pragma.powerup.plazoletamicroservice.domain.spi.IRestaurantPersistencePort;

import java.util.List;
import java.util.Optional;

import static com.pragma.powerup.plazoletamicroservice.configuration.Constants.OWNER_ROLE_NAME;

public class RestaurantUseCase implements IRestaurantServicePort {
    private final IRestaurantPersistencePort restaurantPersistencePort;
    private final IUserFeignClient userFeignClient;

    public RestaurantUseCase(IRestaurantPersistencePort restaurantPersistencePort, IUserFeignClient userFeignClient) {
        this.restaurantPersistencePort = restaurantPersistencePort;
        this.userFeignClient = userFeignClient;
    }

    @Override
    public List<Restaurant> getAllRestaurants() {
        return restaurantPersistencePort.getAllRestaurants();
    }

    @Override
    public void saveRestaurant(Restaurant restaurant){
        Optional<UserFeignDto> userRequested = null;
        try {
            userRequested = Optional.ofNullable(userFeignClient.getUserById(restaurant.getIdUserOwner()));
        }catch (Exception e) {
            throw new MicroserviceUserNotWorking();
        }

        if(userRequested.isPresent()){
            if(userRequested.get().getIdRole().getName().contains(OWNER_ROLE_NAME)){
                restaurantPersistencePort.saveRestaurant(restaurant);
            }else{
                throw new UserItsNotOwner();
            }
        }else{
            throw new UserNotFoundException();
        }
    }
}
