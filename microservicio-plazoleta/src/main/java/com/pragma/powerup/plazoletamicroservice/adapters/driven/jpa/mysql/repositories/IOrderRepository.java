package com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.repositories;

import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.entity.OrderEntity;
import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.entity.OrderStatusEntity;
import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.entity.RestaurantEntity;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static com.pragma.powerup.plazoletamicroservice.configuration.Constants.STATUS_ORDER_IN_PENDING_ID;
import static com.pragma.powerup.plazoletamicroservice.configuration.Constants.STATUS_ORDER_PENDING;

public interface IOrderRepository extends JpaRepository<OrderEntity, Long> {
    Optional<OrderEntity> findFirstByIdUserAndIdStatus(Long idUser, OrderStatusEntity status);
    Optional<List<OrderEntity>> findAllByIdRestaurantAndIdStatus(RestaurantEntity restaurant, OrderStatusEntity status, PageRequest pageRequest);
    Optional<List<OrderEntity>> findAllByIdRestaurantAndIdStatus(RestaurantEntity idRestaurant, OrderStatusEntity status);
    Optional<List<OrderEntity>> findAllByIdRestaurant(RestaurantEntity idRestaurant);
    void deleteByIdRestaurant(RestaurantEntity idRestaurant);

    @Query(value = "SELECT id_chef, AVG(completion_time_minutes) AS prom\n" +
            "FROM plazoleta.order_head\n" +
            "WHERE id_status = 5\n" +
            "GROUP BY id_chef\n" +
            "ORDER BY prom;", nativeQuery = true)
    Optional<List<Object>> testMethod();

    @Query(value = "SELECT order_head.id\n" +
            "FROM plazoleta.order_head order_head\n" +
            "INNER JOIN plazoleta.restaurant restaurant ON order_head.id_restaurant = restaurant.id\n" +
            "WHERE order_head.id_status = 3 \n" +
            "AND timestampdiff(MINUTE, order_head.date, now()) > restaurant.max_processing_time_order;", nativeQuery = true)
    Optional<List<Long>> getAllOrdersWithMaxProcessingTime();

    @Query(value = "SELECT * FROM plazoleta.order_head WHERE id_status != 5 AND id_status != 6 AND id_restaurant = :idRestaurant", nativeQuery = true)
    Optional<List<OrderEntity>> getOrdersInCurseByIdRestaurant(@Param("idRestaurant") Long idRestaurant);

    Optional<Set<OrderEntity>> getAllByIdChefIsNullAndIdRestaurant(RestaurantEntity idRestaurant);

    //restaurantId
    //restaurantId
    //restaurantId
    //jpql
    //Criteria Hibernate
}
