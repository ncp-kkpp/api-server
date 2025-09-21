package com.kkpp.api_server.service.impl;

import com.kkpp.api_server.service.TestService;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {
	
	@Override
    public String hello() {
        return "Hello! We are kkpp";
    }
}
