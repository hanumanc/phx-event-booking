package com.eventbooking.controller;

import com.eventbooking.entity.User;
import com.eventbooking.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/users")
@Tag(name = "User Management", description = "User management APIs")
@SecurityRequirement(name = "bearerAuth")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/profile")
    @PreAuthorize("hasRole('USER') or hasRole('VENDOR') or hasRole('ADMIN')")
    @Operation(summary = "Get user profile", description = "Get current user's profile information")
    public ResponseEntity<?> getUserProfile() {
        Optional<User> user = userService.getCurrentUser();
        if (user.isPresent()) {
            User userProfile = user.get();
            // Remove password from response
            userProfile.setPassword(null);
            return ResponseEntity.ok(userProfile);
        }
        return ResponseEntity.notFound().build();
    }
}