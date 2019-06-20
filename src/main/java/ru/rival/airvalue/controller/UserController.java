package ru.rival.airvalue.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.rival.airvalue.service.api.UserService;
import ru.rival.airvalue.service.api.dto.UserRegistrationRequest;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("registrationForm", new UserRegistrationRequest());
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(
            @ModelAttribute("registrationForm") @Validated UserRegistrationRequest registrationForm,
            BindingResult bindingResult) {

        if (!registrationForm.getPassword().equals(registrationForm.getConfirmPassword())) {
            bindingResult.addError(buildConfirmPasswordConstraintError());
        }

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userService.registration(registrationForm);

        return "redirect:/";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    private ObjectError buildConfirmPasswordConstraintError() {
        return new FieldError("registrationForm", "confirmPassword", "Пароли не совпадают");
    }
}
