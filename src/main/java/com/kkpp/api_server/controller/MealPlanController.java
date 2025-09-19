package com.kkpp.api_server.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kkpp.api_server.dto.MealPlanDto;
import com.kkpp.api_server.dto.response.ResponseDto;
import com.kkpp.api_server.service.MealPlanService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

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
    public ResponseEntity<ResponseDto<HashMap<String, Object>>> createMealPlan(@Valid @RequestBody MealPlanDto request) {
		
		HashMap<String, Object> data = new HashMap<String, Object>();
		
		//TODO Spring Security 구현
		String userId = "test@example.com"; //하드코딩
		Long mealPlanId = mealPlanService.createMealPlan(userId, request);
		data.put("meal_plan_id", mealPlanId);
		
		
		ResponseDto<HashMap<String, Object>> body = ResponseDto.<HashMap<String, Object>>builder()
		        .success(true)
		        .data(data)
		        .build();
	
		return ResponseEntity
                .status(HttpStatus.OK)	
                .body(body);
    }
	
	@Operation(summary = "식단표 목록 조회 API", description = "로그인 한 user가 가지고 있는 식단표 목록을 조회합니다.")
    @GetMapping(value = "/")
    public ResponseEntity<ResponseDto<List<MealPlanDto>>> getMealPlanList() {
		
		//TODO Spring Security 구현
		String userId = "test@example.com"; //하드코딩
		List<MealPlanDto> data = mealPlanService.getMealPlanList(userId);
		
		ResponseDto<List<MealPlanDto>> body = ResponseDto.<List<MealPlanDto>>builder()
											        .success(true)
											        .data(data)
											        .build();
		
		return ResponseEntity
                .status(HttpStatus.OK)	
                .body(body);
    }
	
	@Operation(summary = "식단표 조회 API", description = "식단표를 조회합니다.")
    @GetMapping(value = "/{meal_plan_id}")
    public ResponseEntity<ResponseDto<MealPlanDto>> getMealPlan(@PathVariable("meal_plan_id")  Long mealPlanId) {
		//TODO Spring Security 구현
		String userId = "test@example.com"; //하드코딩
		MealPlanDto data = mealPlanService.getMealPlan(userId, mealPlanId);
		
		ResponseDto<MealPlanDto> body = ResponseDto.<MealPlanDto>builder()
												.success(true)
												.data(data)
												.build();
				
		return ResponseEntity
                .status(HttpStatus.OK)	
                .body(body);
    }
	
	@Operation(summary = "식단표 삭제 API", description = "식단표를 삭제합니다.")
    @DeleteMapping(value = "/{meal_plan_id}")
    public String deleteMealPlan(@PathVariable("meal_plan_id") int mealPlanId) {
    	return "식단표 삭제 API: " + mealPlanId;
    }

}



