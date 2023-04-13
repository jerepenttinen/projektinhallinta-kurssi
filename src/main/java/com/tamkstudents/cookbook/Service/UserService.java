package com.tamkstudents.cookbook.Service;

import com.tamkstudents.cookbook.Controller.Request.UpdateUserDetailsRequest;
import com.tamkstudents.cookbook.Domain.Dao.ImageDao;
import com.tamkstudents.cookbook.Domain.Dao.UserDao;
import com.tamkstudents.cookbook.Domain.RepositoryInterface.UserRepository;
import com.tamkstudents.cookbook.Service.Exceptions.UserNotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final EntityManager entityManager;
    private final MediaService mediaService;

    public List<UserDao> getAll(List<Long> userIds) {
        return userRepository.findAllById(userIds);
    }

    public UserDao getById(Long userId) throws UserNotFoundException {
        return userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
    }

    @Transactional(rollbackOn = UserNotFoundException.class)
    public UserDao updateDetails(Long userId, UpdateUserDetailsRequest updateUserDetailsRequest) throws UserNotFoundException {
        var user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        user.setDescription(updateUserDetailsRequest.getDescription());

        if (updateUserDetailsRequest.getImage() != null) {
            var image = new ImageDao();
            image.setImage(mediaService.base64ToImage(updateUserDetailsRequest.getImage()));
            entityManager.persist(image);
            user.setImage(image);
        }
        entityManager.persist(user);

        return user;
    }
}
