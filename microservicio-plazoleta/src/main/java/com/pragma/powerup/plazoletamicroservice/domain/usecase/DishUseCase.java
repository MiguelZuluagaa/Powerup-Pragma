package com.pragma.powerup.plazoletamicroservice.domain.usecase;

import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.entity.*;
import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.exceptions.UserItsNotOwner;
import com.pragma.powerup.plazoletamicroservice.domain.api.IDishServicePort;
import com.pragma.powerup.plazoletamicroservice.domain.exceptions.DishIsAlreadyActiveException;
import com.pragma.powerup.plazoletamicroservice.domain.exceptions.DishIsAlreadyDisableException;
import com.pragma.powerup.plazoletamicroservice.domain.exceptions.DishNotFound;
import com.pragma.powerup.plazoletamicroservice.domain.exceptions.UserItsNotOwnerOfTheRestaurant;
import com.pragma.powerup.plazoletamicroservice.domain.model.Dish;
import com.pragma.powerup.plazoletamicroservice.domain.model.DishAttributeValue;
import com.pragma.powerup.plazoletamicroservice.domain.model.Restaurant;
import com.pragma.powerup.plazoletamicroservice.domain.spi.ICategoryPersistencePort;
import com.pragma.powerup.plazoletamicroservice.domain.spi.IDishPersistencePort;
import com.pragma.powerup.plazoletamicroservice.domain.spi.IRestaurantPersistencePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.pragma.powerup.plazoletamicroservice.configuration.Constants.ROLE_OWNER;

public class DishUseCase implements IDishServicePort {
    private final IDishPersistencePort dishPersistencePort;

    @Autowired
    private IRestaurantPersistencePort restaurantPersistencePort;
    @Autowired
    private ICategoryPersistencePort categoryPersistencePort;
    @Autowired
    private DishAttributeValueUseCase dishAttributeValueUseCase;


    public DishUseCase(IDishPersistencePort dishPersistencePort) {
        this.dishPersistencePort = dishPersistencePort;
    }

    @Override
    public List<Dish> getAllDishes() {
        return dishPersistencePort.getAllDishes();
    }

    @Override
    public List<Dish> getDishesByCategory(Long idCategory, Long pageSize, Long offset) {
        categoryPersistencePort.existCategoryById(idCategory);
        return dishPersistencePort.getDishesByCategory(idCategory, pageSize, offset);
    }

    @Override
    @Transactional
    public void saveDish(Dish dish, HashMap<Long, String> complements) {
        if(itsOwner()){ // Validate de user, need be owner to create a dish
            if(userAuthenticatedItsOwnerOfTheRestaurant(dish.getIdRestaurant().getId())){ // Validate if the user authenticated is the owner of the restaurant
                if(categoryPersistencePort.existCategoryById(dish.getIdCategory().getId())){ // Validate if the category of the dish exist
                    dish.setActive(true); // Always the dish is active when the owner create it
                    DishEntity dishSaved = dishPersistencePort.saveDish(dish);
                    if(complements != null) insertComplements(dishSaved.getId(),complements);
                }
            }
        }
    }

    public void insertComplements(Long idDish, HashMap<Long, String> complements){
        for (Map.Entry<Long, String> entry : complements.entrySet()) {
            DishAttributeValue record = new DishAttributeValue();
            record.setIdDish(new DishEntity(idDish));
            record.setIdAttributeDish(new AttributeDishEntity(entry.getKey()));
            record.setIdValueAttributeDish(new ValueAttributeDishEntity(null,entry.getValue()));
            dishAttributeValueUseCase.save(record);
        }
    }

    @Override
    public void updateDish(Dish dish) {
        Optional<Dish> dishFound = dishPersistencePort.findDishById(dish.getId());
        if(!dishFound.isPresent()){
            throw new DishNotFound();
        }
        if(itsOwner() && userAuthenticatedItsOwnerOfTheRestaurant(dishFound.get().getIdRestaurant().getId())) {
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

    @Override
    public void activeDish(Long idDish) {
        Optional<Dish> dishFound = dishPersistencePort.findDishById(idDish);
        if(!dishFound.isPresent()){
            throw new DishNotFound();
        }
        if(itsOwner() && userAuthenticatedItsOwnerOfTheRestaurant(dishFound.get().getIdRestaurant().getId())) {
            if (dishFound.get().getActive()) { // Validate if the dish is active already
                throw new DishIsAlreadyActiveException();
            }
            dishFound.get().setActive(true);
            dishPersistencePort.updateDish(dishFound.get());
        }
    }

    @Override
    public void disableDish(Long idDish) {
        Optional<Dish> dishFound = dishPersistencePort.findDishById(idDish);
        if(!dishFound.isPresent()){
            throw new DishNotFound();
        }
        if(itsOwner() && userAuthenticatedItsOwnerOfTheRestaurant(dishFound.get().getIdRestaurant().getId())) {
            if (!dishFound.get().getActive()) { // Validate if the dish is disabled already
                throw new DishIsAlreadyDisableException();
            }
            dishFound.get().setActive(false);
            dishPersistencePort.updateDish(dishFound.get());
        }
    }

    private Boolean itsOwner(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); // Get the user authenticated
        String roleToValidate = authentication.getAuthorities().toArray()[0].toString(); // Get the role of the user authenticated
        if(!roleToValidate.contains(ROLE_OWNER)){
            throw new UserItsNotOwner();
        }
        return true;
    }

    private Boolean userAuthenticatedItsOwnerOfTheRestaurant(Long idRestaurant){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); // Get the user authenticated
        PrincipalUser principalUser = (PrincipalUser) authentication.getPrincipal(); // Get the user authenticated
        Long idUserAuthenticated = principalUser.getId(); // Get the id of the user authenticated
        Optional<Restaurant> restaurant = restaurantPersistencePort.findRestaurantById(idRestaurant);
        if(restaurant.get().getIdUserOwner() != idUserAuthenticated){
            throw new UserItsNotOwnerOfTheRestaurant();
        }
        return true;
    }
}
