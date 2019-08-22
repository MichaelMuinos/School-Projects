/*********************************************************
 *      file: HangmanPanel.java
 *      author: Michael Muinos
 *      class: CS 245 - Programming Graphical User Interfaces
 * 
 *      assignment: Quarter Project, Checkpoint # 1
 *      date last modified: 8/8/16
 * 
 *      purpose: The purpose of this class is to create the main
 *      panel for the hangman game that will be added to the 
 *      HangmanFrame.java class object. The panel uses an absolute layout
 *      to set all the buttons, labels, and images within the panel.
 *********************************************************/
package cs245project;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class HangmanPanel extends javax.swing.JPanel {    
    private final String[] wordList = {"abstract", "cemetery", "nurse", "pharmacy", "climbing"};
    
    private JLabel[] wordLabels;
    private boolean[] visibilityLabels;
    
    private String chosenWord; 
    private int numberOfGuesses;
    private int totalScore;

    // constructor: HangmanPanel
    // purpose: The purpose of this constructor is to create a JPanel object to be
    // passed into the HangmanFrame object. It will initialize the components within
    // the Jpanel layout, set the correct date, initialize the user's score, and
    // choose a random word.
    public HangmanPanel() {
        initComponents();
        dateLabel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        //This next line exists to populate the date upon launch of game. If not here,
        //date populates as "jLabel2" for one runtime second then does date properly.
        dateLabel.setText(new SimpleDateFormat("MMMM d, yyyy HH:mm:ss").format(new Date()));
        setDate();
        
        chosenWord = getRandomWord();
        numberOfGuesses = 0;
        totalScore = 100;
        
        scoreLabel.setText("Total Score: " + String.valueOf(totalScore));
        
        wordLabels = new JLabel[chosenWord.length()];
        visibilityLabels = new boolean[chosenWord.length()];
    }
    
    // method: getRandomWord
    // purpose: The purpose of this method is to choose a random word
    // from the given list of words we were given.
    private String getRandomWord() {
        return wordList[new Random().nextInt(wordList.length)];
    }
    
    // method: paintComponent
    // purpose: The purpose of this method is to override the Jpanels paint method.
    // It will create the lines for the hangman game programmatically, as well as creating
    // the corresponding JLabels to be placed above the lines.
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        
        // Temporary solution, ill come up with some function to 
        // calculate the space correctly for all word lengths
        int firstXCoordinate = 0;
        int secondXCoordinate = 0;
        if(chosenWord.length() <= 5) {
            firstXCoordinate = 125;
          
            secondXCoordinate = 175;
        } else {
            firstXCoordinate = 10;
            secondXCoordinate = 60;
        }

        for(int i = 0; i < chosenWord.length(); i++) {
            g2.drawLine(firstXCoordinate, 285, secondXCoordinate, 285);
            
            JLabel label = new JLabel(Character.toString(chosenWord.charAt(i)));
            label.setBounds((firstXCoordinate + secondXCoordinate) / 2, 260, 20, 20);
            label.setVisible(false);
            this.add(label);
            
            wordLabels[i] = label;
            firstXCoordinate += 75;
            secondXCoordinate += 75;
        }        
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
    
    // method: processButtonClick
    // purpose: The following method will be called everytime a letter button has been
    // clicked. First, it will determine if the letter the user clicked is a
    // goodLetter (i.e. goodLetter = letter was part of word). Next, it will determine
    // if the user has won, if they have, it will create a new result frame and dispose
    // of the current one. If the user has not won, it will check if the letter was not
    // a goodLetter, and it will increase the number of guesses, decrease the score, and change
    // the image.
    private void processButtonClick(String letter) {
        boolean goodLetter = false;
        for(int i = 0; i < chosenWord.length(); i++) {
            if(Character.toString(chosenWord.charAt(i)).equals(letter)) {
                wordLabels[i].setVisible(true);
                visibilityLabels[i] = true;
                goodLetter = true;
            }
        }
        
        // Check if they won or not
        if(isWinner()) {
            ColorGameFrame colorGame = new ColorGameFrame("Color Game", totalScore);
            colorGame.start();
            disposeCurrentFrame();
        }
        
        if(!goodLetter) {
            ++numberOfGuesses;
            totalScore -= 10;

            scoreLabel.setText("Total Score: " + String.valueOf(totalScore));
            
            String path = "resources/";
            if(numberOfGuesses == 6) {
                path += "finalPic.gif";
                disableAllButtons();
                startTimer();
            } else {
                path += "image" + String.valueOf(numberOfGuesses) + ".png"; 
            }

            ImageIcon imageIcon = new ImageIcon(getClass().getClassLoader().getResource(path));
            guillotinePicture.setIcon(imageIcon);
        }
    }
    
    // method: isWinner
    // purpose: The purpose of this method is to determine if the user has won the game or not.
    // It determines if the user has won by running through the visibilityLabels array, if
    // all are true, then we know the user has guessed all the letters and has won.
    private boolean isWinner() {
        for (Boolean visibilityLabel : visibilityLabels) {
            if (!visibilityLabel) {
                return false;
            }
        }
        return true;
    }
    
    // method: startTimer
    // purpose: This method will create a new timer object, start a color game frame,
    // and dispose of the current frame after 3 seconds. 
    // This method is called when the user has lost (i.e. guessed 6 times).
    private void startTimer() {
        // Initialize timer
        // 3 seconds play gif
        Timer timer = new Timer(3000, (ActionEvent e) -> {
            ColorGameFrame colorGame = new ColorGameFrame("Color Game", totalScore);
            colorGame.start();
            disposeCurrentFrame();
        });
        timer.setRepeats(false);
        timer.start();
    }
    
    // method: disableAllButtons
    // purpose: This method will make all buttons on the screen
    // impossible to click.
    private void disableAllButtons() {
        aButton.setEnabled(false);
        bButton.setEnabled(false);
        cButton.setEnabled(false);
        dButton.setEnabled(false);
        eButton.setEnabled(false);
        fButton.setEnabled(false);
        gButton.setEnabled(false);
        hButton.setEnabled(false);
        iButton.setEnabled(false);
        jButton.setEnabled(false);
        kButton.setEnabled(false);
        lButton.setEnabled(false);
        mButton.setEnabled(false);
        nButton.setEnabled(false);
        oButton.setEnabled(false);
        pButton.setEnabled(false);
        qButton.setEnabled(false);
        rButton.setEnabled(false);
        sButton.setEnabled(false);
        tButton.setEnabled(false);
        uButton.setEnabled(false);
        vButton.setEnabled(false);
        wButton.setEnabled(false);
        xButton.setEnabled(false);
        yButton.setEnabled(false);
        zButton.setEnabled(false);
        skipButton.setEnabled(false);
    }
    
    // method: disposeCurrentFrame
    // purpose: This method will reference the panels parent (i.e. HangmanFrame.java)
    // and dispose of the window.
    private void disposeCurrentFrame() {
        // Close JFrame that is containing this JPanel object
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        frame.dispose();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton15 = new javax.swing.JButton();
        skipButton = new javax.swing.JButton();
        aButton = new javax.swing.JButton();
        bButton = new javax.swing.JButton();
        cButton = new javax.swing.JButton();
        dButton = new javax.swing.JButton();
        eButton = new javax.swing.JButton();
        gButton = new javax.swing.JButton();
        fButton = new javax.swing.JButton();
        hButton = new javax.swing.JButton();
        jButton = new javax.swing.JButton();
        iButton = new javax.swing.JButton();
        kButton = new javax.swing.JButton();
        lButton = new javax.swing.JButton();
        mButton = new javax.swing.JButton();
        nButton = new javax.swing.JButton();
        pButton = new javax.swing.JButton();
        oButton = new javax.swing.JButton();
        qButton = new javax.swing.JButton();
        rButton = new javax.swing.JButton();
        sButton = new javax.swing.JButton();
        tButton = new javax.swing.JButton();
        uButton = new javax.swing.JButton();
        vButton = new javax.swing.JButton();
        wButton = new javax.swing.JButton();
        xButton = new javax.swing.JButton();
        yButton = new javax.swing.JButton();
        zButton = new javax.swing.JButton();
        hangmanLabel = new javax.swing.JLabel();
        dateLabel = new javax.swing.JLabel();
        guillotinePicture = new javax.swing.JLabel();
        scoreLabel = new javax.swing.JLabel();

        jButton15.setText("jButton3");
        jButton15.setPreferredSize(new java.awt.Dimension(40, 40));

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(600, 400));
        setLayout(null);

        skipButton.setText("Skip");
        skipButton.setToolTipText("Skip to the color game");
        skipButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                skipButtonActionPerformed(evt);
            }
        });
        add(skipButton);
        skipButton.setBounds(460, 70, 100, 20);

        aButton.setText("a");
        aButton.setBorder(null);
        aButton.setPreferredSize(new java.awt.Dimension(40, 40));
        aButton.setToolTipText("Click this button to submit A as an answer.");
        aButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aButtonActionPerformed(evt);
            }
        });
        add(aButton);
        aButton.setBounds(10, 290, 30, 30);

        bButton.setText("b");
        bButton.setBorder(null);
        bButton.setPreferredSize(new java.awt.Dimension(40, 40));
        bButton.setToolTipText("Click this button to submit B as an answer.");
        bButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bButtonActionPerformed(evt);
            }
        });
        add(bButton);
        bButton.setBounds(50, 290, 30, 30);

        cButton.setText("c");
        cButton.setBorder(null);
        cButton.setPreferredSize(new java.awt.Dimension(40, 40));
        cButton.setToolTipText("Click this button to submit C as an answer.");
        cButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cButtonActionPerformed(evt);
            }
        });
        add(cButton);
        cButton.setBounds(100, 290, 30, 30);

        dButton.setText("d");
        dButton.setBorder(null);
        dButton.setPreferredSize(new java.awt.Dimension(40, 40));
        dButton.setToolTipText("Click this button to submit D as an answer.");
        dButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dButtonActionPerformed(evt);
            }
        });
        add(dButton);
        dButton.setBounds(140, 290, 30, 30);

        eButton.setText("e");
        eButton.setBorder(null);
        eButton.setPreferredSize(new java.awt.Dimension(40, 40));
        eButton.setToolTipText("Click this button to submit E as an answer.");
        eButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eButtonActionPerformed(evt);
            }
        });
        add(eButton);
        eButton.setBounds(190, 290, 30, 30);

        gButton.setText("g");
        gButton.setBorder(null);
        gButton.setPreferredSize(new java.awt.Dimension(40, 40));
        gButton.setToolTipText("Click this button to submit G as an answer.");
        gButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gButtonActionPerformed(evt);
            }
        });
        add(gButton);
        gButton.setBounds(290, 290, 30, 30);

        fButton.setText("f");
        fButton.setBorder(null);
        fButton.setPreferredSize(new java.awt.Dimension(40, 40));
        fButton.setToolTipText("Click this button to submit F as an answer.");
        fButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fButtonActionPerformed(evt);
            }
        });
        add(fButton);
        fButton.setBounds(240, 290, 30, 30);

        hButton.setText("h");
        hButton.setBorder(null);
        hButton.setPreferredSize(new java.awt.Dimension(40, 40));
        hButton.setToolTipText("Click this button to submit H as an answer.");
        hButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hButtonActionPerformed(evt);
            }
        });
        add(hButton);
        hButton.setBounds(340, 290, 30, 30);

        jButton.setText("j");
        jButton.setBorder(null);
        jButton.setPreferredSize(new java.awt.Dimension(40, 40));
        jButton.setToolTipText("Click this button to submit J as an answer.");
        jButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonActionPerformed(evt);
            }
        });
        add(jButton);
        jButton.setBounds(430, 290, 30, 30);

        iButton.setText("i");
        iButton.setBorder(null);
        iButton.setPreferredSize(new java.awt.Dimension(40, 40));
        iButton.setToolTipText("Click this button to submit I as an answer.");
        iButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                iButtonActionPerformed(evt);
            }
        });
        add(iButton);
        iButton.setBounds(380, 290, 30, 30);

        kButton.setText("k");
        kButton.setBorder(null);
        kButton.setPreferredSize(new java.awt.Dimension(40, 40));
        kButton.setToolTipText("Click this button to submit K as an answer.");
        kButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kButtonActionPerformed(evt);
            }
        });
        add(kButton);
        kButton.setBounds(470, 290, 30, 30);

        lButton.setText("l");
        lButton.setBorder(null);
        lButton.setPreferredSize(new java.awt.Dimension(40, 40));
        lButton.setToolTipText("Click this button to submit D as an answer.");
        lButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lButtonActionPerformed(evt);
            }
        });
        add(lButton);
        lButton.setBounds(520, 290, 30, 30);

        mButton.setText("m");
        mButton.setBorder(null);
        mButton.setPreferredSize(new java.awt.Dimension(40, 40));
        mButton.setToolTipText("Click this button to submit M as an answer.");
        mButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mButtonActionPerformed(evt);
            }
        });
        add(mButton);
        mButton.setBounds(560, 290, 30, 30);

        nButton.setText("n");
        nButton.setBorder(null);
        nButton.setPreferredSize(new java.awt.Dimension(40, 40));
        nButton.setToolTipText("Click this button to submit N as an answer.");
        nButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nButtonActionPerformed(evt);
            }
        });
        add(nButton);
        nButton.setBounds(10, 330, 30, 30);

        pButton.setText("p");
        pButton.setBorder(null);
        pButton.setPreferredSize(new java.awt.Dimension(40, 40));
        pButton.setToolTipText("Click this button to submit P as an answer.");
        pButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pButtonActionPerformed(evt);
            }
        });
        add(pButton);
        pButton.setBounds(100, 330, 30, 30);

        oButton.setText("o");
        oButton.setBorder(null);
        oButton.setPreferredSize(new java.awt.Dimension(40, 40));
        oButton.setToolTipText("Click this button to submit O as an answer.");
        oButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                oButtonActionPerformed(evt);
            }
        });
        add(oButton);
        oButton.setBounds(50, 330, 30, 30);

        qButton.setText("q");
        qButton.setBorder(null);
        qButton.setPreferredSize(new java.awt.Dimension(40, 40));
        qButton.setToolTipText("Click this button to submit Q as an answer.");
        qButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                qButtonActionPerformed(evt);
            }
        });
        add(qButton);
        qButton.setBounds(140, 330, 30, 30);

        rButton.setText("r");
        rButton.setBorder(null);
        rButton.setPreferredSize(new java.awt.Dimension(40, 40));
        rButton.setToolTipText("Click this button to submit R as an answer.");
        rButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rButtonActionPerformed(evt);
            }
        });
        add(rButton);
        rButton.setBounds(190, 330, 30, 30);

        sButton.setText("s");
        sButton.setBorder(null);
        sButton.setPreferredSize(new java.awt.Dimension(40, 40));
        sButton.setToolTipText("Click this button to submit S as an answer.");
        sButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sButtonActionPerformed(evt);
            }
        });
        add(sButton);
        sButton.setBounds(240, 330, 30, 30);

        tButton.setText("t");
        tButton.setBorder(null);
        tButton.setPreferredSize(new java.awt.Dimension(40, 40));
        tButton.setToolTipText("Click this button to submit T as an answer.");
        tButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tButtonActionPerformed(evt);
            }
        });
        add(tButton);
        tButton.setBounds(290, 330, 30, 30);

        uButton.setText("u");
        uButton.setBorder(null);
        uButton.setPreferredSize(new java.awt.Dimension(40, 40));
        uButton.setToolTipText("Click this button to submit U as an answer.");
        uButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uButtonActionPerformed(evt);
            }
        });
        add(uButton);
        uButton.setBounds(340, 330, 30, 30);

        vButton.setText("v");
        vButton.setBorder(null);
        vButton.setPreferredSize(new java.awt.Dimension(40, 40));
        vButton.setToolTipText("Click this button to submit V as an answer.");
        vButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vButtonActionPerformed(evt);
            }
        });
        add(vButton);
        vButton.setBounds(380, 330, 30, 30);

        wButton.setText("w");
        wButton.setBorder(null);
        wButton.setPreferredSize(new java.awt.Dimension(40, 40));
        wButton.setToolTipText("Click this button to submit W as an answer.");
        wButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                wButtonActionPerformed(evt);
            }
        });
        add(wButton);
        wButton.setBounds(430, 330, 30, 30);

        xButton.setText("x");
        xButton.setBorder(null);
        xButton.setPreferredSize(new java.awt.Dimension(40, 40));
        xButton.setToolTipText("Click this button to submit X as an answer.");
        xButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                xButtonActionPerformed(evt);
            }
        });
        add(xButton);
        xButton.setBounds(470, 330, 30, 30);

        yButton.setText("y");
        yButton.setBorder(null);
        yButton.setPreferredSize(new java.awt.Dimension(40, 40));
        yButton.setToolTipText("Click this button to submit Y as an answer.");
        yButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                yButtonActionPerformed(evt);
            }
        });
        add(yButton);
        yButton.setBounds(520, 330, 30, 30);

        zButton.setText("z");
        zButton.setBorder(null);
        zButton.setPreferredSize(new java.awt.Dimension(40, 40));
        zButton.setToolTipText("Click this button to submit Z as an answer.");
        zButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                zButtonActionPerformed(evt);
            }
        });
        add(zButton);
        zButton.setBounds(560, 330, 30, 30);

        hangmanLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/HangmanLogo1.png"))); // NOI18N
        add(hangmanLabel);
        hangmanLabel.setBounds(6, 6, 150, 62);

        dateLabel.setFont(new java.awt.Font("Lucida Grande", 0, 12)); // NOI18N
        dateLabel.setText("jLabel2");
        add(dateLabel);
        dateLabel.setBounds(440, 6, 160, 20);

        guillotinePicture.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/imageorigin.png"))); // NOI18N
        guillotinePicture.setText("jLabel3");
        guillotinePicture.setPreferredSize(new java.awt.Dimension(130, 236));
        add(guillotinePicture);
        guillotinePicture.setBounds(230, 10, 130, 236);

        scoreLabel.setText("jLabel3");
        add(scoreLabel);
        scoreLabel.setBounds(470, 40, 150, 14);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * The ActionPerformed methods below have been auto-generated by NetBeans.
     * They will pass in the corresponding letter to be processed by the
     * processButtonClick method. After, the button will be disabled.
     */
    private void aButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aButtonActionPerformed
        processButtonClick("a");
        aButton.setEnabled(false);
    }//GEN-LAST:event_aButtonActionPerformed

    private void bButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bButtonActionPerformed
        processButtonClick("b");
        bButton.setEnabled(false);
    }//GEN-LAST:event_bButtonActionPerformed

    private void cButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cButtonActionPerformed
        processButtonClick("c");
        cButton.setEnabled(false);
    }//GEN-LAST:event_cButtonActionPerformed

    private void dButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dButtonActionPerformed
        processButtonClick("d");
        dButton.setEnabled(false);
    }//GEN-LAST:event_dButtonActionPerformed

    private void eButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eButtonActionPerformed
        processButtonClick("e");
        eButton.setEnabled(false);
    }//GEN-LAST:event_eButtonActionPerformed

    private void fButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fButtonActionPerformed
        processButtonClick("f");
        fButton.setEnabled(false);
    }//GEN-LAST:event_fButtonActionPerformed

    private void gButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gButtonActionPerformed
        processButtonClick("g");
        gButton.setEnabled(false);
    }//GEN-LAST:event_gButtonActionPerformed

    private void hButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hButtonActionPerformed
        processButtonClick("h");
        hButton.setEnabled(false);
    }//GEN-LAST:event_hButtonActionPerformed

    private void iButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_iButtonActionPerformed
        processButtonClick("i");
        iButton.setEnabled(false);
    }//GEN-LAST:event_iButtonActionPerformed

    private void jButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonActionPerformed
        processButtonClick("j");
        jButton.setEnabled(false);
    }//GEN-LAST:event_jButtonActionPerformed

    private void kButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kButtonActionPerformed
        processButtonClick("k");
        kButton.setEnabled(false);
    }//GEN-LAST:event_kButtonActionPerformed

    private void lButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lButtonActionPerformed
        processButtonClick("l");
        lButton.setEnabled(false);
    }//GEN-LAST:event_lButtonActionPerformed

    private void mButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mButtonActionPerformed
        processButtonClick("m");
        mButton.setEnabled(false);
    }//GEN-LAST:event_mButtonActionPerformed

    private void nButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nButtonActionPerformed
        processButtonClick("n");
        nButton.setEnabled(false);
    }//GEN-LAST:event_nButtonActionPerformed

    private void oButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_oButtonActionPerformed
        processButtonClick("o");
        oButton.setEnabled(false);
    }//GEN-LAST:event_oButtonActionPerformed

    private void pButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pButtonActionPerformed
        processButtonClick("p");
        pButton.setEnabled(false);
    }//GEN-LAST:event_pButtonActionPerformed

    private void qButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_qButtonActionPerformed
        processButtonClick("q");
        qButton.setEnabled(false);
    }//GEN-LAST:event_qButtonActionPerformed

    private void rButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rButtonActionPerformed
        processButtonClick("r");
        rButton.setEnabled(false);
    }//GEN-LAST:event_rButtonActionPerformed

    private void sButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sButtonActionPerformed
        processButtonClick("s");
        sButton.setEnabled(false);
    }//GEN-LAST:event_sButtonActionPerformed

    private void tButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tButtonActionPerformed
        processButtonClick("t");
        tButton.setEnabled(false);
    }//GEN-LAST:event_tButtonActionPerformed

    private void uButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uButtonActionPerformed
        processButtonClick("u");
        uButton.setEnabled(false);
    }//GEN-LAST:event_uButtonActionPerformed

    private void vButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vButtonActionPerformed
        processButtonClick("v");
        vButton.setEnabled(false);
    }//GEN-LAST:event_vButtonActionPerformed

    private void wButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_wButtonActionPerformed
        processButtonClick("w");
        wButton.setEnabled(false);
    }//GEN-LAST:event_wButtonActionPerformed

    private void xButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_xButtonActionPerformed
        processButtonClick("x");
        xButton.setEnabled(false);
    }//GEN-LAST:event_xButtonActionPerformed

    private void yButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_yButtonActionPerformed
        processButtonClick("y");
        yButton.setEnabled(false);
    }//GEN-LAST:event_yButtonActionPerformed

    private void zButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_zButtonActionPerformed
        processButtonClick("z");
        zButton.setEnabled(false);
    }//GEN-LAST:event_zButtonActionPerformed

    // method: skipButtonActionPerformed
    // purpose: On click, this method will start the color game frame and dispose of the
    // current frame.
    private void skipButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_skipButtonActionPerformed
        ColorGameFrame colorGame = new ColorGameFrame("Color Game", 0);
        colorGame.start();
        disposeCurrentFrame();
    }//GEN-LAST:event_skipButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton aButton;
    private javax.swing.JButton bButton;
    private javax.swing.JButton cButton;
    private javax.swing.JButton dButton;
    private javax.swing.JLabel dateLabel;
    private javax.swing.JButton eButton;
    private javax.swing.JButton fButton;
    private javax.swing.JButton gButton;
    private javax.swing.JLabel guillotinePicture;
    private javax.swing.JButton hButton;
    private javax.swing.JLabel hangmanLabel;
    private javax.swing.JButton iButton;
    private javax.swing.JButton jButton;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton kButton;
    private javax.swing.JButton lButton;
    private javax.swing.JButton mButton;
    private javax.swing.JButton nButton;
    private javax.swing.JButton oButton;
    private javax.swing.JButton pButton;
    private javax.swing.JButton qButton;
    private javax.swing.JButton rButton;
    private javax.swing.JButton sButton;
    private javax.swing.JLabel scoreLabel;
    private javax.swing.JButton skipButton;
    private javax.swing.JButton tButton;
    private javax.swing.JButton uButton;
    private javax.swing.JButton vButton;
    private javax.swing.JButton wButton;
    private javax.swing.JButton xButton;
    private javax.swing.JButton yButton;
    private javax.swing.JButton zButton;
    // End of variables declaration//GEN-END:variables
}
