package com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.adapter;

import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.entity.RestaurantEntity;

import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.exceptions.NoDataFoundException;
import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.exceptions.RestaurantAlreadyExistException;
import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.exceptions.RestaurantNotExist;
import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.mappers.IRestaurantEntityMapper;
import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.repositories.IRestaurantRepository;
import com.pragma.powerup.plazoletamicroservice.domain.model.Restaurant;
import com.pragma.powerup.plazoletamicroservice.domain.spi.IRestaurantPersistencePort;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class RestaurantMysqlAdapter implements IRestaurantPersistencePort {
    private final IRestaurantRepository restaurantRepository;
    private final IRestaurantEntityMapper restaurantEntityMapper;

    @Override
    public List<Restaurant> getAllRestaurants() {
        List<RestaurantEntity> roleEntityList = restaurantRepository.findAll();
        if (roleEntityList.isEmpty()) {
            throw new NoDataFoundException();
        }
        return restaurantEntityMapper.toRestaurantList(roleEntityList);
    }

    @Override
    public void saveRestaurant(Restaurant restaurant){

        if(restaurantRepository.findByNit(restaurant.getNit()).isPresent()){
            throw new RestaurantAlreadyExistException();
        }
        restaurantRepository.save(restaurantEntityMapper.toEntity(restaurant));
    }

    @Override
    public Boolean existsByIdUserOwner(Long idUserOwner) {
        return restaurantRepository.existsByIdUserOwner(idUserOwner);
    }

    @Override
    public Optional<Restaurant> findRestaurantById(Long id) {
        Optional<RestaurantEntity> restaurantEntity = restaurantRepository.findById(id);
        if (restaurantEntity.isEmpty()) {
            throw new RestaurantNotExist();
        }

        return Optional.of(restaurantEntityMapper.toRestaurant(restaurantEntity.get()));
    }
}
