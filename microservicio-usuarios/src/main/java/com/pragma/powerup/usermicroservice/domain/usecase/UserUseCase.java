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
    public void saveUser(User usuario) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate fechaNacimientoParsed = LocalDate.parse(usuario.getBirdDate(), formatter);
        Integer age = LocalDate.now().getYear()-fechaNacimientoParsed.getYear();
        if(age < 18 ){
            throw new UserItsNotOlder();
        }

        userPersistencePort.saveUser(usuario);
    }

    @Override
    public User findUserByDni(String numeroDocumento){
        return userPersistencePort.findUserByDni(numeroDocumento);
    }

    @Override
    public User findUserById(Long id){
        return userPersistencePort.findUserById(id);
    }
}
