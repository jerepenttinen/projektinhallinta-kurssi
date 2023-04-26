package com.tamkstudents.cookbook.Domain.RepositoryInterface;

import com.tamkstudents.cookbook.Domain.Dao.FoodGroupDao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodGroupRepository extends JpaRepository<FoodGroupDao, Long> {
    FoodGroupDao findFirstByName(String name);
}
