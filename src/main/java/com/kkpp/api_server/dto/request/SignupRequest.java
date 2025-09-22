package com.kkpp.api_server.dto.request;


import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class SignupRequest {
@NotBlank
@Size(max = 320)
@Email
private String loginId; // 이메일 형태가 보장되어야 하면 @Email 추가


@NotBlank
@Size(min = 2, max = 100)
private String name;


@NotBlank
@Size(min = 8, max = 72)
private String password;


public String getLoginId() { return loginId; }
public void setLoginId(String loginId) { this.loginId = loginId; }
public String getName() { return name; }
public void setName(String name) { this.name = name; }
public String getPassword() { return password; }
public void setPassword(String password) { this.password = password; }
}