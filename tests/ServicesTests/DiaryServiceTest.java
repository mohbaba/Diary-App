package ServicesTests;

import data.Repositories.DiaryRepository;
import data.Repositories.DiaryRepositoryImplementation;
import dtos.requests.RegisterRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.DiaryService;
import services.DiaryServiceImplementation;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DiaryServiceTest {
    private DiaryRepository diaryRepository;
    @BeforeEach
    public void setup(){
        diaryRepository = new DiaryRepositoryImplementation();
    }

    @Test
    public void registerUser_CreatesNewDiaryTest(){
        DiaryServiceImplementation diaryService = new DiaryServiceImplementation();
        RegisterRequest request = new RegisterRequest();
        request.setName("User");
        request.setPassword("password");
        diaryService.registerUser(request);
        assertEquals(1,diaryRepository.count());

    }
}
