package com.kkpp.api_server.dto.response;

import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;


@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserResponse {
	@JsonIgnore
	private Long id;
	private String loginId;
	private String name;
	@JsonIgnore
	private String delYn;
	private OffsetDateTime createdDt;
	private OffsetDateTime updatedDt;
	
	
	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }
	public String getLoginId() { return loginId; }
	public void setLoginId(String loginId) { this.loginId = loginId; }
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	public String getDelYn() { return delYn; }
	public void setDelYn(String delYn) { this.delYn = delYn; }
	public OffsetDateTime getCreatedDt() { return createdDt; }
	public void setCreatedDt(OffsetDateTime createdDt) { this.createdDt = createdDt; }
	public OffsetDateTime getUpdatedDt() { return updatedDt; }
	public void setUpdatedDt(OffsetDateTime updatedDt) { this.updatedDt = updatedDt; }
}