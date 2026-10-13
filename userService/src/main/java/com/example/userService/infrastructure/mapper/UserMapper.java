package com.example.userService.infrastructure.mapper;

import com.example.userService.domain.models.User;
import com.example.userService.infrastructure.models.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User entityToDomain(UserEntity userEntity) {
        return User.restoreUser(
                userEntity.getUserId(),
                userEntity.getUserName(),
                userEntity.getPasswordHashCode(),
                userEntity.getUserRole(),
                userEntity.getUserStatus(),
                userEntity.getCreatedLinks(),
                userEntity.getCreatedUserDate(),
                userEntity.getUserUpdateDate()
        );
    }

    public UserEntity domainToEntity(User user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(user.getUserId().value());
        userEntity.setUserName(user.getUserName());
        userEntity.setPasswordHashCode(user.getPasswordHashCode().passwordHash());
        userEntity.setUserRole(user.getUserRole());
        userEntity.setUserStatus(user.getUserStatus());
        userEntity.setCreatedLinks(user.getCreatedLinks());
        userEntity.setCountOfCreatedLinks(user.getCountOfCreatedLinks());
        userEntity.setCreatedUserDate(user.getCreatedUserDate());
        userEntity.setUserUpdateDate(user.getUserUpdateDate());

        return userEntity;
    }
}
