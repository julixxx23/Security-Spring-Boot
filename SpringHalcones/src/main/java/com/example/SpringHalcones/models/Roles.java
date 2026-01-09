package com.example.SpringHalcones.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ROLES")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long Id;
    @Column(name = "NAME")
    private String name;

}
