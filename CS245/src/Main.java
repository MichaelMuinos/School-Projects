/*********************************************************
 *      file: Main.java
 *      author: Jose Gutierrez
 *      class: CS 245 - Programming Graphical User Interfaces
 * 
 *      assignment: Quarter Project, Checkpoint # 1
 *      date last modified: 8/8/16
 * 
 *      purpose: The purpose of this class is to, besides holding
 *      the main method of the program, create a
 *      runnable instance of the program by initializing 
 *      an instance of IntoScreenFrame.java.
 *********************************************************/
package cs245project;


import javax.swing.SwingUtilities;

/**
 *
 * @author Jose Gutierrez
 */
public class Main {

    /**
     * @param args the command line arguments
     * Creates instance of IntroScreenFrame
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                IntroScreenFrame introScreen = new IntroScreenFrame();
                introScreen.setLocationRelativeTo(null);
                introScreen.setVisible(true);
            }
        });
    }
    
}
