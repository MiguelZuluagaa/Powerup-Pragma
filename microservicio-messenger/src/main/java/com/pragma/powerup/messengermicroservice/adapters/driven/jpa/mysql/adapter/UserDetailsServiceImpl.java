package com.pragma.powerup.messengermicroservice.adapters.driven.jpa.mysql.adapter;

import com.pragma.powerup.messengermicroservice.adapters.driven.jpa.mysql.entity.PrincipalUser;
import com.pragma.powerup.messengermicroservice.adapters.driven.microservices.client.IUserFeignClient;
import com.pragma.powerup.messengermicroservice.adapters.driven.microservices.dto.UserFeignDto;
import com.pragma.powerup.messengermicroservice.domain.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    IUserFeignClient userFeignClient;

    public UserDetails loadUserByUsername(String email, String tokenHeader) throws UsernameNotFoundException {
        Optional<UserFeignDto> userFeignDto = Optional.ofNullable(userFeignClient.getUserByEmail(email, tokenHeader));
        UserFeignDto userDto = userFeignDto.orElseThrow(() -> new UsernameNotFoundException("User not found"));
        List<UserFeignDto> users = new ArrayList<>();
        users.add(userDto);
        if (users.isEmpty()) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
        List<Role> roles = new ArrayList<>();

        for (UserFeignDto user : users) {
            roles.add(user.getIdRole());
        }

        return PrincipalUser.build(userFeignDto.get(), roles);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}

