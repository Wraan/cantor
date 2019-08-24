package com.wran.cantor.service;

import com.wran.cantor.dto.RegisterUserForm;
import com.wran.cantor.exception.EmailAlreadyExistsException;
import com.wran.cantor.exception.UserAlreadyExistsException;
import com.wran.cantor.model.User;
import com.wran.cantor.repository.RoleRepository;
import com.wran.cantor.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Base64;
import java.util.Calendar;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User findByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public User register(RegisterUserForm registerForm) throws UserAlreadyExistsException, EmailAlreadyExistsException {
        if(userRepository.existsByUsername(decodeFromBase64(registerForm.getUsername())))
            throw new UserAlreadyExistsException("User already exists!");
        if(userRepository.existsByEmail(decodeFromBase64(registerForm.getEmail())))
            throw new EmailAlreadyExistsException("Email already exists!");

        User user = new User(decodeFromBase64(registerForm.getUsername()),
                passwordEncoder.encode(decodeFromBase64(registerForm.getPassword())),
                decodeFromBase64(registerForm.getEmail()), decodeFromBase64(registerForm.getFirstName()),
                decodeFromBase64(registerForm.getLastName()), Calendar.getInstance(),
                Arrays.asList(roleRepository.findByName("ROLE_USER")));

        return userRepository.save(user);
    }

    private String decodeFromBase64(String base){
        return new String(Base64.getDecoder().decode(base.trim().getBytes()));
    }

    public boolean existsByEmail(String email){
        return userRepository.existsByEmail(email);
    }

    public User save(User user){
        return userRepository.save(user);
    }

    public String encodePassword(String password){
        return passwordEncoder.encode(password);
    }
}
