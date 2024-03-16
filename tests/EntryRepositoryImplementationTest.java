import data.Models.Entry;
import data.Repositories.EntryRepositoryImplementation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

//    @Test
//    public void saveTwoEntries_updateFirstOneTitle_ItChangesTest(){
//        EntryRepositoryImplementation entryRepo = new EntryRepositoryImplementation();
//        Entry entry = new Entry("Hello","How far");
//        Entry entry1 = new Entry("How Far","Its good");
//        entryRepo.save(entry);
//        entryRepo.save(entry1);
//
//    }

    @Test
    public void findEntryByAuthor_returnsEntryTest(){
        EntryRepositoryImplementation entryRepo = new EntryRepositoryImplementation();
        Entry entry = new Entry("Hello","How far");
        Entry entry1 = new Entry("How Far","Its good");

    }
}
