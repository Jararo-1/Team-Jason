package board;

import pieces.*;
/**
 * Represents the 8x8 chess board and manages the state of the board
 */

public class Board {
    // A 2D array representing the board
    // it store pieces or if it is empty
    private Piece[][] grid;

    /**
     * Initializes the board
     */
    public Board() {
        grid = new Piece[8][8];
        initilizeBoard();
    }

    /**
     * Places all pieces in their starting position
     */
    private void initilizeBoard(){
        // Initialize the board
    }

    /**
     * prints the current state of the board
     */
    public void printBoard(){
        System.out.println("  A  B  C  D  E  F  G  H");

        // Loop from row 7 to 0 
        for (int row = 7; row >= 0; row--) {
            System.out.print(row + 1 + " ");
            for (int col = 0; col < 8; col++) {
                if(grid[row][col] == null){
                    System.out.print("## "); // Empty square
                } else {
                    System.out.print("P "); // placeholder for now
                }
            }
            System.out.println(" " + (row + 1)); //prints the rank number
        }
            System.out.println("  A  B  C  D  E  F  G  H");
        }
    }

