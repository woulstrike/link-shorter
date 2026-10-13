package com.example.userService.domain.valueObjects;

import com.example.userService.domain.models.User;

import java.util.Objects;

public record PasswordHash (
        String passwordHash
) {
    public PasswordHash {
        Objects.requireNonNull(passwordHash, "Password hash cannot be null");
    }

    @Override
    public String toString() {
        return "protected";
    }
}
