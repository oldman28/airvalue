package ru.rival.airvalue.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.rival.airvalue.domain.Analysis;
import ru.rival.airvalue.domain.GOSTOptimal;
import ru.rival.airvalue.domain.InputParameters;
import ru.rival.airvalue.domain.User;
import ru.rival.airvalue.repository.AnalysisRepository;
import ru.rival.airvalue.repository.GOSTOptimalRepository;
import ru.rival.airvalue.repository.InputParametersRepository;
import ru.rival.airvalue.repository.UserRepository;
import ru.rival.airvalue.service.api.AnalyzeService;
import ru.rival.airvalue.service.api.SecurityService;
import ru.rival.airvalue.service.api.dto.AnalyzeDataInput;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AnalyzeServiceImpl implements AnalyzeService {

    private final AnalysisRepository analysisRepository;
    private final SecurityService securityService;
    private final UserRepository userRepository;
    private final InputParametersRepository inputParametersRepository;
    private final GOSTOptimalRepository gostOptimalRepository;

    @Override
    public Analysis airAnalyze(AnalyzeDataInput analyzeDataInput) {

        User user = findCurrentUser();

        InputParameters inputParameters = saveInputParameters(user, analyzeDataInput);
        GOSTOptimal gostOptimal = gostOptimalRepository.findBySeasonAndPlace(
                inputParameters.getSeason(), inputParameters.getPlace()
        ).orElseThrow(() -> new RuntimeException("Optimal GOST parameters not found! Analysis break!"));

        //*********************
        // место для кода
        //
        // здесь доступны:
        // входные параметры - inputParameters
        // оптимальные по ГОСТу для нужного сезона и помещения/места - gostOptimal
        //
        // Ожидается что здесь будет каким-то образом построена строчка recommendationText, и записана ниже
        //*********************

        Analysis result = new Analysis()
                .setInputParameters(inputParameters)
                .setUser(user)
                .setDateTime(LocalDateTime.now())
                .setName("Сделай поле на форме анализа чтобы прокинуть имя сюда")
                .setRecommendationText("Сюда закинуть текст который получился после анализа = после твоих доработок");

        return analysisRepository.save(result);
    }

    @Override
    public Analysis findById(Long id) {
        return analysisRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Analysis with id=" + id + " not found!"));
    }

    private InputParameters saveInputParameters(User user, AnalyzeDataInput analyzeDataInput) {
        InputParameters inputParameters = new InputParameters()
                .setUser(user)
                .setArea(analyzeDataInput.getArea())
                .setBudget(analyzeDataInput.getBudget())
                .setHeight(analyzeDataInput.getHeight())
                .setPlace(analyzeDataInput.getPlace())
                .setSeason(analyzeDataInput.getSeason())
                .setTemperature(analyzeDataInput.getTemperature())
                .setWetness(analyzeDataInput.getTemperature());

        return inputParametersRepository.save(inputParameters);
    }

    private User findCurrentUser() {
        String username = securityService.findLoggedInUsername();

        if (username == null) {
            return null;
        }

        return userRepository.findUserByUsername(username).orElse(null);
    }
}
