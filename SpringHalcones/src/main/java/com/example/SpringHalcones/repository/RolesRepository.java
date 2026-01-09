package com.example.SpringHalcones.repository;

import com.example.SpringHalcones.models.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RolesRepository extends JpaRepository<Roles, Long>{
    Optional<Roles> findByName(String name);
    boolean existsByName(String name);
}
