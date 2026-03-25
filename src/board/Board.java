package board;

import pieces.Piece;
import pieces.Pawn;
import pieces.Rook;
import pieces.Knight;
import pieces.Bishop;
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
        // place white pawns on rank 2 and black pawns on rank 7
        for (int col = 0; col < 8; col++) {
            grid[1][col] = new Pawn(Color.WHITE, new Position(1, col));
            grid[6][col] = new Pawn(Color.BLACK, new Position(6, col));
        }

        // place white rooks on rank 1 and black rooks on rank 8
        grid[0][0] = new Rook(Color.WHITE, new Position(0, 0));
        grid[0][7] = new Rook(Color.WHITE, new Position(0, 7));
        grid[7][0] = new Rook(Color.BLACK, new Position(7, 0));
        grid[7][7] = new Rook(Color.BLACK, new Position(7, 7));

        // place white knights on rank 1 and black knights on rank 8
        grid[0][1] = new Knight(Color.WHITE, new Position(0, 1));
        grid[0][6] = new Knight(Color.WHITE, new Position(0, 6));
        grid[7][1] = new Knight(Color.BLACK, new Position(7, 1));
        grid[7][6] = new Knight(Color.BLACK, new Position(7, 6));

        //place white bishops on rank 1 and black bishops on rank 8
        grid[0][2] = new Bishop(Color.WHITE, new Position(0, 2));
        grid[0][5] = new Bishop(Color.WHITE, new Position(0, 5));
        grid[7][2] = new Bishop(Color.BLACK, new Position(7, 2));
        grid[7][5] = new Bishop(Color.BLACK, new Position(7, 5));
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

