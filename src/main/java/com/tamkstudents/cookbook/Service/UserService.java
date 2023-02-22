package com.tamkstudents.cookbook.Service;

import com.tamkstudents.cookbook.Domain.DatabaseModels.Dao.UserDao;
import com.tamkstudents.cookbook.Domain.DatabaseModels.Dto.UserDto;
import com.tamkstudents.cookbook.Domain.DatabaseModels.RepositoryInterface.UserRepository;
import com.tamkstudents.cookbook.Domain.login.LoginCredentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class UserService extends AbstractService{

    @Autowired
    private UserRepository userRepository;

    Logger logger = Logger.getLogger(UserService.class.getName());

    public List<UserDto> getUsers(){
        long startTime = System.nanoTime();
        List<UserDto> dtoList = new ArrayList<>();
        userRepository.findAll().forEach(UserDao->dtoList.add(new UserDto(UserDao)));
        long stopTime = System.nanoTime();
        logger.info("Profile query: "+((stopTime-startTime)/1000000)+" ms");
        return dtoList;
    }

    public UserDto getUser(Long id){
        long startTime = System.nanoTime();
        Optional<UserDao> userDao = userRepository.findById(id);
        UserDto dto = null;
        if(userDao.isPresent()){
            dto = new UserDto(userDao.get());
        }
        long stopTime = System.nanoTime();
        logger.info("Profile query: "+((stopTime-startTime)/1000000)+" ms");
        return dto;
    }

    public UserDto saveNewProfile(LoginCredentials credentials){
        long startTime = System.nanoTime();
        UserDao newProfile = new UserDao(credentials);
        userRepository.save(newProfile);
        UserDto dto = getUser(newProfile.getUser_id());
        long stopTime = System.nanoTime();
        logger.info("Profile save: "+((stopTime-startTime)/1000000)+" ms");
        return dto;
    }
}
