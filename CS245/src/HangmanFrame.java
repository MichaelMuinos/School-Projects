/*************************************************************
 *      file: HangmanFrame.java
 *      author: Michael Muinos
 *      class: CS 245 - Programming Graphical User Interfaces
 *  
 *      assignment: Quarter Project, Checkpoint # 1
 *      date last modified: 8/8/16
 * 
 *      purpose: The purpose of this class is to create the main
 *      window for the hangman game using a JFrame.
 ************************************************************/
package cs245project;


import javax.swing.JFrame;

public class HangmanFrame {
    
    // Jframe variable
    private JFrame frame;
    
    // constructor: HangmanFrame
    // purpose: create a new JFrame object and assign a title
    public HangmanFrame(String name) {
        frame = new JFrame(name);
    }
    
    // method: start
    // purpose: add various attributes to the JFrame
    // object including the size, visibility, resizability option,
    // and initial position of the window
    public void start() {
        frame.add(new HangmanPanel());
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
