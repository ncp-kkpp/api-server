package com.kkpp.api_server.service.impl;


import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kkpp.api_server.dto.request.SignupRequest;
import com.kkpp.api_server.dto.response.UserResponse;
import com.kkpp.api_server.entity.User;
import com.kkpp.api_server.mapper.UserMapper;
import com.kkpp.api_server.repository.UserRepository;


@Service
public class UserService {
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final UserMapper userMapper;


	public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, UserMapper userMapper) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.userMapper = userMapper;
	}


	@Transactional
	public UserResponse join(SignupRequest req) {
		if (userRepository.existsByLoginId(req.getLoginId())) {
			throw new DataIntegrityViolationException("login_id");
		}
		if (userRepository.existsByName(req.getName())) {
			throw new DataIntegrityViolationException("name");
		}
	
		String passwordHash = passwordEncoder.encode(req.getPassword());
		User user = userMapper.toEntityFromSignup(req.getLoginId(),	req.getName(), passwordHash);
		User saved = userRepository.save(user);
		return userMapper.toResponse(saved);
	}
}