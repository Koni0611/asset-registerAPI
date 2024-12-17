package com.asset_register.assert_register.services;

import com.asset_register.assert_register.models.Login;
import com.asset_register.assert_register.models.Register;
import com.asset_register.assert_register.repositories.LoginRepository;
import com.asset_register.assert_register.repositories.RegisterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private RegisterRepository registerRepository;

    @Value("${first.admin.key:}")
    private String firstAdminKey;

    private Register currentUser;

    public boolean authenticate(String username, String password) {
        try {
            Optional<Login> login = loginRepository.findByUsername(username);
            if (login.isPresent()) {
                if (login.get().getPassword().equals(password)) {
                    Optional<Register> register = registerRepository.findByUsername(username);
                    if (register.isPresent()) {
                        currentUser = register.get();
                        return true;
                    } else {
                        System.err.println("Register entry not found for username: " + username);
                    }
                } else {
                    System.err.println("Invalid password for username: " + username);
                }
            } else {
                System.err.println("Login entry not found for username: " + username);
            }
        } catch (Exception e) {
            System.err.println("Error during authentication: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    public boolean authenticateAdmin(String token) {
        // Validate admin by token
        Optional<Register> user = registerRepository.findByToken(token);
        return user.filter(register -> "admin".equalsIgnoreCase(register.getRole())).isPresent();
    }

    public boolean isAdmin() {
        return currentUser != null && "admin".equalsIgnoreCase(currentUser.getRole());
    }

    public Register getCurrentUserByToken(String token) {
        return registerRepository.findByToken(token)
                .orElseThrow(() -> new IllegalStateException("Invalid or missing token"));
    }

    public void saveUser(Register user) {
        registerRepository.save(user); // Save the user to the database
    }

    public Register getCurrentUser() {
        if (currentUser == null) {
            throw new IllegalStateException("No user is currently authenticated.");
        }
        return currentUser; // Return the current authenticated user
    }

}