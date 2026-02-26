package com.kce.pharma.config;

import com.kce.pharma.entity.Role;
import com.kce.pharma.entity.Status;
import com.kce.pharma.entity.User;
import com.kce.pharma.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initAdmin(UserRepository repository,
                                PasswordEncoder encoder) {
        return args -> {

            String adminEmail = "admin@pharma.com";

            if (repository.findByEmail(adminEmail).isEmpty()) {

                User admin = new User();
                admin.setName("Admin");
                admin.setEmail(adminEmail);
                admin.setPassword(encoder.encode("admin123"));
                admin.setRole(Role.ADMIN);
                admin.setStatus(Status.Active);
                admin.setFirstLogin(false);

                repository.save(admin);

                System.out.println("✅ Default admin user created.");
            } else {
                System.out.println("ℹ️ Admin user already exists.");
            }
        };
    }
}