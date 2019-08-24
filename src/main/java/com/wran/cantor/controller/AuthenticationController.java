package com.wran.cantor.controller;

import com.wran.cantor.dto.RegisterUserForm;
import com.wran.cantor.exception.EmailAlreadyExistsException;
import com.wran.cantor.exception.UserAlreadyExistsException;
import com.wran.cantor.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class AuthenticationController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody RegisterUserForm registerForm){
        try {
            userService.register(registerForm);
        } catch (UserAlreadyExistsException | EmailAlreadyExistsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @GetMapping("/register")
    public String registerPage(Authentication authentication){
        if(authentication.isAuthenticated())
            return "/";
        return "register";
    }

    @GetMapping("/login")
    public String loginPage(Authentication authentication){
        if(authentication.isAuthenticated())
            return "/";
        return "login";
    }
}