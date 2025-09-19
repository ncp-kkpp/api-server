package com.kkpp.api_server.service;

import java.util.List;
import com.kkpp.api_server.dto.MealPlanDto;

public interface MealPlanService {
    public Long createMealPlan(String userId, MealPlanDto mealPlan);
    public List<MealPlanDto> getMealPlanList(String userId);
    public MealPlanDto getMealPlan(String userId, Long mealPlanId);
}
