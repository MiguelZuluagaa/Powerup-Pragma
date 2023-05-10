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
    public void saveUser(User usuario) {
        if (userRepository.findByDni(usuario.getDni()).isPresent()) {
            throw new PersonAlreadyExistsException();
        }
        if (userRepository.existsByEmail(usuario.getEmail())){
            throw new MailAlreadyExistsException();
        }

        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        UserEntity testUser = userEntityMapper.toEntity(usuario);
        userRepository.save(testUser);
    }

    @Override
    public User findUserByDni(String numeroDocumento){
        Optional<UserEntity> usuario = userRepository.findByDni(numeroDocumento);
        if (userRepository.findByDni(numeroDocumento).isPresent()){
            return userEntityMapper.ofUserEntitytoUsuario(usuario.get());
        }
        throw new UserNotFoundException();
    }

    @Override
    public User findUserById(Long id){
        Optional<UserEntity> usuario = userRepository.findById(id);
        if (userRepository.findById(id).isPresent()){
            return userEntityMapper.ofUserEntitytoUsuario(usuario.get());
        }
        throw new UserNotFoundException();
    }
}
