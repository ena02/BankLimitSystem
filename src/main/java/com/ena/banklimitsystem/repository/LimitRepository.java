package com.ena.banklimitsystem.repository;

import com.ena.banklimitsystem.model.LimitEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LimitRepository extends JpaRepository<LimitEntity, Integer> {

    List<LimitEntity> findAllByUserIdAndExpenseCategoryId(Integer userId, Integer expenseCategoryId);
}
