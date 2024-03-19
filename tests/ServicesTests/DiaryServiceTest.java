package ServicesTests;

import data.Repositories.DiaryRepository;
import data.Repositories.DiaryRepositoryImplementation;
import dtos.requests.LoginRequest;
import dtos.requests.RegisterRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.DiaryService;
import services.DiaryServiceImplementation;
import services.Exceptions.AccountNotFoundException;
import services.Exceptions.IncorrectPasswordException;
import services.Exceptions.LoginRequiredException;
import services.Exceptions.UsernameExistsException;

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
    public void logUserInUserISLoggedIn_LogoutUserIsLoggedOutTest(){
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


}
