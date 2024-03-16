package services;

import data.Models.Diary;
import data.Repositories.DiaryRepository;
import data.Repositories.DiaryRepositoryImplementation;
import dtos.requests.RegisterRequest;
import services.Exceptions.UsernameExistsException;

public class DiaryServiceImplementation implements DiaryService{
    private final DiaryRepository diaryRepository = new DiaryRepositoryImplementation();
    @Override
    public void registerUser(RegisterRequest registerRequest) {
        validate(registerRequest.getUsername());
        Diary diary = new Diary(registerRequest.getUsername(),registerRequest.getPassword());
        diaryRepository.save(diary);
    }

    private void validate(String username){
        var diary = diaryRepository.findById(username);
        check(username);
        if (diary != null)throw new UsernameExistsException(String.format("%s This username " +
                "already exists",username));
    }

    private void check(String username){
        boolean isValid = username.isEmpty() || username.isBlank();
        boolean containSpace = username.contains(" ");
        if (isValid)throw new IllegalArgumentException("Enter valid username");
        if (containSpace)throw new IllegalArgumentException("Remove spaces");
    }

    @Override
    public void login(String username, String password) {

    }

    @Override
    public long getNumberOfUsers() {
        return diaryRepository.count();
    }
}
