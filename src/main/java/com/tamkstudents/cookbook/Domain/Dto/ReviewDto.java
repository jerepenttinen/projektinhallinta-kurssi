package com.tamkstudents.cookbook.Domain.Dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter @Setter
@Builder
public class ReviewDto {
    private long id;
    private int userId;
    private Timestamp created;
    private String content;
    private boolean upvote;
}
