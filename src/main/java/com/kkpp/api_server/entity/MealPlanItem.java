package com.kkpp.api_server.entity;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(
    name = "meal_plan_item",
    uniqueConstraints = {
        @UniqueConstraint(name = "uq_meal_plan_item", columnNames = {"meal_plan_id", "day_no", "meal_type"})
    },
    indexes = {
        @Index(name = "idx_meal_plan_item_plan", columnList = "meal_plan_id")
    }
)
public class MealPlanItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "meal_plan_id", nullable = false)
    private Long mealPlanId;

    @Column(name = "day_no", nullable = false)
    private Integer dayNo; // 1~31 (DB CHECK)

    // 'breakfast' | 'lunch' | 'dinner'
    @Column(name = "meal_type", length = 20, nullable = false)
    private String mealType;

    @Column(length = 1000)
    private String item;

    // ---- DB CHECK와 일치하도록 저장 전 방어 ----
    private static final Set<String> MEAL_TYPES = Set.of("breakfast","lunch","dinner");

    @PrePersist
    @PreUpdate
    private void normalizeAndValidate() {
        if (dayNo == null || dayNo < 1 || dayNo > 31) {
            throw new IllegalArgumentException("day_no must be between 1 and 31");
        }
        if (mealType != null) {
            mealType = mealType.trim().toLowerCase();
            if (!MEAL_TYPES.contains(mealType)) {
                throw new IllegalArgumentException("Invalid meal_type: " + mealType);
            }
        } else {
            throw new IllegalArgumentException("meal_type is required");
        }
    }

    // ---- getters / setters ----
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
