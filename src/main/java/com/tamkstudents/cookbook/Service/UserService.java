package com.tamkstudents.cookbook.Service;

import com.tamkstudents.cookbook.Domain.DatabaseModels.Dao.UserDao;
import com.tamkstudents.cookbook.Domain.DatabaseModels.Dto.UserDto;
import com.tamkstudents.cookbook.Domain.DatabaseModels.RepositoryInterface.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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
        logger.info("User query: "+((stopTime-startTime)/1000000)+" ms");
        return dtoList;
    }
}
