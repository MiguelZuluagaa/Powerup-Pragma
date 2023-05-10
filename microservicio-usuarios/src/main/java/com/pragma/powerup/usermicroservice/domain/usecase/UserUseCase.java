package com.pragma.powerup.usermicroservice.domain.usecase;

import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.exceptions.UserItsNotOlder;
import com.pragma.powerup.usermicroservice.domain.model.User;
import com.pragma.powerup.usermicroservice.domain.spi.IUserPersistencePort;
import com.pragma.powerup.usermicroservice.domain.api.IUserServicePort;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class UserUseCase implements IUserServicePort {
    private final IUserPersistencePort userPersistencePort;

    public UserUseCase(IUserPersistencePort personPersistencePort) {
        this.userPersistencePort = personPersistencePort;
    }

    @Override
    public void saveUser(User user) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate birdDateParsed = LocalDate.parse(user.getBirdDate(), formatter);
        Integer age = LocalDate.now().getYear()-birdDateParsed.getYear();
        if(age < 18 ){
            throw new UserItsNotOlder();
        }
        userPersistencePort.saveUser(user);
    }

    @Override
    public User findUserByDni(String dni){
        return userPersistencePort.findUserByDni(dni);
    }

    @Override
    public User findUserById(Long id){
        return userPersistencePort.findUserById(id);
    }
}
