package ControllerTests;

import controllers.DiaryController;
import dtos.requests.EntryRequest;
import dtos.requests.RegisterRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DiaryControllerTest {
    DiaryController diaryController;
    RegisterRequest registerRequest;


    @BeforeEach
    public void setup(){
        registerRequest = new RegisterRequest();
        diaryController = new DiaryController();
        registerRequest.setUsername("test");
        registerRequest.setPassword("test");
        diaryController.registerUser(registerRequest);

    }

    @Test
    public void registerUser_UserSuccessfullyRegisteredTest(){
        assertEquals("Registration Successful!",diaryController.registerUser(registerRequest));
    }

    @Test
    public void deleteUser_UserSuccessfullyDeleted(){
        diaryController.registerUser(registerRequest);
        assertEquals("test deleted successfully",diaryController.deleteUser("test"));

    }

    @Test
    public void createEntryTest_EntrySuccessfullyCreated(){
        EntryRequest entryRequest = new EntryRequest();
        entryRequest.setTitle("test");
        entryRequest.setBody("test");
        assertEquals("Entry created successfully",diaryController.createEntry(entryRequest));
    }

    @AfterEach
    public void tearDown(){
        diaryController.deleteUser("test");
    }
}
