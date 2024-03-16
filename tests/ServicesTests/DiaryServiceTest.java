package ServicesTests;

import dtos.requests.RegisterRequest;
import org.junit.jupiter.api.Test;
import services.DiaryService;
import services.DiaryServiceImplementation;
import services.Exceptions.UsernameExistsException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DiaryServiceTest {
    @Test
    public void registerUser_CreatesNewDiaryTest(){
        DiaryService diaryService = new DiaryServiceImplementation();
        RegisterRequest request = new RegisterRequest();
        request.setUsername("User");
        request.setPassword("password");
        diaryService.registerUser(request);
        assertEquals(1L,diaryService.getNumberOfUsers());

    }

    @Test
    public void registerTwoUsersWithSameUsername_throwsUsernameExistsExceptionTest() {
        DiaryService diaryService = new DiaryServiceImplementation();
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
        DiaryService diaryService = new DiaryServiceImplementation();
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
        DiaryService diaryService = new DiaryServiceImplementation();
        RegisterRequest request1 = new RegisterRequest();

        request1.setUsername("");
        request1.setPassword("pass");
        assertThrows(IllegalArgumentException.class,()->diaryService.registerUser(request1));
    }

    @Test
    public void registerUserWithSpace_throwsIllegalArgumentExceptionTest() {
        DiaryService diaryService = new DiaryServiceImplementation();
        RegisterRequest request1 = new RegisterRequest();

        request1.setUsername("  ");
        request1.setPassword("pass");
        assertThrows(IllegalArgumentException.class,()->diaryService.registerUser(request1));
    }

    @Test
    public void registerUserWithNameSeparatedBySpace_throwsIllegalArgumentExceptionTest() {
        DiaryService diaryService = new DiaryServiceImplementation();
        RegisterRequest request1 = new RegisterRequest();

        request1.setUsername("User Name");
        request1.setPassword("pass");
        assertThrows(IllegalArgumentException.class,()->diaryService.registerUser(request1));
    }
}
