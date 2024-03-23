package controllers;

import data.Repositories.DiaryRepository;
import data.Repositories.DiaryRepositoryImplementation;
import dtos.requests.RegisterRequest;
import services.DiaryService;
import services.DiaryServiceImplementation;
import services.Exceptions.DiaryAppException;

public class DiaryController {
    private final DiaryService diaryService = new DiaryServiceImplementation(new DiaryRepositoryImplementation());

    public String registerUser(RegisterRequest registerRequest){
        try {
            diaryService.registerUser(registerRequest);
            return "Registration Successful!";
        }catch (DiaryAppException e){
            return e.getMessage();
        }
    }
}
