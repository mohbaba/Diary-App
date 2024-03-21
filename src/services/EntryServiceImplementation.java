package services;

import data.Models.Entry;
import data.Repositories.EntryRepository;

import java.util.List;

public class EntryServiceImplementation implements EntryService{
    private EntryRepository repository;
    public EntryServiceImplementation(EntryRepository repository){
        this.repository = repository;
    }
    @Override
    public List<Entry> findEntriesByAuthor(String username) {
         return repository.findByAuthor(username);
    }

    @Override
    public void addEntry(Entry entry) {

    }

    @Override
    public Entry getEntry(int id) {
        return null;
    }

    @Override
    public void deleteEntry(int id) {

    }
}
