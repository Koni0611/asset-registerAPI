package com.asset_register.assert_register.controllers;

import com.asset_register.assert_register.models.Register;
import com.asset_register.assert_register.repositories.RegisterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class RegisterController {

    @Autowired
    private RegisterRepository registerRepository;

    // Create
    @PostMapping("/save")
    public ResponseEntity<String> registerUser(@RequestBody Register register) {
        Optional<Register> existingUserByIdentity = registerRepository
                .findByIdentityNumber(register.getIdentityNumber());
        Optional<Register> existingUserByEmail = registerRepository.findByEmail(register.getEmail());

        if (existingUserByIdentity.isPresent() || existingUserByEmail.isPresent()) {
            return ResponseEntity.badRequest().body("User with this identity number or email already exists.");
        }

        registerRepository.save(register);
        return ResponseEntity.ok("User registered successfully.");
    }

    // Read
    @GetMapping("/users")
    public ResponseEntity<List<Register>> getAllUsers() {
        List<Register> users = registerRepository.findAll();
        return ResponseEntity.ok(users);
    }

    // Update
    @PutMapping("/{identityNumber}")
    public ResponseEntity<Register> updateUser(@PathVariable String identityNumber, @RequestBody Register register) {
        Optional<Register> optionalUser = registerRepository.findByIdentityNumber(identityNumber);
        if (optionalUser.isPresent()) {
            Register existingUser = optionalUser.get();
            existingUser.setName(register.getName());
            existingUser.setSurname(register.getSurname());
            existingUser.setIdentityNumber(register.getIdentityNumber());
            existingUser.setGender(register.getGender());
            existingUser.setEmail(register.getEmail());
            existingUser.setPhoneNumber(register.getPhoneNumber());
            existingUser.setUsername(register.getUsername());
            existingUser.setPassword(register.getPassword());
            registerRepository.save(existingUser);
            return ResponseEntity.ok(existingUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete
    @DeleteMapping("/{identityNumber}")
    public ResponseEntity<Void> deleteUser(@PathVariable String identityNumber) {
        Optional<Register> optionalUser = registerRepository.findByIdentityNumber(identityNumber);
        if (optionalUser.isPresent()) {
            registerRepository.delete(optionalUser.get());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}