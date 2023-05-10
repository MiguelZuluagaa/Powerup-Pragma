package com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.adapter;

import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity.UserEntity;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.exceptions.MailAlreadyExistsException;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.exceptions.UserNotFoundException;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.repositories.IUserRepository;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.exceptions.PersonAlreadyExistsException;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.mappers.IUserEntityMapper;
import com.pragma.powerup.usermicroservice.domain.model.User;
import com.pragma.powerup.usermicroservice.domain.spi.IUserPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@RequiredArgsConstructor
public class UserMysqlAdapter implements IUserPersistencePort {
    private final IUserRepository userRepository;
    private final IUserEntityMapper userEntityMapper;
    private final PasswordEncoder passwordEncoder;
    @Override
    public void saveUser(User user) {
        if (userRepository.findByDni(user.getDni()).isPresent()) {
            throw new PersonAlreadyExistsException();
        }
        if (userRepository.existsByEmail(user.getEmail())){
            throw new MailAlreadyExistsException();
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        UserEntity testUser = userEntityMapper.toEntity(user);
        userRepository.save(testUser);
    }

    @Override
    public User findUserByDni(String dni){
        Optional<UserEntity> user = userRepository.findByDni(dni);
        if (userRepository.findByDni(dni).isPresent()){
            return userEntityMapper.ofUserEntityToUser(user.get());
        }
        throw new UserNotFoundException();
    }

    @Override
    public User findUserById(Long id){
        Optional<UserEntity> user = userRepository.findById(id);
        if (userRepository.findById(id).isPresent()){
            return userEntityMapper.ofUserEntityToUser(user.get());
        }
        throw new UserNotFoundException();
    }
}
