package com.kkpp.api_server.dto;

import java.time.OffsetDateTime;
import com.fasterxml.jackson.annotation.JsonInclude;

public class MealPlanDto {

    private Long id;
    private String title;
    private Long userId;
    private String period;            // "weekly" | "monthly"
    private String goals;             // "weight_loss" | "muscle_growth" | "normal"
    private Integer age;
    private String gender;            // "male" | "female" | "other"
    private Integer basicMetabolism;
    private OffsetDateTime createdDt;
    private OffsetDateTime updatedDt;

    // --- 프론트 전용(옵션) 필드: DB에는 저장 안 함 ---
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String clientTag;

    public MealPlanDto() {}

    public MealPlanDto(Long id, String title, Long userId, String period, String goals, Integer age, String gender, Integer basicMetabolism, OffsetDateTime createdDt, OffsetDateTime updatedDt, String clientTag) {
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
        this.clientTag = clientTag;
    }

    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

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

    public String getClientTag() { return clientTag; }
    public void setClientTag(String clientTag) { this.clientTag = clientTag; }
}
