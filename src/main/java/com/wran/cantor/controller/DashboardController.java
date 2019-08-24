package com.wran.cantor.controller;

import com.wran.cantor.model.User;
import com.wran.cantor.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class DashboardController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String dashboardPage(Model model, Principal principal){
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("name", user.getFirstName() + " " + user.getLastName());
        model.addAttribute("availableFunds", 1500.0f);

        return "dashboard";
    }
}
