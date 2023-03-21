package com.tamkstudents.cookbook.Domain.RepositoryInterface;

import com.tamkstudents.cookbook.Domain.Dao.FoodGroupDao;
import com.tamkstudents.cookbook.Domain.Dao.RecipeDao;
import com.tamkstudents.cookbook.Domain.Dao.UserDao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RecipeRepository extends CrudRepository<RecipeDao, Long> {

    List<RecipeDao> findAllByCreator(UserDao creator);
    List<RecipeDao> findAllByFoodGroupsContains(FoodGroupDao foodGroupDao);

}
