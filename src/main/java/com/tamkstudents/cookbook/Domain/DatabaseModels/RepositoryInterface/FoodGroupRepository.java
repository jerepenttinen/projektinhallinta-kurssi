package com.tamkstudents.cookbook.Domain.DatabaseModels.RepositoryInterface;

import com.tamkstudents.cookbook.Domain.DatabaseModels.Dao.FoodGroupDao;
import jakarta.persistence.Id;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodGroupRepository extends CrudRepository<FoodGroupDao, Id> {
    FoodGroupDao findById(int id);
}
