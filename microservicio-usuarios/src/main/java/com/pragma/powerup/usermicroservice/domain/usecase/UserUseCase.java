package com.pragma.powerup.usermicroservice.domain.usecase;

import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.exceptions.SomethingWrongWithTheDate;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.exceptions.UserItsNotOlder;
import com.pragma.powerup.usermicroservice.domain.model.Role;
import com.pragma.powerup.usermicroservice.domain.model.User;
import com.pragma.powerup.usermicroservice.domain.spi.IUserPersistencePort;
import com.pragma.powerup.usermicroservice.domain.api.IUserServicePort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import static com.pragma.powerup.usermicroservice.configuration.Constants.*;

public class UserUseCase implements IUserServicePort {
    private final IUserPersistencePort userPersistencePort;

    public UserUseCase(IUserPersistencePort personPersistencePort) {
        this.userPersistencePort = personPersistencePort;
    }

    @Override
    public void saveUser(User user) {
        if(itsOlder(user.getBirdDate())){
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Long roleNewUser = getRoleNewUser(authentication);
            Role roleDomain = new Role(roleNewUser,null,null);
            user.setIdRole(roleDomain);
            userPersistencePort.saveUser(user);
        }
    }

    @Override
    public User findUserByDni(String dni){
        return userPersistencePort.findUserByDni(dni);
    }

    @Override
    public User findUserById(Long id){
        return userPersistencePort.findUserById(id);
    }

    @Override
    public User findUserByEmail(String email){
        return userPersistencePort.findUserByEmail(email);
    }

    @Override
    public void deleteUserById(Long id){
        userPersistencePort.deleteUserById(id);
    }

    private Long getRoleNewUser(Authentication authentication){
        Map<String, Long> roles = new HashMap<>();
        roles.put("ROLE_"+ROLE_ADMIN, ROLE_OWNER_ID);
        roles.put("ROLE_"+ROLE_OWNER, ROLE_EMPLOYEE_ID);
        roles.put("ROLE_"+ROLE_EMPLOYEE, ROLE_CUSTOMER_ID);
        roles.put("ROLE_"+ROLE_ANONYMOUS, ROLE_CUSTOMER_ID); // If the user is anonymous, the role is customer

        return roles.get(authentication.getAuthorities().toArray()[0].toString());
    }

    private boolean itsOlder(String birdDate){
        try{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate birdDateParsed = LocalDate.parse(birdDate, formatter);
            Integer age = LocalDate.now().getYear()-birdDateParsed.getYear();
            if(age < 18 ){
                throw new UserItsNotOlder();
            }
        }catch (Exception e) {
            throw new SomethingWrongWithTheDate();
        }
        return true;
    }
}
