package com.pragma.powerup.plazoletamicroservice.domain.usecase;

import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.entity.PrincipalUser;
import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.exceptions.UserItsNotOwner;
import com.pragma.powerup.plazoletamicroservice.domain.api.IDishServicePort;
import com.pragma.powerup.plazoletamicroservice.domain.exceptions.DishNotFound;
import com.pragma.powerup.plazoletamicroservice.domain.exceptions.UserItsNotOwnerOfTheRestaurant;
import com.pragma.powerup.plazoletamicroservice.domain.model.Dish;
import com.pragma.powerup.plazoletamicroservice.domain.model.Restaurant;
import com.pragma.powerup.plazoletamicroservice.domain.spi.ICategoryPersistencePort;
import com.pragma.powerup.plazoletamicroservice.domain.spi.IDishPersistencePort;
import com.pragma.powerup.plazoletamicroservice.domain.spi.IRestaurantPersistencePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;
import java.util.Optional;

import static com.pragma.powerup.plazoletamicroservice.configuration.Constants.ROLE_OWNER;

public class DishUseCase implements IDishServicePort {
    private final IDishPersistencePort dishPersistencePort;

    @Autowired
    private IRestaurantPersistencePort restaurantPersistencePort;

    @Autowired
    private ICategoryPersistencePort categoryPersistencePort;

    public DishUseCase(IDishPersistencePort dishPersistencePort) {
        this.dishPersistencePort = dishPersistencePort;
    }

    @Override
    public List<Dish> getAllDishes() {
        return dishPersistencePort.getAllDishes();
    }

    @Override
    public void saveDish(Dish dish) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); // Get the user authenticated
        String roleUser = authentication.getAuthorities().toArray()[0].toString(); // Get the role of the user authenticated
        PrincipalUser principalUser = (PrincipalUser) authentication.getPrincipal(); // Get the user authenticated
        Long idUserLogged = principalUser.getId(); // Get the id of the user authenticated
        if(itsOwner(roleUser)){ // Validate de user, need be owner to create a dish
            if(userAuthenticatedItsOwnerOfTheRestaurant(dish.getIdRestaurant().getId(), idUserLogged)){ // Validate if the user authenticated is the owner of the restaurant
                if(categoryPersistencePort.existCategoryById(dish.getIdCategory().getId())){ // Validate if the category of the dish exist
                    dish.setActive(true); // Always the dish is active when the owner create it
                    dishPersistencePort.saveDish(dish);
                }
            }
        }
    }

    private Boolean itsOwner(String roleToValidate){
        if(!roleToValidate.contains(ROLE_OWNER)){
            throw new UserItsNotOwner();
        }
        return true;
    }

    private Boolean userAuthenticatedItsOwnerOfTheRestaurant(Long idRestaurant, Long idUserAuthenticated){
        Optional<Restaurant> restaurant = restaurantPersistencePort.findRestaurantById(idRestaurant);
        if(restaurant.get().getIdUserOwner() != idUserAuthenticated){
            throw new UserItsNotOwnerOfTheRestaurant();
        }
        return true;
    }

    @Override
    public void updateDish(Dish dish) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); // Get the user authenticated
        String roleUser = authentication.getAuthorities().toArray()[0].toString(); // Get the role of the user authenticated
        PrincipalUser principalUser = (PrincipalUser) authentication.getPrincipal(); // Get the user authenticated
        Long idUserLogged = principalUser.getId(); // Get the id of the user authenticated
        Optional<Dish> dishFound = dishPersistencePort.findDishById(dish.getId());
        if(!dishFound.isPresent()){
            throw new DishNotFound();
        }
        if(itsOwner(roleUser) && userAuthenticatedItsOwnerOfTheRestaurant(dishFound.get().getIdRestaurant().getId(),idUserLogged)) {
            try {
                dish.setId(dishFound.get().getId());
                dish.setName(dishFound.get().getName());
                dish.setUrlImage(dishFound.get().getUrlImage());
                dish.setActive(dishFound.get().getActive());
                dish.setIdCategory(dishFound.get().getIdCategory());
                dish.setIdRestaurant(dishFound.get().getIdRestaurant());

                dishPersistencePort.updateDish(dish);
            } catch (Exception e) {
                throw new RuntimeException();
            }
        }
    }
}
