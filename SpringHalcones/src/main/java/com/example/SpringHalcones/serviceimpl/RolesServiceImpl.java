package com.example.SpringHalcones.serviceimpl;

import com.example.SpringHalcones.dto.Roles.RolesCreateDTO;
import com.example.SpringHalcones.dto.Roles.RolesResponseDTO;
import com.example.SpringHalcones.dto.Roles.RolesSimpleDTO;
import com.example.SpringHalcones.dto.Roles.RolesUpdateDTO;
import com.example.SpringHalcones.models.Roles;
import com.example.SpringHalcones.repository.RolesRepository;
import com.example.SpringHalcones.service.RolesService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RolesServiceImpl implements RolesService {
    private final RolesRepository rolesRepository;

    public RolesServiceImpl(RolesRepository rolesRepository){
        this.rolesRepository = rolesRepository;
    }

    @Override
    public RolesResponseDTO crear(RolesCreateDTO dto){
        String nombreRol = dto.getName().toUpperCase();
        if(rolesRepository.existsByName(nombreRol)){
            throw new RuntimeException("El nombre del rol ya esta en uso " + nombreRol);
        }

        Roles roles = Roles.builder()
                .name(nombreRol)
                .enabled(true)
                .build();

        return RolesResponseDTO.fromEntity(rolesRepository.save(roles));
    }

    @Override
    @Transactional(readOnly = true)
    public RolesResponseDTO buscarPorId(Long id){
        return rolesRepository.findById(id)
                .map(RolesResponseDTO::fromEntity)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado con ID: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<RolesSimpleDTO> buscarTodos(Pageable pageable){
        return rolesRepository.findAll(pageable)
                .map(RolesSimpleDTO::fromEntity);
    }

    @Override
    public RolesResponseDTO actualizar(Long id, RolesUpdateDTO dto){
        Roles roles = rolesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado por ID " + id));

        if(dto.getName() != null){
            String nombreUpper = dto.getName().toUpperCase();
            if(!nombreUpper.equals(roles.getName())){
                if(rolesRepository.existsByName(nombreUpper)){
                    throw new RuntimeException("El Rol ya esta en uso");
                }
                roles.setName(nombreUpper);
            }
        }
        return RolesResponseDTO.fromEntity(rolesRepository.save(roles));
    }

    @Override
    public void cambiarEstado(Long id, Boolean estado){
        Roles roles = rolesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado por ID" +id));
        roles.setEnabled(estado);
        rolesRepository.save((roles));
    }

    @Override
    public void eliminar(Long id){
        Roles roles = rolesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado por ID " + id));

        roles.setEnabled(false);
        rolesRepository.save(roles);
    }
}