package com.kkpp.api_server;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kkpp.api_server.dto.response.ResponseDto;

@RestController
public class RootController {
	
	@GetMapping(value = "/", produces = "application/json")
    public ResponseEntity<ResponseDto<Object>> root() {
        return ResponseEntity.status(HttpStatus.OK).body(ResponseDto.builder().success(true).build());
    }
}