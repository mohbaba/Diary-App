package services;

import data.Models.Entry;

import java.util.List;

public interface EntryService {
    List<Entry> findEntriesByAuthor(String username );
    void addEntry(Entry entry);
    Entry findEntry(int id);
    void deleteEntry(int id);
    void updateEntry(Entry entry);

    List<Entry> findAll();
}
