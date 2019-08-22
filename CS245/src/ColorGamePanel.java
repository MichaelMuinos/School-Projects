/*********************************************************
 *      file: ColorGamePanel.java
 *      author: Michael Muinos, Jose Gutierrez, Erick Rivera
 *      class: CS 245 - Programming Graphical User Interfaces
 * 
 *      assignment: Quarter Project, Checkpoint # 2
 *      date last modified: 8/16/16
 * 
 *      purpose: The purpose of this class is to create the main
 *      panel for the color game that will be added to the 
 *      ColorGameFrame.java class object. The panel uses an absolute layout
 *      to set all the buttons, labels, and images within the panel.
 *********************************************************/
package cs245project;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;


/**
 *
 * @author michael
 */
public class ColorGamePanel extends javax.swing.JPanel {
    private String[] colorName = {"Red", "Yellow", "Green", "Blue", "Purple"};
    private Color[] colors = {Color.RED, Color.YELLOW, Color.GREEN, Color.BLUE, Color.MAGENTA};
    private String[] forCheck = new String[5];
    private String colorCheck = "";
    private Random random = new Random();
    private int gameCount = 1;
    
    private int totalScore;

    /**
     * Creates new form ColorGamePanel
     */
    public ColorGamePanel(int totalScore) {
        initComponents();
        
        randomizeColors();
        
        this.totalScore = totalScore;
        dateLabel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        //This next line exists to populate the date upon launch of game. If not here,
        //date populates as "jLabel2" for one runtime second then does date properly.
        //colorButtons();
        dateLabel.setText(new SimpleDateFormat("MMMM d, yyyy HH:mm:ss").format(new Date()));
        setDate();
        
        scoreLabel.setText("Total Score: " + String.valueOf(totalScore));
        colorNameLabel.setText(colorName[random.nextInt(colorName.length)]);
        int tempInt = random.nextInt(colors.length);
        Color col = colors[tempInt];
        colorCheck = colorName[tempInt];
        colorNameLabel.setForeground(col);
    }
 
    // method: newColor
    // purpose: The purpose of this method is to keep track of the rounds for the
    // game and to handle the logic that both checks the progress and updates the text label.
    public void newColor() {
        if(gameCount == 5) {
            //code to pass in total score to highscores goes here
            SudokuFrame frame = new SudokuFrame("Sudoku", totalScore);
            frame.start();
            JFrame jframe = (JFrame) SwingUtilities.getWindowAncestor(this);
            jframe.dispose();
        }
        gameCount++;
        scoreLabel.setText("Total Score: " + String.valueOf(totalScore));
        colorNameLabel.setText(colorName[random.nextInt(colorName.length)]);
        int tempInt = random.nextInt(colors.length);
        Color col = colors[tempInt];
        colorCheck = colorName[tempInt];
        colorNameLabel.setForeground(col);
        
        randomizeColors();
    }
    
