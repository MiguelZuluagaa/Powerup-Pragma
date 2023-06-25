package com.pragma.powerup.plazoletamicroservice.domain.usecase;

import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.entity.*;
import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.exceptions.NoDataFoundException;
import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.mappers.IDishEntityMapper;
import com.pragma.powerup.plazoletamicroservice.adapters.driven.microservices.client.IMessengerFeignClient;
import com.pragma.powerup.plazoletamicroservice.adapters.driven.microservices.client.ITrackingFeignClient;
import com.pragma.powerup.plazoletamicroservice.adapters.driving.http.assets.DishAsset;
import com.pragma.powerup.plazoletamicroservice.adapters.driving.http.dto.request.CreateOrderRequestDto;
import com.pragma.powerup.plazoletamicroservice.adapters.driving.http.dto.request.FinishOrderDto;
import com.pragma.powerup.plazoletamicroservice.configuration.security.jwt.JwtTokenFilter;
import com.pragma.powerup.plazoletamicroservice.domain.api.IOrderServicePort;
import com.pragma.powerup.plazoletamicroservice.domain.exceptions.*;
import com.pragma.powerup.plazoletamicroservice.domain.model.*;
import com.pragma.powerup.plazoletamicroservice.domain.spi.IDishPersistencePort;
import com.pragma.powerup.plazoletamicroservice.domain.spi.IOrderDishPersistencePort;
import com.pragma.powerup.plazoletamicroservice.domain.spi.IOrderPersistencePort;
import com.pragma.powerup.plazoletamicroservice.domain.spi.IRestaurantPersistencePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.Temporal;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static com.pragma.powerup.plazoletamicroservice.configuration.Constants.*;

public class OrderUseCase implements IOrderServicePort {
    private final IOrderPersistencePort orderPersistencePort;

    @Autowired
    private JwtTokenFilter jwtTokenFilter;
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
    @Autowired
    private DishAttributeValueUseCase dishAttributeValueUseCase;

