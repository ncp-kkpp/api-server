package com.kkpp.api_server.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kkpp.api_server.dto.MealPlanDto;
import com.kkpp.api_server.entity.MealPlan;
import com.kkpp.api_server.entity.MealPlanItem;
import com.kkpp.api_server.mapper.MealPlanItemMapper;
import com.kkpp.api_server.mapper.MealPlanMapper;
import com.kkpp.api_server.repository.MealPlanItemRepository;
import com.kkpp.api_server.repository.MealPlanRepository;
import com.kkpp.api_server.service.MealPlanService;

@Service
@Transactional
public class MealPlanServiceImpl implements MealPlanService {
	
	private final MealPlanRepository mealPlanRepository;
	private final MealPlanMapper mealPlanMapper;
	private final MealPlanItemRepository mealPlanItemRepository;
	private final MealPlanItemMapper mealPlanItemMapper;
	
	public MealPlanServiceImpl(MealPlanRepository mealPlanRepository, MealPlanMapper mealPlanMapper, MealPlanItemRepository mealPlanItemRepository, MealPlanItemMapper mealPlanItemMapper) {
		this.mealPlanRepository = mealPlanRepository;
		this.mealPlanMapper = mealPlanMapper;
		this.mealPlanItemRepository = mealPlanItemRepository;
		this.mealPlanItemMapper = mealPlanItemMapper;
	}
	
	@Override
    public Long createMealPlan(String userId, MealPlanDto mealPlanDto) {
		
		MealPlan mealPlanEntity = mealPlanMapper.toEntity(mealPlanDto);
		mealPlanEntity.setUserId(userId);
		mealPlanRepository.save(mealPlanEntity);
		Long mealPlanId = mealPlanEntity.getId();
		
		//List<MealPlanItemDto> items = mealPlanDto.getItems().stream();
		
		List<MealPlanItem> items = mealPlanDto.getItems().stream()
		        .map(dto -> {
		            MealPlanItem entity = mealPlanItemMapper.toEntity(dto);
		            entity.setMealPlanId(mealPlanId); // FK 매핑 필요
		            return entity;
		        })
		        .toList();
		 
		mealPlanItemRepository.saveAll(items);
		
		return mealPlanId;
    }
}
