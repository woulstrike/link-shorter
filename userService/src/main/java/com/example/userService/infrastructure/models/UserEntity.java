package com.example.userService.infrastructure.models;

import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Table(name = "users")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class UserEntity {
    @Id
    private UUID userId;

    @Column(nullable = false, unique = true)
    private String userName;

    @Column(nullable = false)
    private String passwordHashCode;

    @Column(nullable = false)
    private List<String> createdLinks;

    @Column(nullable = false)
    private Long countOfCreatedLinks;
}
