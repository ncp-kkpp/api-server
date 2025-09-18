package com.kkpp.api_server.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.kkpp.api_server.dto.MealPlanItemDto;
import com.kkpp.api_server.entity.MealPlanItem;

@Mapper(componentModel = "spring")
public interface MealPlanItemMapper {

    @Mapping(source = "mealPlan.id", target = "mealPlanId")
    MealPlanItemDto toDto(MealPlanItem entity);

    @Mapping(source = "mealPlanId", target = "mealPlan.id")
    MealPlanItem toEntity(MealPlanItemDto dto);
}
