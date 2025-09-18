package com.kkpp.api_server.dto;

import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class MealPlanDto {

    private Long id;
    private String title;
    private String userId;
    private String period;
    private String goals;
    private Integer age;
    private String gender;
    private Integer basicMetabolism;
    private OffsetDateTime createdDt;
    private OffsetDateTime updatedDt;
    private List<MealPlanItemDto> items;

    // --- 프론트 전용(옵션) 필드: DB에는 저장 안 함 ---
//    @JsonInclude(JsonInclude.Include.NON_NULL)
//    private String clientTag;

    public MealPlanDto() {}

    public MealPlanDto(Long id, String title, String userId, String period, String goals, Integer age, String gender, Integer basicMetabolism, OffsetDateTime createdDt, OffsetDateTime updatedDt, List<MealPlanItemDto> items) {
        this.id = id;
        this.title = title;
        this.userId = userId;
        this.period = period;
        this.goals = goals;
        this.age = age;
        this.gender = gender;
        this.basicMetabolism = basicMetabolism;
        this.createdDt = createdDt;
        this.updatedDt = updatedDt;
        this.items = items;
    }

    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getPeriod() { return period; }
    public void setPeriod(String period) { this.period = period; }

    public String getGoals() { return goals; }
    public void setGoals(String goals) { this.goals = goals; }

    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public Integer getBasicMetabolism() { return basicMetabolism; }
    public void setBasicMetabolism(Integer basicMetabolism) { this.basicMetabolism = basicMetabolism; }

    public OffsetDateTime getCreatedDt() { return createdDt; }
    public void setCreatedDt(OffsetDateTime createdDt) { this.createdDt = createdDt; }

    public OffsetDateTime getUpdatedDt() { return updatedDt; }
    public void setUpdatedDt(OffsetDateTime updatedDt) { this.updatedDt = updatedDt; }

    public List<MealPlanItemDto> getItems() { return items; }
    public void setItems(List<MealPlanItemDto> items) { this.items = items; }
}
