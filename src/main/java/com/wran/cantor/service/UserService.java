package com.wran.cantor.service;

import com.wran.cantor.dto.RegisterUserForm;
import com.wran.cantor.exception.CredentialsFormatException;
import com.wran.cantor.exception.EmailAlreadyExistsException;
import com.wran.cantor.exception.UserAlreadyExistsException;
import com.wran.cantor.model.User;
import com.wran.cantor.model.Wallet;
import com.wran.cantor.repository.RoleRepository;
import com.wran.cantor.repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Base64;
import java.util.Calendar;
import java.util.regex.Pattern;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private Logger LOGGER = LogManager.getLogger(getClass());

    private final static String EMAIL_REGEX = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?";
    private final static String USERNAME_REGEX = "^[a-z0-9]{4,24}$";
    private final static String PASSWORD_REGEX = "^[a-zA-Z0-9]{6,24}$";

    public User findByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public User register(RegisterUserForm registerForm) throws UserAlreadyExistsException, EmailAlreadyExistsException,
            CredentialsFormatException {
        if (!validateCredentials(registerForm))
            throw new CredentialsFormatException("Invalid format exception!");
        if(userRepository.existsByUsername(decodeFromBase64(registerForm.getUsername())))
            throw new UserAlreadyExistsException("User already exists!");
        if(userRepository.existsByEmail(decodeFromBase64(registerForm.getEmail())))
            throw new EmailAlreadyExistsException("Email already exists!");


        User user = new User(decodeFromBase64(registerForm.getUsername()),
                passwordEncoder.encode(decodeFromBase64(registerForm.getPassword())),
                decodeFromBase64(registerForm.getEmail()), decodeFromBase64(registerForm.getFirstName()),
                decodeFromBase64(registerForm.getLastName()), Calendar.getInstance(),
                Arrays.asList(roleRepository.findByName("ROLE_USER")),
                new Wallet(registerForm.getFunds(), registerForm.getUsdAmount(), registerForm.getEurAmount(),
                        registerForm.getChfAmount(), registerForm.getRubAmount(), registerForm.getCzkAmount(),
                        registerForm.getGbpAmount()));

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

    private boolean validateCredentials(RegisterUserForm form){
        Pattern usernameReg = Pattern.compile(USERNAME_REGEX);
        Pattern emailReg = Pattern.compile(EMAIL_REGEX);
        Pattern passwordReg = Pattern.compile(PASSWORD_REGEX);

        if(!usernameReg.matcher(decodeFromBase64(form.getUsername())).matches())
            return false;
        if (!emailReg.matcher(decodeFromBase64(form.getEmail())).matches())
            return false;
        if(!passwordReg.matcher(decodeFromBase64(form.getPassword())).matches())
            return false;

        return true;
    }

    public boolean changeEmail(User user, String email) {
        Pattern emailReg = Pattern.compile(EMAIL_REGEX);
        if (emailReg.matcher(email).matches()) {
            user.setEmail(email);
            save(user);
            return true;
        }
        return false;
    }

    public boolean changePassword(User user, String password) {
        Pattern passReg = Pattern.compile(PASSWORD_REGEX);
        if(passReg.matcher(password).matches()){
            user.setPassword(encodePassword(password));
            save(user);
            return true;
        }
        return false;
    }
}
