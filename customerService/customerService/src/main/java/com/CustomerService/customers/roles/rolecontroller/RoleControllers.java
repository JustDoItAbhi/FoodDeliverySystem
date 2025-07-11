package com.CustomerService.customers.roles.rolecontroller;

import com.CustomerService.customers.roles.roledto.RolesRequestDto;
import com.CustomerService.customers.roles.roledto.RolesResponseDto;
import com.CustomerService.customers.roles.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleControllers {
@Autowired
    private RoleService roleService;
@PostMapping("/createRole")
    public ResponseEntity<RolesResponseDto> createRole(@RequestBody RolesRequestDto dto){
    return ResponseEntity.ok(roleService.createRole(dto.getRoles()));
}
@GetMapping("/getallroles")
public ResponseEntity<List<RolesResponseDto>> getAllRole(){
    return ResponseEntity.ok(roleService.getAllRoles());
}
@DeleteMapping("deleteRole/{id}")
public ResponseEntity<Boolean> deleteRole(@PathVariable ("id")Long id){
    return ResponseEntity.ok(roleService.deleteRole(id));
}

}
