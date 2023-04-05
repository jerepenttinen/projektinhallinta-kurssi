package com.tamkstudents.cookbook.Domain.RepositoryInterface;

import com.tamkstudents.cookbook.Domain.Dao.UserDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserDao, Long> {}
