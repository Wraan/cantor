package com.wran.cantor.config;

import com.wran.cantor.model.User;
import com.wran.cantor.model.Wallet;
import com.wran.cantor.model.security.Role;
import com.wran.cantor.repository.RoleRepository;
import com.wran.cantor.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

@Component
public class StartupEssentialData implements ApplicationRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Lazy
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        addEssentialRoles();
        addEssentialUsers();
    }

    private void addEssentialRoles(){
        List<Role> roles = new ArrayList<>();
        if(!roleRepository.existsByName("ROLE_ADMIN"))
            roles.add(new Role("ROLE_ADMIN"));
        if(!roleRepository.existsByName("ROLE_USER"))
            roles.add(new Role("ROLE_USER"));

        roleRepository.saveAll(roles);
    }


    private void addEssentialUsers(){
        List<User> users = new ArrayList<>();
        if(!userRepository.existsByUsername("admin"))
            users.add(new User("admin", passwordEncoder.encode("password"),
                    "admin@admin.com", "Admin", "Admin", Calendar.getInstance(),
                    Arrays.asList(roleRepository.findByName("ROLE_ADMIN"), roleRepository.findByName("ROLE_USER")),
                    new Wallet(1500, 1, 10, 100, 1000, 10000, 100000)));
        if(!userRepository.existsByUsername("user"))
            users.add(new User("user", passwordEncoder.encode("password"),
                    "user@user.com", "User", "User", Calendar.getInstance(),
                    Arrays.asList(roleRepository.findByName("ROLE_USER")),
                    new Wallet(1500, 100, 100, 100, 100, 100, 100)));

        if(!users.isEmpty())
            userRepository.saveAll(users);
    }
}
