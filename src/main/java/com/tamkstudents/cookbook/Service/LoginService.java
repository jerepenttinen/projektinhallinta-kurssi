package com.tamkstudents.cookbook.Service;

import com.tamkstudents.cookbook.Controller.Mapper.LoginMapperService;
import com.tamkstudents.cookbook.Controller.Mapper.UserMapperService;
import com.tamkstudents.cookbook.Controller.Request.SignUpRequest;
import com.tamkstudents.cookbook.Domain.DatabaseModels.Dao.LoginUserDao;
import com.tamkstudents.cookbook.Domain.DatabaseModels.Dao.UserDao;
import com.tamkstudents.cookbook.Domain.DatabaseModels.RepositoryInterface.LoginUserRepository;
import com.tamkstudents.cookbook.Domain.DatabaseModels.RepositoryInterface.UserRepository;
import com.tamkstudents.cookbook.Service.Exceptions.EmailOrUsernameTakenException;
import com.tamkstudents.cookbook.Service.Exceptions.FailedToCreateUserException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoginService implements UserDetailsService {
    private final PasswordEncoder passwordEncoder;
    private final LoginUserRepository loginUserRepository;
    private final UserRepository userRepository;
    private final LoginMapperService loginMapperService;
    private final UserMapperService userMapperService;

    @Transactional
    public LoginUserDao createNewUser(SignUpRequest signUpRequest) throws EmailOrUsernameTakenException, FailedToCreateUserException {
        Optional<LoginUserDao> checker = loginUserRepository
                .findByEmailOrLoginUsername(signUpRequest.getEmail(), signUpRequest.getUsername());
        if (checker.isPresent()) {
            throw new EmailOrUsernameTakenException();
        }

        String encryptPassword = passwordEncoder.encode(signUpRequest.getPassword());
        try {
            UserDao newProfile = userRepository.save(userMapperService.createUserDao(signUpRequest));

            var loginUserDao = loginUserRepository.save(loginMapperService.createLoginUserDao(signUpRequest, encryptPassword, newProfile));
            log.info("User creation successful: {} {} profileId: {}", loginUserDao.getUsername(), loginUserDao.getEmail(), loginUserDao.getProfileId());

            return loginUserDao;
        } catch (Exception e) {
            throw new FailedToCreateUserException();
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return loginUserRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException(""));
    }
}
