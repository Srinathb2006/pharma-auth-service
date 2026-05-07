package com.kce.pharma.config;


import com.kce.pharma.entity.Role;
import com.kce.pharma.entity.Status;
import com.kce.pharma.entity.User;
import com.kce.pharma.repository.UserRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class AdminSeeder implements CommandLineRunner {

    private final UserRepository repository;
    private final PasswordEncoder encoder;

    public AdminSeeder(UserRepository repository,
                       PasswordEncoder encoder) {

        this.repository = repository;
        this.encoder = encoder;
    }

    @Override
    public void run(String... args) {

        String adminEmail = "admin@pharmacare.com";

        if (repository.findByEmail(adminEmail).isEmpty()) {

            User admin = new User();

            admin.setEmail(adminEmail);

            admin.setPassword(
                    encoder.encode("admin123")
            );

            admin.setRole(Role.ADMIN);

            admin.setName("Main Admin");

            admin.setPhoneNumber("9999999999");

            admin.setStatus(Status.Active);

            admin.setJoinDate(LocalDate.now());

            admin.setFirstLogin(false);

            repository.save(admin);

            System.out.println("DEFAULT ADMIN CREATED");
        }
    }
}