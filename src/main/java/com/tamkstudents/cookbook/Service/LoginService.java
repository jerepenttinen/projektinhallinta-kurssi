package com.tamkstudents.cookbook.Service;

import com.tamkstudents.cookbook.Domain.DatabaseModels.Dao.LoginUserDao;
import com.tamkstudents.cookbook.Domain.DatabaseModels.Dto.LoginUserDto;
import com.tamkstudents.cookbook.Domain.DatabaseModels.Dto.UserDto;
import com.tamkstudents.cookbook.Domain.DatabaseModels.RepositoryInterface.LoginUserRepository;
import com.tamkstudents.cookbook.Domain.login.SignUpCredentials;
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
    private final UserService userService;

    @Transactional
    public LoginUserDto createNewUser(SignUpCredentials credentials) {
        Optional<LoginUserDao> checker = loginUserRepository
                .findByEmailOrLoginUsername(credentials.getEmail(), credentials.getUsername());
        if (checker.isEmpty()) {
            String encryptPassword = passwordEncoder.encode(credentials.getPassword());
            UserDto userDto = userService.saveNewProfile(credentials);
            try {
                var dao = loginUserRepository.save(new LoginUserDao(credentials, encryptPassword, userDto.getUserId()));
                LoginUserDto newUserDto = new LoginUserDto(dao);
                log.info("User creation successful: {} {} profileId: {}",
                        newUserDto.getUsername(),
                        newUserDto.getEmail(),
                        newUserDto.getProfileId()
                );

                return newUserDto;
            } catch (Throwable err) {
                log.error("User not found after creation");
                return null;
            }
        } else {
            log.warn("User already existing");
            return null;
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return loginUserRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException(""));
    }
}
