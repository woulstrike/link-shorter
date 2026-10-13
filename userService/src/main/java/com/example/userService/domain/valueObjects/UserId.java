package com.example.userService.domain.valueObjects;


import com.github.f4b6a3.uuid.UuidCreator;

import java.util.Objects;
import java.util.UUID;

public record UserId (
        UUID value
) {
   public UserId {
       Objects.requireNonNull(value(), "value is null");
   }

   public static UserId createUserId() {
       return new UserId(UuidCreator.getTimeOrderedEpoch());
   }

   public static UserId of(UUID userId) {
       return new UserId(userId);
   }
}
