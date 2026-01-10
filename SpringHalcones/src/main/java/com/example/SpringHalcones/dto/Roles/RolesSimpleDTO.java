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

    public static RolesSimpleDTO fromEntity(Roles roles){
        return RolesSimpleDTO.builder()
                .id(roles.getId())
                .name(roles.getName())
                .build();
    }

}
