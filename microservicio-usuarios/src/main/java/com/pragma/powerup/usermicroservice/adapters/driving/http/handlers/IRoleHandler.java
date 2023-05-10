package com.pragma.powerup.usermicroservice.adapters.driving.http.handlers;

import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.response.RoleResponseDto;

import java.util.List;

public interface IRoleHandler {
    List<RoleResponseDto> getAllRoles();
}
