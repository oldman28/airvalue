package ru.rival.airvalue.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.rival.airvalue.domain.Role;
import ru.rival.airvalue.domain.User;
import ru.rival.airvalue.repository.RoleRepository;
import ru.rival.airvalue.repository.UserRepository;
import ru.rival.airvalue.service.api.UserService;
import ru.rival.airvalue.service.api.dto.UserRegistrationRequest;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User registration(UserRegistrationRequest request) {
        User user = new User()
                .setUsername(request.getLogin())
                .setPassword(bCryptPasswordEncoder.encode(request.getPassword()))
                .setRole(getRoleForNewUser());

        return userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findUserByUsername(username)
                .orElseThrow(() -> new RuntimeException("User with username=" + username + " not found!"));
    }

    private Role getRoleForNewUser() {
        return roleRepository.findByName("USER")
                .orElseThrow(() -> new RuntimeException("Role with name=USER not found!"));
    }
}
