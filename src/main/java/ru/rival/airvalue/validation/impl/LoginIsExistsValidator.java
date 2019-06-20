package ru.rival.airvalue.validation.impl;

import lombok.RequiredArgsConstructor;
import ru.rival.airvalue.repository.UserRepository;
import ru.rival.airvalue.validation.LoginIsExists;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class LoginIsExistsValidator implements ConstraintValidator<LoginIsExists, String> {

    private final UserRepository userRepository;

    @Override
    public void initialize(LoginIsExists constraintAnnotation) {
    }

    @Override
    public boolean isValid(String login, ConstraintValidatorContext constraintValidatorContext) {
        return !userRepository.findUserByUsername(login).isPresent();
    }
}
