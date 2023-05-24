package com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.adapter;

import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.entity.OrderEntity;
import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.entity.OrderStatusEntity;
import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.exceptions.UserWithOrderInProgressException;
import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.mappers.IOrderEntityMapper;
import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.repositories.IOrderRepository;
import com.pragma.powerup.plazoletamicroservice.adapters.driving.http.dto.request.CreateOrderRequestDto;
import com.pragma.powerup.plazoletamicroservice.adapters.driving.http.dto.request.OrderRequestDto;
import com.pragma.powerup.plazoletamicroservice.adapters.driving.http.dto.response.OrderResponseDto;
import com.pragma.powerup.plazoletamicroservice.domain.model.Order;
import com.pragma.powerup.plazoletamicroservice.domain.spi.IOrderPersistencePort;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class OrderMysqlAdapter implements IOrderPersistencePort {
    private final IOrderRepository orderRepository;
    private final IOrderEntityMapper orderEntityMapper;

    private OrderStatusEntity statusInProgress = new OrderStatusEntity(1L,null,null);

    @Override
    public void createOrder(OrderEntity orderEntity) {
        orderRepository.save(orderEntity);
    }

    @Override
    public Boolean userCanCreateNewOrder(Long idUser) {
        Optional<OrderEntity> orderEntity = orderRepository.findFirstByIdUserAndIdStatus(idUser, statusInProgress);
        if(orderEntity.isPresent()){
            throw new UserWithOrderInProgressException();
        }
        return true;
    }

    @Override
    public void deleteOrderById(Long idOrder) {
        Optional<OrderEntity> orderFound = orderRepository.findById(idOrder);
        if(!orderFound.isPresent()){
            orderRepository.deleteById(idOrder);
        }
    }

    @Override
    public List<Order> getOrdersByStatus(OrderRequestDto orderRequestDto) {
        Optional<List<OrderEntity>> ordersFound = orderRepository.findAllByIdStatusAndIdRestaurant(orderRequestDto.getIdStatus(), orderRequestDto.getIdRestaurant());
        if(!ordersFound.isPresent()){
            throw new RuntimeException();
        }
        return orderEntityMapper.toOrderList(ordersFound.get());
    }
}
