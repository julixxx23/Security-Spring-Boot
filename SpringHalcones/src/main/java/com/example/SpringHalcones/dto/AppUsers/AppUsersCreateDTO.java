package com.example.SpringHalcones.dto.AppUsers;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppUsersCreateDTO {
    @NotBlank(message = "El campo del usuario es obligatorio")
    @Size(min = 2, max = 20, message = "El campo del usuario debe tener entre 2 y 20 caracteres")
    private String username;
    @NotBlank(message = "La contraseña del usuario es obligatoria")
    @Size(min = 2, max = 20, message = "La contraseña debe tener entre 2 y 20 caracteres")
    private String password;
    @NotEmpty(message = "El usuario debe tener al menos un rol asignado")
    private List<Long> roleIds;
}
