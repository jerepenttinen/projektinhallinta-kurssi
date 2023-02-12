package com.tamkstudents.cookbook.Domain.DatabaseModels.RepositoryInterface;

import com.tamkstudents.cookbook.Domain.DatabaseModels.Dao.FoodGroupDao;
import com.tamkstudents.cookbook.Domain.DatabaseModels.Dao.RecipeDao;
import com.tamkstudents.cookbook.Domain.DatabaseModels.Dao.UserDao;
import jakarta.persistence.Id;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface RecipeRepository extends CrudRepository<RecipeDao, Long> {

    List<RecipeDao> findAllByCreator(UserDao creator);
    List<RecipeDao> findAllByFoodGroup(FoodGroupDao foodGroups);
}
