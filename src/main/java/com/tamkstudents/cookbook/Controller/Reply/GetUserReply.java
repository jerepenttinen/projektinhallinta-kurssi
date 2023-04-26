package com.tamkstudents.cookbook.Controller.Reply;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class GetUserReply {
    Long id;
    String username;
    String image;
    String description;
}
