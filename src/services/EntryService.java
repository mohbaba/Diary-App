package services;

import data.Models.Entry;

import java.util.List;

public interface EntryService {
    List<Entry> findEntriesByAuthor(String username );
    void addEntry(Entry entry);
    Entry getEntry(int id);
    void deleteEntry(int id);
}
