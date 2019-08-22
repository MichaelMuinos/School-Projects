/*************************************************************
 *      file: F1Action.java
 *      author: Erick Rivera
 *      class: CS 245 - Programming Graphical User Interfaces
 *  
 *      assignment: Quarter Project, Checkpoint # 3
 *      date last modified: 8/22/16
 * 
 *      purpose: The purpose of this class is to create the dialog for all frames.
 ************************************************************/
package cs245project;

import java.awt.Component;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractAction;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;


public class F1Action extends AbstractAction{
    private static final String KEY_STROKE_AND_KEY = "F1";
    private static final KeyStroke F1_KEY_STROKE = KeyStroke.getKeyStroke(KEY_STROKE_AND_KEY);

    public F1Action() {
        super("F1");
    }
    //method: actionPerformed
    //purpose: performs the dialog box action if the action occured
    @Override
    public void actionPerformed(ActionEvent e) {
        Component component = KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusOwner();
        JComponent rootPane = (JComponent) component;

        if (!(rootPane instanceof JRootPane)) {
            rootPane = (JComponent)SwingUtilities.getAncestorOfClass(JRootPane.class, component);
        }

        ActionListener f1Action = getF1Action(rootPane);

        if (f1Action != null) {
            f1Action.actionPerformed(null);
        } else {
            JOptionPane.showMessageDialog(rootPane, 
                    "Erick Rivera, 009703473\n"
                    + "Jose Guitierrez, 009417096\n"
                    + "Michael Muinos, 009553206\n"
                    + "CS 245 Quarter Project\n"
                            + "Summer 2016", "CS 245 Quarter Project", JOptionPane.PLAIN_MESSAGE);
        }
    }
    //method: getF1Action
   //purpose:gets the key that was entered and checks if it was F1
    private ActionListener getF1Action(JComponent rootPane) {
        InputMap im = rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        if (im == null) return null;
        im = im.getParent();
        if (im == null) return null;
        Object[] keys = im.keys();
        if (keys == null) return null;
        for (Object keyStroke: keys) {
            if (keyStroke.equals(F1_KEY_STROKE)) {
                Object key = im.get(F1_KEY_STROKE);
                return rootPane.getActionMap().get(key);
            }
        }
        return null;
    }
    //method: register
    //purpose:calls the dialog box
    public void register(JRootPane rootPane) {
        rootPane.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(F1_KEY_STROKE, KEY_STROKE_AND_KEY);
        rootPane.getActionMap().put(KEY_STROKE_AND_KEY, this);
    }
}
