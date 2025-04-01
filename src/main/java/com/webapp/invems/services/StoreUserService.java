package com.webapp.invems.services;

import com.webapp.invems.Model.StoreUser;
import com.webapp.invems.repo.StoreUserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class StoreUserService implements UserDetailsService {

    @Autowired
    private StoreUserRepository repository;

    @Autowired
    @Lazy
    private  PasswordEncoder passwordEncoder;

//    @Autowired
//    private  RedisService redisService;

    @Override
    public UserDetails loadUserByUsername(String username) {

        Optional<StoreUser> user = repository.findByUsername(username);
        if (user.isPresent()){
            var userObj = user.get();
            return User.builder()
                    .username(userObj.getUsername())
                    .password(userObj.getPassword())
                    .build();
        }
        return null;
    }

    public Boolean authenticate(String username, String password){
        UserDetails fetchedUser = loadUserByUsername(username);

        if(fetchedUser != null)
        {
            return passwordEncoder.matches(password,fetchedUser.getPassword());

        }
        return false;
    }

    public StoreUser createUser(StoreUser user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repository.save(user);
    }
}

