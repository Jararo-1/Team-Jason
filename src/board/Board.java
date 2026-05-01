package board;

import pieces.Piece;
import pieces.Pawn;
import pieces.Rook;
import pieces.Knight;
import pieces.Bishop;
import pieces.Queen;
import pieces.King;
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

        //place white queen on rank 1 and black queen on rank 8
        grid[0][3] = new Queen(Color.WHITE, new Position(0, 3));
        grid[7][3] = new Queen(Color.BLACK, new Position(7, 3));

        //place white king on rank 1 and black king on rank 8
        grid[0][4] = new King(Color.WHITE, new Position(0, 4));
        grid[7][4] = new King(Color.BLACK, new Position(7, 4));
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

        /**
         * Returns the piece at the specified position
         * @param position the position The coordiniates to check
         * @return the piece at the specified position or null if there is no piece there
         */
        public Piece getPiece(Position position){
            return grid[position.getRow()][position.getCol()];
        }

        /**
         * Physically moves a piece from one position to another
         * @param from the starting position
         * @param to the position to move the piece to
         */
        public void movePiece(Position from, Position to){
            // grab the piece from the starting position
            Piece pieceToMove = grid[from.getRow()][from.getCol()];
            // place the piece at the new position
            grid[to.getRow()][to.getCol()] = pieceToMove;
            // remove the piece from the old position
            grid[from.getRow()][from.getCol()] = null;

            // update the piece's position
            if(pieceToMove != null){
                pieceToMove.updatePosition(to);
            }
        }

        /**
         * Find the king's current position on the board
         * @param color The color of the king
         * @return The position of the king, or null if the king is not found
         */
        public utils.Position getKingPosition(utils.Color color){
            for(int row = 0; row < 8; row++){
                for(int col = 0; col < 8; col ++){
                    utils.Position pos = new utils.Position(row, col);
                    pieces.Piece p = getPiece(pos);

                    //check if the piece is a king of the correct color
                    if(p != null && p.getColor() == color && p instanceof pieces.King){
                        return pos;
                    }
                }
            }
            return null;
        }

        /**
         * Checks if the king is under attack
         * @param color The color of the defending king
         * @return True if the king is under check, false otherwise
         */
        public boolean isInCheck(utils.Color color){
            utils.Position kingPos = getKingPosition(color);
            if (kingPos == null){
                return false;
            }

            // loop thorugh every square on the board to find enemy pieces
            for(int row = 0; row < 8; row++){
                for(int col = 0; col < 8; col++){
                    utils.Position pos = new utils.Position(row, col);
                    pieces.Piece enemyPiece = getPiece(pos);

                    // if it is an enemy piece, see if isValidMove allows it to hit the king
                    if (enemyPiece !=  null && enemyPiece.getColor() != color){
                        if(enemyPiece.isValidMove(this, kingPos)){
                            return true;
                        }
                    }
                }
            }
            return false;
        }

    }

