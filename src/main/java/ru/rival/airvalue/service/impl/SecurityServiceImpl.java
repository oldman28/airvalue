package ru.rival.airvalue.service.impl;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import ru.rival.airvalue.service.api.SecurityService;

@Service
@RequiredArgsConstructor
public class SecurityServiceImpl implements SecurityService {

    private static final Logger logger = LoggerFactory.getLogger(SecurityServiceImpl.class);

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;

    @Override
    public String findLoggedInUsername() {
        Object userDetails = SecurityContextHolder.getContext().getAuthentication().getDetails();
        if (userDetails instanceof UserDetails) {
            return ((UserDetails)userDetails).getUsername();
        }

        return null;
    }

    @Override
    public void autoLogin(String username, String password) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                userDetails, password, userDetails.getAuthorities()
        );

        authenticationManager.authenticate(token);

        if (token.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(token);
            logger.debug("Auto login username=" + username + " successfully!");
        }
    }
}
