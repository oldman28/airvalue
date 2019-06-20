package ru.rival.airvalue.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@SpringBootConfiguration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
            .antMatchers(
                    "/",
                    "/registration",
                    "/analyze",
                    "/manual",
                    "/result/**"
            ).permitAll()
            .antMatchers("/admin/**").hasRole("ADMIN")
            .anyRequest().authenticated()
        .and()
            .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/analyze", true)
            .permitAll()
        .and()
            .logout()
            .permitAll()
            .logoutSuccessUrl("/")
        .and()
            .csrf().disable();
    }

    @Override
    public void configure(WebSecurity web) {
        web
            .ignoring()
            .antMatchers("/h2-console/**")
            .antMatchers("/styles/**")
            .antMatchers("/scripts/**")
            .antMatchers("/images/**")
            .antMatchers("/webjars/**")
            .antMatchers("/favicon.ico");
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
