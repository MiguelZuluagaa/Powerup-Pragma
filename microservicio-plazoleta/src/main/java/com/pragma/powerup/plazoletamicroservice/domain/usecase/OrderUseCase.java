package com.pragma.powerup.plazoletamicroservice.domain.usecase;

import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.entity.*;
import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.mappers.IDishEntityMapper;
import com.pragma.powerup.plazoletamicroservice.adapters.driving.http.assets.DishAsset;
import com.pragma.powerup.plazoletamicroservice.adapters.driving.http.dto.request.CreateOrderRequestDto;
import com.pragma.powerup.plazoletamicroservice.domain.api.IOrderServicePort;
import com.pragma.powerup.plazoletamicroservice.domain.exceptions.ParametersNegativesException;
import com.pragma.powerup.plazoletamicroservice.domain.exceptions.SomeDishesAreNotFromRestaurantException;
import com.pragma.powerup.plazoletamicroservice.domain.model.Dish;
import com.pragma.powerup.plazoletamicroservice.domain.model.Order;
import com.pragma.powerup.plazoletamicroservice.domain.spi.IDishPersistencePort;
import com.pragma.powerup.plazoletamicroservice.domain.spi.IOrderDishPersistencePort;
import com.pragma.powerup.plazoletamicroservice.domain.spi.IOrderPersistencePort;
import com.pragma.powerup.plazoletamicroservice.domain.spi.IRestaurantPersistencePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.pragma.powerup.plazoletamicroservice.configuration.Constants.STATUS_ORDER_IN_PROGRESS_ID;

public class OrderUseCase implements IOrderServicePort {
    private final IOrderPersistencePort orderPersistencePort;

    @Autowired
    private IOrderDishPersistencePort orderDishPersistencePort;
    @Autowired
    private IDishPersistencePort dishPersistencePort;
    @Autowired
    private IDishEntityMapper dishEntityMapper;
    @Autowired
    private IRestaurantPersistencePort restaurantPersistencePort;

    public OrderUseCase(IOrderPersistencePort orderPersistencePort) {
        this.orderPersistencePort = orderPersistencePort;
    }

    private Boolean userCanCreateNewOrder(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); // Get the user authenticated
        PrincipalUser principalUser = (PrincipalUser) authentication.getPrincipal(); // Get the user authenticated
        Long idUserAuthenticated = principalUser.getId(); // Get the id of the user authenticated
        return orderPersistencePort.userCanCreateNewOrder(idUserAuthenticated);
    }

    private OrderEntity initializeOrder(/*Long idChef, */RestaurantEntity restaurant){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); // Get the user authenticated
        PrincipalUser principalUser = (PrincipalUser) authentication.getPrincipal(); // Get the user authenticated
        Long idUserAuthenticated = principalUser.getId(); // Get the id of the user authenticated

        restaurantPersistencePort.findRestaurantById(restaurant.getId());

        OrderEntity order = new OrderEntity();
        order.setIdUser(idUserAuthenticated);
        //order.setIdChef(idChef);
        order.setDate(new Date());
        order.setIdStatus(new OrderStatusEntity(STATUS_ORDER_IN_PROGRESS_ID,null,null));
        order.setIdRestaurant(restaurant);

        return order;
    }

    private ArrayList<OrderDishEntity> validateDishesToSave(ArrayList<DishAsset> dishesRequestDto, Long idRestaurant, OrderEntity order){
        ArrayList<OrderDishEntity> dishesToSave = new ArrayList<>();

        for(DishAsset dish : dishesRequestDto){
            Boolean dishExist = dishPersistencePort.existDishById(dish.getIdDish());
            if(dishExist) {
                Optional<Dish> dishFound = dishPersistencePort.findDishById(dish.getIdDish());
                DishEntity dishEntity = dishEntityMapper.toDishEntity(dishFound.get());
                dishesToSave.add(new OrderDishEntity(null, order, dishEntity, dish.getQuantity()));
            }else{
                deleteOrderById(order.getId());
                throw new SomeDishesAreNotFromRestaurantException();
            }
        }
        return dishesToSave;
    }

    public void createOrder(CreateOrderRequestDto createOrderRequestDto) {
        /*Long idChefDto = createOrderRequestDto.getIdChef();*/


        RestaurantEntity restaurantDto = new RestaurantEntity(createOrderRequestDto.getIdRestaurant());
        if(userCanCreateNewOrder()) {

            OrderEntity order = initializeOrder(/*idChefDto, */restaurantDto);
            orderPersistencePort.createOrder(order);

            ArrayList<OrderDishEntity> dishesToSave = validateDishesToSave(createOrderRequestDto.getDishes(), restaurantDto.getId(), order);
            orderDishPersistencePort.saveOrderDishes(dishesToSave);
        }
    }

    private void deleteOrderById(Long idOrder){
        orderPersistencePort.deleteOrderById(idOrder);
    }

    @Override
    public List<Order> getOrdersByStatus(Long idRestaurant, Long idStatus, Long offset, Long pageSize) {
        if (idRestaurant < 0 || idStatus < 0 || offset < 0 || pageSize < 0) {
            throw new ParametersNegativesException();
        }
        return orderPersistencePort.getOrdersByStatus(idRestaurant,idStatus,offset,pageSize);
    }

    @Override
    public void takeOrder(Long idOrder) {
        if (idOrder < 0) {
            throw new ParametersNegativesException();
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); // Get the user authenticated
        PrincipalUser principalUser = (PrincipalUser) authentication.getPrincipal(); // Get the user authenticated
        Long idUserAuthenticated = principalUser.getId(); // Get the id of the user authenticated

        orderPersistencePort.takeOrder(idOrder,idUserAuthenticated);
    }

}