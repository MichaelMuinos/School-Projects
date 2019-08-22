/*********************************************************
 *      file: IncorrectAnswerPair.java
 *      author: Michael Muinos, Jose Gutierrez, Erick Rivera
 *      class: CS 245 - Programming Graphical User Interfaces
 * 
 *      assignment: Quarter Project, Checkpoint # 2
 *      date last modified: 8/22/16
 * 
 *      purpose: This class represents an object for an incorrect answer
 *      in the sudoku game in which it keeps track of the row and column
 *      index.
 *********************************************************/
package cs245project;

/**
 *
 * @author michael
 */
public class IncorrectAnswerPair {
    
    private int rowIndex;
    private int colIndex;
    
    // constructor
    // sets the row and col index
    public IncorrectAnswerPair(int row, int col) {
        this.rowIndex = row;
        this.colIndex = col;
    }
    
    // method: getRowIndex
    // purpose: get the row index
    public int getRowIndex() {
        return rowIndex;
    }
    
    // method: getColIndex
    // purpose: get the column index
    public int getColIndex() {
        return colIndex;
    }
    
}
