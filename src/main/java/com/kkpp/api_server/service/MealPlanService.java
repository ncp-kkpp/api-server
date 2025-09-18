package com.kkpp.api_server.service;

import com.kkpp.api_server.dto.MealPlanDto;

public interface MealPlanService {
    public Long createMealPlan(String userId, MealPlanDto mealPlan);
}
