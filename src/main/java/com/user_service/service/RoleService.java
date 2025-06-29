package com.user_service.service;

import com.user_service.model.Permission;
import com.user_service.model.Role;
import com.user_service.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class RoleService {

    private RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }

    public Role createRole(Role role){
        return roleRepository.save(role);
    }

    public Role assignPermissions(Role role) {
        Role existingRole = roleRepository.findById(role.getId()).orElseThrow();
        existingRole.setPermissions(role.getPermissions());
        return roleRepository.save(existingRole);
    }
}
