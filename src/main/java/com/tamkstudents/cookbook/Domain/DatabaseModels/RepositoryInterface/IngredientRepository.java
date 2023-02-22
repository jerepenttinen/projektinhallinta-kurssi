package com.tamkstudents.cookbook.Domain.DatabaseModels.RepositoryInterface;

import com.tamkstudents.cookbook.Domain.DatabaseModels.Dao.IngredientDao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientRepository extends CrudRepository<IngredientDao, Long> {}
