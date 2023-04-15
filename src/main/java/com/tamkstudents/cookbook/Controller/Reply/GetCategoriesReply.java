package com.tamkstudents.cookbook.Controller.Reply;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class GetCategoriesReply {
    long id;
    String name;
}
