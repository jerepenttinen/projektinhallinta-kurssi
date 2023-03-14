package com.tamkstudents.cookbook.Domain.DatabaseModels.RepositoryInterface;

import com.tamkstudents.cookbook.Domain.DatabaseModels.Dao.LoginUserDao;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface LoginUserRepository extends CrudRepository <LoginUserDao, Long> {
    Optional<LoginUserDao> findByEmailOrUsername(String email, String username);
    Optional<LoginUserDao> findByEmail(String email);
}
