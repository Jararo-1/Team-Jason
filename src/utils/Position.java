package utils;

/**
 * Represents a specific position on the board
 */
public class Position {
    private int row;
    private int col;

/**
 * Constructs a new position
 * @param row The row index(0-7)
 * @param col The column index(0-7)
 */
    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }
    // Getters 
    public int getRow() {
        return row;
    }
    public int getCol() {
        return col;
    }
}
