package com.example.SpringHalcones.repository;

import com.example.SpringHalcones.models.AppUsers;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppUsersRepository extends JpaRepository<AppUsers, Long>{
    List<AppUsers> findByUsername(String userName);
    boolean existsByUsername(String userName);

}
