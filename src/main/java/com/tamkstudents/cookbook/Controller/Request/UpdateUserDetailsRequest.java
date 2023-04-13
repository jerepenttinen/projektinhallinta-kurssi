package com.tamkstudents.cookbook.Controller.Request;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UpdateUserDetailsRequest {
    String description;
    String image;
}