    // method: randomizeColors
    // purpose: The purpose of this method is to both change what balls are what color
    // and to populate structures to help in the game logic.
    public void randomizeColors() {
        //the next 35 lines of code are required to make images work properly.
        ArrayList<String> used = new ArrayList<>(Arrays.asList(colorName));
        long seed = System.nanoTime();
        Collections.shuffle(used, new Random(seed));
        Collections.shuffle(used, new Random(seed));
        
        String name = "/resources/" + used.remove(0).toLowerCase() + "ball.png";
        String hover = name.substring(0, name.length() - 4) + "hover" + name.substring(name.length() - 4, name.length());
        ballOne.setIcon(new javax.swing.ImageIcon(getClass().getResource(name)));
        ballOne.setRolloverEnabled(true);
        ballOne.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource(hover)));
        ballOne.setToolTipText("Select one of these pokeballs as your answer");
        forCheck[0] = name.substring(name.indexOf("s/") + 2, name.indexOf("ball.png"));

        name = "/resources/" + used.remove(0).toLowerCase() + "ball.png";
        hover = name.substring(0, name.length() - 4) + "hover" + name.substring(name.length() - 4, name.length());        
        ballTwo.setIcon(new javax.swing.ImageIcon(getClass().getResource(name)));
        ballTwo.setRolloverEnabled(true);
        ballTwo.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource(hover)));
        ballTwo.setToolTipText("Select one of these pokeballs as your answer");
        forCheck[1] = name.substring(name.indexOf("s/") + 2, name.indexOf("ball.png"));

        name ="/resources/" +  used.remove(0).toLowerCase() + "ball.png";
        hover = name.substring(0, name.length() - 4) + "hover" + name.substring(name.length() - 4, name.length());        
        ballThree.setIcon(new javax.swing.ImageIcon(getClass().getResource(name)));
        ballThree.setRolloverEnabled(true);
        ballThree.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource(hover)));
        ballThree.setToolTipText("Select one of these pokeballs as your answer");
        forCheck[2] = name.substring(name.indexOf("s/") + 2, name.indexOf("ball.png"));
        
        name = "/resources/" + used.remove(0).toLowerCase() + "ball.png";
        hover = name.substring(0, name.length() - 4) + "hover" + name.substring(name.length() - 4, name.length());
        ballFour.setIcon(new javax.swing.ImageIcon(getClass().getResource(name)));
        ballFour.setRolloverEnabled(true);
        ballFour.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource(hover)));
        ballFour.setToolTipText("Select one of these pokeballs as your answer");
        forCheck[3] = name.substring(name.indexOf("s/") + 2, name.indexOf("ball.png"));
        
        name = "/resources/" + used.remove(0).toLowerCase() + "ball.png";
        hover = name.substring(0, name.length() - 4) + "hover" + name.substring(name.length() - 4, name.length());
        ballFive.setIcon(new javax.swing.ImageIcon(getClass().getResource(name)));
        ballFive.setRolloverEnabled(true);
        ballFive.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource(hover)));
        ballFive.setToolTipText("Select one of these pokeballs as your answer");
        forCheck[4] = name.substring(name.indexOf("s/") + 2, name.indexOf("ball.png"));
    }
    
    // method: setDate
    // purpose: The purpose of this method is to start a timer event to display
    // the correct date updated each second at the top right corner of the screen.
    public void setDate() {  
        ActionListener date = (java.awt.event.ActionEvent e) -> {
            dateLabel.setText(new SimpleDateFormat("MMMM d, yyyy HH:mm:ss").format(new Date()));
        };
        new javax.swing.Timer(1000, date).start();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dateLabel = new javax.swing.JLabel();
        scoreLabel = new javax.swing.JLabel();
        colorNameLabel = new javax.swing.JLabel();
        innerJPanel = new javax.swing.JPanel();
        ballOne = new javax.swing.JButton();
        ballTwo = new javax.swing.JButton();
        ballThree = new javax.swing.JButton();
        ballFour = new javax.swing.JButton();
        ballFive = new javax.swing.JButton();
        gameLogo = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(600, 400));
        setLayout(null);

        dateLabel.setFont(new java.awt.Font("Lucida Grande", 0, 12)); // NOI18N
        dateLabel.setText("jLabel2");
        add(dateLabel);
        dateLabel.setBounds(380, 10, 210, 20);

        scoreLabel.setText("jLabel3");
        add(scoreLabel);
        scoreLabel.setBounds(20, 50, 150, 14);

        colorNameLabel.setBackground(new java.awt.Color(255, 255, 255));
        colorNameLabel.setText("jLabel1");
        add(colorNameLabel);
        colorNameLabel.setBounds(250, 50, 109, 14);

        ballOne.setBorderPainted(false);
        ballOne.setContentAreaFilled(false);
        ballOne.setRequestFocusEnabled(false);
        ballOne.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ballOneMouseClicked(evt);
            }
        });

        ballTwo.setBorderPainted(false);
        ballTwo.setContentAreaFilled(false);
        ballTwo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ballTwoMouseClicked(evt);
            }
        });

        ballThree.setBorderPainted(false);
        ballThree.setContentAreaFilled(false);
        ballThree.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ballThreeMouseClicked(evt);
            }
        });

        ballFour.setBorderPainted(false);
        ballFour.setContentAreaFilled(false);
        ballFour.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ballFourMouseClicked(evt);
            }
        });

        ballFive.setBorderPainted(false);
        ballFive.setContentAreaFilled(false);
        ballFive.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/yellowballhover.png"))); // NOI18N
        ballFive.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ballFiveMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout innerJPanelLayout = new javax.swing.GroupLayout(innerJPanel);
        innerJPanel.setLayout(innerJPanelLayout);
        innerJPanelLayout.setHorizontalGroup(
            innerJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(innerJPanelLayout.createSequentialGroup()
                .addGap(111, 111, 111)
                .addComponent(ballFour)
                .addGap(188, 188, 188)
                .addComponent(ballFive)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(innerJPanelLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(ballOne)
                .addGap(56, 56, 56)
                .addComponent(ballTwo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 341, Short.MAX_VALUE)
                .addComponent(ballThree)
                .addGap(55, 55, 55))
        );
        innerJPanelLayout.setVerticalGroup(
            innerJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(innerJPanelLayout.createSequentialGroup()
                .addGroup(innerJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(innerJPanelLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(ballThree))
                    .addGroup(innerJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, innerJPanelLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(ballTwo))
                        .addGroup(innerJPanelLayout.createSequentialGroup()
                            .addGap(28, 28, 28)
                            .addComponent(ballOne))))
                .addGroup(innerJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(innerJPanelLayout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(ballFour))
                    .addGroup(innerJPanelLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(ballFive)))
                .addContainerGap(222, Short.MAX_VALUE))
        );

        add(innerJPanel);
        innerJPanel.setBounds(6, 76, 585, 294);

        gameLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/PoképickLogo.png"))); // NOI18N
        add(gameLogo);
        gameLogo.setBounds(10, 10, 145, 37);
    }// </editor-fold>//GEN-END:initComponents

    // method: ballOneMouseClicked
    // purpose: The purpose of this method is to handle clicks on the button
    private void ballOneMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ballOneMouseClicked
    if(colorCheck.toLowerCase().equals(forCheck[0])) {
            totalScore += 100;
        } //else += 0
        newColor();        
    }//GEN-LAST:event_ballOneMouseClicked

    // method: ballThreeMouseClicked
    // purpose: The purpose of this method is to handle clicks on the button
    private void ballThreeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ballThreeMouseClicked
    if(colorCheck.toLowerCase().equals(forCheck[2])) {
            totalScore += 100;
        } //else += 0
        newColor();       
    }//GEN-LAST:event_ballThreeMouseClicked

    // method: ballTwoMouseClicked
    // purpose: The purpose of this method is to handle clicks on the button
    private void ballTwoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ballTwoMouseClicked
    if(colorCheck.toLowerCase().equals(forCheck[1])) {
            totalScore += 100;
        } //else += 0
        newColor();        
    }//GEN-LAST:event_ballTwoMouseClicked

    // method: ballFourMouseClicked
    // purpose: The purpose of this method is to handle clicks on the button
    private void ballFourMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ballFourMouseClicked
    if(colorCheck.toLowerCase().equals(forCheck[3])) {
            totalScore += 100;
        } //else += 0
        newColor();
    }//GEN-LAST:event_ballFourMouseClicked

    // method: ballFiveMouseClicked
    // purpose: The purpose of this method is to handle clicks on the button
    private void ballFiveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ballFiveMouseClicked
    if(colorCheck.toLowerCase().equals(forCheck[4])) {
            totalScore += 100;
        } //else += 0
        newColor();
    }//GEN-LAST:event_ballFiveMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ballFive;
    private javax.swing.JButton ballFour;
    private javax.swing.JButton ballOne;
    private javax.swing.JButton ballThree;
    private javax.swing.JButton ballTwo;
    private javax.swing.JLabel colorNameLabel;
    private javax.swing.JLabel dateLabel;
    private javax.swing.JLabel gameLogo;
    private javax.swing.JPanel innerJPanel;
    private javax.swing.JLabel scoreLabel;
    // End of variables declaration//GEN-END:variables
}
