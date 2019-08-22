/*********************************************************
 *      file: CreditsFrame.java
 *      author: Michael Muinos
 *      class: CS 245 - Programming Graphical User Interfaces
 * 
 *      assignment: Quarter Project, Checkpoint # 1
 *      date last modified: 8/14/16
 * 
 *      purpose: The purpose of this class is to serialize an array
 *      list object containing all the top high scores in the games.
 *********************************************************/
package cs245project;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author michael
 */
public class HighScoreSerializer {
    
    private final String FILE_NAME = "highscores.ser";
    private HighScore highScore;
    
    private ArrayList<HighScore> highScoreList;
    
    // constructor
    // purpose: initialize new variable of the total score
    // create new array list object to be serialized
    public HighScoreSerializer(HighScore highScore) {
        this.highScore = highScore;
        highScoreList = new ArrayList<>();
    }
    
    // method: serializeHighScore
    // purpose: This method will sort the arraylist of high scores
    // and then reverse the order so the greatest are at the front.
    // Then if the list is greater than 5, it removes the all high scores after index 4.
    // Finally it writes the object to a file.
    public void serializeHighScore() {
        File file = new File(FILE_NAME);
        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(HighScoreSerializer.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            // deserialize object
            highScoreList = HighScoreDeserializer.deserializeHighScoreList();
        }
        
        highScoreList.add(highScore);
        
        // Sort list by score
        Collections.sort(highScoreList, new Comparator<HighScore>() {
            @Override
            public int compare(HighScore highScore1, HighScore highScore2) {
                return highScore1.getScore() - highScore2.getScore();
            }
        });
        
        // Reverse it so it is greatest to smallest
        Collections.reverse(highScoreList);
        
        // If list has more than 5 entries, delete the elements
        // above 5.
        if(highScoreList.size() > 5) {
            for(int i = 5; i < highScoreList.size(); i++) {
                highScoreList.remove(i);
            }
        }
        
        try {
            FileOutputStream fos = new FileOutputStream(FILE_NAME);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(highScoreList);
            oos.close();
            fos.close();
        } catch (IOException ex) {
            Logger.getLogger(HighScoreSerializer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
