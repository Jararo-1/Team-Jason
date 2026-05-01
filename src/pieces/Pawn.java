package pieces;

import utils.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Resents a chess pawn
 */
public class Pawn extends Piece{
    public Pawn(Color color, Position position) {
        super(color, position);
    }

    @Override
    public String getSymbol() {
        // returns  wp for white  or bd for black pawn
        return (this.color == Color.WHITE) ? "wp" : "bp";
    }

    @Override
    public List<Position> possibleMoves() {
        // not implemented yet
        return new ArrayList<>();
    }

    /**
     * Validates the pawn's moves
     * @param board The currrent state of the board
     * @param end the Target position
     * @return true if the move is valid
     */
    @Override
    public boolean isValidMove(board.Board board, Position end) {
        int startRow = this.position.getRow();
        int startCol = this.position.getCol();
        int endRow = end.getRow();
        int endCol = end.getCol();

        // white pawns start at row 1 and move down the array
        int direction = (this.color == Color.WHITE) ? 1 : -1;
        int startingRow = (this.color == Color.WHITE) ? 1 : 6;

        pieces.Piece targetPiece = board.getPiece(end);

        // Standard forward move of 1 square if empty
        if(startCol == endCol && endRow == startRow + direction){
            return targetPiece == null;
        }

        // First move of 2 squares if both empty
        if(startCol == endCol && startRow == startingRow && endRow == startRow + (2 * direction)){
            utils.Position middleSquare = new utils.Position(startRow + direction, startCol);
            return targetPiece == null && board.getPiece(middleSquare) == null;
        }

        // Diagonal capture (must contain an enemy piece)
        if(Math.abs(startCol - endCol) == 1 && endRow == startRow + direction){
            return targetPiece != null && targetPiece.getColor() != this.color;
        }
        return false;
    }
}
