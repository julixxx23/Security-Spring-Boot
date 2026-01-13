package com.example.SpringHalcones.controller;

import com.example.SpringHalcones.dto.Roles.RolesCreateDTO;
import com.example.SpringHalcones.dto.Roles.RolesResponseDTO;
import com.example.SpringHalcones.dto.Roles.RolesSimpleDTO;
import com.example.SpringHalcones.dto.Roles.RolesUpdateDTO;
import com.example.SpringHalcones.service.RolesService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/role")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class RolesController {
    private final RolesService rolesService;

    @GetMapping
    public ResponseEntity<Page<RolesSimpleDTO>> buscarTodos(
            @PageableDefault(size = 10, sort = "name")Pageable pageable){
        return ResponseEntity.ok(rolesService.buscarTodos(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RolesResponseDTO> buscarPorId(@PathVariable Long id){
        RolesResponseDTO rolesResponseDTO = rolesService.buscarPorId(id);
        return ResponseEntity.ok(rolesResponseDTO);
    }

    @PostMapping
    public ResponseEntity<RolesResponseDTO> crear(@Valid @RequestBody RolesCreateDTO dto){
        RolesResponseDTO rolesResponseDTO = rolesService.crear(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(rolesResponseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RolesResponseDTO> actualizar(@PathVariable Long id, @Valid @RequestBody RolesUpdateDTO dto){
        RolesResponseDTO rolesResponseDTO = rolesService.actualizar(id, dto);
        return ResponseEntity.ok(rolesResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        rolesService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

}
