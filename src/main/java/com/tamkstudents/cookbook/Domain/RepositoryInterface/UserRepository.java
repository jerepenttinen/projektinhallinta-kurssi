package com.tamkstudents.cookbook.Domain.RepositoryInterface;

import com.tamkstudents.cookbook.Domain.Dao.UserDao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserDao, Long> {}
