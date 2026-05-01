package pieces;

import utils.*;
import java.util.ArrayList;
import java.util.List;
/**
 * resents a chess knight
 */
public class Knight extends Piece {
    public Knight(Color color, Position position) {
        super(color, position);
    }

    @Override
    public String getSymbol() {
        // returns  wN for white  or bN for black knight
        return (this.color == Color.WHITE) ? "wN" : "bN";
    }

    @Override
    public List<Position> possibleMoves() {
        // not implemented yet
        return new ArrayList<>();
    }

    /**
     * Validates the knight's moves
     * @param board The current state of the board
     * @param end The target position
     * @return true if the move is valid false otherwise
     */
    @Override
    public boolean isVAlidMove(board.Board board, Position end) {
        int rowDiff = Math.abs(this.position.getRow() - end.getRow());
        int colDiff = Math.abs(this.position.getCol() - end.getCol());

        //L-shape change 2 rows and 1 col OR 2 cols and 1 row
        boolean isLShape = (rowDiff == 2 && colDiff == 1) || (rowDiff == 1 && colDiff == 2);

        if(!isLShape) return false;

        // Make sure we can don't land on our own piece
        pieces.Piece targetPiece = board.getPiece(end);
        if(targetPiece != null && targetPiece.getColor() == this.color){
            return false;
        }
        return true;
    }
}
