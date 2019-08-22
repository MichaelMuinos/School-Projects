/*********************************************************
 *      file: SudokuPanel.java
 *      author: Michael Muinos, Jose Gutierrez, Erick Rivera
 *      class: CS 245 - Programming Graphical User Interfaces
 * 
 *      assignment: Quarter Project, Checkpoint # 2
 *      date last modified: 8/22/16
 * 
 *      purpose: The purpose of this class is to generate the sudoku
 *      game in which the user can play a single game.
 *********************************************************/
package cs245project;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;


/**
 *
 * @author michael
 */
public class SudokuPanel extends javax.swing.JPanel {
    private final int COLUMN_ROW_BOXES = 9;
    
    private ArrayList<IncorrectAnswerPair> incorrectAnswers;
    
    private JTextField[][] labels;
    private int[][] userFields;
    private int[][] answers;
    
    private int totalScore;
    private int sudokuPoints;
    
    private boolean paintComponentCalled;
    
    // constructor
    // sets the total score from the previous game and creates
    // new arrays for the sudoku game to be functional
    public SudokuPanel(int totalScore) {
        initComponents();
        this.totalScore = totalScore;
        sudokuPoints = 540;
        
        incorrectAnswers = new ArrayList<>();
        
        labels = new JTextField[COLUMN_ROW_BOXES][COLUMN_ROW_BOXES];
        userFields = new int[COLUMN_ROW_BOXES][COLUMN_ROW_BOXES];
        answers = new int[COLUMN_ROW_BOXES][COLUMN_ROW_BOXES];
        
        fillCorrectAnswerFields();
        
        dateLabel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        //This next line exists to populate the date upon launch of game. If not here,
        //date populates as "jLabel2" for one runtime second then does date properly.
        //colorButtons();
        dateLabel.setText(new SimpleDateFormat("MMMM d, yyyy HH:mm:ss").format(new Date()));
        setDate();
        scoreLabel.setText("Total Score: " + String.valueOf(totalScore + sudokuPoints));
    }
    
    // method: fillCorrectAnswerFields
    // purpose: sets all the correct answers inside the 2 dimensional array for the sudoku game
    private void fillCorrectAnswerFields() {
        answers[0][0] = 8; answers[0][1] = 3; answers[0][2] = 5; answers[0][3] = 4; answers[0][4] = 1; answers[0][5] = 6;
        answers[0][6] = 9; answers[0][7] = 2; answers[0][8] = 7; answers[1][0] = 2; answers[1][1] = 9; answers[1][2] = 6;
        answers[1][3] = 8; answers[1][4] = 5; answers[1][5] = 7; answers[1][6] = 4; answers[1][7] = 3; answers[1][8] = 1;
        answers[2][0] = 4; answers[2][1] = 1; answers[2][2] = 7; answers[2][3] = 2; answers[2][4] = 9; answers[2][5] = 3;
        answers[2][6] = 6; answers[2][7] = 5; answers[2][8] = 8; answers[3][0] = 5; answers[3][1] = 6; answers[3][2] = 9;
        answers[3][3] = 1; answers[3][4] = 3; answers[3][5] = 4; answers[3][6] = 7; answers[3][7] = 8; answers[3][8] = 2;
        answers[4][0] = 1; answers[4][1] = 2; answers[4][2] = 3; answers[4][3] = 6; answers[4][4] = 7; answers[4][5] = 8; 
        answers[4][6] = 5; answers[4][7] = 4; answers[4][8] = 9; answers[5][0] = 7; answers[5][1] = 4; answers[5][2] = 8;   
        answers[5][3] = 5; answers[5][4] = 2; answers[5][5] = 9; answers[5][6] = 1; answers[5][7] = 6; answers[5][8] = 3;
        answers[6][0] = 6; answers[6][1] = 5; answers[6][2] = 2; answers[6][3] = 7; answers[6][4] = 8; answers[6][5] = 1; 
        answers[6][6] = 3; answers[6][7] = 9; answers[6][8] = 4; answers[7][0] = 9; answers[7][1] = 8; answers[7][2] = 1; 
        answers[7][3] = 3; answers[7][4] = 4; answers[7][5] = 5; answers[7][6] = 2; answers[7][7] = 7; answers[7][8] = 6; 
        answers[8][0] = 3; answers[8][1] = 7; answers[8][2] = 4; answers[8][3] = 9; answers[8][4] = 6; answers[8][5] = 2; 
        answers[8][6] = 8; answers[8][7] = 1; answers[8][8] = 5;
    }
    
