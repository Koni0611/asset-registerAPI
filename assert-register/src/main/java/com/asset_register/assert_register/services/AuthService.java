package com.asset_register.assert_register.services;

import com.asset_register.assert_register.models.Login;
import com.asset_register.assert_register.repositories.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private LoginRepository loginRepository;

    public boolean authenticate(String username, String password) {
        Optional<Login> login = loginRepository.findByUsername(username);
        return login.isPresent() && login.get().getPassword().equals(password);
    }
}