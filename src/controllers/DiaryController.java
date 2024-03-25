package controllers;

import data.Repositories.DiaryRepository;
import data.Repositories.DiaryRepositoryImplementation;
import dtos.requests.EntryRequest;
import dtos.requests.LoginRequest;
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

    public String deleteUser(String username) {
        try {
            diaryService.deleteAccount(username);
            return String.format("%s deleted successfully",username);
        }catch (DiaryAppException e){
            return e.getMessage();
        }
    }

    public String createEntry(EntryRequest entryRequest) {
        try {
            diaryService.createEntry(entryRequest);
            return "Entry created successfully";
        }catch (DiaryAppException e){
            return e.getMessage();
        }
    }

    public String deleteEntry(int id){
        try {
            diaryService.deleteEntry(id);
            return "Entry deleted successfully";
        }catch (DiaryAppException e){
            return e.getMessage();
        }
    }

    public String findEntry(int id){
        try {
            diaryService.findEntry(id);
            return "Found Entry";
        }catch (DiaryAppException e ){
            return e.getMessage();
        }
    }

    public String findAllEntriesBy(String username){
        try {
            diaryService.getEntriesFor(username);
            return String.format("%s entries ",username);
        }catch (DiaryAppException e){
            return e.getMessage();
        }
    }

    public String updateEntry(EntryRequest entryRequest){
        try {
            diaryService.updateEntry(entryRequest);
            return "Entry successfully updated";
        }catch (DiaryAppException e){
            return e.getMessage();
        }
    }

    public String deleteAccount(String username){
        try {
            diaryService.deleteAccount(username);
            return String.format("%s deleted successfully",username);
        }catch (DiaryAppException e){
            return  e.getMessage();
        }
    }

    public String login(LoginRequest loginRequest){
        try {
            diaryService.login(loginRequest);
            return String.format("%s successfully logged in",loginRequest.getUsername());
        }catch (DiaryAppException e){
            return e.getMessage();
        }
    }

    public String logout(String username){
        try {
            diaryService.logout(username);
            return String.format("%s successfully logged out ",username);
        }catch (DiaryAppException e){
            return e.getMessage();
        }
    }

    public String getUserDiary(String username){
        try {
            diaryService.getUserDiary(username);
            return String.format("%s's diary successfully found",username);
        }catch (DiaryAppException e){
            return e.getMessage();
        }
    }

}
