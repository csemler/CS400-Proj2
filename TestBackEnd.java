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
import static org.junit.jupiter.api.Assertions.*;
import java.util.NoSuchElementException;

public class TestBackEnd{
    private SongDatabase songDatabase;  // test instance

    // Instantiate the instance before calling each test methods 
    @BeforeEach
    public void setup(){
      //clear database
      songDatabase = new SongDatabase();
      songDatabase.clearDatabase();

      songDatabase.addSong("title1", "artist1", "pop", 1, 1, 1);
      songDatabase.addSong("title2", "artist2", "hip hop", 2, 2, 2);
      songDatabase.addSong("title3", "artist3", "soul", 3, 3, 3);
      songDatabase.addSong("title4", "artist4", "pop", 4, 4, 4);
      songDatabase.addSong("title5", "artist5", "jazz", 5, 5, 5);
    }
    
    // Test getSong method with valid song title
    @Test
    public void testGetSong() {
      try {
        Song output = songDatabase.getSong("title1");
        
        if (output == null)
          fail("getSong() returnred a null value.");
        
        if (output.getSongTitle() != "title1")
          fail("Test getSong returned the wrong song.");
      }
      catch(Exception e) {
        fail("Test getSong threw an unexpected exception.");
      }
    }
    
    // Test getSong method with a nonexist song title
    @Test
    public void testGetNonExistSong() {
      try {
        //should throw a NoSuchElement exception
        Song output = songDatabase.getSong("titleXXX");
        
        if (output != null)
          fail("Test getSong of non existing song failed.");
      }
      catch (NoSuchElementException e) {
        
      }
      catch(Exception e) {
        fail("Test getSong with nonexisting song threw an unexpected exception.");
      }
    }
    
    // Test hasSong method with valid song title
    @Test
    public void testHasSong() {
      try {  
        if(songDatabase.hasSong("title4") == false)
          fail("Test hasSong() with valid song has failed.");
      }
      catch(Exception e) {
        fail("Test hasSong() threw an unexpected exception.");
      }
    }
    
    // Test hasSong method with non-exist song title
    @Test
    public void testHasNotSong() {
      try {
        if(songDatabase.hasSong("titleXXX") == true)
          fail("test hasSong() of non existing song has failed.");
      }
      catch(Exception e) {
        fail("Test hasSong with a non existing song threw an unexpected exception.");
      }
    }
    
    // Test addSong method with valid song information
    @Test
    public void testAddSong() {
      try {
        //adds new song
        songDatabase.addSong("title6", "artist6", "jazz", 6, 6, 6);
        
        if(songDatabase.hasSong("title6") == false)
          fail("Test addSong() of valid info has failed");
      }
      catch(Exception e) {
        fail("Test add song with valid info threw an unexpected exception.");
      }
    }
    
    // Test addSong method with information contains duplicate song title
    @Test
    public void testAddDuplicateSong() {
      try {
        songDatabase.addSong("title6", "artist6", "jazz", 6, 6, 6);
        if (songDatabase.addSong("title6", "artist6", "jazz", 6, 6, 6) == true)
          fail("Testing adding duplicate song failed.");      
      }
      catch(Exception e) {
        fail("Test add duplicate song threw an unexpected exception.");
      }
    }
        
    // Test updateArtist method with valid song title and newArtist
    @Test
    public void testUpdateArtist() {
      try {
        if (songDatabase.updateArtist("title1", "Morgan Wallen") == false)
          fail ("Test updateArtist with valid song failed.");
        
        if (songDatabase.getSong("title1").getArtist() != "Morgan Wallen")
          fail ("Test updateArtist with valid song failed to update artist.");
      }
      catch(Exception e) {
        fail("Test update artist threw an unexpected exception.");
      }
    }
    
    // Test updateArtist method with invalid input, like newArtist = null || newArtist = “”
    @Test
    public void testUpdateArtistWithInvalidInput() {
      try {
        if (songDatabase.updateArtist("title1", "") == true)
          fail ("Test updateArtist with invalid input failed.");
        
        if (songDatabase.getSong("title1").getArtist().equals(""))
          fail("Test updateArtist with invalid input failed.");
      }
      catch(Exception e) {
        fail("Test update artist with invalid input threw an unexpected exception.");
      }
    }
    
    // Test updateArtist method with non exist song title
    @Test
    public void testUpdateArtistWithNotExistSongTitle() {
      try {        
        //trying to updateArtist should throw a NoSuchElementException
        if (songDatabase.updateArtist("titleXXX", "ArtistXXX") == true)
          fail ("Test updateArtist with non existing song failed.");
      }
      catch(NoSuchElementException e) {
      }
      catch(Exception e) {
        fail("Test update artist of non existing song threw an unexpected exception.");
      }
    }
    
    // Test updateGenre method with valid song title and newArtist
    @Test
    public void testUpdateGenre() {
      try {
        if (songDatabase.updateGenre("title1", "soul") == false)
          fail ("Test updateGenre with valid song failed.");
        
        if (songDatabase.getSong("title1").getGenre().equals("soul") == false)
          fail("Test updating genre with valid song failed.");

      }
      catch(Exception e) {
        fail("Test update genre threw an unexpected exception.");
      }
    }
    
