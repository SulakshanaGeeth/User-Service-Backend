package com.user_service.contoller;

import com.user_service.dto.RegisterRequestDTO;
import com.user_service.model.User;
import com.user_service.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getUsers(){
        List<User> userList = userService.getUsers();
        return ResponseEntity.ok(userList);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequestDTO request) {
        return userService.register(request);
    }

    @PatchMapping("/assign-role")
    public ResponseEntity<User> assignRole(@RequestBody User user) {
        User updatedUser = userService.assignRoles(user);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId){
        userService.deleteUser(userId);
        return ResponseEntity.ok("User Deleted");
    }
}
