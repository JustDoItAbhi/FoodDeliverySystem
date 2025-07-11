package com.CustomerService.customers.roles.services;

import com.CustomerService.customers.mapper.CustomerMappers;
import com.CustomerService.customers.roles.roledto.RolesResponseDto;
import com.CustomerService.models.Roles;
import com.CustomerService.customers.roles.repo.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService{
    @Autowired
    private RoleRepository roleRepository;
    public RolesResponseDto createRole(String roles){
        Optional<Roles>savedRole=roleRepository.findByRoles(roles);
        if(savedRole.isPresent()){
            throw new RuntimeException("ROLE ALREADY EXISTS ::: "+roles);
        }

        Roles roles1=new Roles();
        if(roles.equals(roles.toLowerCase())){
            roles1.setRoles(roles.toUpperCase());
        }
        roles1.setRoles(roles);
        roleRepository.save(roles1);
        RolesResponseDto dto=new RolesResponseDto();
        dto.setRolesReponse(roles1.getRoles());
        return dto;
    }

    @Override
    public List<RolesResponseDto> getAllRoles() {
        List<Roles>roles=roleRepository.findAll();
        List<RolesResponseDto>dtos=new ArrayList<>();
        for(Roles roles1:roles){
            dtos.add(CustomerMappers.fromEntityRole(roles1));
        }
        return dtos;
    }

    @Override
    public boolean deleteRole(long Id) {
         roleRepository.deleteById(Id);
    return true;
    }
}
