package com.pragma.powerup.usermicroservice.adapters.driving.http.mapper;

import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.response.UserResponseDto;
import com.pragma.powerup.usermicroservice.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IUserResponseMapper {
    @Mapping(source = "user.name", target = "name")
    @Mapping(source = "user.surname", target = "surname")
    @Mapping(source = "user.email", target = "email")
    @Mapping(source = "user.phone", target = "phone")
    @Mapping(source = "user.birdDate", target = "birdDate")
    @Mapping(source = "user.dni", target = "dni")
    @Mapping(source = "user.idRole", target = "idRole")
    UserResponseDto toResponse(User user);
    List<UserResponseDto> userListToUserResponseList(List<User> userList);
}
