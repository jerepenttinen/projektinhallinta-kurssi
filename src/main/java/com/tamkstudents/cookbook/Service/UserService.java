package com.tamkstudents.cookbook.Service;

import com.tamkstudents.cookbook.Domain.Dao.UserDao;
import com.tamkstudents.cookbook.Domain.RepositoryInterface.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<UserDao> getAll(List<Long> userIds) {
        return userRepository.findAllById(userIds);
    }
}
