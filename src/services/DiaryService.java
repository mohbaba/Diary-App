package services;

import dtos.requests.RegisterRequest;

public interface DiaryService {
    void registerUser(RegisterRequest registerRequest);
    void login(String username, String password);

    long getNumberOfUsers();
}
