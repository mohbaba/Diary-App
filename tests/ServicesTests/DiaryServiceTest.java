package ServicesTests;

import data.Repositories.DiaryRepository;
import data.Repositories.DiaryRepositoryImplementation;
import dtos.requests.EntryRequest;
import dtos.requests.LoginRequest;
import dtos.requests.RegisterRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.DiaryService;
import services.DiaryServiceImplementation;
import services.Exceptions.*;

import static org.junit.jupiter.api.Assertions.*;

public class DiaryServiceTest {
    private DiaryService diaryService;

    @BeforeEach
    public void setup(){
        DiaryRepository diaryRepository = new DiaryRepositoryImplementation();
        diaryService = new DiaryServiceImplementation(diaryRepository);

    }

    @Test
    public void registerUser_CreatesNewDiaryTest(){
        RegisterRequest request = new RegisterRequest();
        request.setUsername("User");
        request.setPassword("password");
        diaryService.registerUser(request);
        assertEquals(1L,diaryService.getNumberOfUsers());

    }

    @Test
    public void registerTwoUsersWithSameUsername_throwsUsernameExistsExceptionTest() {
        RegisterRequest request = new RegisterRequest();
        RegisterRequest request1 = new RegisterRequest();
        request.setUsername("User");
        request.setPassword("password");
        diaryService.registerUser(request);

        request1.setUsername("User");
        request1.setPassword("pass");
        assertThrows(UsernameExistsException.class,()->diaryService.registerUser(request1));
    }

    @Test
    public void registerTwoUsersWithSameUsernameDifferentCases_throwsUsernameExistsExceptionTest() {
        RegisterRequest request = new RegisterRequest();
        RegisterRequest request1 = new RegisterRequest();
        request.setUsername("User");
        request.setPassword("password");
        diaryService.registerUser(request);

        request1.setUsername("uSer");
        request1.setPassword("pass");
        assertThrows(UsernameExistsException.class,()->diaryService.registerUser(request1));
    }

    @Test
    public void registerUserWithEmptyString_throwsUsernameExistsExceptionTest() {
        RegisterRequest request1 = new RegisterRequest();

        request1.setUsername("");
        request1.setPassword("pass");
        assertThrows(IllegalArgumentException.class,()->diaryService.registerUser(request1));
    }

    @Test
    public void registerUserWithSpace_throwsIllegalArgumentExceptionTest() {
        RegisterRequest request1 = new RegisterRequest();

        request1.setUsername("  ");
        request1.setPassword("pass");
        assertThrows(IllegalArgumentException.class,()->diaryService.registerUser(request1));
    }

    @Test
    public void registerUserWithNameSeparatedBySpace_throwsIllegalArgumentExceptionTest() {
        RegisterRequest request1 = new RegisterRequest();

        request1.setUsername("User Name");
        request1.setPassword("pass");
        assertThrows(IllegalArgumentException.class,()->diaryService.registerUser(request1));
    }

    @SuppressWarnings("SpellCheckingInspection")
    @Test
    public void registerTwoUsersLogSecondUserIn_SecondUserIsLoggedInTest() {
        RegisterRequest request = new RegisterRequest();

        request.setUsername("Username");
        request.setPassword("pass");
        diaryService.registerUser(request);

        RegisterRequest request1 = new RegisterRequest();
        request1.setUsername("USername2");
        request1.setPassword("pass");
        diaryService.registerUser(request1);

        LoginRequest user = new LoginRequest();
        user.setUsername("username2");
        user.setPassword("pass");
        assertTrue(diaryService.login(user));

    }

    @SuppressWarnings("SpellCheckingInspection")
    @Test
    public void registerTwoUsersLogSecondUserInWithCaseSensitiveUsername_SecondUserIsLoggedInTest() {
        RegisterRequest request = new RegisterRequest();

        request.setUsername("Username");
        request.setPassword("pass");
        diaryService.registerUser(request);

        RegisterRequest request1 = new RegisterRequest();
        request1.setUsername("USername2");
        request1.setPassword("pass");
        diaryService.registerUser(request1);

        LoginRequest user = new LoginRequest();
        user.setUsername("uSername2");
        user.setPassword("pass");
        assertTrue(diaryService.login(user));

    }

