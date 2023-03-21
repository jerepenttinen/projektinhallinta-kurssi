package com.tamkstudents.cookbook.Controller.Reply;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class CurrentUserReply {
    @NotBlank
    private final Long id;
    @NotBlank
    private final String username;
    @NotBlank
    private final String firstName;
    @NotBlank
    private final String lastName;
    @NotBlank
    private final Long profileId;
    @NotBlank
    private final String email;
}
