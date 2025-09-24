package com.kkpp.api_server.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kkpp.api_server.config.Groups;
import com.kkpp.api_server.dto.request.UserRequest;
import com.kkpp.api_server.dto.response.ResponseDto;
import com.kkpp.api_server.dto.response.UserResponse;
import com.kkpp.api_server.mapper.UserMapper;
import com.kkpp.api_server.service.impl.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	private final UserService userService;
	private final UserMapper userMapper;
	private final HttpSessionSecurityContextRepository contextRepository = new HttpSessionSecurityContextRepository();

	public AuthController(UserService userService, UserMapper userMapper) {
		this.userService = userService;
		this.userMapper = userMapper;
		
	}
	
	@GetMapping("/xsrf-token")
	public Map<String, String> csrf(HttpServletRequest request) {
		var token = (CsrfToken) request.getAttribute(CsrfToken.class.getName());

		return Map.of(token.getHeaderName(), token.getToken());
	}

	@PostMapping("/join")
	public ResponseEntity<ResponseDto<UserResponse>> join(@Validated(Groups.Join.class) @RequestBody UserRequest request) {
		ResponseDto<UserResponse> body = null;
		UserResponse data = null;
		try {
			data = userService.join(request);
			body = ResponseDto.<UserResponse>builder().success(true).data(data).build();
		} catch (DataIntegrityViolationException e) {
			String msg = e.getMessage();

			if ("login_id".equals(msg)) {
				HashMap<String, Object> error = new HashMap<String, Object>();
				error.put("code", "AUTH.ALREADY_EXSIST_ID");
				error.put("message", "중복된 ID 입니다.");
				body = ResponseDto.<UserResponse>builder().success(false).error(error).build();
				return ResponseEntity.status(HttpStatus.OK).body(body);
			} else if ("name".equals(msg)) {
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
	
	@PostMapping("/login")
	public ResponseEntity<ResponseDto<UserResponse>> login(@Validated(Groups.Login.class) @RequestBody UserRequest userRequest, HttpServletRequest request,	HttpServletResponse response) {
        ResponseDto<UserResponse> body = null;
		UsernamePasswordAuthenticationToken auth = null;
		try {
			auth = userService.loginAndIssueToken(userRequest);
		} catch (BadCredentialsException e) {
			HashMap<String, Object> error = new HashMap<String, Object>();
			error.put("code", "AUTH.BAD_CREDENTIALS");
			error.put("message", "계정 정보를 확인해주세요.");
			body = ResponseDto.<UserResponse>builder().success(false).error(error).build();
			return ResponseEntity.status(HttpStatus.OK).body(body);
		}
		
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(auth);
        SecurityContextHolder.setContext(context);
        contextRepository.saveContext(context, request, response);
        
        UserResponse data = (UserResponse) auth.getPrincipal();
        		
        body = ResponseDto.<UserResponse>builder().success(true).data(data).build();
        return ResponseEntity.status(HttpStatus.OK).body(body);
    }

	@PostMapping("/logout")
	public ResponseEntity<ResponseDto<Object>> logout(HttpServletRequest request) {
		request.getSession(false);
		if (request.getSession(false) != null) {
			request.getSession(false).invalidate();
		}
		SecurityContextHolder.clearContext();
		ResponseDto<Object> body = ResponseDto.<Object>builder().success(true).build();
		return ResponseEntity.status(HttpStatus.OK).body(body);
	}
	
	//테스트용
	/*
	@GetMapping("/me")
	public ResponseEntity<?> me() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth == null || !auth.isAuthenticated() || auth.getPrincipal().equals("anonymousUser")) {
			return ResponseEntity.status(401).body(Map.of("ok", false, "error", "UNAUTHORIZED"));
		}
		UserResponse u  = (UserResponse) auth.getPrincipal();
		return ResponseEntity.ok(Map.of("ok", true, "login_id", u.getLoginId(), "name", u.getName()));
	}
	*/


}