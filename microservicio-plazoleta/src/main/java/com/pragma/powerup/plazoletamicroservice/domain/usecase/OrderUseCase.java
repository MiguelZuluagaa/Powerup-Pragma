package com.pragma.powerup.plazoletamicroservice.domain.usecase;

import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.entity.*;
import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.mappers.IDishEntityMapper;
import com.pragma.powerup.plazoletamicroservice.adapters.driven.microservices.client.IMessengerFeignClient;
import com.pragma.powerup.plazoletamicroservice.adapters.driven.microservices.client.ITrackingFeignClient;
import com.pragma.powerup.plazoletamicroservice.adapters.driving.http.assets.DishAsset;
import com.pragma.powerup.plazoletamicroservice.adapters.driving.http.dto.request.CreateOrderRequestDto;
import com.pragma.powerup.plazoletamicroservice.adapters.driving.http.dto.request.FinishOrderDto;
import com.pragma.powerup.plazoletamicroservice.domain.api.IOrderServicePort;
import com.pragma.powerup.plazoletamicroservice.domain.exceptions.*;
import com.pragma.powerup.plazoletamicroservice.domain.model.Dish;
import com.pragma.powerup.plazoletamicroservice.domain.model.Order;
import com.pragma.powerup.plazoletamicroservice.domain.model.Tracking;
import com.pragma.powerup.plazoletamicroservice.domain.spi.IDishPersistencePort;
import com.pragma.powerup.plazoletamicroservice.domain.spi.IOrderDishPersistencePort;
import com.pragma.powerup.plazoletamicroservice.domain.spi.IOrderPersistencePort;
import com.pragma.powerup.plazoletamicroservice.domain.spi.IRestaurantPersistencePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.ObjectError;

import java.lang.reflect.Array;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.Temporal;
import java.util.*;
import java.util.stream.Collectors;

