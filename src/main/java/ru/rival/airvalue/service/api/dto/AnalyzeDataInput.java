package ru.rival.airvalue.service.api.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * Класс для представления данных на основании которых производится анализ
 */
@Data
@Accessors(chain = true)
public class AnalyzeDataInput {

    @NotNull
    Season season;

    @NotNull
    Place place;

    @NotNull
    Integer area;

    @NotNull
    Integer height;

    @NotNull
    Integer budget;

    @NotNull
    Integer temperature;

    @NotNull
    Integer wetness;
}