    @Test
    public void loginUserWithIncorrectPassword_ThrowsExceptionTest() {
        RegisterRequest request = new RegisterRequest();

        request.setUsername("Username");
        request.setPassword("pass");
        diaryService.registerUser(request);

        LoginRequest login = new LoginRequest();
        login.setUsername("username");
        login.setPassword("Pass");
        assertThrows(IncorrectPasswordException.class,()->diaryService.login(login));

    }
    @Test
    public void loginUserWithNonExistingUsername_ThrowsExceptionTest(){
        LoginRequest login = new LoginRequest();
        login.setUsername("username");
        login.setPassword("Pass");
        assertThrows(AccountNotFoundException.class,()->diaryService.login(login));
    }

    @Test
    public void getUserDiaryWithoutLoggingIn_ThrowsExceptionTest(){
        RegisterRequest request = new RegisterRequest();

        request.setUsername("Username");
        request.setPassword("pass");
        diaryService.registerUser(request);

        LoginRequest login = new LoginRequest();
        login.setUsername("username");
        login.setPassword("Pass");

        diaryService.logout("username");
        assertThrows(LoginRequiredException.class, ()->diaryService.getUserDiary("username"));
    }

    @Test
    public void logUserInUserISLoggedIn_Logout_UserIsLoggedOutTest(){
        RegisterRequest request = new RegisterRequest();

        request.setUsername("Username");
        request.setPassword("pass");
        diaryService.registerUser(request);

        LoginRequest login = new LoginRequest();
        login.setUsername("username");
        login.setPassword("Pass");

        diaryService.logout("username");
        assertFalse(diaryService.isLoggedIn());

    }

    @Test
    public void logUserInGetUserDiary_ReturnsTheUserDiaryTest(){
        RegisterRequest request = new RegisterRequest();

        request.setUsername("Username");
        request.setPassword("pass");
        diaryService.registerUser(request);

        LoginRequest login = new LoginRequest();
        login.setUsername("username");
        login.setPassword("Pass");

        assertNotNull(diaryService.getUserDiary(request.getUsername()));
        assertEquals("username",diaryService.getUserDiary(request.getUsername()).getUsername());
        assertNotEquals("Username",diaryService.getUserDiary(request.getUsername()).getUsername());
    }

    @Test
    public void createEntryAndSave_EntryIsCreatedTest(){
        RegisterRequest request = new RegisterRequest();

        request.setUsername("Username");
        request.setPassword("pass");
        diaryService.registerUser(request);

        LoginRequest login = new LoginRequest();
        login.setPassword("Pass");
        login.setUsername("username");
        EntryRequest entryRequest = new EntryRequest();
        entryRequest.setTitle("Title");
        entryRequest.setBody("Body");
        entryRequest.setAuthor(login.getUsername());
        diaryService.createEntry(entryRequest);
        assertEquals(1,diaryService.getEntriesFor("username").size());
        assertEquals("Title",diaryService.getEntriesFor("username").getFirst().getTitle());

    }

    @Test
    public void createEntryAndSaveWithoutLogin_ThrowExceptionTest(){
        EntryRequest entryRequest = new EntryRequest();
        entryRequest.setTitle("Title");
        entryRequest.setBody("Body");
        entryRequest.setAuthor("username");
        assertThrows(LoginRequiredException.class,()->diaryService.createEntry(entryRequest));

    }

    @Test
    public void createTwoEntriesDeleteOne_NumberOfEntriesReduce(){
        RegisterRequest request = new RegisterRequest();

        request.setUsername("Username");
        request.setPassword("pass");
        diaryService.registerUser(request);

        request.setUsername("username2");
        request.setPassword("pass");
        diaryService.registerUser(request);

        EntryRequest entryRequest = new EntryRequest();
        entryRequest.setTitle("Title");
        entryRequest.setBody("Body");
        entryRequest.setAuthor("username2");
        diaryService.createEntry(entryRequest);

        entryRequest.setTitle("Title");
        entryRequest.setBody("Body");
        entryRequest.setAuthor("Username");
        diaryService.createEntry(entryRequest);

        diaryService.deleteEntry(1);
        assertThrows(EntryNotFoundException.class,
                ()->diaryService.getEntriesFor("username2").size());
    }

