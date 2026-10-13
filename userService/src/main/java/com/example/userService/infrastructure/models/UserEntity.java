package com.example.userService.infrastructure.models;

import com.example.userService.domain.enums.UserRole;
import com.example.userService.domain.enums.UserStatus;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
    @Enumerated(value = EnumType.STRING)
    private UserRole userRole;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserStatus userStatus;

    @Column(nullable = false)
    private List<String> createdLinks;

    @Column(nullable = false)
    private Integer countOfCreatedLinks;

    @Column(nullable = false)
    private LocalDateTime createdUserDate;

    @Column(nullable = false)
    private LocalDateTime userUpdateDate;
}
