package com.kkpp.api_server.controller;

import com.kkpp.api_server.service.MealPlanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "meal-plan", description = "식단표 관련 API")
@Validated
@RestController
@RequestMapping("/meal-plan")
public class MealPlanController {

	private final MealPlanService mealPlanService;

	public MealPlanController(MealPlanService mealPlanService) {
	    this.mealPlanService = mealPlanService;
	}
    
	@Operation(summary = "식단표 생성 API", description = "request로 전달받은 데이터를 토대로 DB에 식단표를 생성합니다.")
    @PostMapping(value = "/create")
    public String createMealPlan() {
    	return "식단표 생성 API";
    }
	
	@Operation(summary = "식단표 목록 조회 API", description = "로그인 한 user가 가지고 있는 식단표 목록을 조회합니다.")
    @GetMapping(value = "/")
    public String getMealPlanList() {
    	return "식단표 목록 조회 API";
    }
	
	@Operation(summary = "식단표 조회 API", description = "식단표를 조회합니다.")
    @GetMapping(value = "/{meal_plan_id}")
    public String getMealPlan(@PathVariable("meal_plan_id") int mealPlanId) {
    	return "식단표 조회 API: " + mealPlanId;
    }
	
	@Operation(summary = "식단표 삭제 API", description = "식단표를 삭제합니다.")
    @DeleteMapping(value = "/{meal_plan_id}")
    public String deleteMealPlan(@PathVariable("meal_plan_id") int mealPlanId) {
    	return "식단표 삭제 API: " + mealPlanId;
    }

}



