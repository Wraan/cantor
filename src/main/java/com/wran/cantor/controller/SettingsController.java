package com.wran.cantor.controller;

import com.wran.cantor.model.User;
import com.wran.cantor.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.RoundingMode;
import java.security.Principal;
import java.text.DecimalFormat;
import java.util.Base64;


@Controller
public class SettingsController {

    private Logger LOGGER = LogManager.getLogger(getClass());

    @Autowired
    private UserService userService;

    @GetMapping("/settings")
    public String settingsPage(Model model, Principal principal){
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("name", user.getFirstName() + " " + user.getLastName());
        return "settings";
    }

    @PostMapping("/settings/funds")
    public ResponseEntity<String> changeFunds(@RequestParam("newValue") String fundsString, Principal principal){
        fundsString = new String(Base64.getDecoder().decode(fundsString.trim().getBytes()));

        float funds;
        try{
            funds = (float) (Math.floor(Float.parseFloat(fundsString) * 10000) / 10000); // 4 decimal places
        } catch (NumberFormatException e){
            return new ResponseEntity<>("Funds format is invalid!", HttpStatus.NOT_ACCEPTABLE);
        }

        User user = userService.findByUsername(principal.getName());
        //add funds
        userService.save(user);

        LOGGER.info("Funds changed to {} for user: {}", funds, user.getUsername());

        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @PostMapping("/settings/email")
    public ResponseEntity<String> changeEmail(@RequestParam("newValue") String email, Principal principal){
        email = new String(Base64.getDecoder().decode(email.trim().getBytes()));

        if(userService.existsByEmail(email))
            return new ResponseEntity<>("User already exists!", HttpStatus.CONFLICT);

        User user = userService.findByUsername(principal.getName());
        user.setEmail(email);
        userService.save(user);

        LOGGER.info("Email changed to {} for user: {}", email, user.getUsername());

        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @PostMapping("/settings/password")
    public ResponseEntity<String> changePassword(@RequestParam("newValue") String password, Principal principal){
        password = new String(Base64.getDecoder().decode(password.trim().getBytes()));

        User user = userService.findByUsername(principal.getName());
        user.setPassword(userService.encodePassword(password));
        userService.save(user);

        LOGGER.info("Password changed for user: {}", user.getUsername());

        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

}
