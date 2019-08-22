/*********************************************************
 *      file: HighScore.java
 *      author: Michael Muinos
 *      class: CS 245 - Programming Graphical User Interfaces
 * 
 *      assignment: Quarter Project, Checkpoint # 1
 *      date last modified: 8/14/16
 * 
 *      purpose: The purpose of this class is to contain the basic
 *               data for high score frame.
 *********************************************************/
package cs245project;

import java.io.Serializable;


public class HighScore implements Serializable {
    
    private String name;
    private int score;
    
    // constructor: HighScore
    // purpose: populates name and score with passed in vals
    public HighScore(String name, int score) {
        this.name = name;
        this.score = score;
    }

    //method: getName
    //purpose: returns stored name value to caller.
    public String getName() {
        return name;
    }

    //method: getScore
    //purpose: returns stored score value to caller.    
    public int getScore() {
        return score;
    }

    //method: toString
    //purpose: returns string representation of name and score to caller.
    @Override
    public String toString() {
        return "Name: " + this.name + ", Score: " + this.score;
    }
    
}
