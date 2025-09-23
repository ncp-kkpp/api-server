package com.kkpp.api_server;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kkpp.api_server.dto.response.ResponseDto;
import com.kkpp.api_server.dto.response.ResponseDto.ResponseDtoBuilder;

@RestController
public class RootController {
    @GetMapping("/")
    public ResponseEntity<ResponseDtoBuilder<Object>> root() {
        return ResponseEntity.status(HttpStatus.OK).body(ResponseDto.builder().success(true));
    }
}