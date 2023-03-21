package com.tamkstudents.cookbook.Domain.Dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter @Setter
public class ReviewDto {
    private long id;
    private int userId;
    private Date created;
    private Date editedLastTime;
    private String content;
}
