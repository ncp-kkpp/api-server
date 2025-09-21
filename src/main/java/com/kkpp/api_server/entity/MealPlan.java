package com.kkpp.api_server.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.OffsetDateTime;
import java.util.Set;

@Entity
@Table(name = "meal_plan")
public class MealPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String title;

    // DDL: user_id VARCHAR(320) NOT NULL
    @Column(name = "user_id", nullable = false)
    private String userId;  // ← 나중에 User 엔티티 생기면 ManyToOne으로 교체 가능

    // DDL: period VARCHAR(10) CHECK ('weekly','monthly')
    @Column(length = 10, nullable = false)
    private String period;

    // DDL: goals VARCHAR(50) CHECK ('weight_loss','muscle_growth','normal')
    @Column(length = 50, nullable = false)
    private String goals;

    private Integer age;

    // DDL: gender VARCHAR(10) CHECK ('male','female','other')
    @Column(length = 10)
    private String gender;

    @Column(name = "basic_metabolism")
    private Integer basicMetabolism;

    @CreationTimestamp
    @Column(name = "created_dt", updatable = false, nullable = false)
    private OffsetDateTime createdDt;

    @UpdateTimestamp
    @Column(name = "updated_dt", nullable = false)
    private OffsetDateTime updatedDt;

    // --- DB CHECK와 충돌 피하려고 저장 직전/갱신 직전에 소문자+trim 방어 ---
    private static final Set<String> PERIODS = Set.of("weekly","monthly");
    private static final Set<String> GOALS   = Set.of("weight_loss","muscle_growth","normal");
    private static final Set<String> GENDERS = Set.of("male","female","other");

    @PrePersist
    @PreUpdate
    private void normalizeAndValidate() {
        if (period != null) {
            period = period.trim().toLowerCase();
            if (!PERIODS.contains(period)) {
                throw new IllegalArgumentException("Invalid period: " + period);
            }
        }
        if (goals != null) {
            goals = goals.trim().toLowerCase(); // 스키마 오탈자(공백) 방어
            if (!GOALS.contains(goals)) {
                throw new IllegalArgumentException("Invalid goals: " + goals);
            }
        }
        if (gender != null) {
            gender = gender.trim().toLowerCase();
            if (!GENDERS.contains(gender)) {
                throw new IllegalArgumentException("Invalid gender: " + gender);
            }
        }
    }

    // ---- getters / setters ----
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
}
