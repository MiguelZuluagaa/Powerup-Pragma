package com.pragma.powerup.plazoletamicroservice.configuration;

import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.exceptions.*;
import com.pragma.powerup.plazoletamicroservice.adapters.driven.jpa.mysql.exceptions.RestaurantNotExist;
import com.pragma.powerup.plazoletamicroservice.domain.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static com.pragma.powerup.plazoletamicroservice.configuration.Constants.*;

@ControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationException(MethodArgumentNotValidException ex) {
        List<String> errorMessages = new ArrayList<>();
        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            if (error instanceof FieldError) {
                FieldError fieldError = (FieldError) error;
                errorMessages.add(fieldError.getField() + ": " + fieldError.getDefaultMessage());
            } else {
                errorMessages.add(error.getDefaultMessage());
            }
        }
        return new ResponseEntity<>(errorMessages, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Map<String, String>> handleAuthenticationException(AuthenticationException noDataFoundException) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Collections.singletonMap(RESPONSE_ERROR_MESSAGE_KEY, WRONG_CREDENTIALS_MESSAGE));
    }
    @ExceptionHandler(NoDataFoundException.class)
    public ResponseEntity<Map<String, String>> handleNoDataFoundException(NoDataFoundException noDataFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(RESPONSE_ERROR_MESSAGE_KEY, NO_DATA_FOUND_MESSAGE));
    }
    @ExceptionHandler(PersonAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> handlePersonAlreadyExistsException(
            PersonAlreadyExistsException personAlreadyExistsException) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Collections.singletonMap(RESPONSE_ERROR_MESSAGE_KEY, PERSON_ALREADY_EXISTS_MESSAGE));
    }
    @ExceptionHandler(MailAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> handleMailAlreadyExistsException(
            MailAlreadyExistsException mailAlreadyExistsException) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Collections.singletonMap(RESPONSE_ERROR_MESSAGE_KEY, MAIL_ALREADY_EXISTS_MESSAGE));
    }
    @ExceptionHandler(PersonNotFoundException.class)
    public ResponseEntity<Map<String, String>> handlePersonNotFoundException(
            PersonNotFoundException personNotFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(RESPONSE_ERROR_MESSAGE_KEY, PERSON_NOT_FOUND_MESSAGE));
    }
    @ExceptionHandler(RoleNotAllowedForCreationException.class)
    public ResponseEntity<Map<String, String>> handleRoleNotAllowedForCreationException(
            RoleNotAllowedForCreationException roleNotAllowedForCreationException) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(Collections.singletonMap(RESPONSE_ERROR_MESSAGE_KEY, ROLE_NOT_ALLOWED_MESSAGE));
    }
    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> handleUserAlreadyExistsException(
            UserAlreadyExistsException userAlreadyExistsException) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Collections.singletonMap(RESPONSE_ERROR_MESSAGE_KEY, USER_ALREADY_EXISTS_MESSAGE));
    }
    @ExceptionHandler(RestaurantAlreadyExistException.class)
    public ResponseEntity<Map<String, String>> handleRestaurantAlreadyExistsException(
            RestaurantAlreadyExistException restaurantAlreadyExistException) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Collections.singletonMap(RESPONSE_ERROR_MESSAGE_KEY, RESTAURANT_ALREADY_EXISTS_MESSAGE));
    }
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleUserNotFoundException(
            UserNotFoundException userNotFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(RESPONSE_ERROR_MESSAGE_KEY, USER_NOT_FOUND_MESSAGE));
    }
    @ExceptionHandler(RoleNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleRoleNotFoundException(
            RoleNotFoundException roleNotFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(RESPONSE_ERROR_MESSAGE_KEY, ROLE_NOT_FOUND_MESSAGE));
    }
    @ExceptionHandler(UserItsNotOwner.class)
    public ResponseEntity<Map<String, String>> handleUserItsNotOwner(
            UserItsNotOwner userItsNotOwner) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(RESPONSE_ERROR_MESSAGE_KEY, USER_ITS_NOT_OWNER));
    }
    @ExceptionHandler(MicroserviceUserNotWorking.class)
    public ResponseEntity<Map<String, String>> handlerMicroserviceUserNotWorking(
            MicroserviceUserNotWorking microserviceUserNotWorking) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(RESPONSE_ERROR_MESSAGE_KEY, MICROSERVICE_USER_NOT_WORKING));
    }
    @ExceptionHandler(RestaurantNotExist.class)
    public ResponseEntity<Map<String, String>> handlerRestaurantNotExist(
            RestaurantNotExist restaurantNotExist) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(RESPONSE_ERROR_MESSAGE_KEY, RESTAURANT_DOES_NOT_EXIST));
    }
    @ExceptionHandler(UserItsNotOwnerOfTheRestaurant.class)
    public ResponseEntity<Map<String, String>> handlerUserItsNotOwnerOfTheRestaurant(
            UserItsNotOwnerOfTheRestaurant userItsNotOwnerOfTheRestaurant) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(RESPONSE_ERROR_MESSAGE_KEY, USER_ITS_NOT_OWNER_OF_THE_RESTAURANT));
    }
    @ExceptionHandler(DishNotFound.class)
    public ResponseEntity<Map<String, String>> handlerDishNotFound(
            DishNotFound dishNotFound) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(RESPONSE_ERROR_MESSAGE_KEY, DISH_NOT_FOUND));
    }
    @ExceptionHandler(CategoryDontExistException.class)
    public ResponseEntity<Map<String, String>> handlerCategoryDontExistException(
            CategoryDontExistException categoryNotFound) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(RESPONSE_ERROR_MESSAGE_KEY, CATEGORY_NOT_FOUND));
    }
    @ExceptionHandler(DishIsAlreadyActiveException.class)
    public ResponseEntity<Map<String, String>> handlerDishIsAlreadyActiveException(
            DishIsAlreadyActiveException dishIsAlreadyActiveException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(RESPONSE_ERROR_MESSAGE_KEY, DISH_IS_ALREADY_ACTIVE));
    }
    @ExceptionHandler(DishIsAlreadyDisableException.class)
    public ResponseEntity<Map<String, String>> handlerDishIsAlreadyDisableException(
            DishIsAlreadyDisableException dishIsAlreadyDisableException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(RESPONSE_ERROR_MESSAGE_KEY, DISH_IS_ALREADY_DISABLE));
    }
    @ExceptionHandler(PageAndOffsetMustBePositive.class)
    public ResponseEntity<Map<String, String>> handlerPageAndOffsetMustBePositive(
            PageAndOffsetMustBePositive pageAndOffsetMustBePositive) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(RESPONSE_ERROR_MESSAGE_KEY, PAGE_AND_OFFSET_MUST_BE_POSITIVE));
    }
    @ExceptionHandler(SomeDishesAreNotFromRestaurantException.class)
    public ResponseEntity<Map<String, String>> handlerSomeDishesAreNotFromRestaurantException(
            SomeDishesAreNotFromRestaurantException someDishesAreNotFromRestaurantException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(RESPONSE_ERROR_MESSAGE_KEY, SOME_DISHES_ARE_NOT_FROM_RESTAURANT));
    }
    @ExceptionHandler(UserWithOrderInProgressException.class)
    public ResponseEntity<Map<String, String>> handlerUserWithOrderInProgressException(
            UserWithOrderInProgressException userWithOrderInProgressException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(RESPONSE_ERROR_MESSAGE_KEY, USER_WITH_ORDER_IN_PROGRESS));
    }
    @ExceptionHandler(ParametersNegativesException.class)
    public ResponseEntity<Map<String, String>> handlerParametersNegativesException(
            ParametersNegativesException parametersNegativesException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(RESPONSE_ERROR_MESSAGE_KEY, PARAMETER_NEGATIVES));
    }
    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<Map<String, String>> handlerOrderNotFoundException(
            OrderNotFoundException orderNotFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(RESPONSE_ERROR_MESSAGE_KEY, ORDER_NOT_FOUND));
    }
    @ExceptionHandler(OrderIsAlreadyTakenException.class)
    public ResponseEntity<Map<String, String>> handlerOrderIsAlreadyTakenException(
            OrderIsAlreadyTakenException orderIsAlreadyTakenException) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Collections.singletonMap(RESPONSE_ERROR_MESSAGE_KEY, ORDER_IS_ALREADY_TAKEN));
    }
    @ExceptionHandler(UserCantMarkOrderReadyException.class)
    public ResponseEntity<Map<String, String>> handlerUserCantMarkOrderReadyException(
            UserCantMarkOrderReadyException userCantMarkOrderReadyException) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Collections.singletonMap(RESPONSE_ERROR_MESSAGE_KEY, USER_CANT_MARK_READY_ORDER));
    }
    @ExceptionHandler(CantMarkOrderReadyException.class)
    public ResponseEntity<Map<String, String>> handlerCantMarkOrderReadyException(
            CantMarkOrderReadyException cantMarkOrderReadyException) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Collections.singletonMap(RESPONSE_ERROR_MESSAGE_KEY, CANT_MARK_READY_ORDER));
    }
    @ExceptionHandler(UserCantFinishedOrderException.class)
    public ResponseEntity<Map<String, String>> handlerUserCantFinishedOrderException(
            UserCantFinishedOrderException userCantFinishedOrderException) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Collections.singletonMap(RESPONSE_ERROR_MESSAGE_KEY, USER_CANT_MARK_FINISHED_ORDER));
    }
    @ExceptionHandler(PinWrongException.class)
    public ResponseEntity<Map<String, String>> handlerPinWrongException(
            PinWrongException pinWrongException) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Collections.singletonMap(RESPONSE_ERROR_MESSAGE_KEY, PIN_WRONG));
    }
    @ExceptionHandler(CantMarkOrderFinishedException.class)
    public ResponseEntity<Map<String, String>> handlerCantMarkOrderFinishedException(
            CantMarkOrderFinishedException cantMarkOrderFinishedException) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Collections.singletonMap(RESPONSE_ERROR_MESSAGE_KEY, CANT_MARK_FINISHED_ORDER));
    }
    @ExceptionHandler(UserCantCancelOrderException.class)
    public ResponseEntity<Map<String, String>> handlerUserCantCancelOrderException(
            UserCantCancelOrderException userCantCancelOrderException) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Collections.singletonMap(RESPONSE_ERROR_MESSAGE_KEY, USER_CANT_CANCEL_ORDER));
    }
    @ExceptionHandler(UserItsNotOfTheOrderException.class)
    public ResponseEntity<Map<String, String>> handlerUserItsNotOfTheOrderException(
            UserItsNotOfTheOrderException userItsNotOfTheOrderException) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Collections.singletonMap(RESPONSE_ERROR_MESSAGE_KEY, USER_ITS_NOT_OF_THE_ORDER));
    }
    @ExceptionHandler(QuantityDishInvalidException.class)
    public ResponseEntity<Map<String, String>> handlerQuantityDishInvalidException(
            QuantityDishInvalidException quantityDishInvalidException) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Collections.singletonMap(RESPONSE_ERROR_MESSAGE_KEY, QUANTITY_DISH_INVALID));
    }
    @ExceptionHandler(OrderCannotBeTakenException.class)
    public ResponseEntity<Map<String, String>> handlerOrderCannotBeTakenException(
            OrderCannotBeTakenException orderCannotBeTakenException) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Collections.singletonMap(RESPONSE_ERROR_MESSAGE_KEY, ORDER_CANNOT_BE_TAKE));
    }
    @ExceptionHandler(UserItsNotOwnerOrderException.class)
    public ResponseEntity<Map<String, String>> handlerUserItsNotOwnerOrderException(
            UserItsNotOwnerOrderException userItsNotOwnerOrderException) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Collections.singletonMap(RESPONSE_ERROR_MESSAGE_KEY, USER_CANNOT_CANCEL_ORDER_ITS_NOT_OWNER));
    }


}
