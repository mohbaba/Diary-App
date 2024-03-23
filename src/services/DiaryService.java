package services;

import data.Models.Diary;
import data.Models.Entry;
import dtos.requests.EntryRequest;
import dtos.requests.LoginRequest;
import dtos.requests.RegisterRequest;
import java.util.List;

public interface DiaryService {
    void registerUser(RegisterRequest registerRequest);
    boolean login(LoginRequest login);
    long getNumberOfUsers();
    Diary getUserDiary(String username);
    void logout(String username);
    boolean isLoggedIn();
    void createEntry(EntryRequest entryRequest);
    List<Entry> getEntriesFor(String username);
    void deleteEntry(int id);
    Entry findEntry(int id);
    List<Entry> findAllEntries();
    void updateEntry(EntryRequest entryUpdate);
    void deleteAccount(String username);
}
