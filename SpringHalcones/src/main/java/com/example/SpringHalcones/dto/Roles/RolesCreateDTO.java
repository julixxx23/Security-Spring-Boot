package com.example.SpringHalcones.dto.Roles;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RolesCreateDTO {
    @NotBlank(message = "El nombre del Rol es obligatorio")
    @Size(min = 2, max = 12, message = "El rol debe contener entre 2 y 12 caracteres")
    private String name;
}
