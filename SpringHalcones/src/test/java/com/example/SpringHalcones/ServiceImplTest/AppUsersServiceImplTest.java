package com.example.SpringHalcones.ServiceImplTest;

import com.example.SpringHalcones.repository.AppUsersRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AppUsersServiceImplTest {
    @Mock
    private AppUsersRepository appUsersRepository;

}
