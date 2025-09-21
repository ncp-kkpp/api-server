package com.kkpp.api_server.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.kkpp.api_server.dto.FreqIngredientDto;
import com.kkpp.api_server.entity.FreqIngredient;

@Mapper(componentModel = "spring")
public interface FreqIngredientMapper {

    FreqIngredient toEntity(FreqIngredientDto dto);

    FreqIngredientDto toDto(FreqIngredient entity);

    // 부분 업데이트용 - dto의 필드 무시
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(FreqIngredientDto dto, @MappingTarget FreqIngredient entity);
}
