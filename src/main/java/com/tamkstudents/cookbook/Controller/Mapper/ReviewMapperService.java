package com.tamkstudents.cookbook.Controller.Mapper;

import com.tamkstudents.cookbook.Domain.Dao.ReviewDao;
import com.tamkstudents.cookbook.Domain.Dto.ReviewDto;
import org.springframework.stereotype.Service;

@Service
public class ReviewMapperService {
    public ReviewDto reviewDtoFromReviewDao(ReviewDao review) {
        return ReviewDto.builder()
                .id(review.getId())
                .content(review.getContent())
                .userId(review.getReviewer().getId().intValue())
                .created(review.getCreatedAt())
                .upvote(review.getIsUpvote())
                .build();
    }
}
