package com.tamkstudents.cookbook.Domain.RepositoryInterface;

import com.tamkstudents.cookbook.Domain.Dao.LoginUserDao;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface LoginUserRepository extends CrudRepository <LoginUserDao, Long> {
    Optional<LoginUserDao> findByEmailOrLoginUsername(String email, String username);
    Optional<LoginUserDao> findByEmail(String email);
}
