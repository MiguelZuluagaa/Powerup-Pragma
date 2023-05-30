package com.pragma.powerup.messengermicroservice.adapters.driven.microservices.mappers;

import com.pragma.powerup.messengermicroservice.adapters.driven.microservices.dto.UserFeignDto;
import com.pragma.powerup.messengermicroservice.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IUserClientMapper {
    User toUser(UserFeignDto user);
}
