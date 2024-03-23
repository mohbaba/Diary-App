package services;

import data.Models.Entry;
import data.Repositories.EntryRepository;
import data.Repositories.EntryRepositoryImplementation;
import services.Exceptions.EntryNotFoundException;

import java.util.List;

public class EntryServiceImplementation implements EntryService{
    private  EntryRepository repository = new EntryRepositoryImplementation();

    @Override
    public List<Entry> findEntriesByAuthor(String username) {
         return repository.findByAuthor(username);
    }

    @Override
    public void addEntry(Entry entry) {
        repository.save(entry);
    }

    @Override
    public Entry findEntry(int id) {
        Entry foundEntry = repository.findById(id);
        checkNullEntry(foundEntry);
        return foundEntry;
    }

    private static void checkNullEntry(Entry foundEntry) {
        if (foundEntry == null)throw new EntryNotFoundException("Entry does not exist");
    }

    @Override
    public void deleteEntry(int id) {
        Entry foundEntry = findEntry(id);
        checkNullEntry(foundEntry);
        repository.delete(id);
    }

    @Override
    public void updateEntry(Entry entry) {
        addEntry(entry);
    }

    @Override
    public List<Entry> findAll() {
        List<Entry> entries = repository.findAll();
        if (entries.isEmpty())throw new EntryNotFoundException("No Entries Currently Found!");
        return entries;
    }
}
