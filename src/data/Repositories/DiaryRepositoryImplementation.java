package data.Repositories;

import data.Models.Diary;

import java.util.ArrayList;
import java.util.List;

public class DiaryRepositoryImplementation implements DiaryRepository{
    List<Diary> diaries = new ArrayList<>();
    @Override
    public Diary save(Diary diary) {
        diaries.add(diary);
        return diary;
    }

    @Override
    public List<Diary> findAll() {
        return diaries;
    }

    @Override
    public long count() {
        return diaries.size();
    }

    @Override
    public Diary findById(String username) {
        Diary foundDiary = null;
        for (Diary diary : diaries) {
            if (diary.getUsername().equals(username))foundDiary = diary;

        }
        return foundDiary;
    }

    @Override
    public void delete(String username) {
        Diary foundDiary = findById(username);
        diaries.remove(foundDiary);
    }

    @Override
    public void delete(Diary diary) {
        diaries.remove(diary);
    }
}
