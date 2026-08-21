package com.solestore.config;

import com.solestore.entity.Role;
import com.solestore.entity.User;
import com.solestore.repository.RoleRepository;
import com.solestore.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initializeData(
            RoleRepository roleRepository,
            UserRepository userRepository,
            PasswordEncoder passwordEncoder) {

        return args -> {

            Role customerRole = roleRepository.findByName("ROLE_CUSTOMER")
                    .orElseGet(() -> {
                        Role role = new Role();
                        role.setName("ROLE_CUSTOMER");
                        return roleRepository.save(role);
                    });

            Role adminRole = roleRepository.findByName("ROLE_ADMIN")
                    .orElseGet(() -> {
                        Role role = new Role();
                        role.setName("ROLE_ADMIN");
                        return roleRepository.save(role);
                    });

            String adminEmail = "admin@solestore.com";

            if (!userRepository.existsByEmailIgnoreCase(adminEmail)) {

                User admin = new User();

                admin.setName("SoleStore Admin");
                admin.setEmail(adminEmail);
                admin.setPassword(passwordEncoder.encode("Admin@12345"));
                admin.setMobile("9999999999");
                admin.setRole(adminRole);

                userRepository.save(admin);
            }
        };
    }
}
