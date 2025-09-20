package com.kkpp.api_server.service.impl;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kkpp.api_server.dto.MealPlanDto;
import com.kkpp.api_server.dto.MealPlanItemDto;
import com.kkpp.api_server.entity.MealPlan;
import com.kkpp.api_server.entity.MealPlanItem;
import com.kkpp.api_server.mapper.MealPlanItemMapper;
import com.kkpp.api_server.mapper.MealPlanMapper;
import com.kkpp.api_server.repository.MealPlanItemRepository;
import com.kkpp.api_server.repository.MealPlanRepository;
import com.kkpp.api_server.service.MealPlanService;

import jakarta.persistence.EntityNotFoundException;

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

	@Override
	public List<MealPlanDto> getMealPlanList(String userId) {
		//TODO 본인 식단표 아닐 경우 예외 발생, 로그인 사용자 검증
		
		List<MealPlanDto> mealPlanList = mealPlanRepository.findAllByUserId(userId).stream()
									.map(entity -> {
											MealPlanDto dto = mealPlanMapper.toDto(entity);
											return dto;
									})
									.toList();
		return mealPlanList;
	}

	@Override
	public MealPlanDto getMealPlan(String userId, Long mealPlanId) {
		//TODO 로그인 사용자 검증, 본인 식단표 아닐 경우 예외 발생
		
		MealPlan mealPlanEntity = mealPlanRepository.findById(mealPlanId)
			    .orElseThrow(() -> new EntityNotFoundException("MealPlan not found: " + mealPlanId));

		MealPlanDto mealPlanDto = mealPlanMapper.toDto(mealPlanEntity);
		
		List<MealPlanItemDto> items = mealPlanItemRepository
		        .findAllByMealPlanId(mealPlanId, Sort.by(Sort.Order.asc("dayNo")))
		        .stream()
		        .map(entity -> mealPlanItemMapper.toDto(entity))
		        .toList();
		
		mealPlanDto.setItems(items);

		return mealPlanDto;
	}

	@Override
	public boolean deleteMealPlan(String userId, Long mealPlanId) {
		//TODO 로그인 사용자 검증, 본인 식단표 아닐 경우 예외 발생
		if (!mealPlanRepository.existsById(mealPlanId)) {
		    throw new EntityNotFoundException("MealPlan not found with id: " + mealPlanId);
		}
		mealPlanRepository.deleteById(mealPlanId);
		return true;
	}
}
