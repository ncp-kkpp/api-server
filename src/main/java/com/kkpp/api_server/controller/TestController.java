package com.kkpp.api_server.controller;

import com.kkpp.api_server.service.TestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "TEST", description = "테스트")
@Validated
@RestController
@RequestMapping("/")
public class TestController {

    private final TestService testService;

    public TestController(TestService testService) {
        this.testService = testService;
    }
    
    @Operation(summary = "root", description = "첫 진입화면")
    @GetMapping(value = "/")
    public String root() {
    	return "It works!";
    }

    @Operation(summary = "hello", description = "Hello")
    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
    	String result = testService.hello();
        return ResponseEntity.ok(result);
    }

}
