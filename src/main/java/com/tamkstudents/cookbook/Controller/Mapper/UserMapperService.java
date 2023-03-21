package com.tamkstudents.cookbook.Controller.Mapper;

import com.tamkstudents.cookbook.Controller.Request.SignUpRequest;
import com.tamkstudents.cookbook.Domain.DatabaseModels.Dao.UserDao;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.HashSet;

@Service
public class UserMapperService {
    public UserDao createUserDao(SignUpRequest signUpRequest) {
        return UserDao.builder()
                .userName(signUpRequest.getUsername())
                .createdAt(new Timestamp(System.currentTimeMillis()))
                .recipes(new HashSet<>())
                .build();
    }
}
