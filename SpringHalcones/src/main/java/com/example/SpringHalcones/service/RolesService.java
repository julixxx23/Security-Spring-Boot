package com.example.SpringHalcones.service;

import com.example.SpringHalcones.dto.Roles.RolesCreateDTO;
import com.example.SpringHalcones.dto.Roles.RolesResponseDTO;
import com.example.SpringHalcones.dto.Roles.RolesSimpleDTO;
import com.example.SpringHalcones.dto.Roles.RolesUpdateDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RolesService {
    RolesResponseDTO crear(RolesCreateDTO dto);
    RolesResponseDTO actualizar(Long id, RolesUpdateDTO dto);
    RolesResponseDTO buscarPorId(Long id);
    Page<RolesSimpleDTO> buscarTodos(Pageable pageable);
    void eliminar(Long id);
    void cambiarEstado(Long id, Boolean estado);


}
