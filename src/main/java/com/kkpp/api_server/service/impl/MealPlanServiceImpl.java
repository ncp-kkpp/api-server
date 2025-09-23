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
		
		List<MealPlanItem> items = mealPlanDto.getItems().stream()
		        .map(dto -> {
		            MealPlanItem entity = mealPlanItemMapper.toEntity(dto);
		            entity.setMealPlanId(mealPlanId);
		            return entity;
		        })
		        .toList();
		 
		mealPlanItemRepository.saveAll(items);
		
		return mealPlanId;
    }

	@Override
	public List<MealPlanDto> getMealPlanList(String userId) {
		
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
		
		MealPlan mealPlanEntity = mealPlanRepository.findById(mealPlanId)
			    .orElseThrow(() -> new EntityNotFoundException("식단표가 존재하지 않습니다."));
		
		if(!mealPlanEntity.getUserId().equals(userId)) {
			throw new EntityNotFoundException("식단표가 존재하지 않습니다.");
		}
		
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
		if (!mealPlanRepository.existsByIdAndUserId(mealPlanId, userId)) {
		    throw new EntityNotFoundException("식단표가 존재하지 않습니다.");
		}
		mealPlanRepository.deleteById(mealPlanId);
		return true;
	}
}
