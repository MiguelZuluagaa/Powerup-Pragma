package com.pragma.powerup.plazoletamicroservice.configuration;

import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.exceptions.MicroserviceUserNotWorking;
import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.exceptions.UserWithOrderInProgressException;
import com.pragma.powerup.plazoletamicroservice.domain.exceptions.SomeDishesAreNotFromRestaurantException;

public class Constants {

    private Constants() {
        throw new IllegalStateException("Utility class");
    }

    public static final Long ROLE_ADMIN_ID = 1L;
    public static final Long ROLE_OWNER_ID = 2L;
    public static final Long ROLE_EMPLOYEE_ID = 3L;
    public static final Long ROLE_CUSTOMER_ID = 4L;
    public static final String ROLE_ADMIN = "ADMIN";
    public static final String ROLE_OWNER = "OWNER";
    public static final String ROLE_EMPLOYEE = "EMPLOYEE";
    public static final String ROLE_CUSTOMER = "CUSTOMER";
    public static final String ROLE_ANONYMOUS = "ANONYMOUS";
    public static final Long STATUS_ORDER_IN_PROGRESS_ID = 1L;
    public static final Long STATUS_ORDER_IN_PREPARATION_ID = 2L;
    public static final Long STATUS_ORDER_IN_PENDING_ID = 3L;
    public static final Long STATUS_ORDER_IN_READY_ID = 4L;
    public static final String RUTE_MICROSERVICE_USER = "http://localhost:8090";
    public static final String NAME_MICROSERVICE_USER = "user-service";
    public static final String RUTE_MICROSERVICE_MESSENGER = "http://localhost:8094";
    public static final String NAME_MICROSERVICE_MESSENGER = "messenger-service";
    public static final int MAX_PAGE_SIZE = 2;
    public static final String RESPONSE_MESSAGE_KEY = "message";
    public static final String PERSON_CREATED_MESSAGE = "Person created successfully";
    public static final String RESTAURANT_CREATED_MESSAGE = "Restaurant created successfully";
    public static final String DISH_CREATED_MESSAGE = "Dish created successfully";
    public static final String ORDER_CREATED_MESSAGE = "Order created successfully";
    public static final String ORDER_TOOK_MESSAGE = "Order took successfully";
    public static final String ORDER_CLOSED_MESSAGE = "The order was closed correctly";
    public static final String ORDER_READY_MESSAGE = "The order was mark as ready correctly";
    public static final String DISH_UPDATED_MESSAGE = "Dish updated successfully";
    public static final String USER_CREATED_MESSAGE = "User created successfully";
    public static final String USER_DELETED_MESSAGE = "User deleted successfully";
    public static final String RESPONSE_ERROR_MESSAGE_KEY = "error";
    public static final String WRONG_CREDENTIALS_MESSAGE = "Wrong credentials";
    public static final String NO_DATA_FOUND_MESSAGE = "No data found for the requested petition";
    public static final String PERSON_ALREADY_EXISTS_MESSAGE = "A person already exists with the DNI number provided";
    public static final String MAIL_ALREADY_EXISTS_MESSAGE = "A person with that mail already exists";
    public static final String PERSON_NOT_FOUND_MESSAGE = "No person found with the id provided";
    public static final String ROLE_NOT_FOUND_MESSAGE = "No role found with the id provided";
    public static final String USER_ITS_NOT_OWNER = "The user with the id provided does not correspond to an owner";
    public static final String MICROSERVICE_USER_NOT_WORKING = "We are currently experiencing internal problems, please try again later! ";
    public static final String RESTAURANT_DOES_NOT_EXIST = "The restaurant with the id provided does not exist";
    public static final String USER_ITS_NOT_OWNER_OF_THE_RESTAURANT = "The user logged is not the owner of the restaurant";
    public static final String DISH_NOT_FOUND = "The dish with the id provided does not exist";
    public static final String CATEGORY_NOT_FOUND = "The category with the id provided does not exist";
    public static final String DISH_IS_ALREADY_ACTIVE = "The dish is already active";
    public static final String DISH_IS_ALREADY_DISABLE = "The dish is already disable";
    public static final String PAGE_AND_OFFSET_MUST_BE_POSITIVE = "The parameters page and offset must be positive";
    public static final String SOME_DISHES_ARE_NOT_FROM_RESTAURANT = "Some dishes are not from the restaurant";
    public static final String USER_WITH_ORDER_IN_PROGRESS = "The user has an order in progress";
    public static final String PARAMETER_NEGATIVES = "Parameters cannot be negative";
    public static final String ORDER_NOT_FOUND = "The order with the id provided does not exist";
    public static final String ORDER_IS_ALREADY_TAKEN = "The order is already taken";
    public static final String USER_CANT_MARK_READY_ORDER = "Sorry, you can't mark the order as ready";
    public static final String CANT_MARK_READY_ORDER = "Sorry, you can't mark the order as ready";
    public static final String ROLE_NOT_ALLOWED_MESSAGE = "No permission granted to create users with this role";
    public static final String USER_ALREADY_EXISTS_MESSAGE = "A user already exists with the role provided";
    public static final String RESTAURANT_ALREADY_EXISTS_MESSAGE = "A restaurant already exists with the nit provided";
    public static final String USER_NOT_FOUND_MESSAGE = "User not found";
    public static final String SWAGGER_TITLE_MESSAGE = "User API Pragma Power Up";
    public static final String SWAGGER_DESCRIPTION_MESSAGE = "User microservice";
    public static final String SWAGGER_VERSION_MESSAGE = "1.0.0";
    public static final String SWAGGER_LICENSE_NAME_MESSAGE = "Apache 2.0";
    public static final String SWAGGER_LICENSE_URL_MESSAGE = "http://springdoc.org";
    public static final String SWAGGER_TERMS_OF_SERVICE_MESSAGE = "http://swagger.io/terms/";
}
