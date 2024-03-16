package data.Repositories;

import data.Models.Entry;

import java.util.List;

public interface EntryRepository {
    Entry save(Entry entry);
    List<Entry> findAll();
    Entry findById(int id);
    Entry findByAuthor(String author);
    long count();
    void delete(int id);
    void delete(Entry entry);
}
