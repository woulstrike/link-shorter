package com.example.userService.application.services;

import com.example.userService.infrastructure.interfaces.IUserJPA;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final IUserJPA userRepository;

}
