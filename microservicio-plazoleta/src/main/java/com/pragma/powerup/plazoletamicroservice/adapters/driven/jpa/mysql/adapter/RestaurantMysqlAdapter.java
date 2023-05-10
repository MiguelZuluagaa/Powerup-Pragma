package com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.adapter;

import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.entity.RestaurantEntity;

import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.exceptions.NoDataFoundException;
import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.exceptions.RestaurantAlreadyExistException;
import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.mappers.IRestaurantEntityMapper;
import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.repositories.IRestaurantRepository;
import com.pragma.powerup.plazoletamicroservice.domain.model.Restaurant;
import com.pragma.powerup.plazoletamicroservice.domain.spi.IRestaurantPersistencePort;
import lombok.RequiredArgsConstructor;

import java.util.List;

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
        return restaurantEntityMapper.toRestaranteList(roleEntityList);
    }

    @Override
    public void saveRestaurant(Restaurant restaurant){

        if(restaurantRepository.findByNit(restaurant.getNit()).isPresent()){
            throw new RestaurantAlreadyExistException();
        }

        restaurantRepository.save(restaurantEntityMapper.toEntity(restaurant));






        //RestauranteEntity restauranteEntity = restauranteEntityMapper.toEntity(restaurante);
        //Optional<UsuarioEntity> usuarioEntity = usuarioRepository.findById(restauranteEntity.getIdUsuarioPropietario().getId());
        //if(usuarioEntity.isPresent()){
        //    Optional<RoleEntity> roleUsuarioEntity = roleRepository.findById(usuarioEntity.get().getIdRol().getId());
        //    if(roleUsuarioEntity.isPresent() && roleUsuarioEntity.get().getName().equals("ROLE_PROPIETARIO")){
                //restauranteRepository.save(restauranteEntityMapper.toEntity(restaurante));
        //    }else{
        //        throw new UserItsNotPropietario();
        //    }
        //}else{
        //    throw new UserNotFoundException();
        //}
    }
}
