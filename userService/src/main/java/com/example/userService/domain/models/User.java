package com.example.userService.domain.models;

import com.example.userService.domain.exceptions.DomainException;
import com.example.userService.domain.valueObjects.UserId;
import com.github.f4b6a3.uuid.UuidCreator;
import lombok.Getter;

import java.util.*;

@Getter
public class User {
    private final UserId userId;

    private String userName;

    private final String passwordHashCode;

    private List<String> createdLinks;

    private Integer countOfCreatedLinks;

    private User(UserId userId, String userName, String passwordHashCode, List<String> createdLinks) throws DomainException {
        if (userName == null) { throw new DomainException("User name cannot be null."); }
        if (passwordHashCode == null) { throw new DomainException("Password hash code cannot be null."); }
        if (createdLinks == null) { throw new DomainException("Created links cannot be null."); }

        this.userId = userId;
        this.userName = userName;
        this.passwordHashCode = passwordHashCode;
        this.createdLinks = new ArrayList<>(createdLinks);
        this.countOfCreatedLinks = createdLinks.size();
    }

    public User createUser(String userName, String passwordHashCode, List<String> createdLinks) throws DomainException {
        return new User(userId, userName, passwordHashCode, createdLinks);
    }


    public User restoreUser(UUID userId, String userName, String passwordHashCode, List<String> createdLinks) throws DomainException {
        UserId restoredUserId = UserId.of(userId);
        return new User(restoredUserId, userName, passwordHashCode, createdLinks);
    }

    public Boolean isUserNameExists(String userName) throws DomainException {
        if (userName == null) { throw new DomainException("UserName cannot be null."); }
        if (userName.isEmpty()) { throw new DomainException("UserName cannot be empty."); }

        if (this.userName == userName) {
            return true;
        }
    }
}
