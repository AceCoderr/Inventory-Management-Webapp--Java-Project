package com.webapp.invems.Controller;

import com.webapp.invems.Model.LoginRequest;
import com.webapp.invems.Model.StoreUser;
import com.webapp.invems.Utils.JwtUtil;
import com.webapp.invems.repo.StoreUserRepository;
import com.webapp.invems.services.StoreUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private StoreUserRepository storeUserRepository;

    @Autowired
    private  StoreUserService storeUserService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping(value = "/login")
    public ResponseEntity<String> Login(@RequestBody LoginRequest user){
        try{
            boolean isAuthenticated = storeUserService.authenticate(user.getUsername(), user.getPassword());
            if(isAuthenticated){
                String jwt = jwtUtil.generateToken(user.getUsername());
                return new ResponseEntity<>(jwt,HttpStatus.OK);
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
