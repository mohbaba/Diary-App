package services;

import data.Models.Diary;
import data.Repositories.DiaryRepository;
import data.Repositories.DiaryRepositoryImplementation;
import dtos.requests.RegisterRequest;

public class DiaryServiceImplementation implements DiaryService{
    private final DiaryRepositoryImplementation diaryRepository = new DiaryRepositoryImplementation();
    @Override
    public void registerUser(RegisterRequest registerRequest) {
        Diary diary = new Diary(registerRequest.getName(),registerRequest.getPassword());
        diaryRepository.save(diary);
    }

    @Override
    public void login(String username, String password) {

    }
}
