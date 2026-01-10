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
    AppUsersResponseDTO actualizar(Long id, AppUsersUpdateDTO dto);
    AppUsersResponseDTO buscarPorId(Long id);
    Page<AppUsersSimpleDTO> buscarTodos(String username, Pageable pageable);
    void eliminar(Long id);
    void cambiarEstado(Long id, Boolean estado);


}