    public OrderUseCase(IOrderPersistencePort orderPersistencePort) {
        this.orderPersistencePort = orderPersistencePort;
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
    public ArrayList<HashMap<OrderEntity, Set<OrderDishEntity>>> getPendingOrders(Long idRestaurant) {
        Optional<Set<OrderEntity>> recordsFound = orderPersistencePort.getAllByIdChefIsNullAndIdRestaurant(idRestaurant);
        if(recordsFound.get().size() < 0){
            throw new NoDataFoundException();
        }

        Set<OrderEntity> recordsOrderPending = recordsFound.get()
                .stream().
                filter(record -> record.getIdStatus().getName().contains("PENDING"))
                .collect(Collectors.toSet());

        HashMap<OrderEntity, Set<OrderDishEntity>> ordersWithDetails = new HashMap<>();
        recordsOrderPending.stream().forEach(record -> {
            Optional<Set<OrderDishEntity>> detailsOrder = orderDishPersistencePort.getOrderDishesByIdOrder(record.getId());
            ordersWithDetails.put(record,detailsOrder.get());
        });
        ArrayList<HashMap<OrderEntity, Set<OrderDishEntity>>> ordersSorted = sortOrders(ordersWithDetails);

        return ordersSorted;
    }

    private ArrayList<HashMap<OrderEntity, Set<OrderDishEntity>>> sortOrders(HashMap<OrderEntity, Set<OrderDishEntity>> orders){

        ArrayList<HashMap<OrderEntity, Set<OrderDishEntity>>> arrayOrdersMeat = new ArrayList<>();
        ArrayList<HashMap<OrderEntity, Set<OrderDishEntity>>> arrayOrdersSoups = new ArrayList<>();
        ArrayList<HashMap<OrderEntity, Set<OrderDishEntity>>> arrayOrdersDesserts = new ArrayList<>();
        ArrayList<HashMap<OrderEntity, Set<OrderDishEntity>>> arrayOrdersOthers = new ArrayList<>();

        for (Map.Entry<OrderEntity, Set<OrderDishEntity>> order : orders.entrySet()) {
            Set<OrderDishEntity> detailOrder = order.getValue();
            ArrayList<String> typesDishes = new ArrayList<>();

            detailOrder.stream().forEach(record ->{
                String typeDish = record.getIdDish().getIdTypeDish().getName();
                typesDishes.add(typeDish);
            });


            HashMap<OrderEntity, Set<OrderDishEntity>> hashMap = new HashMap<>();
            if(typesDishes.stream().anyMatch(type -> type.contains("MEAT"))){
                hashMap.put(order.getKey(), order.getValue());
                arrayOrdersMeat.add(hashMap);
            }else if(typesDishes.stream().anyMatch(type -> type.contains("SOUP"))){
                hashMap.put(order.getKey(), order.getValue());
                arrayOrdersSoups.add(hashMap);
            }else if(typesDishes.stream().anyMatch(type -> type.contains("DESSERTS"))){
                hashMap.put(order.getKey(), order.getValue());
                arrayOrdersDesserts.add(hashMap);
            }else{
                hashMap.put(order.getKey(), order.getValue());
                arrayOrdersOthers.add(hashMap);
            }
        }

        ArrayList<HashMap<OrderEntity, Set<OrderDishEntity>>> ordersToReturn = new ArrayList<>();

        arrayOrdersMeat = sortOrdersMeat(arrayOrdersMeat);
        arrayOrdersSoups = sortOrdersSoups(arrayOrdersSoups);
        arrayOrdersDesserts = sortOrdersDesserts(arrayOrdersDesserts);

        arrayOrdersMeat.forEach(record -> ordersToReturn.add(record));
        arrayOrdersSoups.forEach(record -> ordersToReturn.add(record));
        arrayOrdersDesserts.forEach(record -> ordersToReturn.add(record));
        arrayOrdersOthers.forEach(record -> ordersToReturn.add(record));

        return ordersToReturn;
    }

    private ArrayList<HashMap<OrderEntity, Set<OrderDishEntity>>> sortOrdersMeat(ArrayList<HashMap<OrderEntity, Set<OrderDishEntity>>> orders){
        int n = orders.size();

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1; j++) {
                HashMap<OrderEntity, Set<OrderDishEntity>> orderOne = orders.get(j);
                HashMap<OrderEntity, Set<OrderDishEntity>> orderTwo = orders.get(j+1);

                //Get max value of order one
                AtomicInteger maxValueOrderOne = new AtomicInteger();
                Set<OrderDishEntity> orderOneDetail = orderOne.values().stream().findFirst().get();
                orderOneDetail.forEach(record -> {
                    if(record.getIdDishAttributeValue() != null && record.getIdDishAttributeValue().getIdAttributeDish().getName().contains("GRAMOS")){
                        AtomicInteger newMeat = new AtomicInteger();
                        newMeat.set(Integer.parseInt(record.getIdDishAttributeValue().getIdValueAttributeDish().getValue()));
                        if(maxValueOrderOne.longValue() < newMeat.longValue()){
                            maxValueOrderOne.set(newMeat.intValue());
                        }
                    }
                });

                //Get max value of order two
                AtomicInteger maxValueOrderTwo = new AtomicInteger();
                Set<OrderDishEntity> orderTwoDetail = orderTwo.values().stream().findFirst().get();
                orderTwoDetail.forEach(record -> {
                    if(record.getIdDishAttributeValue() != null && record.getIdDishAttributeValue().getIdAttributeDish().getName().contains("GRAMOS")){
                        AtomicInteger newMeat = new AtomicInteger();
                        newMeat.set(Integer.parseInt(record.getIdDishAttributeValue().getIdValueAttributeDish().getValue()));
                        if(maxValueOrderTwo.longValue() < newMeat.longValue()){
                            maxValueOrderTwo.set(newMeat.intValue());
                        }
                    }
                });

                if(maxValueOrderOne.longValue() < maxValueOrderTwo.longValue()){
                    HashMap<OrderEntity, Set<OrderDishEntity>> temp = orders.get(j+1);
                    orders.set(j+1,orders.get(j));
                    orders.set(j,temp);
                }
            }
        }

