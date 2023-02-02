package com.tamkstudents.cookbook.Domain.DatabaseModels.Dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter @Setter
public class ReviewDto {
    private int id;
    private int userId;
    private Date created;
    private Date editedLastTime;
    private String content;
}
