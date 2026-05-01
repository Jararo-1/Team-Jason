package pieces;

import utils.*;
import java.util.ArrayList;
import java.util.List;

/**
 * resents a chess rook
 */
public class Rook extends Piece {
    public Rook(Color color, Position position) {
        super(color, position);
    }
    @Override
    public String getSymbol() {
        // returns  wR for white  or bR for black rook
        return (this.color == Color.WHITE) ? "wR" : "bR";
    }

    @Override
    public List<Position> possibleMoves() {
        // not implemented yet
        return new ArrayList<>();
    }

    /**
     * Validates the rook's moves
     * @param board The current state of the board
     * @param end The target position
     * @return true if the move is valid and ublocked false otherwise
     */
    @Override
    public boolean isValidMove(board.Board board, Position end) {
        int startRow = this.position.getRow();
        int startCol = this.position.getCol();
        int endRow = end.getRow();
        int endCol = end.getCol();

        // rook can only move horizontally or vertically
        if(startRow != endRow && startCol != endCol){
            return false;
        }

        // figure out which direction we are stepping
        int rowStep = Integer.compare(endRow, startRow);
        int colStep = Integer.compare(endCol, startCol);

        //check no pieces along the path
        int currentRow = startRow + rowStep;
        int currentCol = startCol + colStep;
        while(currentRow != endRow || currentCol != endCol) {
            if(board.getPiece(new utils.Position(currentRow, currentCol)) != null){
                return false;
            }
            currentRow += rowStep;
            currentCol += colStep;
        }
        // Make sure not landing on our own piece
        pieces.Piece targetPiece = board.getPiece(end);
        if(targetPiece != null && targetPiece.getColor() == this.color){
            return false;
        }
        return true;
    }
}
