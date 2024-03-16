package RepositoryTests;

import data.Models.Entry;
import data.Repositories.EntryRepositoryImplementation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

public class EntryRepositoryImplementationTest {

    @Test
    public void testThatEntryCanBeSaved(){
        EntryRepositoryImplementation entryRepo = new EntryRepositoryImplementation();
        Entry entry = new Entry("Hello","hey");
        entryRepo.save(entry);
        assertEquals(1L,entryRepo.count());
    }

    @Test
    public void saveTwoEntries_deleteOneById_OneEntryLeftTest(){
        EntryRepositoryImplementation entryRepo = new EntryRepositoryImplementation();
        Entry entry = new Entry("Hello","How far");
        Entry entry1 = new Entry("How Far","Its good");
        entryRepo.save(entry);
        entryRepo.save(entry1);
        assertEquals(2L,entryRepo.count());
        entryRepo.delete(1);
        assertEquals(1L,entryRepo.count());

    }

    @Test
    public void saveEntry_updateFirstOneTitle_ItChangesTest(){
        EntryRepositoryImplementation entryRepository = new EntryRepositoryImplementation();
        Entry entry = new Entry("Title","Body");
        entryRepository.save(entry);
        assertEquals(1,entryRepository.count());


        Entry updateEntry = new Entry();
        updateEntry.setId(1);
        updateEntry.setTitle("New Title");
        updateEntry.setBody("New Body");
        entryRepository.save(updateEntry);
        assertEquals(1,entryRepository.count());
        assertEquals("New Title",entryRepository.findAll().getFirst().getTitle());
    }

    @Test
    public void saveTwoEntries_DeleteOneEntryObjectRemainsTwoTest(){
        EntryRepositoryImplementation entryRepository = new EntryRepositoryImplementation();
        Entry entry = new Entry("Hello","How far");
        entryRepository.save(entry);
        Entry entry1 = new Entry("How Far","Its good");
        entryRepository.save(entry1);
        assertEquals(2,entryRepository.count());

        entryRepository.delete(entry1);
        assertEquals(1,entryRepository.count());

    }

    @Test
    public void saveTwoEntriesFindTheFirstOneById_ReturnsFirstOneTest(){
        EntryRepositoryImplementation entryRepository = new EntryRepositoryImplementation();
        Entry entry = new Entry("Hello","How far");
        entryRepository.save(entry);
        Entry entry1 = new Entry("How Far","Its good");
        entryRepository.save(entry1);
        assertEquals(2,entryRepository.count());

        assertSame(entry,entryRepository.findById(entry.getId()));
    }

    @Test
    public void testFindAll(){
        EntryRepositoryImplementation entryRepository = new EntryRepositoryImplementation();
        Entry entry = new Entry("Hello","How far");
        entryRepository.save(entry);
        Entry entry1 = new Entry("How Far","Its good");
        entryRepository.save(entry1);
        Entry entry2 = new Entry("Title 2","Body 2");
        entryRepository.save(entry2);

        assertEquals(3,entryRepository.count());

    }

    @Test
    public void saveThreeEntriesUpdateThirdEntry_(){
        EntryRepositoryImplementation entryRepository = new EntryRepositoryImplementation();
        Entry entry = new Entry("Hello","How far");
        entryRepository.save(entry);
        Entry entry1 = new Entry("How Far","Its good");
        entryRepository.save(entry1);
        Entry entry2 = new Entry("Title 2","Body 2");
        entryRepository.save(entry2);

        assertEquals(3,entryRepository.count());
    }

}
