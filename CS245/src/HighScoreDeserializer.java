/*********************************************************
 *      file: CreditsFrame.java
 *      author: Michael Muinos
 *      class: CS 245 - Programming Graphical User Interfaces
 * 
 *      assignment: Quarter Project, Checkpoint # 1
 *      date last modified: 8/14/16
 * 
 *      purpose: The purpose of this class is to deserialize the array
 *      list object that was created in HighScoreSerializer.java
 *********************************************************/
package cs245project;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author michael
 */
public class HighScoreDeserializer {
    public static final String FILE_NAME = "highScores.ser";
    
    // method: deserializeHighScoreList
    // purpose: The purpose of this method is to deserialize the object stored in 
    // highScores.ser file. It will grab the array list of high score objects.
    public static ArrayList<HighScore> deserializeHighScoreList() {
        ArrayList<HighScore> highScoreList = null;
        try {
            FileInputStream fis = new FileInputStream(FILE_NAME);
            ObjectInputStream ois = new ObjectInputStream(fis);
            highScoreList = (ArrayList<HighScore>) ois.readObject();
            ois.close();
            fis.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(HighScoreDeserializer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(HighScoreDeserializer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return highScoreList;
    }
    
}
