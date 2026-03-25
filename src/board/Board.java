package board;

import pieces.Piece;
import pieces.Pawn;
import utils.Color;
import utils.Position;
;
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
        // place white pawns on rank 2
        for (int col = 0; col < 8; col++) {
            grid[1][col] = new Pawn(Color.WHITE, new Position(1, col));
        }

        // place black pawns on rank 7
        for (int col = 0; col < 8; col++) {
            grid[6][col] = new Pawn(Color.BLACK, new Position(6, col));
        }
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
                    //asks the piece for its symbol
                    System.out.print(grid[row][col].getSymbol() + " ");
                }
            }
            System.out.println(" " + (row + 1)); //prints the rank number
        }
            System.out.println("  A  B  C  D  E  F  G  H");
        }
    }

