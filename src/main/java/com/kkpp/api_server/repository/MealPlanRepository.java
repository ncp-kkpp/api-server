package com.kkpp.api_server.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kkpp.api_server.entity.MealPlan;

public interface MealPlanRepository extends JpaRepository<MealPlan, Long> {

    // 특정 유저가 만든 전체 식단표 조회
    java.util.List<MealPlan> findAllByUserId(String userId);

}
