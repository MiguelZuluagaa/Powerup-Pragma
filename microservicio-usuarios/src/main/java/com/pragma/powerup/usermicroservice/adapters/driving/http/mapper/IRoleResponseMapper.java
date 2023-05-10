package com.pragma.powerup.usermicroservice.adapters.driving.http.mapper;

import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.response.RoleResponseDto;
import com.pragma.powerup.usermicroservice.domain.model.Role;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IRoleResponseMapper {
    List<RoleResponseDto> toResponseList(List<Role> roleList);
}
