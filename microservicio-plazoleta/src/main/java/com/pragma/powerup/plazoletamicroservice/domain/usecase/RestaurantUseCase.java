package com.pragma.powerup.plazoletamicroservice.domain.usecase;

import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.exceptions.RoleNotFoundException;
import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.exceptions.UserNotFoundException;
import com.pragma.powerup.plazoletamicroservice.adapters.driven.microservices.client.IUserFeignClient;
import com.pragma.powerup.plazoletamicroservice.adapters.driven.microservices.dto.UserResponseDto;
import com.pragma.powerup.plazoletamicroservice.domain.api.IRestaurantServicePort;
import com.pragma.powerup.plazoletamicroservice.domain.model.Restaurant;
import com.pragma.powerup.plazoletamicroservice.domain.spi.IRestaurantPersistencePort;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

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
        Optional<UserResponseDto> userRequested = Optional.ofNullable(userFeignClient.getUserById(restaurant.getIdUserOwner()));
        if(userRequested.isPresent()){
            if(userRequested.get().getIdRole().getName().contains("PROPIETARIO")){
                restaurantPersistencePort.saveRestaurant(restaurant);
            }else{
                throw new RoleNotFoundException();
            }
        }else{
            throw new UserNotFoundException();
        }
    }

}