    // Test updateGenre method with invalid input, like newArtist = null || newArtist = “”
    @Test
    public void testUpdateGenreWithInvalidInput() {
      try {
        if (songDatabase.updateGenre("title1", "") == true)
          fail ("Test updateGenre with invalid input failed.");
        
        if (songDatabase.getSong("title1").getGenre().equals(""))
          fail("Test updateArtist with invalid input failed.");
      }
      catch(Exception e) {
        fail("Test update genre with invalid input threw an unexpected exception.");
      }
    }
    
    // Test updateGenre method with non exist song title
    @Test
    public void testUpdateGenreWithNotExistSongTitle() {
      try {
        if (songDatabase.updateGenre("titleXXX", "pop") == true)
          fail ("Test update genre with non existing song failed.");
      }
      catch(Exception e) {
        fail("Test update genre of non existing song threw an unexpected exception.");
      }
    }
    
    // Test updateYear method with valid song title and newArtist
    @Test
    public void testUpdateYear() {
      try {
        if (songDatabase.updateYear("title1", 2020) == false)
          fail ("Test update year with valid song failed.");
        
        if (songDatabase.getSong("title1").getYear() != 2020)
          fail("Test updating year with valid song failed.");
      }
      catch(Exception e) {
        fail("Test update year threw an unexpected exception.");
      }
    }
    
    // Test updateYear method with invalid input, like newYear <= 0
    @Test
    public void testUpdateYearWithInvalidInput() {
      try {
        if (songDatabase.updateYear("title1", -3) == true)
          fail ("Test update year with invalid input failed.");
        
        if (songDatabase.getSong("title1").getYear() == -3)
          fail("Test update year with invalid song failed.");
      }
      catch(Exception e) {
        fail("Test update year with invalid input threw an unexpected exception.");
      }
    }
    
    // Test updateYear method with non exist song title
    @Test
    public void testUpdateYearWithNotExistSongTitle() {
      try {
        if (songDatabase.updateYear("titleXXX", 2000) == true)
          fail ("Test update year with non existing song failed.");
      }
      catch(Exception e) {
        fail("Test update year of non existing song threw an unexpected exception.");
      }
    }
    
    // Test updateBpm method with valid song title and newArtist
    @Test
    public void testUpdateBpm() {
      try {
        if (songDatabase.updateBpm("title1", 20) == false)
          fail ("Test update BPM with valid song failed.");
        
        if (songDatabase.getSong("title1").getBpm() != 20)
          fail("Test updating BPM with valid song failed.");
      }
      catch(Exception e) {
        fail("Test update BPM threw an unexpected exception.");
      }
    }
    
    // Test updateBpm method with invalid input, like newBpm <= 0
    @Test
    public void testUpdateBpmWithInvalidInput() {
      try {
        if (songDatabase.updateBpm("title1", -3) == true)
          fail ("Test update BPM with invalid input failed.");
        
        if (songDatabase.getSong("title1").getBpm() == -3)
          fail("Test update BPM with invalid song failed.");
      }
      catch(Exception e) {
        fail("Test update BPM with invalid input threw an unexpected exception.");
      }
    }
    
    // Test updateBpm method with non exist song title
    @Test
    public void testUpdateBpmWithNotExistSongTitle() {
      try {
        if (songDatabase.updateYear("titleXXX", 2000) == true)
          fail ("Test update BPM with non existing song failed.");
      }
      catch(Exception e) {
        fail("Test update BPM of non existing song threw an unexpected exception.");
      }
    }
    
    // Test updateDuration method with valid song title and newArtist
    @Test
    public void testUpdateDuration() {
      try {
        if (songDatabase.updateDuration("title1", 120) == false)
          fail ("Test update duration with valid song failed.");
        
        if (songDatabase.getSong("title1").getDuration() != 120)
          fail("Test updating duration with valid song failed.");
      }
      catch(Exception e) {
        fail("Test update duration threw an unexpected exception.");
      }
    }
    
    // Test updateDuration method with invalid input, like newDuration <= 0
    @Test
    public void testUpdateDurationWithInvalidInput() {
      try {
        if (songDatabase.updateDuration("title1", -3) == true)
          fail ("Test update duration with invalid input failed.");
        
        if (songDatabase.getSong("title1").getDuration() == -3)
          fail("Test update duration with invalid song failed.");
      }
      catch(Exception e) {
        fail("Test update duration of song with invalid input threw an unexpected exception.");
      }
    }
    
    // Test updateDuration method with non exist song title
    @Test
    public void testUpdateDurationWithNotExistSongTitle() {
      try {
        if (songDatabase.updateDuration("titleXXX", 2000) == true)
          fail ("Test update duration with non existing song failed.");
      }
      catch(Exception e) {
        fail("Test update duration of non existing song threw an unexpected exception.");
      }
    }
    
    // Test getNumOfSong method if return correct num
    @Test
    public void testGetNumOfSong() {
      try {
        if (songDatabase.getNumOfSong() != 5)
          fail("Test get number of songs failed.");
      }
      catch(Exception e) {
        fail("Test get number of songs threw an unexpected exception.");
      }
    }
    
    // Test getGenreFrequency method if return correct frequency/percentage
    @Test
    public void testGetGenreFrequency() {
      try {
        if (songDatabase.getGenreFrequency("pop") != .4)
          fail("Test genre frequency fail");
      }
      catch(Exception e) {
        fail("Test get genre frequency threw an unexpected exception.");
      }
    }
}
