package com.kkpp.api_server.service;

import java.util.List;
import java.util.Optional;

import com.kkpp.api_server.dto.FreqIngredientDto;
import com.kkpp.api_server.entity.FreqIngredient;

public interface FreqIngredientService {
    public List<FreqIngredientDto> upsertFreqIngrdt(String userId, List<String> items);
    public List<FreqIngredientDto> getFreqIngrdt(String userId);
    public List<FreqIngredientDto> deleteFreqIngrdt(String userId, Long ingrdtId);
    public List<FreqIngredientDto> resetFreqIngrdt(String userId);
}
