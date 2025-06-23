package com.user_service.contoller;

import com.user_service.model.Permission;
import com.user_service.service.PermissionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/permissions")
public class PermissionController {

    private PermissionService permissionService;

    @PostMapping
    public ResponseEntity<Permission> create( @RequestBody Permission permission) {
        Permission createdPermission = permissionService.createPermission(permission);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPermission);
    }

    @GetMapping
    public ResponseEntity<List<Permission>> findAll(){
        return ResponseEntity.ok(permissionService.getPermissions());
    }
}
