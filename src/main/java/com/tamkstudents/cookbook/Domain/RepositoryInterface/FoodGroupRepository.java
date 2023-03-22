package com.tamkstudents.cookbook.Domain.RepositoryInterface;

import com.tamkstudents.cookbook.Domain.Dao.FoodGroupDao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodGroupRepository extends CrudRepository<FoodGroupDao, Long> {
    FoodGroupDao findFirstByName(String name);
}
