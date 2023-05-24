package com.pragma.powerup.plazoletamicroservice.domain.usecase;

import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.entity.*;
import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.mappers.IDishEntityMapper;
import com.pragma.powerup.plazoletamicroservice.adapters.driving.http.dto.request.CreateOrderRequestDto;
import com.pragma.powerup.plazoletamicroservice.domain.api.IOrderServicePort;
import com.pragma.powerup.plazoletamicroservice.domain.exceptions.SomeDishesAreNotFromRestaurantException;
import com.pragma.powerup.plazoletamicroservice.domain.model.Dish;
import com.pragma.powerup.plazoletamicroservice.domain.spi.IDishPersistencePort;
import com.pragma.powerup.plazoletamicroservice.domain.spi.IOrderDishPersistencePort;
import com.pragma.powerup.plazoletamicroservice.domain.spi.IOrderPersistencePort;
import com.pragma.powerup.plazoletamicroservice.domain.spi.IRestaurantPersistencePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.Date;
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

    private OrderStatusEntity statusInProgress = new OrderStatusEntity(STATUS_ORDER_IN_PROGRESS_ID,null,null);

    public OrderUseCase(IOrderPersistencePort orderPersistencePort) {
        this.orderPersistencePort = orderPersistencePort;
    }

    public void createOrder(CreateOrderRequestDto createOrderRequestDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); // Get the user authenticated
        PrincipalUser principalUser = (PrincipalUser) authentication.getPrincipal(); // Get the user authenticated
        Long idUserAuthenticated = principalUser.getId(); // Get the id of the user authenticated

        if(orderPersistencePort.userCanCreateNewOrder(idUserAuthenticated)) {

            OrderEntity order = initializeOrder(idUserAuthenticated, createOrderRequestDto.getIdChef(), createOrderRequestDto.getIdRestaurant());
            restaurantPersistencePort.findRestaurantById(order.getIdRestaurant().getId());
            orderPersistencePort.createOrder(order);

            ArrayList<OrderDishEntity> dishesRequestDto = createOrderRequestDto.getDishes();
            ArrayList<OrderDishEntity> dishesToSave = new ArrayList<>();

            for(OrderDishEntity dish : dishesRequestDto){
                Optional<Dish> dishFound = dishPersistencePort.findDishById(dish.getIdDish().getId());
                if (dishFound.get().getIdRestaurant().getId() != createOrderRequestDto.getIdRestaurant().getId()) {
                    deleteOrderById(order.getId());
                    throw new SomeDishesAreNotFromRestaurantException();
                }
                DishEntity dishEntity = dishEntityMapper.toDishEntity(dishFound.get());
                dishesToSave.add( new OrderDishEntity(null, order, dishEntity, dish.getQuantity()) );
            }
            orderDishPersistencePort.saveOrderDishes(dishesToSave);
        }
    }

    private void deleteOrderById(Long idOrder){
        orderPersistencePort.deleteOrderById(idOrder);
    }

    private OrderEntity initializeOrder(Long idUser, Long idChef, RestaurantEntity restaurant){
        OrderEntity order = new OrderEntity();
        order.setIdUser(idUser);
        order.setIdChef(idChef);
        order.setDate(new Date());
        order.setIdStatus(statusInProgress);
        order.setIdRestaurant(restaurant);

        return order;
    }

    private ArrayList<Optional<Dish>> getDataDishes(ArrayList<OrderDishEntity> dishes){
        ArrayList<Optional<Dish>> dishesToReturn = new ArrayList<>();
        for (OrderDishEntity dish : dishes) {
            Optional<Dish> dishFound = dishPersistencePort.findDishById(dish.getId());
            dishesToReturn.add(dishFound);
        }
        return dishesToReturn;
    }
}
