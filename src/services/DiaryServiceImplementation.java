package services;

import data.Models.Diary;
import data.Models.Entry;
import data.Repositories.DiaryRepository;
import dtos.requests.EntryRequest;
import dtos.requests.LoginRequest;
import dtos.requests.RegisterRequest;
import services.Exceptions.*;
import java.util.List;

public class DiaryServiceImplementation implements DiaryService{
    private final DiaryRepository diaryRepository;
    private final EntryService entryService = new EntryServiceImplementation();
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
        checkLogin();
        Entry entry = new Entry();
        entry.setTitle(entryRequest.getTitle());
        entry.setBody(entryRequest.getBody());
        entry.setAuthor(entryRequest.getAuthor());

        entryService.addEntry(entry);
    }

    @Override
    public List<Entry> getEntriesFor(String username) {
        checkLogin();
        List<Entry> entriesByAuthor = entryService.findEntriesByAuthor(username);
        if (entriesByAuthor.isEmpty())throw new EntryNotFoundException("No entries found");
        return entryService.findEntriesByAuthor(username);
    }

    @Override
    public void deleteEntry(int id) {
        checkLogin();
        entryService.deleteEntry(id);
    }

    @Override
    public Entry findEntry(int id) {
        checkLogin();
        return entryService.findEntry(id);
    }

    @Override
    public List<Entry> findAllEntries() {
        checkLogin();
        return entryService.findAll();
    }

    @Override
    public void updateEntry(EntryRequest entryUpdate) {
        checkLogin();
        Entry entry = new Entry();
        entry.setId(entryUpdate.getId());
        entry.setAuthor(entryUpdate.getAuthor());
        entry.setTitle(entryUpdate.getTitle());
        entry.setBody(entryUpdate.getBody());
        entryService.updateEntry(entry);
    }

    @Override
    public void deleteAccount(String username) {
        checkLogin();
        var findDiary = diaryRepository.findById(username);
        if (findDiary == null)throw new AccountNotFoundException("Account does not exist");
        diaryRepository.delete(findDiary);
    }


    @Override
    public long getNumberOfUsers() {
        checkLogin();
        return diaryRepository.count();
    }

    @Override
    public Diary getUserDiary(String username) {
        checkLogin();
        Diary foundDiary =  diaryRepository.findById(username);
        if (foundDiary == null)throw new AccountNotFoundException("Account Not found");
        return diaryRepository.findById(username);

    }

    private void checkLogin() {
        if (!isLoggedIn)throw new LoginRequiredException("User is currently logged out");
    }


    @Override
    public void logout(String username) {
        var diary = diaryRepository.findById(username);
        diary.setLocked(true);
        isLoggedIn = false;

    }
}
