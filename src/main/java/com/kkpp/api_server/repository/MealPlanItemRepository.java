package com.kkpp.api_server.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.kkpp.api_server.entity.MealPlanItem;

public interface MealPlanItemRepository extends JpaRepository<MealPlanItem, Long> {

	// 특정 유저가 만든 전체 식단표 조회
	List<MealPlanItem> findAllByMealPlanId(Long mealPlanId, Sort sort);
	
	// 중복 체크: 같은 day_no + meal_type 이 이미 존재하는지 확인 (추후 추가될 수 있는 수정 기능 고려하여 작성)
    boolean existsByMealPlanIdAndDayNoAndMealType(Long mealPlanId, Integer dayNo, String mealType);
}
