package pieces;

import utils.*;
import java.util.ArrayList;
import java.util.List;

/**
 * resents a chess king
 */

public class King extends Piece {
    public King(Color color, Position position) {
        super(color, position);
    }

    @Override
    public String getSymbol() {
        // returns  wK for white  or bK for black king
        return (this.color == Color.WHITE) ? "wK" : "bK";
    }

    @Override
    public List<Position> possibleMoves() {
        // not implemented yet
        return new ArrayList<>();
    }

    /**
     * Validates the king's moves
     * @param board The current state of the board
     * @param end The target position
     * @return true if the move is valid false otherwise
     */
    @Override
    public boolean isValidMove(board.Board board, Position end) {
        int rowDiff = Math.abs(this.position.getRow() - end.getRow());
        int colDiff = Math.abs(this.position.getCol() - end.getCol());

    //The king moves one square in any direction
    if(rowDiff > 1 || colDiff > 1){
        return false;
    }

    //The king cannot move to the square it is already on
    if(rowDiff == 0 && colDiff == 0){
        return false;
    }

    // Make sure the king does not land on our own piece
    pieces.Piece targetPiece = board.getPiece(end);
    if(targetPiece != null && targetPiece.getColor() == this.color){
        return false;
    }
    
    return true;
    }
}
