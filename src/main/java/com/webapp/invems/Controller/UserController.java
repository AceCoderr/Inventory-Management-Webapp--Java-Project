package com.webapp.invems.Controller;

import com.webapp.invems.Model.LoginRequest;
import com.webapp.invems.Model.StoreUser;
import com.webapp.invems.Model.StoreUserRepository;
import com.webapp.invems.Model.StoreUserService;
import jakarta.mail.Store;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    public StoreUserRepository storeUserRepository;

    @Autowired
    public StoreUserService storeUserService;

    @PostMapping(value = "/login")
    public ResponseEntity<String> Login(@RequestBody LoginRequest user, HttpSession session){
        try{
            boolean isAuthenticated = storeUserService.authenticate(user.getUsername(), user.getPassword());
            if(isAuthenticated){
                session.setAttribute("user",user.getUsername());
                return ResponseEntity.ok("true");
            }else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
            }
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unknown error occurred");
        }
    }

    @PostMapping(value="/signup")
    public StoreUser createUser(@RequestBody StoreUser user){
        StoreUser newUser = storeUserService.createUser(user);
        return newUser;
    }
}
