package com.example.SpringHalcones.repository;

import com.example.SpringHalcones.models.AppUsers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
public interface AppUsersRepository extends JpaRepository<AppUsers, Long>{
    Optional<AppUsers> findByUsername(String userName);
    boolean existsByUsername(String userName);
    Page<AppUsers> findByUsernameContaining(String username, Pageable pageable);
}