// --== CS400 File Header Information ==--
// Name: Christopher Semler
// Email: csemler@wisc.edu
// Team: CG
// Role: Test Engineer
// TA: Yeping Wang
// Lecturer: Florian Heimerl
// Notes to Grader: none

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.NoSuchElementException;
import static org.junit.jupiter.api.Assertions.*;

public class TestDataWrangler {
    private Database database = new Database();  // test instance
    
    // Instantiate the instance before calling each test methods 
    @BeforeEach
    public void setup(){
      database.clearDatabase();
      
      database.addSong(new Song("title1", "artist1", "jazz", 1, 1, 1));
      database.addSong(new Song("title2", "artist2", "pop", 2, 2, 2));
      database.addSong(new Song("title3", "artist3", "hip hop", 3, 3, 3));
      database.addSong(new Song("title4", "artist4", "soul", 4, 4, 4));
      database.addSong(new Song("title5", "artist5", "pop", 5, 5, 5));
    }
    
    // test get method by add a song object, and trying to get the song object from the database
    @Test
    public void testGetSong(){
      try {
        Song output = database.getSong("title1");
        
        if(output == null)
          fail("Get song returned null.");
        
        if(!output.getSongTitle().equals("title1") || !output.getArtist().equals("artist1"))
          fail("Get song returned wrong song.");
      }
      catch(Exception e) {
        fail ("Get song threw an unexpected exception.");
      }
    }

    // test get method by get a non-exist song object
    @Test
    public void testGetNotExistSong(){
      try {
        boolean output = false;
        try { 
          database.getSong("title99");
        }
        catch (NoSuchElementException e) {
          output = true;
        }
        
        if(output == false)
          fail("Get not existing song failed");
      }
      catch (Exception e) {
        fail("Get not existing song threw an unexpected exception.");
      }
    }

    // test add method by adding a song object, and check whether addition is successful with containsSong()
    @Test
    public void testAddSong() {
      try {      
        //creates new song to be added
        Song input = new Song("title", "artist", "pop", 99, 99, 99);
        
        //will add the song and should return true if successful
        if(database.addSong(input) == false)
          fail("Add song returned false.");
        
        if(database.containsSong("title") == false)
          fail("Add song failed.");
      }
      catch (Exception e) {
        fail("Add song threw an unexpected exception.");
      }
    }

    // test add method by adding a duplicate song object
    @Test
    public void testAddDuplicateSong() {
      try {
        //creates new song object to be added 
        Song input = new Song("title99", "artist99", "pop", 99, 99, 99);
        //adds song object the first time
        database.addSong(input);
        
        if (database.containsSong(input.getSongTitle()) == false)
          fail("Add song failed.");
        
        try {
          if (database.addSong(input) == true)
            fail("Adding duplicate song test failed");
        }
        catch(Exception e) {
          fail("Add duplicate song threw an exception.");
        }
      }
      catch (Exception e) {
        fail("Add duplicate song threw an unexpected exception.");
      }
    }

    // test update method by update a song
    @Test
    public void updateSong() {
      try {
        Song input = new Song("title1", "artistXXX", "hip hop", 99, 99, 99);
        
        //checks to make sure newSong does already exist
        if(database.containsSong(input.getSongTitle()) == false)
          database.addSong(input);
        
        //update the song
        database.updateSong(input);
        
        //check for newSong again
        if(database.containsSong(input.getSongTitle()) == false)
          fail("Update song failed.");
        
        Song newDuplicate = database.getSong(input.getSongTitle());
        
        if (newDuplicate.getArtist() != input.getArtist())
          fail("Update song failed.");
        
        if (newDuplicate.getGenre() != input.getGenre())
          fail("Update song failed.");
      }
      catch(Exception e) {
        fail("Update song threw an unexpected exception.");
      }
    }

    // test update method by update a non-exist song
    @Test
    public void updateNonExistSong() {
      try {
        Song input = new Song("title123", "artist123", "pop", 99, 99, 99);
        //update the song that doesn't exist
        //should return false because a song with "title123" as a title should not be in database
        if (database.updateSong(input) == true)
          fail("Update non existing song failed.");        
      }
      catch(Exception e) {
        fail("Update non existing song threw an unexpected exception.");
      }
    }

    // test contain method by passing a valid song title
    @Test
    public void testContainsSong() {
      try {
        if(database.containsSong("title1") == false)
          fail("Test containsSong() with a valid song failed.");
      }
      catch(Exception e) {
        fail("Contains song threw an unexpected exception.");
      }
    }

    // test contain method by passing an non-exist song title
    @Test
    public void testNotContainsSong() {
      try {
        if(database.containsSong("titleXXX") == true)
          fail("Test containsSong() with an invalid song failed.");
      }
      catch(Exception e) {
        fail("Not contains song threw an unexpected exception.");
      }
    }

    // test clear method
    @Test
    public void testClearDatabase() {
      try {
        //adds a song to database to make sure it is not empty
        database.addSong(new Song("title", "artist", "soul", 1, 1, 1));
        
        //checks to make sure it was added correctly
        if (database.containsSong("title") == false)
          fail("Add song AND/OR contains song methods failed.");
        
        //clears database
        database.clearDatabase();
        
        //makes sure song was removed
        if (database.containsSong("title") == true)
          fail("Clear database did not remove song");
        
        //checks to make sure size is 0
        if (database.size() != 0)
          fail("Database size is not 0 after clear");
      }
      catch(Exception e) {
        fail("Clear database threw an unexpected exception.");
      }
    } 
}
