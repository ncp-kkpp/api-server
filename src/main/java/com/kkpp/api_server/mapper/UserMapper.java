package com.kkpp.api_server.mapper;


import org.mapstruct.Mapper;

import com.kkpp.api_server.dto.response.UserResponse;
import com.kkpp.api_server.entity.User;


@Mapper(componentModel = "spring")
public interface UserMapper {

	User toEntityFromRequest(String loginId, String name, String passwordHash);

	UserResponse toResponse(User user);
}