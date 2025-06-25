package com.user_service.contoller;

import com.user_service.model.User;
import com.user_service.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }
    //assign permisstion
    @PatchMapping("/assign-role")
    public ResponseEntity<User> assignRole(@RequestBody User user){
        User updatedUser = userService.assignRoles(user);
        return ResponseEntity.ok(updatedUser);
    }
}
