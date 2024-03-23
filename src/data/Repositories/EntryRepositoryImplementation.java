package data.Repositories;

import data.Models.Entry;

import java.util.ArrayList;
import java.util.List;

public class EntryRepositoryImplementation implements EntryRepository{
    private final List<Entry> entries = new ArrayList<>();
    private int numberOfEntries;
    @Override
    public Entry save(Entry entry) {
        if (isNew(entry)){
            add(entry);
            return entry;
        }
        update(entry);
        return null;
    }

    private boolean isNew(Entry entry){
        return entry.getId() == 0;
    }

    private void add(Entry entry){
        entry.setId(generateId());
        entries.add(entry);
    }

    private void update(Entry entry){
        for (Entry findEntry : entries) {
            if (findEntry.getId() == entry.getId()){
                delete(findEntry);
                entries.add(entry);
            }
        }


    }
    @Override
    public List<Entry> findAll() {
        return entries;
    }

    @Override
    public Entry findById(int id) {
        for (Entry findEntry : entries) {
            if (findEntry.getId() == id)return findEntry;
        }
        return null;
    }

    @Override
    public List<Entry> findByAuthor(String author) {
        List<Entry> authorEntries = new ArrayList<>();
        for (Entry entry : entries) {
            if (entry.getAuthor().equals(author))authorEntries.add(entry);
        }

        return authorEntries;
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
        return ++numberOfEntries;
    }

    @Override
    public void delete(Entry entry) {
        entries.remove(entry);
    }
}
