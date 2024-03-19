package services;

import data.Models.Diary;
import dtos.requests.LoginRequest;
import dtos.requests.RegisterRequest;

public interface DiaryService {
    void registerUser(RegisterRequest registerRequest);
    boolean login(LoginRequest login);
    long getNumberOfUsers();
    Diary getUserDiary(String username);

    void logout(String username);

    boolean isLoggedIn();
//    void createEntry(String title, String body);
}
