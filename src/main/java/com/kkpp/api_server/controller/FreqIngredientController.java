package com.kkpp.api_server.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kkpp.api_server.dto.FreqIngredientDto;
import com.kkpp.api_server.dto.MealPlanDto;
import com.kkpp.api_server.dto.response.ResponseDto;
import com.kkpp.api_server.service.FreqIngredientService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;

@Tag(name = "freq-ingrdt", description = "자주 검색하는 식재료 관련 API")
@Validated
@RestController
@RequestMapping("/freq-ingrdt")
public class FreqIngredientController {

	private final FreqIngredientService freqIngredientService;

	public FreqIngredientController(FreqIngredientService freqIngredientService) {
	    this.freqIngredientService = freqIngredientService;
	}
    
	@Operation(summary = "자주 검색하는 식재료 저장 API", description = "request로 전달받은 데이터를 토대로 DB에 자주 검색하는 식재료를 저장하거나 검색 빈도를 증가시킵니다.")
    @PostMapping(value = "/upsert")
    public ResponseEntity<ResponseDto<List<FreqIngredientDto>>> upsertFreqIngrdt(@RequestBody List<String> ingredients) {
		
		//TODO Spring Security 구현
		String userId = "test@example.com"; //하드코딩
		
		List<FreqIngredientDto> data = freqIngredientService.upsertFreqIngrdt(userId, ingredients);
		ResponseDto<List<FreqIngredientDto>> body = ResponseDto.<List<FreqIngredientDto>>builder().success(true).data(data).build();

		return ResponseEntity.status(HttpStatus.OK).body(body);
    }
	
	@Operation(summary = "자주 검색하는 식재료 조회 API", description = "로그인 한 user가 자주 검색한 식재료 상위 10가지 목록을 조회합니다.")
    @GetMapping(value = "/")
    public ResponseEntity<ResponseDto<List<FreqIngredientDto>>> getFreqIngrdt() {
		
		//TODO Spring Security 구현
		String userId = "test@example.com"; //하드코딩
		
		List<FreqIngredientDto> data = freqIngredientService.getFreqIngrdt(userId);
		ResponseDto<List<FreqIngredientDto>> body = ResponseDto.<List<FreqIngredientDto>>builder().success(true).data(data).build();

		return ResponseEntity.status(HttpStatus.OK).body(body);
    }
	
	@Operation(summary = "자주 검색하는 식재료 삭제 API", description = "식재료를 DB에서 삭제합니다.")
	@DeleteMapping(value = "/delete/{ingrdt_id}")
    public ResponseEntity<ResponseDto<List<FreqIngredientDto>>> deleteFreqIngrdt(@PathVariable("ingrdt_id")  Long ingrdtId) {
		
		ResponseDto<List<FreqIngredientDto>> body = null;
		//TODO Spring Security 구현
		String userId = "test@example.com"; //하드코딩
		
		
		try {
			List<FreqIngredientDto> data = freqIngredientService.deleteFreqIngrdt(userId, ingrdtId);
			body = ResponseDto.<List<FreqIngredientDto>>builder().success(true).data(data).build();
		}
		catch(EntityNotFoundException e1) {
			
			HashMap<String, Object> error = new HashMap<String, Object>();
			error.put("code", "FREQ_INGRDT.INVALID_ID");
			error.put("message", "존재하지 않는 식재료 입니다.");
			body = ResponseDto.<List<FreqIngredientDto>>builder().success(false).error(error).build();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(body);
    }
	
	@Operation(summary = "자주 검색하는 식재료 reset API", description = "모든 식재료를 DB에서 삭제합니다.")
	@DeleteMapping(value = "/reset")
    public ResponseEntity<ResponseDto<List<FreqIngredientDto>>> resetFreqIngrdt() {
		
		//TODO Spring Security 구현
		String userId = "test@example.com"; // 하드코딩
		
		List<FreqIngredientDto> data = freqIngredientService.resetFreqIngrdt(userId);
		ResponseDto<List<FreqIngredientDto>> body = ResponseDto.<List<FreqIngredientDto>>builder().success(true).data(data).build();

		return ResponseEntity.status(HttpStatus.OK).body(body);
	}

}



