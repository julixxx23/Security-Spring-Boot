package com.example.SpringHalcones.serviceimpl;

import com.example.SpringHalcones.models.AppUsers;
import com.example.SpringHalcones.repository.AppUsersRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final AppUsersRepository appUsersRepository;

    public CustomUserDetailsService(AppUsersRepository appUsersRepository) {
        this.appUsersRepository = appUsersRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //Buscar usuario en BD
        AppUsers appUser = appUsersRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

        //Convertir Roles a 'GrantedAuthority' de Spring
        var authorities = appUser.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());

        //Retornar el objeto User oficial de Spring (¡Esto evita el StackOverflow!)
        return new User(
                appUser.getUsername(),
                appUser.getPassword(),
                appUser.getEnabled(),
                true,
                true,
                true,
                authorities
        );
    }
}