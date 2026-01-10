package com.example.SpringHalcones.dto.AppUsers;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppUsersUpdateDTO {
    @NotBlank(message = "El campo del usuario es obligatorio")
    @Size(min = 2, max = 20, message = "El campo del usuario debe tener entre 2 y 20 caracteres")
    private String username;
    @NotBlank(message = "La contraseña del usuario es obligatoria")
    @Size(min = 2, max = 10, message = "La contraseña debe tener entre 2 y 10 caracteres")
    private String password;
    @NotNull(message = "Los roles son obligatorios")
    private Set<Long> rolesIds;

}
