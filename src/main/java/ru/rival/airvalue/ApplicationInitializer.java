package ru.rival.airvalue;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import ru.rival.airvalue.domain.Role;
import ru.rival.airvalue.domain.User;
import ru.rival.airvalue.repository.RoleRepository;
import ru.rival.airvalue.repository.UserRepository;

import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ApplicationInitializer implements ApplicationRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void run(ApplicationArguments args) {
        initRoles();
        buildDemoAdmin();
        buildDemoUser();
    }

    private User buildDemoAdmin() {
        Role roleAdmin = roleRepository.findByName("ADMIN")
                .orElseThrow(() -> new RuntimeException("Can`t create admin! Role with name=ADMIN not found!"));

        User admin = new User()
                .setUsername("admin")
                .setPassword(bCryptPasswordEncoder.encode("admin"))
                .setRole(roleAdmin);

        return userRepository.save(admin);
    }

    private User buildDemoUser() {
        Role roleUser = roleRepository.findByName("USER")
                .orElseThrow(() -> new RuntimeException("Can`t create user! Role with name=USER not found!"));

        User user = new User()
                .setUsername("user")
                .setPassword(bCryptPasswordEncoder.encode("user"))
                .setRole(roleUser);

        return userRepository.save(user);
    }

    private List<Role> initRoles() {
        Role admin = new Role()
                .setName("ADMIN");

        Role user = new Role()
                .setName("USER");

        return roleRepository.saveAll(Arrays.asList(admin, user));
    }
}
