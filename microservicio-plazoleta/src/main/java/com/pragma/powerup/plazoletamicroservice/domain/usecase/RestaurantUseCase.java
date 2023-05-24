package com.pragma.powerup.plazoletamicroservice.domain.usecase;

import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.entity.RestaurantEntity;
import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.exceptions.MicroserviceUserNotWorking;
import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.exceptions.RoleNotFoundException;
import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.exceptions.UserItsNotOwner;
import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.exceptions.UserNotFoundException;
import com.pragma.powerup.plazoletamicroservice.adapters.driven.microservices.client.IUserFeignClient;
import com.pragma.powerup.plazoletamicroservice.adapters.driven.microservices.dto.UserFeignDto;
import com.pragma.powerup.plazoletamicroservice.configuration.security.jwt.JwtTokenFilter;
import com.pragma.powerup.plazoletamicroservice.domain.api.IRestaurantServicePort;
import com.pragma.powerup.plazoletamicroservice.domain.exceptions.PageAndOffsetMustBePositive;
import com.pragma.powerup.plazoletamicroservice.domain.model.Restaurant;
import com.pragma.powerup.plazoletamicroservice.domain.spi.IRestaurantPersistencePort;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

import static com.pragma.powerup.plazoletamicroservice.configuration.Constants.ROLE_OWNER;

public class RestaurantUseCase implements IRestaurantServicePort {
    private final IRestaurantPersistencePort restaurantPersistencePort;
    private final IUserFeignClient userFeignClient;

    @Autowired
    private JwtTokenFilter jwtTokenFilter;

    public RestaurantUseCase(IRestaurantPersistencePort restaurantPersistencePort, IUserFeignClient userFeignClient) {
        this.restaurantPersistencePort = restaurantPersistencePort;
        this.userFeignClient = userFeignClient;
    }

    @Override
    public List<Restaurant> getAllRestaurants() {
        return restaurantPersistencePort.getAllRestaurants();
    }

    @Override
    public List<Restaurant> getRestaurantsWithPagination(Long pageSize, Long offset) {
        if(pageSize < 0 || offset < 0){
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
            restaurantPersistencePort.saveRestaurant(restaurant);
        }else{
            throw new UserItsNotOwner();
        }

    }
}
