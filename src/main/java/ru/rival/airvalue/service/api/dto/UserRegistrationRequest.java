package ru.rival.airvalue.service.api.dto;

import lombok.Data;
import ru.rival.airvalue.validation.LoginIsExists;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class UserRegistrationRequest {

    @LoginIsExists(message = "Имя пользователя занято")
    @NotBlank(message = "Логин не может быть пустым")
    @Size(min = 4, max = 32, message = "Логин должен быть длиной от 4 до 32 символов")
    String login;

    @NotBlank(message = "Пароль не может быть пустым")
    @Size(min = 6, max = 32, message = "Пароль должен быть длиной от 6 до 32 символов")
    String password;

    @NotBlank(message = "Пароль не может быть пустым")
    String confirmPassword;
}
