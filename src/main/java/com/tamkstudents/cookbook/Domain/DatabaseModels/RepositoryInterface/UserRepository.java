package com.tamkstudents.cookbook.Domain.DatabaseModels.RepositoryInterface;

import com.tamkstudents.cookbook.Domain.DatabaseModels.Dao.UserDao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserDao, Long> {}
