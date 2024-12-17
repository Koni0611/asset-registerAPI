package com.asset_register.assert_register.controllers;

import com.asset_register.assert_register.models.Register;
import com.asset_register.assert_register.services.AuthService;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/login")
public class LoginController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> loginUser(@RequestParam String username, @RequestParam String password) {
        Map<String, Object> response = new HashMap<>();
        System.out.println("Login attempt: username=" + username);

        try {
            boolean isAuthenticated = authService.authenticate(username, password);
            System.out.println("Authentication result: " + isAuthenticated);

            if (isAuthenticated) {
                Register currentUser = authService.getCurrentUser();
                System.out.println("Authenticated user: " + currentUser);

                // Generate a unique token
                String token = java.util.UUID.randomUUID().toString();
                currentUser.setToken(token);
                authService.saveUser(currentUser);

                response.put("message", "Login successful");
                response.put("role", currentUser.getRole());
                response.put("userId", currentUser.getId());
                response.put("token", token);
                return ResponseEntity.ok(response);
            } else {
                response.put("message", "Invalid username or password");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            }
        } catch (Exception e) {
            System.err.println("Error during login: " + e.getMessage());
            e.printStackTrace();
            response.put("message", "An internal server error occurred.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

}