package com.tamkstudents.cookbook.Controller.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class CreateReviewRequest {
    @NotBlank
    String content;

    @NotNull
    Boolean upvote;
}
