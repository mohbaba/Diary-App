package data.Repositories;

import data.Models.Entry;

import java.util.ArrayList;
import java.util.List;

public class EntryRepositoryImplementation implements EntryRepository{
    private final List<Entry> entries = new ArrayList<>();
    private int numberOfEntries;
    @Override
    public Entry save(Entry entry) {
        entry.setId(generateId());
        entries.add(entry);
        return entry;
    }

    @Override
    public List<Entry> findAll() {
        return null;
    }

    @Override
    public Entry findById(int id) {
        return null;
    }

    @Override
    public Entry findByAuthor(String author) {
        return null;
    }

    @Override
    public long count() {
        return entries.size();
    }

    @Override
    public void delete(int id) {
        entries.removeIf(entry -> entry.getId() == id);
    }

    private int generateId(){
        ++numberOfEntries;
    }

    @Override
    public void delete(Entry entry) {

    }
}
