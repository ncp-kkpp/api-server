package com.kkpp.api_server.dto;

import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FreqIngredientDto {

    private Long id;

    @NotBlank
    @Size(max = 320)
    private String userId;

    @NotBlank
    private String name;

    @PositiveOrZero
    private Integer searchCount;

    private OffsetDateTime createDt;
    private OffsetDateTime updateDt;
}
