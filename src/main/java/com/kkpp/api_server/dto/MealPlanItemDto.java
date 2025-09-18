package com.kkpp.api_server.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class MealPlanItemDto {

    private Long id;
    private Long mealPlanId;

    @Min(1) @Max(31)
    private Integer dayNo;

    private String mealType;
    private String item;

    public MealPlanItemDto() {}

    public MealPlanItemDto(Long id, Long mealPlanId, Integer dayNo, String mealType, String item) {
        this.id = id;
        this.mealPlanId = mealPlanId;
        this.dayNo = dayNo;
        this.mealType = mealType;
        this.item = item;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getMealPlanId() { return mealPlanId; }
    public void setMealPlanId(Long mealPlanId) { this.mealPlanId = mealPlanId; }

    public Integer getDayNo() { return dayNo; }
    public void setDayNo(Integer dayNo) { this.dayNo = dayNo; }

    public String getMealType() { return mealType; }
    public void setMealType(String mealType) { this.mealType = mealType; }

    public String getItem() { return item; }
    public void setItem(String item) { this.item = item; }
}
