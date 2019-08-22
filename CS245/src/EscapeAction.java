/*************************************************************
 *      file: EscapeAction.java
 *      author: Erick Rivera
 *      class: CS 245 - Programming Graphical User Interfaces
 *  
 *      assignment: Quarter Project, Checkpoint # 3
 *      date last modified: 8/22/16
 * 
 *      purpose: The purpose of this class is to create the escape action for all frames.
 ************************************************************/

package cs245project;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class EscapeAction extends AbstractAction {
    
    private static final String KEY_STROKE_AND_KEY = "ESCAPE";
    private static final KeyStroke ESCAPE_KEY_STROKE = KeyStroke.getKeyStroke(KEY_STROKE_AND_KEY);

    public EscapeAction() {
        super("Escape");
    }
    //method:actionPerformed
    //purpose: program closes when escape is hit
    @Override
    public void actionPerformed(ActionEvent e) {
        Component component = KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusOwner();
        JComponent rootPane = (JComponent) component;

        if (!(rootPane instanceof JRootPane)) {
            rootPane = (JComponent)SwingUtilities.getAncestorOfClass(JRootPane.class, component);
        }

        ActionListener escapeAction = getEscapeAction(rootPane);

        if (escapeAction != null) {
            escapeAction.actionPerformed(null);
        } else {
            System.exit(0);
        }
    }
    //method:getEscapeAction
    //purpose: checks if key entered was Esc
    private ActionListener getEscapeAction(JComponent rootPane) {
        InputMap im = rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        if (im == null) return null;
        im = im.getParent();
        if (im == null) return null;
        Object[] keys = im.keys();
        if (keys == null) return null;
        for (Object keyStroke: keys) {
            if (keyStroke.equals(ESCAPE_KEY_STROKE)) {
                Object key = im.get(ESCAPE_KEY_STROKE);
                return rootPane.getActionMap().get(key);
            }
        }
        return null;
    }

    //method: register
    //purpose:get the key that was entered
    public void register(JRootPane rootPane) {
        rootPane.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(ESCAPE_KEY_STROKE, KEY_STROKE_AND_KEY);
        rootPane.getActionMap().put(KEY_STROKE_AND_KEY, this);
    }
}
