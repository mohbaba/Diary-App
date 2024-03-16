package data.Repositories;

import data.Models.Diary;

import java.util.List;

public interface DiaryRepository {
    Diary save(Diary diary);
    List<Diary> findAll();
    long count();
    Diary findById(String username);
    void delete(String username);
    void delete(Diary diary);
}
