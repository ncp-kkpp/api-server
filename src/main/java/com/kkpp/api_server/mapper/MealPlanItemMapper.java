package com.kkpp.api_server.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.kkpp.api_server.dto.MealPlanItemDto;
import com.kkpp.api_server.entity.MealPlanItem;

@Mapper(componentModel = "spring")
public interface MealPlanItemMapper {

    @Mapping(source = "mealPlanId", target = "mealPlanId")
    MealPlanItemDto toDto(MealPlanItem entity);

    @Mapping(source = "mealPlanId", target = "mealPlanId")
    MealPlanItem toEntity(MealPlanItemDto dto);
}
