package com.pragma.powerup.messengermicroservice.adapters.driven.microservices.mappers;

import com.pragma.powerup.messengermicroservice.adapters.driven.microservices.dto.UserFeignDto;
import com.pragma.powerup.messengermicroservice.domain.model.Role;
import com.pragma.powerup.messengermicroservice.domain.model.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-30T14:21:39-0500",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.6.jar, environment: Java 17.0.4 (Oracle Corporation)"
)
@Component
public class IUserClientMapperImpl implements IUserClientMapper {

    @Override
    public User toUser(UserFeignDto user) {
        if ( user == null ) {
            return null;
        }

        Long id = null;
        String name = null;
        String surname = null;
        String dni = null;
        String phone = null;
        String birdDate = null;
        String email = null;
        String password = null;
        Role idRole = null;

        id = user.getId();
        name = user.getName();
        surname = user.getSurname();
        dni = user.getDni();
        phone = user.getPhone();
        birdDate = user.getBirdDate();
        email = user.getEmail();
        password = user.getPassword();
        idRole = user.getIdRole();

        User user1 = new User( id, name, surname, dni, phone, birdDate, email, password, idRole );

        return user1;
    }
}