import static com.pragma.powerup.plazoletamicroservice.configuration.Constants.*;

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
    @Autowired
    private IMessengerFeignClient messengerFeignClient;
    @Autowired
    private ITrackingFeignClient trackingFeignClient;

    public OrderUseCase(IOrderPersistencePort orderPersistencePort) {
        this.orderPersistencePort = orderPersistencePort;
    }

    private void sendNotificationToUser(String statusOrder, String phoneNumber){
        messengerFeignClient.sendMessage(statusOrder, phoneNumber);
    }

    private void createLoggOrder(OrderEntity order, String previousStatus, String currentStatus){
        Tracking tracking = initializeTracking(order, previousStatus, currentStatus);
        trackingFeignClient.trackingOrder(tracking);
    }

    private Tracking initializeTracking(OrderEntity order, String previousStatus, String currentStatus){
        Tracking tracking = new Tracking();
        tracking.setIdOrder(order.getId());
        tracking.setIdEmployee(order.getIdChef());
        tracking.setIdCustomer(order.getIdUser());
        tracking.setIdRestaurant(order.getIdRestaurant().getId());
        tracking.setPreviousStatus(previousStatus);
        tracking.setCurrentStatus(currentStatus);
        return tracking;
    }

    private Boolean userCanCreateNewOrder(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); // Get the user authenticated
        PrincipalUser principalUser = (PrincipalUser) authentication.getPrincipal(); // Get the user authenticated
        Long idUserAuthenticated = principalUser.getId(); // Get the id of the user authenticated
        return orderPersistencePort.userCanCreateNewOrder(idUserAuthenticated);
    }

    private OrderEntity initializeOrder(RestaurantEntity restaurant){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); // Get the user authenticated
        PrincipalUser principalUser = (PrincipalUser) authentication.getPrincipal(); // Get the user authenticated
        Long idUserAuthenticated = principalUser.getId(); // Get the id of the user authenticated

        restaurantPersistencePort.findRestaurantById(restaurant.getId());

        OrderEntity order = new OrderEntity();
        order.setIdUser(idUserAuthenticated);
        //order.setIdChef(idChef);
        order.setDate(new Date());
        order.setIdStatus(new OrderStatusEntity(STATUS_ORDER_IN_PENDING_ID,null,null));
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

                if (dish.getQuantity() <= 0) {
                    deleteOrderById(order.getId());
                    throw new QuantityDishInvalidException();
                }

                dishesToSave.add(new OrderDishEntity(null, order, dishEntity, dish.getQuantity()));
            }else{
                deleteOrderById(order.getId());
                throw new SomeDishesAreNotFromRestaurantException();
            }
        }
        return dishesToSave;
    }

    public void createOrder(CreateOrderRequestDto createOrderRequestDto) {
        RestaurantEntity restaurantDto = new RestaurantEntity(createOrderRequestDto.getIdRestaurant());
        if(userCanCreateNewOrder()) {

            OrderEntity order = initializeOrder(restaurantDto);
            orderPersistencePort.createOrder(order);

            ArrayList<OrderDishEntity> dishesToSave = validateDishesToSave(createOrderRequestDto.getDishes(), restaurantDto.getId(), order);
            orderDishPersistencePort.saveOrderDishes(dishesToSave);
            sendNotificationToUser("Order #"+order.getId()+" created successfully", "+573004469428");
            createLoggOrder(order, "",STATUS_ORDER_PENDING);
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
    public List<Order> getReportOfOrdersCompleted(Long idRestaurant) {
        return orderPersistencePort.findAllByIdRestaurantAndIdStatus(idRestaurant,STATUS_ORDER_FINISHED);
    }

    @Override
    public Map<Long, Double> getReportOfOrdersCompletedByEmployee(Long idRestaurant) {
        Map<Long,Double> data = new HashMap<>();
        List<Order> orders = orderPersistencePort.findAllByIdRestaurantAndIdStatus(idRestaurant,STATUS_ORDER_FINISHED);

        int cont = 0;
        int contSize = 0;
        Long idUserPrevious = 0L;
        for(Order order : orders) {
            contSize++;
            if(data.get(order.getIdChef()) != null){
                Double newValue = data.get(order.getIdChef()) + order.getCompletionTimeMinutes();
                data.put(order.getIdChef(), newValue);
            }else{
                if(data.isEmpty()){
                    data.put(order.getIdChef(), order.getCompletionTimeMinutes());
                }else{
                    data.put(idUserPrevious, data.get(idUserPrevious)/cont);

                    cont = 0;
                    data.put(order.getIdChef(), order.getCompletionTimeMinutes());
                }
            }

            cont++;

            if(contSize == orders.size()){
                data.put(order.getIdChef(), data.get(order.getIdChef())/cont);
            }else{
                idUserPrevious = order.getIdChef();
            }
        }

        Map<Long, Double> sortedData = data.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue, LinkedHashMap::new));


        //Optional<List<Object>> test = orderPersistencePort.testMethod();


        return sortedData;
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
        sendNotificationToUser("Order #"+idOrder+" is in preparation", "+573004469428");

        Optional<OrderEntity> orderFound = orderPersistencePort.findOrderById(idOrder);
        createLoggOrder(orderFound.get(), STATUS_ORDER_PENDING,STATUS_ORDER_IN_PREPARATION);
    }

    @Override
    public void markAsReady(Long idOrder) {
        if (idOrder < 0) {
            throw new ParametersNegativesException();
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); // Get the user authenticated
        PrincipalUser principalUser = (PrincipalUser) authentication.getPrincipal(); // Get the user authenticated
        Long idUserAuthenticated = principalUser.getId(); // Get the id of the user authenticated

        Optional<OrderEntity> orderFound = orderPersistencePort.findOrderById(idOrder);

        if(orderFound.get().getIdStatus().getName().contains(STATUS_ORDER_IN_PREPARATION)) {
            if (orderFound.get().getIdChef() != idUserAuthenticated) {
                throw new UserCantMarkOrderReadyException();
            }
            orderFound.get().setPinOrder(generatePin());
            orderPersistencePort.markOrderReady(orderFound.get());
            sendNotificationToUser("Order #" + idOrder + " is ready to receive", "+573004469428");
            createLoggOrder(orderFound.get(), STATUS_ORDER_IN_PREPARATION,STATUS_ORDER_READY);
        }else{
            throw new CantMarkOrderReadyException();
        }
    }

    private String generatePin(){
        return "1234";
    }

    @Override
    public void cancelOrder(Long idOrder) {
        if (idOrder < 0) {
            throw new ParametersNegativesException();
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); // Get the user authenticated
        PrincipalUser principalUser = (PrincipalUser) authentication.getPrincipal(); // Get the user authenticated
        Long idUserAuthenticated = principalUser.getId(); // Get the id of the user authenticated

        Optional<OrderEntity> orderFound = orderPersistencePort.findOrderById(idOrder);

        if(orderFound.get().getIdUser() != idUserAuthenticated){
            throw new UserItsNotOwnerOrderException();
        }

        if(orderFound.get().getIdStatus().getName().contains(STATUS_ORDER_PENDING)) {
            if (!orderFound.get().getIdUser().equals(idUserAuthenticated)) {
                throw new UserItsNotOfTheOrderException();
            }
            orderPersistencePort.cancelOrder(orderFound.get());
            sendNotificationToUser("Order #" + idOrder + " was cancelled correctly", "+573004469428");
            createLoggOrder(orderFound.get(), STATUS_ORDER_PENDING,STATUS_ORDER_CANCELLED);
        }else{
            throw new UserCantCancelOrderException();
        }
    }

    private Double calcCompletionTimeOfTheOrder(Date dateCreated){
        LocalDateTime dateNow = LocalDateTime.now();

        Temporal temporal = LocalDateTime.from(dateCreated.toInstant().atZone(ZoneId.systemDefault()));

        Duration duration = Duration.between(temporal, dateNow);
        long minutes = duration.toMinutes();

        return (double) minutes;
    }

    @Override
    public void finishOrder(FinishOrderDto finishOrderDto) {
        if (finishOrderDto.getIdOrder() < 0) {
            throw new ParametersNegativesException();
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); // Get the user authenticated
        PrincipalUser principalUser = (PrincipalUser) authentication.getPrincipal(); // Get the user authenticated
        Long idUserAuthenticated = principalUser.getId(); // Get the id of the user authenticated

        Optional<OrderEntity> orderFound = orderPersistencePort.findOrderById(finishOrderDto.getIdOrder());

        if(orderFound.get().getIdStatus().getName().contains(STATUS_ORDER_READY)) {
            if (orderFound.get().getIdChef() != idUserAuthenticated) {
                throw new UserCantFinishedOrderException();
            }

            if(!orderFound.get().getPinOrder().equals(finishOrderDto.getPinOrder())){
                throw new PinWrongException();
            }

            orderFound.get().setCompletionTimeMinutes(calcCompletionTimeOfTheOrder(orderFound.get().getDate()));
            orderPersistencePort.markOrderFinished(orderFound.get());
            sendNotificationToUser("Order #" + finishOrderDto.getIdOrder() + " finished correctly", "+573004469428");
            createLoggOrder(orderFound.get(), STATUS_ORDER_READY,STATUS_ORDER_FINISHED_NAME);
        }else{
            throw new CantMarkOrderFinishedException();
        }
    }

    @Override
    public String getOrderStatusById(Long idOrder) {
        return orderPersistencePort.findOrderById(idOrder).get().getIdStatus().getName();
    }

}
