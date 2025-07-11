package com.CustomerService.customers.roles.services;

import com.CustomerService.customers.roles.roledto.RolesResponseDto;

import java.util.List;

public interface RoleService {
    RolesResponseDto createRole(String roles);
    List<RolesResponseDto> getAllRoles();
    boolean deleteRole(long roleId);
}
