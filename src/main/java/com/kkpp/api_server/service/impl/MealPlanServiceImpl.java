package com.kkpp.api_server.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kkpp.api_server.dto.MealPlanDto;
import com.kkpp.api_server.entity.MealPlan;
import com.kkpp.api_server.mapper.MealPlanMapper;
import com.kkpp.api_server.repository.MealPlanRepository;
import com.kkpp.api_server.service.MealPlanService;

@Service
@Transactional
public class MealPlanServiceImpl implements MealPlanService {
	
	private final MealPlanRepository mealPlanRepository;
	private final MealPlanMapper mealPlanMapper;
	
	public MealPlanServiceImpl(MealPlanRepository mealPlanRepository, MealPlanMapper mealPlanMapper) {
		this.mealPlanRepository = mealPlanRepository;
		this.mealPlanMapper = mealPlanMapper;
	}
	
	@Override
    public Long createMealPlan(String userId, MealPlanDto mealPlanDto) {
		
		MealPlan mealPlanEntity = mealPlanMapper.toEntity(mealPlanDto);
		mealPlanEntity.setUserId(userId);
		mealPlanRepository.save(mealPlanEntity);

		return mealPlanEntity.getId();
    }
}
