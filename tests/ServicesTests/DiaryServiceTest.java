package ServicesTests;

import data.Repositories.DiaryRepository;
import data.Repositories.DiaryRepositoryImplementation;
import dtos.requests.LoginRequest;
import dtos.requests.RegisterRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.DiaryService;
import services.DiaryServiceImplementation;
import services.Exceptions.UsernameExistsException;

import static org.junit.jupiter.api.Assertions.*;

public class DiaryServiceTest {
    private DiaryRepository diaryRepository;

    @BeforeEach
    public void setup(){
        diaryRepository = new DiaryRepositoryImplementation();
    }
    @Test
    public void registerUser_CreatesNewDiaryTest(){
        DiaryService diaryService = new DiaryServiceImplementation(diaryRepository);
        RegisterRequest request = new RegisterRequest();
        request.setUsername("User");
        request.setPassword("password");
        diaryService.registerUser(request);
        assertEquals(1L,diaryService.getNumberOfUsers());

    }

    @Test
    public void registerTwoUsersWithSameUsername_throwsUsernameExistsExceptionTest() {
        DiaryService diaryService = new DiaryServiceImplementation(diaryRepository);
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
        DiaryService diaryService = new DiaryServiceImplementation(diaryRepository);
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
        DiaryService diaryService = new DiaryServiceImplementation(diaryRepository);
        RegisterRequest request1 = new RegisterRequest();

        request1.setUsername("");
        request1.setPassword("pass");
        assertThrows(IllegalArgumentException.class,()->diaryService.registerUser(request1));
    }

    @Test
    public void registerUserWithSpace_throwsIllegalArgumentExceptionTest() {
        DiaryService diaryService = new DiaryServiceImplementation(diaryRepository);
        RegisterRequest request1 = new RegisterRequest();

        request1.setUsername("  ");
        request1.setPassword("pass");
        assertThrows(IllegalArgumentException.class,()->diaryService.registerUser(request1));
    }

    @Test
    public void registerUserWithNameSeparatedBySpace_throwsIllegalArgumentExceptionTest() {
        DiaryService diaryService = new DiaryServiceImplementation(diaryRepository);
        RegisterRequest request1 = new RegisterRequest();

        request1.setUsername("User Name");
        request1.setPassword("pass");
        assertThrows(IllegalArgumentException.class,()->diaryService.registerUser(request1));
    }

    @Test
    public void registerTwoUsersLogSecondUserIn_SecondUserIsLoggedInTest() {
        DiaryService diaryService = new DiaryServiceImplementation(diaryRepository);
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
}
