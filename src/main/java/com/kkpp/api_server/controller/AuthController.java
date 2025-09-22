package com.kkpp.api_server.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kkpp.api_server.dto.MealPlanDto;
import com.kkpp.api_server.dto.request.SignupRequest;
import com.kkpp.api_server.dto.response.ResponseDto;
import com.kkpp.api_server.dto.response.UserResponse;
import com.kkpp.api_server.service.impl.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {
private final UserService userService;


	public AuthController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/xsrf-token")
    public Map<String, String> csrf(org.springframework.security.web.csrf.CsrfToken token) {
        return Map.of("XSRF-TOKEN", token.getToken());
    }

	@PostMapping("/join")
		public ResponseEntity<ResponseDto<UserResponse>> join(@Valid @RequestBody SignupRequest request) {
		ResponseDto<UserResponse> body = null;
		UserResponse data = null;
		try {
			data = userService.join(request);
			body = ResponseDto.<UserResponse>builder().success(true).data(data).build();
		} catch(DataIntegrityViolationException e) {
			String msg = e.getMessage();
			
			if("login_id".equals(msg)) {
				HashMap<String, Object> error = new HashMap<String, Object>();
				error.put("code", "AUTH.ALREADY_EXSIST_ID");
				error.put("message", "중복된 ID 입니다.");
				body = ResponseDto.<UserResponse>builder().success(false).error(error).build();
				return ResponseEntity.status(HttpStatus.OK).body(body);
			}
			else if ("name".equals(msg)) {
				HashMap<String, Object> error = new HashMap<String, Object>();
				error.put("code", "AUTH.ALREADY_EXSIST_ID");
				error.put("message", "중복된 이름 입니다.");
				body = ResponseDto.<UserResponse>builder().success(false).error(error).build();
				return ResponseEntity.status(HttpStatus.OK).body(body);
			} else {
				e.printStackTrace();
			}
		}
		return ResponseEntity.status(HttpStatus.OK).body(body);
	}

}