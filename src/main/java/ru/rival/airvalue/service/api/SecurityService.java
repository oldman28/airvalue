package ru.rival.airvalue.service.api;

public interface SecurityService {

    String findLoggedInUsername();

    void autoLogin(String username, String password);
}
