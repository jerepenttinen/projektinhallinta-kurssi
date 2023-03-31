package com.tamkstudents.cookbook.Controller.Request;

import jakarta.validation.constraints.NotBlank;
import lombok.Value;

@Value
public class CreateReviewRequest {
    @NotBlank
    String content;

    @NotBlank
    Boolean isUpvote;
}
