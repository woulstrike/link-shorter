package com.example.userService.infrastructure.interfaces;

import com.example.userService.infrastructure.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IUserJPA extends JpaRepository <UserEntity, UUID> {
}
