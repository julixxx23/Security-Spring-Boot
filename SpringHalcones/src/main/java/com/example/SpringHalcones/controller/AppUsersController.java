package com.example.SpringHalcones.controller;

import com.example.SpringHalcones.dto.AppUsers.AppUsersCreateDTO;
import com.example.SpringHalcones.dto.AppUsers.AppUsersResponseDTO;
import com.example.SpringHalcones.dto.AppUsers.AppUsersSimpleDTO;
import com.example.SpringHalcones.dto.AppUsers.AppUsersUpdateDTO;
import com.example.SpringHalcones.service.AppUsersService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/users")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class AppUsersController {
    public final AppUsersService appUsersService;

    @GetMapping
    public ResponseEntity<Page<AppUsersSimpleDTO>> buscarTodos(
            @RequestParam(required = false) String username,
            @PageableDefault(size = 10, sort = "username")Pageable pageable){
        return ResponseEntity.ok(appUsersService.buscarTodos(username, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppUsersResponseDTO> buscarPorId(@PathVariable Long id){
        AppUsersResponseDTO appUsersResponseDTO = appUsersService.buscarPorId(id);
        return ResponseEntity.ok(appUsersResponseDTO);
    }
    @PostMapping
    public ResponseEntity<AppUsersResponseDTO> crear(@Valid @RequestBody AppUsersCreateDTO dto){
        AppUsersResponseDTO appUsersResponseDTO = appUsersService.crear(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(appUsersResponseDTO);
    }
    @PutMapping("/{id}")
    public ResponseEntity<AppUsersResponseDTO> actualizar(@PathVariable Long id, @Valid @RequestBody AppUsersUpdateDTO dto){
        AppUsersResponseDTO appUsersResponseDTO = appUsersService.actualizar(id, dto);
        return ResponseEntity.ok(appUsersResponseDTO);
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<Void> cambiarEstado(@PathVariable Long id, @RequestParam Boolean estado){
        appUsersService.cambiarEstado(id, estado);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(Long id){
        appUsersService.eliminar(id);
        return ResponseEntity.noContent().build();
    }


}
