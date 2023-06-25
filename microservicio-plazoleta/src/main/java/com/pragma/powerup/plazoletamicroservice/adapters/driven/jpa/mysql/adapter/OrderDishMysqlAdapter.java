package com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.adapter;

import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.entity.OrderDishEntity;
import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.entity.OrderEntity;
import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.mappers.IOrderDishEntityMapper;
import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.repositories.IOrderDishRepository;
import com.pragma.powerup.plazoletamicroservice.domain.spi.IOrderDishPersistencePort;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Set;

@RequiredArgsConstructor
public class OrderDishMysqlAdapter implements IOrderDishPersistencePort {
    private final IOrderDishRepository orderDishRepository;
    private final IOrderDishEntityMapper orderDishEntityMapper;

    @Override
    public void saveOrderDishes(ArrayList<OrderDishEntity> orderDishEntities) {
        orderDishRepository.saveAll(orderDishEntities);
    }

    @Override
    public void deleteOrderByOrderId(Long idOrder) {
        orderDishRepository.deleteByIdOrder(new OrderEntity(idOrder));
    }

    @Override
    public Optional<Set<OrderDishEntity>> getOrderDishesByIdOrder(Long idOrder) {
       return orderDishRepository.getAllByIdOrder(new OrderEntity(idOrder));
    }
}
