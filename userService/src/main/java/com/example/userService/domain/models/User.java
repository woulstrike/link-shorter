package com.example.userService.domain.models;

import com.example.userService.domain.enums.UserRole;
import com.example.userService.domain.enums.UserStatus;
import com.example.userService.domain.exceptions.DomainException;
import com.example.userService.domain.valueObjects.PasswordHash;
import com.example.userService.domain.valueObjects.UserId;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.*;

@Getter
public class User {
    private final UserId userId;

    private String userName;

    private PasswordHash passwordHashCode;

    private UserRole userRole;

    private UserStatus userStatus;

    private final List<String> createdLinks;

    private Integer countOfCreatedLinks;

    private final LocalDateTime createdUserDate;

    private LocalDateTime userUpdateDate;

    private User(UserId userId, String userName, PasswordHash passwordHashCode, UserRole userRole, UserStatus userStatus, List<String> createdLinks, LocalDateTime createdUserDate, LocalDateTime userUpdateDate) {
        if (userName == null) throw new DomainException("User name cannot be null.");
        if (passwordHashCode == null) throw new DomainException("Password hash code cannot be null.");
        if (createdLinks == null) throw new DomainException("Created links cannot be null.");
        if (userRole == null) throw new DomainException("User role cannot be null.");

        this.userId = userId;
        this.userName = userName;
        this.passwordHashCode = passwordHashCode;
        this.userRole = userRole;
        this.userStatus = userStatus;
        this.createdLinks = new ArrayList<>(createdLinks);
        this.countOfCreatedLinks = createdLinks.size();
        this.createdUserDate = LocalDateTime.now();
        this.userUpdateDate = userUpdateDate;
    }

    public User createUser(String userName, PasswordHash passwordHashCode, UserStatus userStatus, List<String> createdLinks, LocalDateTime createdUserDate) {
        return new User(userId, userName, passwordHashCode, UserRole.USER, UserStatus.ACTIVE, createdLinks, createdUserDate, LocalDateTime.now());
    }

    public User restoreUser(UUID userId, String userName, PasswordHash passwordHashCode, UserRole userRole, UserStatus userStatus, List<String> createdLinks, LocalDateTime createdUserDate, LocalDateTime userUpdateDate) throws DomainException {
        UserId restoredUserId = UserId.of(userId);
        return new User(restoredUserId, userName, passwordHashCode, userRole, userStatus, createdLinks, createdUserDate,  userUpdateDate);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof User user)) return false;
        return Objects.equals(userId, user.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(userId);
    }

    public void updateUserName(String userName) {
        if (userName == null || userName.isBlank()) throw new DomainException("UserName is required.");
        if (this.userName.equals(userName)) throw new DomainException("UserName already set.");

        this.userName = userName;
        this.userUpdateDate = LocalDateTime.now();
    }

    public void updateCreatedLinks(String link) {
        if (link == null || link.isBlank()) throw new DomainException("Link is required.");
        if (this.createdLinks.contains(link)) throw new DomainException("You already created this link.");

        this.createdLinks.add(link);
        this.countOfCreatedLinks++;
        this.userUpdateDate = LocalDateTime.now();
    }

    public void updatePassword(String passwordHash) {
        if (passwordHash == null || passwordHash.isBlank()) throw new DomainException("New password required.");

        this.passwordHashCode = new PasswordHash(passwordHash);
        this.userUpdateDate = LocalDateTime.now();
    }

    public void deleteUser(UserRole userRole) {
        if (userRole == null) throw new DomainException("User role required.");
        if (this.userStatus.equals(UserStatus.DELETED) || this.userStatus.equals(UserStatus.BANNED)) throw new DomainException("User not found.");

        this.userStatus = UserStatus.DELETED;
        this.userUpdateDate = LocalDateTime.now();
    }

    public void banUser(UserRole userRole) {
        if (userRole == null) throw new DomainException("User role required.");
        if (this.userRole.equals(UserRole.USER)) throw new DomainException("Permission denied.");
        if (this.userStatus.equals(UserStatus.DELETED) || this.userStatus.equals(UserStatus.BANNED)) throw new DomainException("User not found.");

        this.userStatus = UserStatus.BANNED;
        this.userUpdateDate = LocalDateTime.now();
    }

    public void updateUserRole(UserRole userRole) {
        if (userRole == null) throw new DomainException("User role is required.");

        this.userRole = userRole;
    }
}
