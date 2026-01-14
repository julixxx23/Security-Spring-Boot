package com.example.SpringHalcones.config;

import com.example.SpringHalcones.models.AppUsers;
import com.example.SpringHalcones.models.Roles;
import com.example.SpringHalcones.repository.AppUsersRepository;
import com.example.SpringHalcones.repository.RolesRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(AppUsersRepository userRepository,
                                   RolesRepository rolesRepository,
                                   PasswordEncoder passwordEncoder) {
        return args -> {

            //Buscar o Crear el Rol ADMIN
            Roles adminRole = rolesRepository.findByName("ADMIN").orElseGet(() -> {
                Roles nuevoRol = new Roles();
                nuevoRol.setName("ADMIN");
                return rolesRepository.save(nuevoRol);
            });

            //Buscar o Crear el Usuario 'halcon'
            if (userRepository.findByUsername("halcon").isEmpty()) {
                AppUsers user = new AppUsers();
                user.setUsername("halcon");
                //Encriptamos "admin123" a BCrypt
                user.setPassword(passwordEncoder.encode("admin123"));
                user.setEnabled(true);
                user.setRoles(Collections.singleton(adminRole));

                userRepository.save(user);
                System.out.println("🦅 USUARIO ADMIN CREADO: User: halcon | Pass: admin123");
            }
        };
    }
}