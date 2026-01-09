package com.example.SpringHalcones.service;

import com.example.SpringHalcones.dto.AppUsers.AppUsersCreateDTO;
import com.example.SpringHalcones.dto.AppUsers.AppUsersResponseDTO;
import com.example.SpringHalcones.dto.AppUsers.AppUsersSimpleDTO;
import com.example.SpringHalcones.dto.AppUsers.AppUsersUpdateDTO;
import com.example.SpringHalcones.repository.AppUsersRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AppUsersService{
    AppUsersResponseDTO crear(AppUsersCreateDTO dto);
    AppUsersResponseDTO actualizar(AppUsersUpdateDTO dto);
    AppUsersResponseDTO buscarPorId(Long id);
    Page<AppUsersSimpleDTO> buscarTodos(Pageable pageable);
    Void eliminar(Long id);
    Void cambiarEstado(Long id, Boolean estado);


}
