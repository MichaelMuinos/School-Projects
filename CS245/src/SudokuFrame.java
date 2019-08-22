/*************************************************************
 *      file: HangmanResultFrame.java
 *      author: Michael Muinos
 *      class: CS 245 - Programming Graphical User Interfaces
 *  
 *      assignment: Quarter Project, Checkpoint # 3
 *      date last modified: 8/22/16
 * 
 *      purpose: The purpose of this class is to create a new JFrame
 *      object for use with the Sodoku game by creating a new
 *      sodoku panel.
 ************************************************************/
package cs245project;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;

public class SudokuFrame extends JFrame {
    
    private JFrame frame;
    private int totalScore;
    
    // constructor: SodokuFrame
    // purpose: create a new JFrame object and assign a title
    public SudokuFrame(String title, int totalScore) {
        frame = new JFrame(title);
        this.totalScore = totalScore;
    }
    
    // method: start
    // purpose: add various attributes to the JFrame
    // object including the size, visibility, resizability option,
    // and initial position of the window
    public void start() {
        frame.add(new SudokuPanel(totalScore));
        frame.setResizable(false);
        frame.setSize(600, 400);
        frame.validate();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        EscapeAction escape = new EscapeAction();
        escape.register(frame.getRootPane());
        
        F1Action dialog = new F1Action();
        dialog.register(frame.getRootPane());
    }
}