    // method: fillGivenSpaces
    // purpose: sets the given spaces that were already given for the sudoku game
    private void fillGivenSpaces() {
        labels[0][0].setText(String.valueOf(8));
        labels[0][3].setText(String.valueOf(4));
        labels[0][5].setText(String.valueOf(6));
        labels[0][8].setText(String.valueOf(7));
        labels[1][6].setText(String.valueOf(4));
        labels[2][1].setText(String.valueOf(1));
        labels[2][6].setText(String.valueOf(6));
        labels[2][7].setText(String.valueOf(5));
        labels[3][0].setText(String.valueOf(5));
        labels[3][2].setText(String.valueOf(9));
        labels[3][4].setText(String.valueOf(3));
        labels[3][6].setText(String.valueOf(7));
        labels[3][7].setText(String.valueOf(8));
        labels[4][4].setText(String.valueOf(7));
        labels[5][1].setText(String.valueOf(4));
        labels[5][2].setText(String.valueOf(8));
        labels[5][4].setText(String.valueOf(2));
        labels[5][6].setText(String.valueOf(1));
        labels[5][8].setText(String.valueOf(3));
        labels[6][1].setText(String.valueOf(5));
        labels[6][2].setText(String.valueOf(2));
        labels[6][7].setText(String.valueOf(9));
        labels[7][2].setText(String.valueOf(1));
        labels[8][0].setText(String.valueOf(3));
        labels[8][3].setText(String.valueOf(9));
        labels[8][5].setText(String.valueOf(2));
        labels[8][8].setText(String.valueOf(5));
        
        for(int i = 0; i < COLUMN_ROW_BOXES; i++) {
            for(int j = 0; j < COLUMN_ROW_BOXES; j++) {
                if(!labels[i][j].getText().isEmpty()) {
                    labels[i][j].setEditable(false);
                    labels[i][j].setBackground(Color.PINK);
                }
            }
        }
    }
    
    // method: setDate
    // purpose: The purpose of this method is to start answers timer event to display
    // the correct date updated each second at the top right corner of the screen.
    private void setDate() {  
        ActionListener date = (java.awt.event.ActionEvent e) -> {
            dateLabel.setText(new SimpleDateFormat("MMMM d, yyyy HH:mm:ss").format(new Date()));
        };
        new javax.swing.Timer(1000, date).start();
    }

    // method: paintComponent
    // purpose: creates the grid for the sudoku game and draws the text fields inside
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        g2.setColor(Color.WHITE);
        g2.fillRect(160, 50, 270, 270);
        
        // creates the grid
        int x = 160;
        int y = 50;