        return orders;


    }

    private ArrayList<HashMap<OrderEntity, Set<OrderDishEntity>>> sortOrdersSoups(ArrayList<HashMap<OrderEntity, Set<OrderDishEntity>>> orders){
        int n = orders.size();

        HashMap<String, Long> prioritySoups = new HashMap<>();
        prioritySoups.put("ARROZ", 1L);
        prioritySoups.put("PAPAS", 2L);
        prioritySoups.put("YUCA", 3L);

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1; j++) {
                HashMap<OrderEntity, Set<OrderDishEntity>> orderOne = orders.get(j);
                HashMap<OrderEntity, Set<OrderDishEntity>> orderTwo = orders.get(j+1);

                //Get max value of order one
                AtomicInteger maxValueOrderOne = new AtomicInteger();
                Set<OrderDishEntity> orderOneDetail = orderOne.values().stream().findFirst().get();
                orderOneDetail.forEach(record -> {
                    if(record.getIdDishAttributeValue() != null && record.getIdDishAttributeValue().getIdAttributeDish().getName().contains("SIDE DISH")){
                        AtomicInteger newMeat = new AtomicInteger();
                        String typeSideDish = record.getIdDishAttributeValue().getIdValueAttributeDish().getValue();
                        newMeat.set(prioritySoups.get(typeSideDish).intValue());
                        newMeat.set(newMeat.intValue());
                        if(maxValueOrderOne.longValue() < newMeat.longValue()){
                            maxValueOrderOne.set(newMeat.intValue());
                        }
                    }
                });

                //Get max value of order two
                AtomicInteger maxValueOrderTwo = new AtomicInteger();
                Set<OrderDishEntity> orderTwoDetail = orderTwo.values().stream().findFirst().get();
                orderTwoDetail.forEach(record -> {
                    if(record.getIdDishAttributeValue() != null && record.getIdDishAttributeValue().getIdAttributeDish().getName().contains("SIDE DISH")){
                        AtomicInteger newMeat = new AtomicInteger();
                        String typeSideDish = record.getIdDishAttributeValue().getIdValueAttributeDish().getValue();
                        newMeat.set(prioritySoups.get(typeSideDish).intValue());
                        newMeat.set(newMeat.intValue());
                        if(maxValueOrderTwo.longValue() < newMeat.longValue()){
                            maxValueOrderTwo.set(newMeat.intValue());
                        }
                    }
                });

                if(maxValueOrderOne.longValue() < maxValueOrderTwo.longValue()){
                    HashMap<OrderEntity, Set<OrderDishEntity>> temp = orders.get(j+1);
                    orders.set(j+1,orders.get(j));
                    orders.set(j,temp);
                }
            }
        }

        return orders;
    }

    private ArrayList<HashMap<OrderEntity, Set<OrderDishEntity>>> sortOrdersDesserts(ArrayList<HashMap<OrderEntity, Set<OrderDishEntity>>> orders){
        int n = orders.size();

        HashMap<String, Long> priorityDesserts = new HashMap<>();
        priorityDesserts.put("HELADOS", 1L);
        priorityDesserts.put("FLANES", 2L);

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1; j++) {
                HashMap<OrderEntity, Set<OrderDishEntity>> orderOne = orders.get(j);
                HashMap<OrderEntity, Set<OrderDishEntity>> orderTwo = orders.get(j+1);

                //Get max value of order one
                AtomicInteger maxValueOrderOne = new AtomicInteger();
                Set<OrderDishEntity> orderOneDetail = orderOne.values().stream().findFirst().get();
                orderOneDetail.forEach(record -> {
                    if(record.getIdDishAttributeValue() != null && record.getIdDishAttributeValue().getIdAttributeDish().getName().contains("DESSERT")){
                        AtomicInteger newMeat = new AtomicInteger();
                        String typeSideDish = record.getIdDishAttributeValue().getIdValueAttributeDish().getValue();
                        newMeat.set(priorityDesserts.get(typeSideDish).intValue());
                        newMeat.set(newMeat.intValue());
                        if(maxValueOrderOne.longValue() < newMeat.longValue()){
                            maxValueOrderOne.set(newMeat.intValue());
                        }
                    }
                });

                //Get max value of order two
                AtomicInteger maxValueOrderTwo = new AtomicInteger();
                Set<OrderDishEntity> orderTwoDetail = orderTwo.values().stream().findFirst().get();
                orderTwoDetail.forEach(record -> {
                    if(record.getIdDishAttributeValue() != null && record.getIdDishAttributeValue().getIdAttributeDish().getName().contains("DESSERT")){
                        AtomicInteger newMeat = new AtomicInteger();
                        String typeSideDish = record.getIdDishAttributeValue().getIdValueAttributeDish().getValue();
                        newMeat.set(priorityDesserts.get(typeSideDish).intValue());
                        newMeat.set(newMeat.intValue());
                        if(maxValueOrderTwo.longValue() < newMeat.longValue()){
                            maxValueOrderTwo.set(newMeat.intValue());
                        }
                    }
                });

                if(maxValueOrderOne.longValue() < maxValueOrderTwo.longValue()){
                    HashMap<OrderEntity, Set<OrderDishEntity>> temp = orders.get(j+1);
                    orders.set(j+1,orders.get(j));
                    orders.set(j,temp);
                }
            }
        }

        return orders;
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
            if(!restaurantItsAvailable(orderFound.get().getIdRestaurant().getId())){
                deleteRestaurant(orderFound.get().getIdRestaurant().getId());
            }
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



    private String generatePin(){
        return "1234";
    }

    private void sendNotificationToUser(String statusOrder, String phoneNumber){
        messengerFeignClient.sendMessage(statusOrder, phoneNumber);
    }

    private void createLoggOrder(OrderEntity order, String previousStatus, String currentStatus){
        TrackingOrder trackingOrder = initializeTracking(order, previousStatus, currentStatus);
        trackingFeignClient.trackingOrder(trackingOrder);
    }

    private TrackingOrder initializeTracking(OrderEntity order, String previousStatus, String currentStatus){
        TrackingOrder trackingOrder = new TrackingOrder();
        trackingOrder.setIdOrder(order.getId());
        trackingOrder.setIdEmployee(order.getIdChef());
        trackingOrder.setIdCustomer(order.getIdUser());
        trackingOrder.setIdRestaurant(order.getIdRestaurant().getId());
        trackingOrder.setPreviousStatus(previousStatus);
        trackingOrder.setCurrentStatus(currentStatus);
        return trackingOrder;
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

        Optional<Restaurant> restaurantFound = restaurantPersistencePort.findRestaurantById(restaurant.getId());
        if(!restaurantFound.get().getIdRestaurantStatus().getName().contains(RESTAURANT_STATUS_ACTIVE_NAME)){
            throw new RestaurantNotAvailableException();
        }

        OrderEntity order = new OrderEntity();
        order.setIdUser(idUserAuthenticated);
        //order.setIdChef(idChef);
        order.setDate(new Date());
        order.setIdStatus(new OrderStatusEntity(STATUS_ORDER_IN_PENDING_ID,null,null));
        order.setIdRestaurant(restaurant);

        return order;
    }

    private ArrayList<OrderDishEntity> validateDishesToSave(ArrayList<DishAsset> dishesRequestDto, OrderEntity order){
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

                if(dish.getIdComplement() != null){
                    DishAttributeValueEntity complementFound = dishAttributeValueUseCase.findById(dish.getIdComplement());
                    if(complementFound.getIdDish().getId() != dish.getIdDish()){
                        throw new ComplementNotValidException();
                    }
                    dishesToSave.add(new OrderDishEntity(null, order, dishEntity, complementFound, dish.getQuantity()));
                }else {
                    dishesToSave.add(new OrderDishEntity(null, order, dishEntity, null, dish.getQuantity()));
                }
            }else{
                deleteOrderById(order.getId());
                throw new SomeDishesAreNotFromRestaurantException();
            }
        }
        return dishesToSave;
    }

    public void createOrder(CreateOrderRequestDto createOrderRequestDto) {
        RestaurantEntity restaurantDto = new RestaurantEntity(createOrderRequestDto.getIdRestaurant());

        if(!restaurantItsAvailable(createOrderRequestDto.getIdRestaurant())){
            throw new RestaurantNotAvailableException();
        }

        if(userCanCreateNewOrder()) {

            OrderEntity order = initializeOrder(restaurantDto);
            orderPersistencePort.createOrder(order);

            ArrayList<OrderDishEntity> dishesToSave = validateDishesToSave(createOrderRequestDto.getDishes(), order);
            orderDishPersistencePort.saveOrderDishes(dishesToSave);
            sendNotificationToUser("Order #"+order.getId()+" created successfully", "+573004469428");
            createLoggOrder(order, "",STATUS_ORDER_PENDING);
        }
    }

    private void deleteOrderById(Long idOrder){
        orderPersistencePort.deleteOrderById(idOrder);
    }

    /*@Scheduled(cron = "0 * * * * ?") // Check every minute
    public void validateOrdersExpired(){
        Optional<List<Long>> ordersFound = orderPersistencePort.getAllOrdersWithMaxProcessingTime();
        System.out.println("Executing validateOrdersExpire!!");
        if(ordersFound.get().size() >= 1){
            ordersFound.get().stream().forEach(order ->{
                closeOrderExpired(order);
            });
        }
    }*/

    private void closeOrderExpired(Long idOrder){
        Optional<OrderEntity> orderFound = orderPersistencePort.findOrderById(idOrder);
        if(orderFound.get().getIdStatus().getName().contains(STATUS_ORDER_PENDING)){
            orderPersistencePort.cancelOrder(orderFound.get());
            sendNotificationToUser("Order #"+orderFound.get().getId()+" expired", "+573004469428");
            createLoggOrder(orderFound.get(), STATUS_ORDER_PENDING,STATUS_ORDER_CANCELLED_BY_SYSTEM);
        }
    }

    private Boolean restaurantItsAvailable(Long idRestaurant){
        Restaurant restaurantFound = restaurantPersistencePort.findRestaurantById(idRestaurant).get();
        if(restaurantFound.getIdRestaurantStatus().getName().contains(RESTAURANT_STATUS_PENDING_DELETED_NAME)){
            return false;
        }
        return true;
    }

    @Transactional
    public void deleteRestaurant(Long idRestaurant){
        Optional<Restaurant> restaurantFound = restaurantPersistencePort.findRestaurantById(idRestaurant);
        if(!canDeleteRestaurant(idRestaurant)){
            if (!restaurantFound.get().getIdRestaurantStatus().getName().contains(RESTAURANT_STATUS_PENDING_DELETED_NAME)){
                restaurantFound.get().setIdRestaurantStatus(new RestaurantStatus(RESTAURANT_STATUS_PENDING_DELETED_ID,null,null));
                restaurantPersistencePort.saveRestaurant(restaurantFound.get());
                trackingFeignClient.trackingRestaurant(new TrackingRestaurant(idRestaurant,new Date(),RESTAURANT_STATUS_ACTIVE_NAME,RESTAURANT_STATUS_PENDING_DELETED_NAME));
            }
        }else{
            restaurantFound.get().setIdRestaurantStatus(new RestaurantStatus(RESTAURANT_STATUS_DELETED_ID,null,null));
            trackingFeignClient.trackingRestaurant(new TrackingRestaurant(idRestaurant,new Date(),restaurantFound.get().getIdRestaurantStatus().getName(),RESTAURANT_STATUS_DELETED_NAME));
            restaurantPersistencePort.saveRestaurant(restaurantFound.get());
        }
    }

    private Boolean canDeleteRestaurant(Long idRestaurant){
        return !orderPersistencePort.existsOrdersInCurseByIdRestaurant(idRestaurant);
    }
}
