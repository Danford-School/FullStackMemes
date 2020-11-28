import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BackupDBTest {
    BackupDB db = new BackupDB();
    @Test
    void addAUser() {
        assertEquals(db.addAUser("Danford", "taco"), "User added!");
        DBUnit test = db.storage.get(db.StorageSearch("Danford", "taco"));
        assertEquals(test.name, "Danford");
        assertEquals(test.password, "taco");
        assert(test.saved.isEmpty());
    }

    @Test
    void saveAMeme() {
        assertEquals(db.saveAMeme("Danford", "taco", "http://imnotreal.com"), "Success, new user added!");
        ArrayList<String> stillATest = db.getSavedMemes("Danford", "taco");
        assert(stillATest.contains("http://imnotreal.com"));
    }

    @Test
    void removeAMeme() {
        assertEquals(db.saveAMeme("Danford", "taco", "http://imnotreal.com"), "Success, new user added!");
        ArrayList<String> stillATest = db.getSavedMemes("Danford", "taco");
        assert(stillATest.contains("http://imnotreal.com"));

        db.removeAMeme("Danford", "taco", "http://imnotreal.com");
        assert(stillATest.isEmpty());
    }

    @Test
    void getSavedMemes() {
        db.saveAMeme("Danford", "taco", "http://imnotreal.com");
        ArrayList<String> stillATest = db.getSavedMemes("Danford", "taco");
        assert(stillATest.contains("http://imnotreal.com"));
    }

    @Test
    void removeUser() {
        db.saveAMeme("Danford", "taco", "http://imnotreal.com");
        ArrayList<String> stillATest = db.getSavedMemes("Danford", "taco");
        assertEquals(db.removeUser("Danford", "taco"), "User removed!");
        assert(!stillATest.contains("Danford"));
    }

    @Test
    void storageSearch() {
        db.saveAMeme("Danford", "taco", "http://imnotreal.com");
        int testInt = db.StorageSearch("Danford", "taco");
        assert(testInt != -1);
        DBUnit test = db.storage.get(testInt);
        assertEquals(test.name, "Danford");
        assertEquals(test.password, "taco");
        assert(test.saved.contains("http://imnotreal.com"));
    }
}