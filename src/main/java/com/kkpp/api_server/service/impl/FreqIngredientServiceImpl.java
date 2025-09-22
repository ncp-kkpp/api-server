package com.kkpp.api_server.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kkpp.api_server.dto.FreqIngredientDto;
import com.kkpp.api_server.entity.FreqIngredient;
import com.kkpp.api_server.mapper.FreqIngredientMapper;
import com.kkpp.api_server.repository.FreqIngredientRepository;
import com.kkpp.api_server.service.FreqIngredientService;

import jakarta.persistence.EntityNotFoundException;

@Service
@Transactional
public class FreqIngredientServiceImpl implements FreqIngredientService {
	
	private final FreqIngredientRepository freqIngredientRepository;
	private final FreqIngredientMapper freqIngredientMapper;
	
	public FreqIngredientServiceImpl(FreqIngredientRepository freqIngredientRepository, FreqIngredientMapper freqIngredientMapper) {
		this.freqIngredientRepository = freqIngredientRepository;
		this.freqIngredientMapper = freqIngredientMapper;
	}

	@Override
	public List<FreqIngredientDto> upsertFreqIngrdt(String userId, List<String> items) {
		
		for(String ingrdt: items) {
			freqIngredientRepository.upsertIngrdt(userId, ingrdt);
		}
		
		return getFreqIngrdt(userId);
	}

	@Override
	public List<FreqIngredientDto> getFreqIngrdt(String userId) {
		
		List<FreqIngredient> entityResult = freqIngredientRepository.findTop10ByUserIdOrderBySearchCountDescUpdateDtDesc(userId);
		List<FreqIngredientDto> result = entityResult.stream().map(entity -> {
			return freqIngredientMapper.toDto(entity);
		}).toList();
		
		return result;
	}

	@Override
	public List<FreqIngredientDto> deleteFreqIngrdt(String userId, Long ingrdtId) {
		FreqIngredient entity = freqIngredientRepository.findById(ingrdtId)
	            .orElseThrow(() -> new EntityNotFoundException("해당 식재료가 존재하지 않습니다."));
	    if (!entity.getUserId().equals(userId)) {
	        throw new EntityNotFoundException("본인 소유의 식재료가 아닙니다.");
	    }
	    freqIngredientRepository.delete(entity);
		return getFreqIngrdt(userId);
	}

	@Override
	public List<FreqIngredientDto> resetFreqIngrdt(String userId) {
		
		freqIngredientRepository.deleteByUserId(userId);
		return getFreqIngrdt(userId);
	}
	
}
