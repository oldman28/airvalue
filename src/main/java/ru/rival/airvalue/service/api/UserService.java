package ru.rival.airvalue.service.api;

import ru.rival.airvalue.domain.User;
import ru.rival.airvalue.service.api.dto.UserRegistrationRequest;

public interface UserService {

    User registration(UserRegistrationRequest request);

    User findByUsername(String username);
}
