package com.tamkstudents.cookbook.Domain.RepositoryInterface;

import com.tamkstudents.cookbook.Domain.Dao.IngredientDao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientRepository extends CrudRepository<IngredientDao, Long> {
    IngredientDao findFirstByName(String name);
}
