package com.webapp.invems.Controller;

import com.webapp.invems.Model.StoreUser;
import com.webapp.invems.Model.StoreUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;

@RestController
public class RegistrationController {

    @Autowired
    public StoreUserRepository storeUserRepository;

    @Autowired
    public PasswordEncoder passwordEncoder;


    @PostMapping(value="/req/signup")
    public StoreUser createUser(StoreUser user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return storeUserRepository.save(user);
    }
}
