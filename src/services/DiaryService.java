package services;

import dtos.requests.LoginRequest;
import dtos.requests.RegisterRequest;

public interface DiaryService {
    void registerUser(RegisterRequest registerRequest);
    boolean login(LoginRequest login);
    long getNumberOfUsers();
}
