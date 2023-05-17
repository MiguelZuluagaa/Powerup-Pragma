package com.pragma.powerup.plazoletamicroservice.domain.usecase;

import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.exceptions.UserItsNotOwner;
import com.pragma.powerup.plazoletamicroservice.domain.api.IDishServicePort;
import com.pragma.powerup.plazoletamicroservice.domain.model.Dish;
import com.pragma.powerup.plazoletamicroservice.domain.spi.IDishPersistencePort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

import static com.pragma.powerup.plazoletamicroservice.configuration.Constants.ROLE_OWNER;

public class DishUseCase implements IDishServicePort {
    private final IDishPersistencePort dishPersistencePort;

    public DishUseCase(IDishPersistencePort dishPersistencePort) {
        this.dishPersistencePort = dishPersistencePort;
    }

    @Override
    public List<Dish> getAllDishes() {
        return dishPersistencePort.getAllDishes();
    }

    @Override
    public void saveDish(Dish dish) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String roleUser = authentication.getAuthorities().toArray()[0].toString();
        //Todo: We need validate de user authenticated is the owner of the restaurant declared in the dish
        if(roleUser.contains(ROLE_OWNER)){
            dish.setActive(true); // Always the dish is active when the owner create it
            dishPersistencePort.saveDish(dish);
        }else{
            throw new UserItsNotOwner();
        }
    }
}
