package RepositoryTests;

import data.Models.Diary;
import data.Repositories.DiaryRepositoryImplementation;
import org.junit.jupiter.api.Test;

import java.util.Arrays;


import static org.junit.jupiter.api.Assertions.*;

public class DiaryRepoImplementationTest {

    @Test
    public void testDiaryCanSave(){
        DiaryRepositoryImplementation diaryRepo = new DiaryRepositoryImplementation();
        Diary diary = new Diary("abike","password");
        diaryRepo.save(diary);
        assertEquals(1L,diaryRepo.count());
    }

    @Test
    public void testDiaryCanBeSavedAndDeleted(){
        DiaryRepositoryImplementation diaryRepo = new DiaryRepositoryImplementation();
        Diary diary = new Diary("Bolaji","password");
        diaryRepo.save(diary);
        assertEquals(1L,diaryRepo.count());
        diaryRepo.delete(diary);
        assertEquals(0L,diaryRepo.count());
    }

    @Test
    public void testFindById_ReturnsTheDiaryFound(){
        DiaryRepositoryImplementation diaryRepo = new DiaryRepositoryImplementation();
        Diary diary = new Diary("Dee","password");
        Diary diary1 = new Diary("mohbaba","password");
        diaryRepo.save(diary);
        diaryRepo.save(diary1);
        assertEquals(2L,diaryRepo.count());
        assertSame(diary1, diaryRepo.findById("mohbaba"));
    }

    @Test
    public void testThatDiaryCanBeDeletedByUsername(){
        DiaryRepositoryImplementation diaryRepo = new DiaryRepositoryImplementation();
        Diary diary = new Diary("Abike1","password");
        diaryRepo.save(diary);
        assertEquals(1L,diaryRepo.count());
        diaryRepo.delete("Abike1");
        assertEquals(0L, diaryRepo.count());

    }
    @Test
    public void testThatDiaryCanBeDeletedWithDiaryObject(){
        DiaryRepositoryImplementation diaryRepo = new DiaryRepositoryImplementation();
        Diary diary = new Diary("Abike1","password");
        diaryRepo.save(diary);
        assertEquals(1L,diaryRepo.count());
        diaryRepo.delete(diary);
        assertEquals(0L, diaryRepo.count());
    }

    @Test
    public void saveThreeDiaries_DeleteOneDiaryObjectTest(){
        DiaryRepositoryImplementation diaryRepo = new DiaryRepositoryImplementation();
        Diary diary = new Diary("Abike1","password");
        Diary diary1 = new Diary("username1","password");
        Diary diary2 = new Diary("username3","password");
        diaryRepo.save(diary);
        diaryRepo.save(diary1);
        diaryRepo.save(diary2);
        assertEquals(3L,diaryRepo.count());
        diaryRepo.delete(diary2);
        diaryRepo.delete(diary1);
        assertEquals(1L,diaryRepo.count());

    }

    @Test
    public void testFindAll(){
        DiaryRepositoryImplementation diaryRepo = new DiaryRepositoryImplementation();
        Diary diary = new Diary("Abike1","password");
        Diary diary1 = new Diary("username1","password");
        Diary diary2 = new Diary("username3","password");
        diaryRepo.save(diary);
        diaryRepo.save(diary1);
        diaryRepo.save(diary2);
        assertEquals(Arrays.toString(new String[]{"Abike1", "username1", "username3"}),diaryRepo.findAll().toString());
    }



}
