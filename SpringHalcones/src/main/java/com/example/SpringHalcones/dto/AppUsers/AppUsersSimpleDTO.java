package com.example.SpringHalcones.dto.AppUsers;

import com.example.SpringHalcones.models.AppUsers;
import com.example.SpringHalcones.models.Roles;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppUsersSimpleDTO {
    private Long id;
    private String username;
    private Boolean enabled;
    private List<String> roles;


    public static AppUsersResponseDTO fromEntity(AppUsers appUsers){
        return AppUsersResponseDTO.builder()
                .id(appUsers.getId())
                .username(appUsers.getUsername())
                .enabled(appUsers.getEnabled())
                .roles(appUsers.getRoles().stream()
                        .map(Roles::getName)
                        .toList())
                .build();
    }
}
