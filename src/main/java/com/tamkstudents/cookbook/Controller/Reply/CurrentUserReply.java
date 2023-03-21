package com.tamkstudents.cookbook.Controller.Reply;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record CurrentUserReply(
        @NotNull
        Long id,
        @NotNull
        String username,
        @NotNull
        String firstName,
        @NotNull
        String lastName,
        @NotNull
        Long profileId,
        @NotNull
        String email
) {
}
