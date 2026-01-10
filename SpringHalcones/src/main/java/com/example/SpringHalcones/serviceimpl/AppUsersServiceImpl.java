package com.example.SpringHalcones.serviceimpl;

import com.example.SpringHalcones.dto.AppUsers.AppUsersCreateDTO;
import com.example.SpringHalcones.dto.AppUsers.AppUsersResponseDTO;
import com.example.SpringHalcones.dto.AppUsers.AppUsersSimpleDTO;
import com.example.SpringHalcones.dto.AppUsers.AppUsersUpdateDTO;
import com.example.SpringHalcones.models.AppUsers;
import com.example.SpringHalcones.models.Roles;
import com.example.SpringHalcones.repository.AppUsersRepository;
import com.example.SpringHalcones.repository.RolesRepository;
import com.example.SpringHalcones.service.AppUsersService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;

@Service
@Transactional
public class AppUsersServiceImpl implements AppUsersService {

    private final AppUsersRepository appUsersRepository;
    private final RolesRepository rolesRepository;
    private final PasswordEncoder passwordEncoder;

    public AppUsersServiceImpl(
            AppUsersRepository appUsersRepository,
            RolesRepository rolesRepository,
            PasswordEncoder passwordEncoder){
        this.appUsersRepository = appUsersRepository;
        this.rolesRepository = rolesRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public AppUsersResponseDTO crear(AppUsersCreateDTO dto){
        if(appUsersRepository.existsByUsername(dto.getUsername())){
            throw new RuntimeException("El usuario ya esta en uso " + dto.getUsername());
        }

        List<Roles> rolesEncontrados = rolesRepository.findAllById(dto.getRoleIds());

        if (rolesEncontrados.isEmpty()) {
            throw new RuntimeException("No se encontraron roles válidos con los IDs proporcionados");
        }

        AppUsers appUsers = AppUsers.builder()
                .username(dto.getUsername())
                .password(passwordEncoder.encode(dto.getPassword()))
                .enabled(true)
                .roles(new HashSet<>(rolesEncontrados))
                .build();

        return AppUsersResponseDTO.fromEntity(appUsersRepository.save(appUsers));
    }

    @Override
    public AppUsersResponseDTO actualizar(Long id, AppUsersUpdateDTO dto){
        AppUsers appUsers = appUsersRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));
        if(dto.getUsername() != null && !dto.getUsername().equals(appUsers.getUsername())) {
            //Validación del username
            if(appUsersRepository.existsByUsername(dto.getUsername())){
                throw new RuntimeException("El username ya está en uso: " + dto.getUsername());
            }
            appUsers.setUsername(dto.getUsername());
        }
        //Actualización de contraseña
        if(dto.getPassword() != null && !dto.getPassword().isEmpty()) {
            appUsers.setPassword(passwordEncoder.encode(dto.getPassword()));
        }
        if(dto.getRolesIds() != null && !dto.getRolesIds().isEmpty()) {
            List<Roles> rolesEncontrados = rolesRepository.findAllById(dto.getRolesIds());
            if (rolesEncontrados.isEmpty()) {
                throw new RuntimeException("No se encontraron roles válidos con los IDs proporcionados");
            }
            //Actualización de Roles
            appUsers.setRoles(new HashSet<>(rolesEncontrados));
        }
        return AppUsersResponseDTO.fromEntity(appUsersRepository.save(appUsers));
    }

    @Override
    @Transactional(readOnly = true)
    public AppUsersResponseDTO buscarPorId(Long id){
        return appUsersRepository.findById(id)
                .map(AppUsersResponseDTO::fromEntity)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<AppUsersSimpleDTO> buscarTodos(String username, Pageable pageable){
        return appUsersRepository.findByUsernameContaining(username, pageable)
                .map(AppUsersSimpleDTO::fromEntity);
    }

    @Override
    public void eliminar(Long id){
        cambiarEstado(id, false);
    }

    @Override
    public void cambiarEstado(Long id, Boolean estado){
        AppUsers appUsers = appUsersRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado por ID" + id));
        appUsers.setEnabled(estado);
        appUsersRepository.save(appUsers);
    }



}