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
public interface IUsuarioResponseMapper {
    @Mapping(source = "usuario.name", target = "name")
    @Mapping(source = "usuario.surname", target = "surname")
    @Mapping(source = "usuario.email", target = "email")
    @Mapping(source = "usuario.phone", target = "phone")
    @Mapping(source = "usuario.birdDate", target = "birdDate")
    @Mapping(source = "usuario.dni", target = "dni")
    @Mapping(source = "usuario.idRole", target = "idRole")
    UserResponseDto toResponse(User usuario);
    List<UserResponseDto> userListToUserResponseList(List<User> userList);
}
