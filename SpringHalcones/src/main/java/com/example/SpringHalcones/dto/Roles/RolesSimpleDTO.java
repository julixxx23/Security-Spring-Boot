package com.example.SpringHalcones.dto.Roles;

import com.example.SpringHalcones.models.Roles;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RolesSimpleDTO {
    private Long id;
    private String name;

    public static RolesResponseDTO fromEntity(Roles roles){
        return RolesResponseDTO.builder()
                .id(roles.getId())
                .name(roles.getName())
                .build();
    }

}
