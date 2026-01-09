package com.example.SpringHalcones.models;

import jakarta.persistence.*;
import lombok.*;
import java.util.Set; // Importante usar Set para roles

@Entity
@Table(name = "APP_USERS")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppUsers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "USERNAME", nullable = false, unique = true)
    private String username;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "ENABLED", nullable = false)
    private Boolean enabled;


    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "USER_ROLES",
            joinColumns = @JoinColumn(name = "USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "ROLE_ID")
    )
    private Set<Roles> roles;
}