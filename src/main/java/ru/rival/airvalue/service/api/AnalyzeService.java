package ru.rival.airvalue.service.api;

import ru.rival.airvalue.domain.Analysis;
import ru.rival.airvalue.service.api.dto.AnalyzeDataInput;

public interface AnalyzeService {

    Analysis airAnalyze(AnalyzeDataInput analyzeDataInput);

    Analysis findById(Long id);
}
