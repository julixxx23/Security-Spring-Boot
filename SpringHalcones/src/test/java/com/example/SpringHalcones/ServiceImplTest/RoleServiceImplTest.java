package com.example.SpringHalcones.ServiceImplTest;

import com.example.SpringHalcones.repository.RolesRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class RoleServiceImplTest {
    @Mock
    private RolesRepository rolesRepository;
}
