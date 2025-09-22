package com.kkpp.api_server.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.kkpp.api_server.dto.MealPlanDto;
import com.kkpp.api_server.entity.MealPlan;

@Mapper(componentModel = "spring")
public interface MealPlanMapper {

    MealPlanMapper INSTANCE = Mappers.getMapper(MealPlanMapper.class);

    MealPlan toEntity(MealPlanDto dto);

    MealPlanDto toDto(MealPlan entity);
}
