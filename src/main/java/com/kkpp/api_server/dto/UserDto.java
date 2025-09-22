package com.kkpp.api_server.dto;

import java.time.OffsetDateTime;

public class UserDto {

    private Long id;
    private String loginId;
    private String name;
    private String passwordHash;
    private String delYn;
    private OffsetDateTime createdDt;
    private OffsetDateTime updatedDt;

    // 기본 생성자
    public UserDto() {}

    // 전체 필드 생성자
    public UserDto(Long id, String loginId, String name, String passwordHash, String delYn, OffsetDateTime createdDt, OffsetDateTime updatedDt) {
        this.id = id;
        this.loginId = loginId;
        this.name = name;
        this.passwordHash = passwordHash;
        this.delYn = delYn;
        this.createdDt = createdDt;
        this.updatedDt = updatedDt;
    }

    public Long getId() { return id;}
    public void setId(Long id) { this.id = id; }

    public String getLoginId() { return loginId; }
    public void setLoginId(String loginId) { this.loginId = loginId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPasswordHash() { return passwordHash; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }

    public String getDelYn() { return delYn; }
    public void setDelYn(String delYn) { this.delYn = delYn; }

    public OffsetDateTime getCreatedDt() {  return createdDt; }
    public void setCreatedDt(OffsetDateTime createdDt) { this.createdDt = createdDt; }

    public OffsetDateTime getUpdatedDt() { return updatedDt; }
    public void setUpdatedDt(OffsetDateTime updatedDt) { this.updatedDt = updatedDt; }
}