    @Test
    public void createTwoEntriesFindSecondEntry_ReturnsActualEntry(){
        RegisterRequest request = new RegisterRequest();

        request.setUsername("Username");
        request.setPassword("pass");
        diaryService.registerUser(request);

        request.setUsername("username2");
        request.setPassword("pass");
        diaryService.registerUser(request);

        EntryRequest entryRequest = new EntryRequest();
        entryRequest.setTitle("Title");
        entryRequest.setBody("Body");
        entryRequest.setAuthor("username2");
        diaryService.createEntry(entryRequest);

        entryRequest.setTitle("Title");
        entryRequest.setBody("Body");
        entryRequest.setAuthor("Username");
        diaryService.createEntry(entryRequest);

        assertEquals(diaryService.getEntriesFor("username2").getFirst(),diaryService.findEntry(1));
    }

    @Test
    public void getEntriesByAuthorWithoutLogin_ThrowsExceptionTest(){
        RegisterRequest request = new RegisterRequest();

        request.setUsername("Username");
        request.setPassword("pass");
        diaryService.registerUser(request);
        diaryService.logout(request.getUsername());
        assertThrows(LoginRequiredException.class,()->diaryService.getEntriesFor("username"));
    }


    @Test
    public void findAllEntries_ReturnsTheEntriesInTheRepository(){
        RegisterRequest request = new RegisterRequest();

        request.setUsername("Username");
        request.setPassword("pass");
        diaryService.registerUser(request);

        request.setUsername("username2");
        request.setPassword("pass");
        diaryService.registerUser(request);

        EntryRequest entryRequest = new EntryRequest();
        entryRequest.setTitle("Title");
        entryRequest.setBody("Body");
        entryRequest.setAuthor("username2");
        diaryService.createEntry(entryRequest);

        entryRequest.setTitle("Title");
        entryRequest.setBody("Body");
        entryRequest.setAuthor("Username");
        diaryService.createEntry(entryRequest);

        assertEquals(2,diaryService.findAllEntries().size());
    }

    @Test
    public void FindAllEntriesFromEmptyRepository_ThrowsException(){
        RegisterRequest request = new RegisterRequest();

        request.setUsername("Username");
        request.setPassword("pass");
        diaryService.registerUser(request);
        assertThrows(EntryNotFoundException.class,()->diaryService.findAllEntries());
    }

    @Test
    public void findAllEntriesWithDiaryServiceWithoutLogin_throwsExceptionTest(){
        RegisterRequest request = new RegisterRequest();

        request.setUsername("Username");
        request.setPassword("pass");
        diaryService.registerUser(request);
        diaryService.logout("username");
        assertThrows(LoginRequiredException.class,()->diaryService.findAllEntries());
    }

    @Test
    public void updateExistingEntry_EntryUpdatesTest(){
        RegisterRequest request = new RegisterRequest();

        request.setUsername("Username");
        request.setPassword("pass");
        diaryService.registerUser(request);

        EntryRequest entryRequest = new EntryRequest();
        entryRequest.setTitle("Title");
        entryRequest.setBody("Body");
        entryRequest.setAuthor("Username");
        diaryService.createEntry(entryRequest);
        assertEquals("Title",diaryService.getEntriesFor("username").getFirst().getTitle());

        entryRequest.setTitle("New Title");
        entryRequest.setBody("New Body");
        entryRequest.setAuthor("username");
        entryRequest.setId(1);
        diaryService.updateEntry(entryRequest);

        assertEquals("New Title",diaryService.getEntriesFor("username").getFirst().getTitle());

    }

    @Test
    public void deleteUser_DiaryIsDeletedTest(){
        RegisterRequest request = new RegisterRequest();

        request.setUsername("Username");
        request.setPassword("pass");
        diaryService.registerUser(request);
        assertEquals(1,diaryService.getNumberOfUsers());


        diaryService.deleteAccount("username");
        assertEquals(0,diaryService.getNumberOfUsers());

    }

    @Test
    public void deleteUserWhileLoggedOut_ThrowExceptionTest() {
        RegisterRequest request = new RegisterRequest();

        request.setUsername("Username");
        request.setPassword("pass");
        diaryService.registerUser(request);
        diaryService.logout("username");

        assertThrows(LoginRequiredException.class, () -> diaryService.deleteAccount("username"));
    }



}
