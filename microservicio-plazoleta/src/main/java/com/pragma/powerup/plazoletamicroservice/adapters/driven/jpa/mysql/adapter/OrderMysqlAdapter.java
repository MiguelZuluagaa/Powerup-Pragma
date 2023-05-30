package com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.adapter;

import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.entity.OrderEntity;
import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.entity.OrderStatusEntity;
import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.entity.RestaurantEntity;
import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.exceptions.NoDataFoundException;
import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.exceptions.OrderNotFoundException;
import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.exceptions.UserWithOrderInProgressException;
import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.mappers.IOrderEntityMapper;
import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.repositories.IOrderRepository;
import com.pragma.powerup.plazoletamicroservice.domain.exceptions.OrderIsAlreadyTakenException;
import com.pragma.powerup.plazoletamicroservice.domain.model.Order;
import com.pragma.powerup.plazoletamicroservice.domain.spi.IOrderPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

import static com.pragma.powerup.plazoletamicroservice.configuration.Constants.STATUS_ORDER_IN_PREPARATION_ID;
import static com.pragma.powerup.plazoletamicroservice.configuration.Constants.STATUS_ORDER_IN_PROGRESS_ID;

@RequiredArgsConstructor
public class OrderMysqlAdapter implements IOrderPersistencePort {
    private final IOrderRepository orderRepository;
    private final IOrderEntityMapper orderEntityMapper;

    @Override
    public void createOrder(OrderEntity orderEntity) {
        orderRepository.save(orderEntity);
    }

    @Override
    public Boolean userCanCreateNewOrder(Long idUser) {
        Optional<OrderEntity> orderEntity = orderRepository.findFirstByIdUserAndIdStatus(idUser, new OrderStatusEntity(STATUS_ORDER_IN_PROGRESS_ID));
        if(orderEntity.isPresent()){
            throw new UserWithOrderInProgressException();
        }
        return true;
    }

    @Override
    public void deleteOrderById(Long idOrder) {
        Optional<OrderEntity> orderFound = orderRepository.findById(idOrder);
        if(!orderFound.isPresent()){
           throw new OrderNotFoundException();
        }
        orderRepository.deleteById(idOrder);
    }

    @Override
    public List<Order> getOrdersByStatus(Long idRestaurant, Long idStatus, Long offset, Long pageSize) {
        if(pageSize < 1){
            throw new NoDataFoundException();
        }
        RestaurantEntity restaurant = new RestaurantEntity(idRestaurant);
        OrderStatusEntity status = new OrderStatusEntity(idStatus);

        Optional<List<OrderEntity>> ordersFound = orderRepository.findAllByIdRestaurantAndIdStatus(
                restaurant,
                status,
                (PageRequest.of(Math.toIntExact(offset), Math.toIntExact(pageSize))));
        if(ordersFound.get().size() < 1 || pageSize < 1){
            throw new NoDataFoundException();
        }
        return orderEntityMapper.toOrderList(ordersFound.get());
    }

    @Override
    public void takeOrder(Long idOrder, Long idUser) {
        Optional<OrderEntity> orderFound = findOrderById(idOrder);
        if(orderFound.get().getIdStatus().getId() == STATUS_ORDER_IN_PREPARATION_ID){
            throw new OrderIsAlreadyTakenException();
        }
        orderFound.get().setIdStatus(new OrderStatusEntity(STATUS_ORDER_IN_PREPARATION_ID));
        orderFound.get().setIdChef(idUser);
        orderRepository.save(orderFound.get());
    }

    @Override
    public Optional<OrderEntity> findOrderById(Long idOrder) {
        Optional<OrderEntity> orderFound = orderRepository.findById(idOrder);
        if(!orderFound.isPresent()){
            throw new OrderNotFoundException();
        }
        return orderFound;
    }
}
