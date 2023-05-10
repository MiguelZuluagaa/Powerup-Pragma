package com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.mappers;

import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity.RoleEntity;
import com.pragma.powerup.usermicroservice.domain.model.Role;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IRoleEntityMapper {
    List<Role> toRoleList(List<RoleEntity> roleEntityList);
}