        g2.setColor(Color.BLACK);

        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                g2.setStroke(new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0));
                g2.drawRect(x, y, 30, 30);
                x += 30;
            }
            x = 160;
            y += 30;
        }

        //draws the darker lines
        g2.setStroke(new BasicStroke(5));
        g2.drawLine(250, 51, 250, 319);
        g2.drawLine(340, 51, 340, 319);
        g2.drawLine(161, 140, 429, 140);
        g2.drawLine(161, 230, 429, 230);

        g2.setStroke(new BasicStroke(3));
        g2.drawRect(160, 50, 270, 270);
        
        if(!paintComponentCalled) {       
            // creates the grid
            int xTextField = 160;
            int yTextField = 50;

            for(int i = 0; i < 9; i++) {
                for(int j = 0; j < 9; j++) {
                    JTextField field = new JTextField();
                    field.setBounds(xTextField + 4, yTextField + 4, 20, 20);
                    this.add(field);
                    labels[i][j] = field;

                    xTextField += 30;
                }
                xTextField = 160;
                yTextField += 30;
            }

            //draws the darker lines
            g2.setStroke(new BasicStroke(5));
            g2.drawLine(250, 51, 250, 319);
            g2.drawLine(340, 51, 340, 319);
            g2.drawLine(161, 140, 429, 140);
            g2.drawLine(161, 230, 429, 230);

            g2.setStroke(new BasicStroke(3));
            g2.drawRect(160, 50, 270, 270);

            paintComponentCalled = true;

            fillGivenSpaces();
        }
    }
    
    // method: isNumeric
    // purpose: determines if the string is only numeric
    private boolean isNumeric(String str) {
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) return false;
        }
        return true;
    }
    
    // method: containsIncorrectPair
    // purpose: determines if the row and column index is a pair that has already
    // been marked as incorrect
    private boolean containsIncorrectPair(int row, int col) {
        for(int i = 0; i < incorrectAnswers.size(); i++) {
            if(incorrectAnswers.get(i).getRowIndex() == row 
                    && incorrectAnswers.get(i).getColIndex() == col) {
                return true;
            }
        }
        return false;
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
        submitButton = new javax.swing.JButton();
        endButton = new javax.swing.JButton();
        scoreLabel = new javax.swing.JLabel();
        sudokuLogo = new javax.swing.JLabel();

        dateLabel.setText("jLabel1");

        submitButton.setText("Submit");
        submitButton.setToolTipText("Submit your attempt to solve the game.");
        submitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitButtonActionPerformed(evt);
            }
        });
        add(submitButton);

        endButton.setText("End");
        endButton.setToolTipText("Skips game and views final score.");
        endButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                endButtonActionPerformed(evt);
            }
        });

        scoreLabel.setText("jLabel3");

        sudokuLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/SudokuLogo.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(submitButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 406, Short.MAX_VALUE)
                        .addComponent(endButton)
                        .addGap(15, 15, 15))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(sudokuLogo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(scoreLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(dateLabel)
                        .addComponent(scoreLabel))
                    .addComponent(sudokuLogo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 263, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(submitButton)
                    .addComponent(endButton))
                .addGap(42, 42, 42))
        );
    }// </editor-fold>//GEN-END:initComponents

    // method: endButtonActionPerformed
    // purpose: creates a new frame and disposes of the current sudoku game
    private void endButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_endButtonActionPerformed
        ResultFrame frame = new ResultFrame(totalScore);
        frame.setResultFrameAttributes();
        JFrame jframe = (JFrame) SwingUtilities.getWindowAncestor(this);
        jframe.dispose();
    }//GEN-LAST:event_endButtonActionPerformed

    // method: submitButtonActionPerformed
    // purpose: checks if the specified user entries is a correct answer. if not,
    // it will decrement the user points and the user can try again
    private void submitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitButtonActionPerformed
        boolean incorrectInput = false;
        boolean allCorrectAnswers = true;
        
        for(int i = 0; i < labels.length; i++) {
            for(int j = 0; j < labels[0].length; j++) {
                String str = labels[i][j].getText();
                if(str.isEmpty() || !isNumeric(str) || str.equalsIgnoreCase("0") || str.length() != 1) {
                    labels[i][j].setBorder(BorderFactory.createLineBorder(Color.RED));
                    incorrectInput = true;
                } else {
                    labels[i][j].setBorder(BorderFactory.createLineBorder(Color.GRAY));
                    userFields[i][j] = Integer.parseInt(str);
                }
            }
        }
        
        if(!incorrectInput) {
            // check answers
            for(int i = 0; i < COLUMN_ROW_BOXES; i++) {
                for(int j = 0; j < COLUMN_ROW_BOXES; j++) {
                    if(userFields[i][j] != answers[i][j]) {
                        allCorrectAnswers = false;
                        if(!containsIncorrectPair(i,j)) {
                            sudokuPoints -= 10;
                            incorrectAnswers.add(new IncorrectAnswerPair(i,j));
                        }
                    }
                }
            }
            
            scoreLabel.setText("Total Score:" + String.valueOf(totalScore + sudokuPoints));
        
            if(allCorrectAnswers) {
                ResultFrame resultFrame = new ResultFrame(totalScore + sudokuPoints);
                resultFrame.setResultFrameAttributes();
                JFrame jframe = (JFrame) SwingUtilities.getWindowAncestor(this);
                jframe.dispose();
            }
        }
    }//GEN-LAST:event_submitButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel dateLabel;
    private javax.swing.JButton endButton;
    private javax.swing.JLabel scoreLabel;
    private javax.swing.JButton submitButton;
    private javax.swing.JLabel sudokuLogo;
    // End of variables declaration//GEN-END:variables

}
