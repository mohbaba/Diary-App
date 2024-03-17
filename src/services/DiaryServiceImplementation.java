package services;

import data.Models.Diary;
import data.Repositories.DiaryRepository;
import dtos.requests.LoginRequest;
import dtos.requests.RegisterRequest;
import services.Exceptions.AccountNotFoundException;
import services.Exceptions.IncorrectPasswordException;
import services.Exceptions.UsernameExistsException;

public class DiaryServiceImplementation implements DiaryService{
//    private final DiaryRepository diaryRepository = new DiaryRepositoryImplementation();
    private DiaryRepository diaryRepository;

    public DiaryServiceImplementation(DiaryRepository diaryRepository){
        this.diaryRepository = diaryRepository;
    }
    @Override
    public void registerUser(RegisterRequest registerRequest) {
        validate(registerRequest.getUsername());
        var diary = new Diary(registerRequest.getUsername(),registerRequest.getPassword());
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
    public boolean login(LoginRequest login) {

        return false;
    }

    private void validate(LoginRequest loginRequest){
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();
        var diary = diaryRepository.findById(username);
        if (diary == null)throw new AccountNotFoundException("Account does not exist, please " +
                "register");
        if (!diary.getPassword().equals(password))throw new IncorrectPasswordException("The " +
                "password you entered is incorrect");
    }

    @Override
    public long getNumberOfUsers() {
        return diaryRepository.count();
    }
}
