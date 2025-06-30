package com.user_service.contoller;

import com.user_service.model.Role;
import com.user_service.service.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/roles")
public class RoleController {

    private RoleService roleService;

    public RoleController(RoleService roleService){
        this.roleService = roleService;
    }

    @PostMapping
    public ResponseEntity<Role> createRole(@RequestBody Role role){
        Role createdRole = roleService.createRole(role);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRole);
    }

    @PatchMapping("/assign-permissions")
    public ResponseEntity<Role> assignPermissions(@RequestBody Role role){
        Role updatedRole = roleService.assignPermissions(role);
        return ResponseEntity.status(HttpStatus.OK).body(updatedRole);
    }

    @GetMapping
    public ResponseEntity<List<Role>> getRoles(){
        List<Role> roles = roleService.getRoles();
        return ResponseEntity.status(HttpStatus.OK).body(roles);
    }
}
