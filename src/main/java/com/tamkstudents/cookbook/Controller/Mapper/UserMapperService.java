package com.tamkstudents.cookbook.Controller.Mapper;

import com.tamkstudents.cookbook.Controller.Reply.GetUserReply;
import com.tamkstudents.cookbook.Controller.Request.SignUpRequest;
import com.tamkstudents.cookbook.Domain.Dao.UserDao;
import com.tamkstudents.cookbook.Service.MediaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.HashSet;

@Service
@RequiredArgsConstructor
public class UserMapperService {
    private final MediaService mediaService;
    public UserDao createUserDao(SignUpRequest signUpRequest) {
        return UserDao.builder()
                .userName(signUpRequest.getUsername())
                .createdAt(new Timestamp(System.currentTimeMillis()))
                .recipes(new HashSet<>())
                .build();
    }

    public GetUserReply getUserReplyFromUserDao(UserDao userDao) {
        final var builder = GetUserReply.builder()
                .id(userDao.getId())
                .username(userDao.getUserName());
        if (userDao.getImage() != null) {
            builder.image(mediaService.imageToBase64(userDao.getImage().getImage()));
        }
        if (userDao.getDescription() != null) {
            builder.description(userDao.getDescription());
        }
        return builder.build();
    }
}
