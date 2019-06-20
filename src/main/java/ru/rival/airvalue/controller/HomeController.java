package ru.rival.airvalue.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.rival.airvalue.domain.Analysis;
import ru.rival.airvalue.service.api.AnalyzeService;
import ru.rival.airvalue.service.api.dto.AnalyzeDataInput;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final AnalyzeService analyzeService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/analyze")
    public String analyze(Model model) {
        model.addAttribute("analyzeDataInput", new AnalyzeDataInput());
        return "analyze";
    }

    @PostMapping("/analyze")
    public String analyze(@ModelAttribute("analyzeDataInput") AnalyzeDataInput analyzeDataInput) {

        Analysis analysis = analyzeService.airAnalyze(analyzeDataInput);

        return "redirect:/result/" + analysis.getId();
    }

    @GetMapping("/manual")
    public String manual() {
        return "manual";
    }

    @GetMapping("/history")
    public String history() {
        return "history";
    }

    @GetMapping("/result/{id}")
    public String result(@PathVariable("id") Long id, Model model) {

        Analysis analysis = analyzeService.findById(id);
        model.addAttribute("analysis", analysis);

        return "result";
    }
}
