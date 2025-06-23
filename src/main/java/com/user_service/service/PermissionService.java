package com.user_service.service;

import com.user_service.model.Permission;
import com.user_service.repository.PermissionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional(readOnly = true)  // default: read-only
public class PermissionService {

    private PermissionRepository permissionRepository;

    public PermissionService(PermissionRepository permissionRepository){
        this.permissionRepository = permissionRepository;
    }

    @Transactional                // override: write transaction
    public Permission createPermission(Permission permission){
        return permissionRepository.save(permission);
    }

    public List<Permission> getPermissions(){
        return permissionRepository.findAll();
    }
}
