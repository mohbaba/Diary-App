package services;

import data.Models.Diary;
import data.Models.Entry;
import data.Repositories.DiaryRepository;
import dtos.requests.EntryRequest;
import dtos.requests.LoginRequest;
import dtos.requests.RegisterRequest;
import services.Exceptions.AccountNotFoundException;
import services.Exceptions.IncorrectPasswordException;
import services.Exceptions.LoginRequiredException;
import services.Exceptions.UsernameExistsException;

import java.util.ArrayList;

public class DiaryServiceImplementation implements DiaryService{
    private final DiaryRepository diaryRepository;
    private boolean isLoggedIn;

    public DiaryServiceImplementation(DiaryRepository diaryRepository){
        this.diaryRepository = diaryRepository;
    }
    @Override
    public void registerUser(RegisterRequest registerRequest) {
        validate(registerRequest.getUsername());
        var diary = new Diary(registerRequest.getUsername(),registerRequest.getPassword());
        isLoggedIn = true;
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

        return validateAccount(login);
    }

    private boolean validateAccount(LoginRequest loginRequest){
        var diary = diaryRepository.findById(loginRequest.getUsername());
        if (diary == null)throw new AccountNotFoundException("Account does not exist, please " +
                "register");
        return validatePassword(diary,loginRequest);
    }

    private boolean validatePassword(Diary diary, LoginRequest loginRequest){
        if (diary.getPassword().equals(loginRequest.getPassword())){
            diary.setLocked(false);
            isLoggedIn = true;
            return true;
        }
        throw new IncorrectPasswordException("The password you entered is incorrect");
    }

    public boolean isLoggedIn(){
        return isLoggedIn;
    }

    @Override
    public void createEntry(EntryRequest entryRequest) {

    }

    @Override
    public ArrayList<Entry> getEntriesFor(String username) {
        return null;
    }

    @Override
    public long getNumberOfUsers() {
        return diaryRepository.count();
    }

    @Override
    public Diary getUserDiary(String username) {
        if (!isLoggedIn)throw new LoginRequiredException("User is currently logged out");
        return diaryRepository.findById(username);

    }

    @Override
    public void logout(String username) {
        var diary = diaryRepository.findById(username);
        diary.setLocked(true);
        isLoggedIn = false;


    }
}
