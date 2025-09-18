package com.kkpp.api_server.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kkpp.api_server.entity.MealPlanItem;

public interface MealPlanItemRepository extends JpaRepository<MealPlanItem, Long> {

    // 중복 체크: 같은 day_no + meal_type 이 이미 존재하는지 확인
    boolean existsByMealPlanIdAndDayNoAndMealType(Long mealPlanId, Integer dayNo, String mealType);
}
